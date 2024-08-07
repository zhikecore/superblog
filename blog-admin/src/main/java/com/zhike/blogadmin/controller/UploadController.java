package com.zhike.blogadmin.controller;

import com.zhike.blogbase.exception.BizException;
import com.zhike.blogbase.utils.QiNiuYun.QiNiuUtil;
import com.zhike.blogbase.utils.QiNiuYun.model.GetQiNiuTokenDto;
import com.zhike.blogbase.utils.QiNiuYun.property.QiNiuProperties;
import com.zhike.blogbase.utils.StringUtil;
import com.zhike.blogpojo.DTO.input.ReqFileUploadTokenDto;
import com.zhike.blogpojo.VO.RespMediaTokenVo;
import com.zhike.blogpojo.VO.RespUploadMediaVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Api(tags = "API: 文件")
@RequestMapping("api/v1/media")
@RestController
//@AllArgsConstructor
public class UploadController {

    @Resource
    private QiNiuUtil qiNiuUtil;
    @Resource
    private QiNiuProperties qiniuProperties;

    /**
     * 上传文件
     * @param file
     * @return
     */
    @PostMapping
    @ApiOperation(value = "媒体文件上传", notes = "媒体文件上传")
    public RespUploadMediaVo uploadMedia(@RequestParam("file") MultipartFile file,
                                         @RequestParam("bucketType") Integer bucketType,
                                         @RequestParam("dir") String dir) {
        if (ObjectUtils.isEmpty(file) || file.getSize() <= 0) {
            throw new BizException("上传文件大小不能为空!");
        }
        RespUploadMediaVo vo=new RespUploadMediaVo();
        String fileKey = qiNiuUtil.upload(file,bucketType,dir);
        if(bucketType.equals(0))
        {
            vo.setFileKey(fileKey);
            vo.setFullUrl(qiNiuUtil.getFUllPublicUrl(fileKey));
        }else if(bucketType.equals(1)){
            vo.setFileKey(fileKey);
            vo.setFullUrl(qiNiuUtil.getFUllPrivateUrl(fileKey));
        }

        return vo;
    }

    /**
     * 获取上传token
     * @param dto
     * @return
     */
    @GetMapping("qiniu-token")
    @ApiOperation(value = "获取上传token", notes = "获取上传token")
    public RespMediaTokenVo getUploadToken(ReqFileUploadTokenDto dto) {
        if (StringUtil.isEmpty(dto.getDir())) {
            throw new BizException("文件名不能为空");
        }
        GetQiNiuTokenDto getQiNiuTokenDto=new GetQiNiuTokenDto();
        if(dto.getBucketType().equals(0))
        {
           getQiNiuTokenDto.setBucket(qiniuProperties.getPublicBucket());
        }else if(dto.getBucketType().equals(1)){
            getQiNiuTokenDto.setBucket(qiniuProperties.getPrivateBucket());
        }
        String token = qiNiuUtil.getToken(getQiNiuTokenDto);
        String fileKey=qiNiuUtil.getFileKey(dto.getDir());

        return new RespMediaTokenVo(token, fileKey);
    }
}
