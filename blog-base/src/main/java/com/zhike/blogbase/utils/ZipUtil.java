package com.zhike.blogbase.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

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
 * ZipUtil at 2022/1/16 21:20,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
public class ZipUtil {
	private static final int buffer = 2048;

	/**
	 * 解压Zip文件
	 * 
	 * @param path zip文件目录
	 */
	public static void unZip(String path) {
		int count = -1;
		String savepath = "";

		File file = null;
		InputStream is = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;

		savepath = path.substring(0, path.lastIndexOf(".")) + File.separator; // 保存解压文件目录
		new File(savepath).mkdir(); // 创建保存目录
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(path, Charset.forName("GBK")); // 解决中文乱码问题
			Enumeration<?> entries = zipFile.entries(); // 枚举ZIP中的所有文件

			while (entries.hasMoreElements()) {
				byte buf[] = new byte[buffer];

				ZipEntry entry = (ZipEntry) entries.nextElement();

				String filename = entry.getName(); // 获取文件名
				filename = savepath + filename;
				boolean ismkdir = false;
				if (filename.lastIndexOf("/") != -1) { // 检查此文件是否带有文件夹
					ismkdir = true;
				}

				if (entry.isDirectory()) { // 如果此枚举文件是文件夹则创建,并且遍历下一个
					file = new File(filename);
					file.mkdirs();
					continue;
				}
				file = new File(filename); // 此枚举文件不是目录
				if (!file.exists()) { // 如果文件不存在并且文件带有目录
					if (ismkdir) {
						new File(filename.substring(0, filename.lastIndexOf("/"))).mkdirs(); // 先创建目录
					}
				}
				file.createNewFile(); // 再创建文件

				is = zipFile.getInputStream(entry);
				fos = new FileOutputStream(file);
				bos = new BufferedOutputStream(fos, buffer);

				while ((count = is.read(buf)) > -1) {
					bos.write(buf, 0, count);
				}
				bos.flush();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
				if (fos != null) {
					fos.close();
				}
				if (is != null) {
					is.close();
				}
				if (zipFile != null) {
					zipFile.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
