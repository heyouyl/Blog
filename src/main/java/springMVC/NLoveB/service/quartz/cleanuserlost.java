package springMVC.NLoveB.service.quartz;

import java.util.Date;

import javax.annotation.Resource;

import org.quartz.JobExecutionException;

import springMVC.NLoveB.service.iter.SerUserInfoIter;

/*
 * 清除用户的lostpass字段
 */
/**
 * 
 * @ClassName:  cleanuserlost   
 * @Description:TODO(清除用户的lostpass字段)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:07:42   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public class cleanuserlost{
	private String command;

	@Resource(name="seruserinfo")
	private SerUserInfoIter userinfo;
	
	public void test() throws JobExecutionException {
		System.out.println("开始执行计划任务了哦"+command+"，执行时间"+(new Date()));
		//清零操作
		userinfo.cleanlostpass();
		System.out.println("所有用户错误次数清零完成");
	}


	public void setCommand(String command) {
		this.command = command;
	}
}
