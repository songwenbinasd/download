package cn.sintang.io.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * �г�ĳ��Ŀ¼�µ������ļ���file.txt��
 * @author sintang
 * @date 2017-07-16
 */
public class FileListUtil {
	// �ļ�����
	List<String> fileList = new ArrayList<String>();
	
	public static void main(String[] args) {
		FileListUtil fileUtil = new FileListUtil();
		long startTime = System.currentTimeMillis();
		List<String> list = fileUtil.getFileList("D:\\");
		fileUtil.writeToFile(list, "file.txt");
		long endTime = System.currentTimeMillis();
		System.out.printf("��ʱ��%d����",endTime-startTime);
	}
	/**
	 * ��ȡĿ¼�����е��ļ�
	 * @param dir
	 * @return
	 */
	public List<String> getFileList(String dir){
		File dirFile = new File(dir);
		if(dirFile.exists() && dirFile.isDirectory()) {
			File[] fileArr = dirFile.listFiles();
			if(fileArr!=null && fileArr.length>0) {
				for (File file : fileArr) {
					if(file.isDirectory()) {
						//������ļ��У��ݹ����
						getFileList(file.getPath());
					}else {
						fileList.add(file.getAbsolutePath());
					}
				}
			}
		}else {
			throw new RuntimeException("��Ŀ¼������!");
		}
		return fileList;
	}
	
	/**
	 * д�뵽�ļ�
	 * @param fileNameList
	 * @param writeFileName
	 */
	public void writeToFile(List<String> fileNameList, String writeFileName) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(writeFileName));
			for (String filePath : fileNameList) {
				bw.write(filePath);
				bw.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(bw!=null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
