package springMVC.NLoveB.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @ClassName:  FileUtil   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:16:57   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public class FileUtil {
	/**
	 * @param name
	 * @param text
	 * @throws IOException
	 */
	public static void write(String name, String text) throws IOException {
		File file = new File(name);
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.append(text);
		bw.flush();
		bw.close();
		fw.close();
	}

	/**
	 * @param name
	 * @param text
	 * @throws IOException
	 */
	public static void writeln(String name, String text) throws IOException {
		//write(name, text + "\r\n");
		write(name, text);
	}
}
