package springMVC.NLoveB.service.impl;

import org.springframework.stereotype.Service;

import springMVC.NLoveB.service.iter.KeymIter;
/**
 * 
 * @ClassName:  KeymImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:11:35   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Service("keym")
public class KeymImpl implements KeymIter{
	public String key="";
	@Override
	public String jiami(String type,String text){
		this.key = "";
		int sl,t,q,a,b;  //q是137行求余被，a是138行求变量因子，b是153行求余除
		char ch;
		char[] ch1=new char[4];
		
		//获取加密参数
		char[] ch2=new char[6];
		String str=text,str1,str2=type;
		for(int ttt=0;ttt<6;ttt++){
			ch2[ttt]=str2.charAt(ttt);
		}
		q=Integer.parseInt(new String(ch2,0,2),10);
		a=Integer.parseInt(new String(ch2,2,2),10);
		b=Integer.parseInt(new String(ch2,4,2),10);
		
		for(int i=0;i<str.length();i++){
			t=i%q;                                    //q为变量 ,为
			ch=(char)(((int)str.charAt(i))+a*t+(q*a)%(a+b));     //2为变量

			str1=Integer.toHexString(ch);             //此处str1表示单个汉子的16进制表示
			
			sl=str1.length();
			if(sl<4){
				for(int k=0;k<4-sl;k++){
					str1="0"+str1;
				}
			}
			
			//此处是将str1分解并且如果单位字符是0到9，就转换一下，使得其值大于F
			for (int m = 0; m < str1.length(); m ++){
				ch1[m] = str1.charAt(m); 
				if(ch1[m]>='0'&&ch1[m]<='9') {
					this.key = this.key+(String.valueOf((char)(57-(int)ch1[m]+(i+b)%11+103)));
				} else {
					this.key = this.key+(String.valueOf(ch1[m]));
				}
			}
		}
		return this.key;
	}
	
	@Override
	public String jiemi(String type,String text){
		this.key = "";
		int hz,l,t,i=0,k=9,a=2,b=2;         //k是147行求余被，a是147行变量因子，b是142求余除
		String str=text,str1,str2=type;
		
		l=str.length();
		hz=l/4;
		char[] ch1=new char[l];
		
		//获取解密参数
		char[] ch2=new char[6];
		for(int ttt=0;ttt<6;ttt++){
			ch2[ttt]=str2.charAt(ttt);
		}
		k=Integer.parseInt(new String(ch2,0,2),10);
		a=Integer.parseInt(new String(ch2,2,2),10);
		b=Integer.parseInt(new String(ch2,4,2),10);
		
		
		for(int q=0;q<hz;q++){
			i=4*q;
			for(int w=i;w<i+4;w++){
				ch1[w]=str.charAt(w);
				if(ch1[w]>'f') {
					ch1[w]=(char)(57-((int)ch1[w]-(q+b)%11-103));
				}

			}
			str1=new String(ch1,i,4);
			t=Integer.parseInt(str1,16);
			t=t-a*(q%k)-(k*a)%(a+b);
        	String ss=String.valueOf((char)t);
        	this.key = this.key+ss;
		}
		return this.key;
	}
}