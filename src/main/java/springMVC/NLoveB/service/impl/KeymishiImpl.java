package springMVC.NLoveB.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import springMVC.NLoveB.service.iter.KeymIter;
import springMVC.NLoveB.service.iter.KeymishiIter;
/**
 * 
 * @ClassName:  KeymishiImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:12:01   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Service("serkeyms")
public class KeymishiImpl implements KeymishiIter{
	
	@Resource(name= "keym")
	private KeymIter keym;
//	@Resource(name = "perrlo")
//	public PeRegisterLoginIter prl;
	
	//通过用户名进行加密，返回最终的加密串
	@Override
	public String getEamilkey(String username) {
		
		String emailkey="";
		emailkey = keym.jiami("123466", username);
		emailkey = emailkey+"|"+keym.jiami("123466", emailkey);
		
		return emailkey;
	}
	//通过key解密
	@Override
	public String getJEamilkey(String emailkey){
		return emailkey;
		
//		String[] key;
//		if(emailkey==null||emailkey.length()<0){
//			return "error";
//		}else{
//			key = emailkey.split("\\|");
//			if(key.length == 2){
//				String key1 = keym.jiemi("123466", key[0]);
//				String key2 = keym.jiemi("123466",keym.jiemi("123466",key[1]));
//				if(key1.equals(key2)){
//					//将用户权限设置为正常
//					return ""+prl.updataoveruser(key1);
//				}else{
//					return "error";
//				}
//			}else{
//				return "error";
//			}
//		}
	}
	@Override
	public String getJamkey(String arg) {
		return keym.jiami("562304", arg);
	}
	@Override
	public String getJemkey(String key) {
		if(key==null||key.length()<0){
			return "error";
		}else{
			return keym.jiemi("562304",key);
		}
	}
	
	
}