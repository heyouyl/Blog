package springMVC.NLoveB.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @ClassName:  Upload   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:14:15   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public class Upload {
	//urlPath的格式如下 xxx\\xxx\\
	/*
	 * file 保存的文件
	 * urlPath 保存的相对路劲 如 \\images\\upload\\
	 * filename 文件名 如111.png
	 */
	
	public static String getFileUrl(MultipartFile file){
		String fileName = System.currentTimeMillis()+file.getOriginalFilename();
		String webinf = EnumPath.webinf.getClassPath();
		String uploadpath = EnumPath.updatepath.getClassPath();
		
		File localFile = new File(webinf+uploadpath+fileName);

		try {
			file.transferTo(localFile);
			return uploadpath+fileName;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uploadpath+fileName;

	}
}
