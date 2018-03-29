package springMVC.NLoveB.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @ClassName:  wordpass   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:14:10   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public final class wordpass {
	private final int len =8;
	/*
	 * param str 需要加密的字符串
	 * param salt 加密的一个随机数（一般为八位）
	 */
	public static String WordpressEncrypt(String str, String salt) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest((salt + str).getBytes());
			byte[] palin = str.getBytes();		//密码
			for (int i = 0; i < 8192; i++) {
				byte[] newplain = new byte[hash.length + palin.length];
				/*
				 * 要复制的数组；从那里开始复制；复制到那个数组；从那里开始；复制多长
				 */
				System.arraycopy(hash, 0, newplain, 0, hash.length);
				System.arraycopy(palin, 0, newplain, hash.length, palin.length);
				//md5加密
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				//digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
				//BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
				hash = md5.digest(newplain);
			}
			int[] x = new int[hash.length];
			for (int i = 0; i < hash.length; i++) {
				x[i] = hash[i] & 0xff;
				//	System.out.println(re);		
				//	return re;
			}
			return "$P$B" + salt + encode64(x, 16);
			//return String.valueOf(hash.length);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "fail";
		}
	}

	private static String encode64(int[] input, int number) {
		String hash = "";
		int output = 0;
		int[] input_2 = new int[number];
		for (int i = 0; i < number; i++) {
			input_2[i] = input[i];
			//text_2.Text += "'" + input_2[i] + "'" ;
		}
		String itoa64 = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		int output_2 = 0;
		int len_2 = 0;
		int value_2 = 0;
		for (int i = 0; i < number; i++) {
			int value = input_2[i];
			output = input_2[i];
			hash += itoa64.substring((value % 64 + 64) % 64, (value % 64 + 64) % 64 + 1);
			if (i + 1 <= number) {
				if (i + 1 < number) {
					value = input_2[++i];
					output_2 = (value << 8);		//左移8位
					output = output + output_2;
				}
				value_2 = output;
				int len = Integer.toBinaryString(output).length();
				if (len - 6 > 0) {
					output = (output >> 6);			//右移6位
				} else {
					output = 0;
				}
				value = output;
				hash += itoa64.substring((value % 64 + 64) % 64, (value % 64 + 64) % 64 + 1);
			} else {
				break;
			}
			if (i + 1 < number) {
				value = input_2[++i];
				output_2 = (value << 16);			//左移16位
				output = value_2 + output_2;
				value_2 = output;
				len_2 = Integer.toBinaryString(output).length();
				output_2 = output;
				output = (output >> 12);			//右移12位
				value = output;
				hash += itoa64.substring((value % 64 + 64) % 64, (value % 64 + 64) % 64 + 1);
			} else {
				break;
			}
			if (i + 1 < number) {
				len_2 = Integer.toBinaryString(output_2).length();
				output = (output_2 >> 18);			//右移18位
				value = output;
				hash += itoa64.substring((value % 64 + 64) % 64, (value % 64 + 64) % 64 + 1);
			}
		}
		return hash;
	}

	//len为固定的
	public String random(){
		String str = null;
		for(int i=0;i<len;i++){
			str = str+String.valueOf((char) (Math.random()*94+33));
		}
		return str;
		
	}

}
