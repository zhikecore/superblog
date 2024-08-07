package com.zhike.blogbase.utils.QiNiuYun;

import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.zhike.blogbase.utils.QiNiuYun.model.GetQiNiuTokenDto;
import com.zhike.blogbase.utils.QiNiuYun.property.QiNiuProperties;
import com.zhike.blogbase.utils.StringUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@AllArgsConstructor
@Slf4j
public class QiNiuUtil {

    private QiNiuProperties qiniuProperties;
//    @Resource
    private final UploadManager uploadManager;

    private static final List<String> FILTER_WHITE_LIST = Arrays.asList("webp", "png", "jpeg","jpg","gif");
    /**
     * 获取七牛云token
     * @param dto:
     * @return String
     */
    public String getToken(GetQiNiuTokenDto dto) {
        String token="";
        try {
            token = createToken(dto);
        } catch (Exception e) {
            log.error("获取七牛token异常", e);
        }
        return token;
    }

    /**
     * 文件上传
     * @param file 文件
     * @param bucketType 上传空间类型
     * @param dir 七牛云存储目录
     * @return String
     */
    public String upload(MultipartFile file,Integer bucketType,String dir) {

        String fileKey ="";

        // 图片校验
        check(file);

        // 获取上传token
        GetQiNiuTokenDto getQiNiuTokenDto=new GetQiNiuTokenDto();
        if(bucketType.equals(0))
        {
            //公共空间
            getQiNiuTokenDto.setBucket(qiniuProperties.getPublicBucket());

        }else if(bucketType.equals(1))
        {
            //私有空间
            getQiNiuTokenDto.setBucket(qiniuProperties.getPrivateBucket());
        }
        String token = getToken(getQiNiuTokenDto);

        String originalFilename = file.getOriginalFilename();
        // 文件后缀
        if (StringUtils.isEmpty(originalFilename)) {
            log.error("上传文件失败originalFilename：" + originalFilename);
            return fileKey;
        }
        // 上传
        fileKey =getFileKey(dir);
        try {
            uploadManager.put(file.getInputStream(), fileKey, token, null, null);
        } catch (Exception e) {
            log.error("文件上传失败：" + originalFilename);
            return fileKey;
        }
       return fileKey;
    }

    /**
     * 获取fileKey
     * @param dir 七牛云存储目录
     * @return String
     */
    public String getFileKey(String dir) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date();
        String fileKey = dir + "/" + dateFormat.format(now) + UUID.randomUUID();
        return fileKey;
    }

    /**
     * 校验文件类型
     * @param file:
     */
    private void check(MultipartFile file) {
        String name = file.getOriginalFilename();
        if (StringUtil.isEmpty(name)) {
            log.error("上传文件名为空");
            return;
        }

        String extension = "";
        int i = name.lastIndexOf('.');
        if (i > 0) {
            extension = name.substring(i + 1);
        }
        if (FILTER_WHITE_LIST.contains(extension)) {
            return;
        }

        BufferedImage read;
        try {
            read = ImageIO.read(file.getInputStream());
        } catch (IOException e) {
            log.error("不支持的文件类型");
            return;
        }

        if (read == null) {
            log.error("不支持的文件类型");
            return;
        }
    }

    /**
     * 拼接公开空间url
     *
     * @param fileKey:
     * @return String
     */
    public String getFUllPublicUrl(String fileKey) {
        return qiniuProperties.getDomain() + "/" + fileKey;
    }

    /**
     * 拼接私有空间url
     *
     * @param fileKey:
     * @return String
     */
    public String getFUllPrivateUrl(String fileKey) {
        String domainOfBucket = qiniuProperties.getPrivateUrl();
        String encodedFileName;
        try {
            encodedFileName = URLEncoder.encode(fileKey, "utf-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        String publicUrl = String.format("%s%s", domainOfBucket, encodedFileName);

        Auth auth = Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey());
        long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
        return auth.privateDownloadUrl(publicUrl, expireInSeconds).replace(qiniuProperties.getPrivateUrl(), "");
    }

    /**
     * 批量拼接私有空间url
     *
     * @param fileKeys:
     * @return Map
     */
    public Map<String, String> convertPrivateUrls(Set<String> fileKeys) {
        Map<String, String> result = new HashMap<>();
        String domainOfBucket = qiniuProperties.getPrivateUrl();
        Auth auth = Auth.create(qiniuProperties.getAccessKey(),qiniuProperties.getSecretKey());
        for (String fileName : fileKeys) {
            String encodedFileName;
            try {
                encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

            long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
            String publicUrl = auth.privateDownloadUrl(String.format("%s%s", domainOfBucket, encodedFileName), expireInSeconds)
                    .replace(qiniuProperties.getPrivateUrl(), "");
            result.put(fileName, publicUrl);
        }
        return result;
    }

    /**
     * 创建token
     * @param dto:
     * @return Map
     */
    private String createToken(GetQiNiuTokenDto dto) {
        Auth auth = Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey());
        String token = auth.uploadToken(dto.getBucket());
        return token;
    }
}

