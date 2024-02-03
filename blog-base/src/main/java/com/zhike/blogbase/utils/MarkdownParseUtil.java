package com.zhike.blogbase.utils;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

/**
 * Copyright (C) 2023  智客工坊(52interview.com)
 * The SpringBoot Super-blog Project.
 * All rights reserved.
 * <p>
 * > Github地址: https://github.com/zhikecore/superblog.git
 * > 教程地址: https://www.52interview.com/book/36
 * > 智客工坊社区：https://www.52interview.com/
 * <p>
 * 智客工坊(52interview.com) - 经验创造价值,分享成就未来。
 * <p>
 * JsonUtil at 2023/11/26 15:29,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
public class MarkdownParseUtil {

    /**
     * markdown 转换为html
     *
     * @param markdown markdown 内容
     * @return String html
     * @author ming
     * @date 2022-01-19 11:44:05
     */
    public static String parseHtml(String markdown) {
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String html = renderer.render(parser.parse(markdown));
        return  html;
    }

}
