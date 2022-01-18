package com.zhike.blogdao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhike.blogpojo.DO.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@Component(value = "ArticleMapper")
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 新增文章信息
     */
    @Insert("INSERT INTO `article` (`ArticleTypeId`, `ArticleTypeName`, `UserId`, `TagIds`, `Tags`, `Title`, `LinkUrl`, `Cover`, `Navigation`, `Summary`, `Content`, `IsUp`, `IsRecommend`, `IsHot`, `OpenState`, `ScanNum`, `LikeNum`, `CommentNum`, `ForwardNum`, `IsSoftDelete`, `Description`, `CreateTime`, `ModifyTime`) \n" +
            "VALUES('9','.NET','1','2',#{Tags},#{Title},'https://mp.weixin.qq.com/s/kTFDKVSYpfW7Tem2aswFNw','','',NULL,'','0','0','0','0','0','0','0','0','0','',now(),now());")
    int insertArticle(@Param("Tags") String tags, @Param("Title") String title);

    @Update("UPDATE `article` SET `Tags`=#{Tags},`Title`=#{Title}\n" +
            "WHERE id=#{Id}")
    int updateByid(@Param("Id") int id , @Param("Tags") String tags,@Param("Title") String title);
}