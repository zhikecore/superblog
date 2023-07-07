package com.zhike.job;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhike.blogbase.constant.RedisKeyConstant;
import com.zhike.blogbase.utils.JsonUtil;
import com.zhike.blogbase.utils.RedisUtils;
import com.zhike.blogdao.mapper.ArticleMapper;
import com.zhike.blogpojo.DO.Article;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class SyncArticleDataFromRedis2MysqlJob {


    @Autowired
    private ArticleMapper articleMapper;

    @Resource
    private RedisUtils redisUtils;

    @Scheduled(cron = "0/10 * * * * ?")//10s 执行一次
//    @Scheduled(cron = "0 37 19 * * ?")//每天零点执行
    public void run() throws Exception {
        log.info("开始redis文章点赞数据同步!");

        //1.更新文章总的点赞数
        Map<Object, Object> articleCountMap = redisUtils.hmget(RedisKeyConstant.ARTICLE_LIKED_USERS);

        for (Map.Entry<Object, Object> entry : articleCountMap.entrySet()) {
            Integer articleId =Integer.parseInt(entry.getKey().toString());
            String articleLikedResult = (String) entry.getValue();
            Set<Long> userIdSet =articleLikedResult==null?new HashSet<>(): JsonUtil.deserializeToSet(articleLikedResult,Long.class);

            //同步某篇文章总的点赞数到MySQL
            synchronizeTotalLikeCount(articleId, userIdSet);
        }

        log.info("完成redis文章点赞数据同步!");
    }

    /**
     * 同步某篇文章总的点赞数到MySQL
     * */
    private void synchronizeTotalLikeCount(Integer articleId, Set<Long> userIdSet) {

        try
        {
            Integer totalLikeCount = userIdSet.size();
            Article article =articleMapper.selectOne( new LambdaQueryWrapper<Article>()
                    .eq(Article::getId,articleId));

            if(article==null)
            {
                return;
            }
            article.setLikeNum(totalLikeCount);
            articleMapper.updateById(article);
        }catch (Exception ex)
        {
           log.error("synchronizeTotalLikeCount 失败! articleId:“"+articleId+"error:"+ex.getMessage());
        }
    }
}


