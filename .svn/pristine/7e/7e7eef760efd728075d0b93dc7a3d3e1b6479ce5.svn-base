package springMVC.NLoveB.controller.apage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName:  alipay   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午10:59:38   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Controller
@RequestMapping("/apage")
public class alipay {
	@RequestMapping("/{iden}/{iden1}")
	public ModelAndView indexshop(@PathVariable String iden,@PathVariable String iden1){
		return new ModelAndView("apage/"+iden+"/"+iden1);
	}
}
