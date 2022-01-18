package com.zhike.blogbase.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Copyright (C) 2022  智客工坊(52interview.com)
 * The SpringBoot Super-blog Project.
 * All rights reserved.
 * <p>
 * > Github地址: https://github.com/zhikecore/superblog.git
 * > 教程地址: https://www.52interview.com/book/36
 * > 智客工坊社区：https://www.52interview.com/
 * <p>
 * 智客工坊(52interview.com) - 经验创造价值,分享成就未来。
 * <p>
 * FileUtil at 2022/1/16 21:20,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
public class FileUtil {
	/**
	 * 下载url的文件到指定文件路径里面,如果文件父文件夹不存在则自动创建 url 下载的http地址 path 文件存储地址 return
	 * 如果文件大小大于2k则返回true
	 */
	public static boolean downloadCreateDir(String url, String path) {
		HttpURLConnection connection = null;
		InputStream in = null;
		FileOutputStream o = null;
		try {
			URL httpUrl = new URL(url);
			connection = (HttpURLConnection) httpUrl.openConnection();
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("Charset", "gbk");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setRequestMethod("GET");

			byte[] data = new byte[1024];
			File f = new File(path);
			File parentDir = f.getParentFile();
			if (!parentDir.exists()) {
				parentDir.mkdirs();
			}
			if (connection.getResponseCode() == 200) {
				in = connection.getInputStream();
				o = new FileOutputStream(path);
				int n = 0;
				while ((n = in.read(data)) > 0) {
					o.write(data, 0, n);
					o.flush();
				}
			}
			if (f.length() > 2048) { // 代表文件大小
				return true; // 如果文件大于2k则返回true
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			try {
				o.close();
			} catch (Exception ex) {
			}
			try {
				connection.disconnect();
			} catch (Exception ex) {
			}
		}
		return false;
	}
	
	
	/**
     * 把GBK文件转为UTF-8
     * 两个参数值可以为同一个路径
     * @param srcFileName 源文件
     * @param destFileName 目标文件
     * @throws IOException
     */
    private static void transferFile(String srcFileName, String destFileName) throws IOException {
        String line_separator = System.getProperty("line.separator"); 
        FileInputStream fis = new FileInputStream(srcFileName);
        StringBuffer content = new StringBuffer();
        DataInputStream in = new DataInputStream(fis);
        BufferedReader d = new BufferedReader(new InputStreamReader(in, "GBK"));  //源文件的编码方式
        String line = null;
        while ((line = d.readLine()) != null)
         content.append(line + line_separator);
        d.close();
        in.close();
        fis.close();
            
        Writer ow = new OutputStreamWriter(new FileOutputStream(destFileName), "utf-8");  //需要转换的编码方式
        ow.write(content.toString());
        ow.close();
    }
}
