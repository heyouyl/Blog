package springMVC.NLoveB.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 
 * @ClassName:  Chinese   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:18:40   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public class Chinese {

	//判断这个字符是否是汉字，返回boolean
	public static boolean isChinese(char c) {
	    boolean result = false;
	    if (c >= 19968 && c <= 171941) {// 汉字范围 \u4e00-\u9fa5 (文)
	        result = true;
	    }
	    return result;
	}
	
	//判断这个字符是否是汉字，返回boolean
	public static boolean isChinese(char[] c) {
	    boolean result = false;
	    for(char cc:c){
	    	if (cc >= 19968 && cc <= 171941) {// 汉字范围 \u4e00-\u9fa5 (文)
		        result = true;
		    }
	    }
	    
	    return result;
	}
	
	//判断是否汉字，有，就将汉字转换为小写
	public static String opChinese(String str) {
		String restr="";
		String encode = null;
		char[] charArray = str.toCharArray();
		for(char c:charArray){
	    	if (c >= 19968 && c <= 171941) {// 汉字范围 \u4e00-\u9fa5 (文)
				try {
					encode = URLEncoder.encode(String.valueOf(c),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String lowerCase = encode.toLowerCase();
				restr=restr+lowerCase;
		    }else{
		    	restr=restr+c;
		    }
	    }
		return restr;
	}
	//判断是否汉字，有，就将汉字转换为小写
	
	public static String deChinese(String str) {
		String decode = null;
		try {
			decode = URLDecoder.decode(str,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return decode;
	}
}
