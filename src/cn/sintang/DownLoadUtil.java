package cn.sintang;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import javax.swing.JLabel;

/**
 * 下载网络文件
 * @author sintang
 * @date 2017-07-15
 */
public class DownLoadUtil {
	
	public static final String DOWN_LOAD_DIR = "F:\\download\\";
	
	public static void main(String[] args) throws Exception {
		switch (args.length) {
		case 1:
			download(args[0]);
			break;
		case 2:
			download(args[0],args[1]);
			break;
		case 3:
			download(args[0],args[1],args[2]);
			break;
		default:
			download("");
			break;
		}
	}
	/**
	 * 下载文件
	 * @param url
	 * @throws Exception 
	 */
	public static void download(String url) throws Exception{
		String fileName = "down";
		if(url!=null && !"".equals(url)){
			fileName = url.substring(url.lastIndexOf("/"));
		}
		download(url,fileName, DOWN_LOAD_DIR);
	}
	/**
	 * 下载文件
	 * @param url
	 * @throws Exception 
	 */
	public static void download(String url,JLabel statusLabel) throws Exception{
		String fileName = "down";
		if(url!=null && !"".equals(url)){
			fileName = url.substring(url.lastIndexOf("/"));
		}
		download(url,fileName, DOWN_LOAD_DIR,statusLabel);
	}
	/**
	 * 下载文件
	 * @param url
	 * @param fileName 文件名
	 * @throws Exception 
	 */
	public static void download(String url,String fileName) throws Exception{
		download(url,fileName, DOWN_LOAD_DIR);
	}
	/**
	 * 下载文件
	 * @param url
	 * @param fileName 文件名
	 * @param downDir 目录
	 * @throws Exception 
	 */
	public static void download(String url,String fileName,String downDir) throws Exception{
		if(url==null || "".equals(url)){
			throw new RuntimeException("下载地址不能为空");
		}
		URL urlAddr = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) urlAddr.openConnection();
		// 设置超时时间为30秒
		conn.setReadTimeout(10*1000);
		//防止屏蔽程序抓取而返回403错误  
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
        InputStream in = conn.getInputStream();
        double fileSize = conn.getContentLength();
        System.out.println("文件大小："+new DecimalFormat("#.00").format(fileSize/1024/1024)+"M");
        copyFile(in,fileSize,fileName, downDir);
	}
	/**
	 * 下载文件
	 * @param url
	 * @param fileName 文件名
	 * @param downDir 目录
	 * @throws Exception 
	 */
	public static void download(String url,String fileName,String downDir,JLabel statusLabel) throws Exception{
		if(url==null || "".equals(url)){
			throw new RuntimeException("下载地址不能为空");
		}
		URL urlAddr = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) urlAddr.openConnection();
		// 设置超时时间为30秒
		conn.setReadTimeout(10*1000);
		//防止屏蔽程序抓取而返回403错误  
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
		InputStream in = conn.getInputStream();
		double fileSize = conn.getContentLength();
		System.out.println("文件大小："+new DecimalFormat("#.00").format(fileSize/1024/1024)+"M");
		copyFile(in,fileSize,fileName, downDir,statusLabel);
	}
	/**
	 * 复制文件
	 * @param in
	 * @param fileName
	 * @param downDir
	 * @throws Exception 
	 */
	public static void copyFile(InputStream in,double fileSize,String fileName,String downDir){
		long startTime = System.currentTimeMillis();
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(DOWN_LOAD_DIR+fileName));
			byte[] buf = new byte[2024];
			int len = 0;
			double writeSum = 0;
			while((len = in.read(buf))!=-1){
				writeSum+=len;
				bos.write(buf, 0, len);
//				System.out.println("完成比例："+new DecimalFormat("#.00").format((writeSum/fileSize)*100)+"%");
			}
			System.out.println("下载完成！");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			long endTime = System.currentTimeMillis();
			System.out.printf("耗时：%s毫秒",(endTime-startTime));
			if(bos!=null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 复制文件
	 * @param in
	 * @param fileName
	 * @param downDir
	 * @throws Exception 
	 */
	public static void copyFile(InputStream in,double fileSize,String fileName,String downDir,JLabel statusLabel){
		long startTime = System.currentTimeMillis();
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(DOWN_LOAD_DIR+fileName));
			byte[] buf = new byte[2024];
			int len = 0;
			double writeSum = 0;
			while((len = in.read(buf))!=-1){
				writeSum+=len;
				bos.write(buf, 0, len);
//				statusLabel.setText("完成比例："+new DecimalFormat("#.00").format((writeSum/fileSize)*100)+"%");
			}
			System.out.println("下载完成！");
			statusLabel.setText("下载完成！");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			long endTime = System.currentTimeMillis();
			System.out.printf("耗时：%s毫秒",(endTime-startTime));
			if(bos!=null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
