package springMVC.NLoveB.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import springMVC.NLoveB.dao.iter.DaoArticle_postsIter;
import springMVC.NLoveB.dao.iter.DaotermsIter;
import springMVC.NLoveB.po.Postmeta;
import springMVC.NLoveB.po.Posts;
import springMVC.NLoveB.po.Term_relationships;
import springMVC.NLoveB.po.Term_taxonomy;
import springMVC.NLoveB.po.Terms;
import springMVC.NLoveB.service.iter.SerArticle_PostsIter;
import springMVC.NLoveB.utils.Chinese;
import springMVC.NLoveB.utils.configProperties;
/**
 * 
 * @ClassName:  SerArticle_PostsImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:11:50   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Service("serarposts")
public class SerArticle_PostsImpl implements SerArticle_PostsIter{
	@Resource(name="daoarposts")
	private DaoArticle_postsIter darpost;
	@Resource(name="daoterms")
	private DaotermsIter dterms;
	
	private SimpleDateFormat yyymmddhhmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat yyymmdd = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public int getcount() {
		List<Number> arcount = darpost.arcount();
		return arcount.get(0).intValue();
	}

	@Override
	public int getcount(String s) {
		List<Number> arcount = darpost.arcount(s);
		return arcount.get(0).intValue();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String,Object>> getAtticle(String page,String s) {
		if(null==page||page.length()==0){
			page = "1";
		}

		List<Posts> article;
		List<Map<String,Object>> lists = new ArrayList<Map<String,Object>>();
		if(null==s||s.length()==0){
			article = darpost.article(Integer.parseInt(page), Integer.parseInt(configProperties.getProp("posts_per_page")), Integer.parseInt(configProperties.getProp("arcount")));
		}else{
			//查询s的大小
			int count = getcount(s);
			article = darpost.article(Integer.parseInt(page), Integer.parseInt(configProperties.getProp("posts_per_page")),s, count);
		}
		
		int L_article = article.size();
		for(int i=0;i<L_article;i++){
			Posts post = article.get(i);
			Map<String,Object> map = new HashMap<String,Object>();
			
			int poid = post.getPoid(); 
			//获得postmeta 已加入缓存
			List<Postmeta> pmlist = darpost.postmeta(poid);
			
			for(Postmeta p:pmlist){
				if(p.getMetakey().equals("views")){
					map.put(p.getMetakey(), p.getMetavalue());
					break;
				}
			}
			//获得分类和tag
			Iterator<Term_relationships> term_relationship = post.getTerm_relationships().iterator();
			
			while(term_relationship.hasNext()){
				Term_relationships next = term_relationship.next();
				Term_taxonomy taxonomyid = next.getTaxonomyid();
				taxonomyid = dterms.getTerm_taxonomy(taxonomyid.getTermtaxonomyid());
				
				Terms termid = taxonomyid.getTermid();
				//termsname 是缩略名
				if(taxonomyid.getTaxonomy().equals("category")){
					map.put("category", termid.getName());
					map.put("slug", termid.getSlug());
					break;
				}
			}
			
			map.put("arid", poid);//文章的id
			map.put("arname", post.getPotitle());		//文章的名字
			map.put("artitle", post.getPotitle());		//文章的title
			map.put("commentcount", post.getPocomment_count());		//评论总数
			map.put("ardate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(post.getPodate()));//文章的时间
			map.put("aruser", post.getUserid());//文章的作者
			
			String rehtml = post.getPocontent().replaceAll("\\&[a-zA-Z]{0,9};", "").replaceAll("<[^>]*>", "\n\t");
			int len = rehtml.length();
			map.put("excerpt", rehtml.substring(0, len>=200?200:len));//文章的作者
			map.put("arurl", post.getPodate().toString().replace("-", "_").substring(0, 7)+"_"+post.getPoid()+".html");
			lists.add(map);
		}
		
		return lists;
	}

	@Override
	public Map<String, String> Atticletermsid(String termsid) {
		List<Posts> posts = darpost.termsid(Integer.parseInt(termsid));
		if(null==posts||posts.size()==0){
			return null;
		}else{
			Map<String,String> map = new HashMap<String,String>();
			map.put("arname", posts.get(0).getPotitle());			//文章的name
			map.put("artitle", posts.get(0).getPotitle());			//文章的title
			String rehtml = posts.get(0).getPocontent().replaceAll("\\&[a-zA-Z]{0,9};", "").replaceAll("<[^>]*>", "\n\t");
			int len = rehtml.length();
			map.put("excerpt",  rehtml.substring(0, len>=200?200:len));//文章的简介
			map.put("arurl", posts.get(0).getPodate().toString().replace("-", "_").substring(0, 7)+"_"+posts.get(0).getPoid()+".html");
			return map;
		}
	}
	
	@Override
	public String getSetinitValue(String key){
		Map<String,String> parseObject = JSON.parseObject(configProperties.getProp("setinit"),Map.class);
		return parseObject.get(key);
	}

	//获得分类的数据大小
	@Override
	public int catetagsize(String termsname,String categroy){
		List<Term_taxonomy> term_taxonomy = dterms.getTerm_taxonomy(termsname, categroy);
		//获得当前的数据大小
		if(null==term_taxonomy||term_taxonomy.size()==0){
			return 0;
		}else{
			int arcount = term_taxonomy.get(0).getCount();
			return arcount;
		}

	}
	@Override
	public List<Map<String, Object>> getArtermsslug(String page, String termsname,String categroy) {
		if(null==page||page.length()==0){
			page = "1";
		}

		List<Map<String,Object>> lists = new ArrayList<Map<String,Object>>();
		//获得当前的数据大小
		termsname = Chinese.opChinese(termsname);
		
		int arcount = catetagsize(termsname, categroy);
		List<Posts> article = darpost.termslug(Integer.parseInt(page), Integer.parseInt(configProperties.getProp("posts_per_page")), arcount,termsname,categroy);
		Map<String,Object> map = null;
		int L_article = article.size();
		for(int i=0;i<L_article;i++){
			Posts post = article.get(i);
			map = new HashMap<String,Object>();
			
			int poid = post.getPoid();
			//获得postmeta 已加入缓存
			List<Postmeta> pmlist = darpost.postmeta(poid);
			
			Iterator<Term_relationships> term_relationship = post.getTerm_relationships().iterator();
			
			for(Postmeta p:pmlist){
				if(p.getMetakey().equals("views")){
					map.put(p.getMetakey(), p.getMetavalue());
					break;
				}
			}
			

			while(term_relationship.hasNext()){
				Term_relationships next = term_relationship.next();
				Term_taxonomy taxonomyid = next.getTaxonomyid();
				taxonomyid = dterms.getTerm_taxonomy(taxonomyid.getTermtaxonomyid());
				
				Terms termid = taxonomyid.getTermid();
				//termsname 是缩略名
				if(taxonomyid.getTaxonomy().equals(categroy)&&termid.getSlug().equals(termsname)){
					map.put("category", termid.getName());
					map.put("slug", termid.getSlug());
				}
			}
			
			map.put("arid", poid);//文章的id
			map.put("arname", post.getPotitle());		//文章的名字
			map.put("artitle", post.getPotitle());		//文章的title
			map.put("commentcount", post.getPocomment_count());		//评论总数
			map.put("ardate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(post.getPodate()));//文章的时间
			map.put("aruser", post.getUserid());//文章的作者
			String rehtml = post.getPocontent().replaceAll("\\&[a-zA-Z]{0,9};", "").replaceAll("<[^>]*>", "\n\t");
			int len = rehtml.length();
			map.put("excerpt", rehtml.substring(0, len>=200?200:len));//文章的作者
			map.put("arurl", post.getPodate().toString().replace("-", "_").substring(0, 7)+"_"+poid+".html");
			lists.add(map);
		}
		
		return lists;
	}

	@Override
	public Map<String, Object> getAtticleid(String arid) {
		Map<String,Object> map = new HashMap<String,Object>();
		Posts post = darpost.articlid(Integer.parseInt(arid));
		int poid = post.getPoid();
		map.put("arid", poid);//文章的id
		map.put("arname", post.getPotitle());		//文章的名字
		map.put("artitle", post.getPotitle());		//文章的title
		map.put("artype", post.getPost_type());		//文章的分类
		map.put("commentcount", post.getPocomment_count());		//评论总数
		map.put("ardate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(post.getPodate()));//文章的时间
		map.put("aruser", post.getUserid());//文章的作者
		map.put("content", post.getPocontent());//文章的内容
		map.put("arurl", post.getPodate().toString().replace("-", "_").substring(0, 7)+"_"+arid+".html");

		//获得分类和tag
		Iterator<Term_relationships> term_relationship = post.getTerm_relationships().iterator();

		//获得postmeta 已加入缓存
		List<Postmeta> pmlist = darpost.postmeta(poid);
				
		for(Postmeta p:pmlist){
			if(p.getMetakey().equals("views")||p.getMetakey().equals("description")||p.getMetakey().equals("keywords")){
				map.put(p.getMetakey(), p.getMetavalue());
			}
		}
		
		//创建两个list
		List<Map<String,Object>> lists1 = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> lists2 = new ArrayList<Map<String,Object>>();
		while(term_relationship.hasNext()){
			Term_relationships next = term_relationship.next();
			Term_taxonomy taxonomyid = next.getTaxonomyid();
			taxonomyid = dterms.getTerm_taxonomy(taxonomyid.getTermtaxonomyid());
			
			if(taxonomyid.getTaxonomy().equals("category")){
				Map<String,Object> map1 = new HashMap<String,Object>();
				map1.put("category", taxonomyid.getTermid().getName());
				map1.put("slug", Chinese.deChinese(taxonomyid.getTermid().getSlug()));
				lists1.add(map1);	
			}else{
				Map<String,Object> map2 = new HashMap<String,Object>();
				map2.put("category", taxonomyid.getTermid().getName());
				map2.put("slug", Chinese.deChinese(taxonomyid.getTermid().getSlug()));
				lists2.add(map2);	
			}
		}
		map.put("categorys", lists1);
		map.put("tags", lists2);
		return map;
	}
	
	@Override
	public String updateeditArticle(HttpServletRequest request){
		//获得poid
		String spoid = request.getParameter("arid");
		if(null==spoid||spoid.length()==0){
			return "editerror";
		}
		
		updateArticle(request,spoid);
		
		return "editok";
	}
	
	
	//将原始的terms减1
	@Override
	public void subtermsone(Term_taxonomy[] oldtt){
		//查询oldtt减一 
		dterms.updateTermsSubOne(oldtt);
	}
	@Override
	public Map<String,Object> updateArticle(HttpServletRequest request,String poid) {
		/*
		 * 添加完一篇文章，需要做如下工作
		 * 1，跟新配置文件中的总数据
		 * 2，跟新配置文件中的分类数据
		 */
		//获得posts的数据
		Posts posts = null;
		Term_taxonomy[] oldtt=null;
		Set<Postmeta> setpm = new HashSet<Postmeta>();;
		
		if(null==poid || poid.length()==0){
			//表示新增
			posts = new Posts();
			posts.setUserid(Integer.parseInt(request.getParameter("userid")));
			posts.setGuid("http://www.028888.net/?p=");
			posts.setPotitle(request.getParameter("txtName"));		//文章的标题
			posts.setPocontent(request.getParameter("addtext"));	//文章的正文
			posts.setExcerpt(request.getParameter("txtName"));
			if(null==request.getParameter("txtDate")||request.getParameter("txtDate").length()==0){
				posts.setPodate(new Date());
				posts.setPomodified(new Date());
			}else{
				posts.setPodate(request.getParameter("txtDate"));	//文章的发布时间
				posts.setPomodified(request.getParameter("txtDate"));
			}
			
			/*
			 * 获得postmeta  有多个 保存再set中
			 * 文章的浏览次数arcount
			 * 文章的关键字arkeywords
			 * 文章的描述ardescription
			 * 文章直达连接arlink
			 */
			//表示新增
			Postmeta arcount = new Postmeta();
			Postmeta arkeywords = new Postmeta();
			Postmeta ardescription = new Postmeta();
			Postmeta arlink = new Postmeta();
			
			arcount.setMetakey("views");
			String parameter = request.getParameter("arcount");

			if(null==parameter||parameter.length()==0){
				parameter="0";
			}
	
			arcount.setMetavalue(parameter);
			arcount.setPostid(posts);
			arkeywords.setMetakey("keywords");
			arkeywords.setMetavalue(request.getParameter("arkeywords"));
			arkeywords.setPostid(posts);
			ardescription.setMetakey("description");
			ardescription.setMetavalue(request.getParameter("ardescription"));
			ardescription.setPostid(posts);
			arlink.setMetakey("link");
			arlink.setMetavalue(request.getParameter("arlink"));
			arlink.setPostid(posts);
			setpm.add(arcount);
			setpm.add(arkeywords);
			setpm.add(ardescription);
			setpm.add(arlink);			
		}else{
			//表示修改
			posts = darpost.articlid(Integer.parseInt(poid));
			posts.setPotitle(request.getParameter("txtName"));		//文章的标题
			posts.setPocontent(request.getParameter("addtext"));	//文章的正文
			posts.setExcerpt(request.getParameter("txtName"));
			if(null==request.getParameter("txtDate")||request.getParameter("txtDate").length()==0){
				//修改时间
				posts.setPomodified(new Date());
			}else{
				posts.setPomodified(request.getParameter("txtDate"));
			}
			
			//获得原始的term_taxonomy[]
			Set<Term_relationships> oldset = posts.getTerm_relationships();
			oldtt = new Term_taxonomy[oldset.size()];
			int oldi=0;
			for(Term_relationships ftt:oldset){
				oldtt[oldi]=ftt.getTaxonomyid();
				oldi++;
			}
			
			List<Postmeta> osetpm =  darpost.postmeta(posts.getPoid());
			for(Postmeta pm:osetpm){
				if(pm.getMetakey().equals("keywords")){
					pm.setMetavalue(request.getParameter("arkeywords"));
				}
				if(pm.getMetakey().equals("description")){
					pm.setMetavalue(request.getParameter("ardescription"));
				}
				if(pm.getMetakey().equals("link")){
					pm.setMetavalue(request.getParameter("arlink"));
				}
				if(pm.getMetakey().equals("views")){
					pm.setMetavalue(request.getParameter("arcount"));
				}
				setpm.add(pm);
			}
		}
		posts.setPostmeta(setpm);
		
		/*
		 * 文章的分类和标签，因为存在该分类，所以需要获得id即可 标签需要去查询了
		 * 分类addarfl
		 * 标签artags
		 */
		//获得分类和标签的数组
		//获得标签数组
		//处理标签
		String[] split = request.getParameter("artags").split("[,、]");
//		//处理tags中的中文
//		int splitlen = split.length;
//		
//		for(int i=0;i<splitlen;i++){
//			split[i]=Chinese.opChinese(split[i]);
//		}
		Term_taxonomy[] tt = new Term_taxonomy[split.length+1];
		Terms[] termss=new Terms[split.length+1];
		
		//当前只有一个分类
		termss[0]=dterms.getTermsid(Integer.parseInt(request.getParameter("addarfl")));
		Term_taxonomy uptt = termss[0].getTerm_taxonomy();
		uptt.setCount(uptt.getCount()+1);
		tt[0]=uptt;
		
		//获得标签的数据
		Map<String, List<Terms>> mapTerms = dterms.isGetTermtags(split);
		List<Terms> list = mapTerms.get("noterms");		//没有这个对象
		List<Terms> list2 = mapTerms.get("yesterms");	//有这个对象
		int listlen = list.size();
		for(int i=0;i<listlen;i++){
			termss[i+1]=list.get(i);
			Term_taxonomy term_taxonomy = list.get(i).getTerm_taxonomy();
			term_taxonomy.setCount(1);
			tt[i+1]=term_taxonomy;
		}
		for(int i=listlen;i<list2.size()+listlen;i++){
			termss[i+1]=list2.get(i-listlen);
			Term_taxonomy term_taxonomy = list2.get(i-listlen).getTerm_taxonomy();
			term_taxonomy.setCount(term_taxonomy.getCount()+1);
			tt[i+1]=term_taxonomy;
		}
		
		//获得Term_relationships集合
		Set<Term_relationships> set = new HashSet<Term_relationships>();
		for(int i=0;i<tt.length;i++){
			Term_relationships tr = new Term_relationships();
			tr.setObjectid(posts);
			tr.setTaxonomyid(tt[i]);
			set.add(tr);
		}
		
		//设置posts和Term_relationships的集合
		posts.setTerm_relationships(set);
		
		//文章 termss[] Term_taxonomy[] tt
		Map<String, Object> mapnew = darpost.addticle(posts,termss,tt);
		
		//Terms[] newtt = (Terms[]) mapnew.get("o");
		
		//操作terms.将旧数据的count减1
		if(null==poid || poid.length()==0){
			//新增，不做处理
		}else{
			//表示修改
			subtermsone(oldtt);
		}
		
		return mapnew;
	}
	@Override
	public String updateeditPage(HttpServletRequest request){
		//获得poid
		String spoid = request.getParameter("arid");
		if(null==spoid||spoid.length()==0){
			return "editerror";
		}
		
		updatePage(request,spoid);
		
		return "editok";
	}
	
	@Override
	public String updatePage(HttpServletRequest request,String poid){
		Posts posts = null;
		Set<Postmeta> setpm = new HashSet<Postmeta>();;
		
		if(null==poid || poid.length()==0){
			//表示新增
			posts = new Posts();
			posts.setUserid(Integer.parseInt(request.getParameter("userid")));
			posts.setGuid("http://www.028888.net/?p=");
			posts.setPotitle(request.getParameter("txtName"));		//文章的标题
			posts.setPocontent(request.getParameter("addtext"));	//文章的正文
			posts.setExcerpt(request.getParameter("txtName"));
			posts.setPost_type("page");
			if(null==request.getParameter("txtDate")||request.getParameter("txtDate").length()==0){
				posts.setPodate(new Date());
				posts.setPomodified(new Date());
			}else{
				posts.setPodate(request.getParameter("txtDate"));	//文章的发布时间
				posts.setPomodified(request.getParameter("txtDate"));
			}
			
			/*
			 * 获得postmeta  有多个 保存再set中
			 * 文章的浏览次数arcount
			 * 文章的关键字arkeywords
			 * 文章的描述ardescription
			 * 文章直达连接arlink
			 */
			//表示新增
			Postmeta arcount = new Postmeta();
			Postmeta arkeywords = new Postmeta();
			Postmeta ardescription = new Postmeta();
			Postmeta arlink = new Postmeta();
			
			arcount.setMetakey("views");
			String parameter = request.getParameter("arcount");

			if(null==parameter||parameter.length()==0){
				parameter="0";
			}
	
			arcount.setMetavalue(parameter);
			arcount.setPostid(posts);
			arkeywords.setMetakey("keywords");
			arkeywords.setMetavalue(request.getParameter("arkeywords"));
			arkeywords.setPostid(posts);
			ardescription.setMetakey("description");
			ardescription.setMetavalue(request.getParameter("ardescription"));
			ardescription.setPostid(posts);
			arlink.setMetakey("link");
			arlink.setMetavalue(request.getParameter("arlink"));
			arlink.setPostid(posts);
			setpm.add(arcount);
			setpm.add(arkeywords);
			setpm.add(ardescription);
			setpm.add(arlink);			
		}else{
			//表示修改
			posts = darpost.articlid(Integer.parseInt(poid));
			posts.setPotitle(request.getParameter("txtName"));		//文章的标题
			posts.setPocontent(request.getParameter("addtext"));	//文章的正文
			posts.setExcerpt(request.getParameter("txtName"));
			if(null==request.getParameter("txtDate")||request.getParameter("txtDate").length()==0){
				//修改时间
				posts.setPomodified(new Date());
			}else{
				posts.setPomodified(request.getParameter("txtDate"));
			}
			
			List<Postmeta> osetpm =  darpost.postmeta(posts.getPoid());
			for(Postmeta pm:osetpm){
				if(pm.getMetakey().equals("keywords")){
					pm.setMetavalue(request.getParameter("arkeywords"));
				}
				if(pm.getMetakey().equals("description")){
					pm.setMetavalue(request.getParameter("ardescription"));
				}
				if(pm.getMetakey().equals("link")){
					pm.setMetavalue(request.getParameter("arlink"));
				}
				if(pm.getMetakey().equals("views")){
					pm.setMetavalue(request.getParameter("arcount"));
				}
				setpm.add(pm);
			}
		}
		posts.setPostmeta(setpm);
		Posts addDraftTicle = darpost.addUserTicle(posts);
		if(addDraftTicle.getPoid()>0){
			return "statustok,"+addDraftTicle.getPoid();
		}else{
			return "statuserror";
		}
	}
	@Override
	public String addUserArticle(String arname, String arcontent, String link, String userid,String poststatus) {
		Posts posts = new Posts();
		posts.setPotitle(arname);		//文章的标题
		posts.setPocontent(arcontent);	//文章的正文
		posts.setUserid(Integer.parseInt(userid));
		posts.setExcerpt(arname);
		posts.setGuid("http://www.028888.net/?p=");
		posts.setPodate(new Date());
		posts.setPomodified(new Date());
		posts.setPostatus(poststatus);
		
		
		if(null==link || link.length()==0){
			
		}else{
			Set<Postmeta> setpm = new HashSet<Postmeta>();
			Postmeta arlink = new Postmeta();
			arlink.setMetakey("link");
			arlink.setMetavalue(link);
			arlink.setPostid(posts);
			
			posts.setPostmeta(setpm);
		}
	
		Posts addDraftTicle = darpost.addUserTicle(posts);
		if(addDraftTicle.getPoid()>0){
			return "statustok,"+addDraftTicle.getPoid();
		}else{
			return "statuserror";
		}
	}

	@Override
	public String useractionjson(int page, String poststatus, int userid) {
		// 获得当前用户的当前状态的文章大小		
		int userarticleLength = useractionstatuslen(poststatus,userid);
		
		List<Posts> userarticle = darpost.userarticle(page, Integer.parseInt(configProperties.getProp("posts_per_page")), userarticleLength, poststatus, userid);
		//开始组装数据
		List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
		Map<String,Object> listmap = new HashMap<String,Object>();
		Map<String,Object> article = null;
		for(Posts post:userarticle){
			/*
			 * thumb：右侧图像	link：文章连接		title：文章的标题
			 * desc：文章的说明	time文章发布的时间	cat分类
			 * view：查看次数	comment：评论次数	like：点赞次数
			 */
			int poid = post.getPoid();
			article = new HashMap<String,Object>();
			article.put("thumb", "");
			article.put("link", post.getPodate().toString().replace("-", "_").substring(0, 7)+"_"+poid+".html");
			article.put("title", post.getPotitle());		//文章的名字
			String rehtml = post.getPocontent().replaceAll("\\&[a-zA-Z]{0,9};", "").replaceAll("<[^>]*>", "\n\t");
			int len = rehtml.length();
			article.put("desc", rehtml.substring(0, len>=200?200:len));//文章的作者
			article.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(post.getPodate()));//文章的时间
			article.put("comment", post.getPocomment_count());//文章的时间
			
			Iterator<Term_relationships> term_relationship = post.getTerm_relationships().iterator();
			
			//获得已经缓存的postmeta
			List<Postmeta> pmlist = darpost.postmeta(poid);
			//用户的文章可能存在没有保存的情况，没有保存 views是不存在的
			String views = "-1";
			for(Postmeta p:pmlist){
				if(p.getMetakey().equals("views")){
					views=p.getMetavalue();
					break;
				}
			}
			if(views.equals("-1")){
				article.put("views",-1);
				article.put("like",-1);
			}else{
				article.put("views",views);
				article.put("like",-1);
			}
			
			while(term_relationship.hasNext()){
				Term_relationships next = term_relationship.next();
				Term_taxonomy taxonomyid = next.getTaxonomyid();
				taxonomyid = dterms.getTerm_taxonomy(taxonomyid.getTermtaxonomyid());
				
				Terms termid = taxonomyid.getTermid();
				if(taxonomyid.getTaxonomy().equals("category")){
					article.put("cat", termid.getName());
					//分类为英文字符，可以不用转换
					article.put("slug", Chinese.deChinese(termid.getSlug()));
					break;
				}
				article.put("cat", "未分类");
				article.put("slug", "uncategorized");
				
			}
			items.add(article);
		}
		
		listmap.put("max", userarticleLength);
		listmap.put("items", items);

		return JSON.toJSONString(listmap);
	}

	@Override
	public int useractionstatuslen(String poststatus, int userid) {
		// 获得当前用户的当前状态的文章大小		
		List<Number> arcount = darpost.userarticleLength(poststatus,userid);
		int userarticleLength = arcount.get(0).intValue();
		return userarticleLength;
	}

	@Override
	public int[] categoryidToObject(int categoryid) {
		List<Object> relationshipsObject = dterms.relationshipsObject(categoryid);
		
		int len = relationshipsObject.size();
		int[] poids = new int[len];
		
		for(int i=0;i<len;i++){
			poids[i]=(int) relationshipsObject.get(i);
		}
		return poids;
	}

	@Override
	public Postmeta postmetaview(int poid) {
		List<Object> postmetaviews = darpost.postmetaviews(poid);
		Postmeta x = null;
		if(null==postmetaviews||postmetaviews.size()==0){
			Posts p = new Posts();
			p.setPoid(poid);
			
			x = new Postmeta();
			x.setMetakey("views");
			x.setMetavalue("1");
			x.setPostid(p);
			
		}else{
			x = (Postmeta) postmetaviews.get(0);
			x.setMetavalue(String.valueOf((Integer.parseInt(x.getMetavalue())+1)));
		}
		return x;
	}

	@Override
	public <T> void addpostmetaview(T t) {
		// TODO Auto-generated method stub
		darpost.addpostmetaviews(t);
	}

	@Override
	public String adminallpostsjson(String page, String pagesize, String poststatus) {
		int ipage = 0;
		int ipagesize = 0;
		if(null==page||page.length()==0){
			ipage = 1;
		}else{
			ipage=Integer.parseInt(page);
		}
		if(null==pagesize||pagesize.length()==0){
			ipagesize = Integer.parseInt(configProperties.getProp("posts_per_page"));
		}else{
			ipagesize=Integer.parseInt(pagesize);
		}
		
		int arcount = Integer.parseInt(configProperties.getProp("arcount"));
		List<Posts> adminpost = darpost.adminpost(ipage, ipagesize, arcount, poststatus);

		Map<String,Object> hashMap = new HashMap<String,Object>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		
		for(Posts p:adminpost){
			map = new HashMap<String,Object>();
			int pid = p.getPoid();
			map.put("arid", pid);//文章的id
			map.put("arname", p.getPotitle());		//文章的名字
			map.put("arstatus", p.getPostatus());		//文章的状态
			map.put("commentstatus", p.getPocommentstatus());	//文章评论状态
			map.put("pass", p.getPopass());			//文章的密码
			map.put("ardate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(p.getPodate()));//文章的时间
			map.put("aruid", p.getUserid());
			map.put("arurl", p.getPodate().toString().replace("-", "_").substring(0, 7)+"_"+pid+".html");
			list.add(map);
		}
		hashMap.put("Rows",list);
		hashMap.put("Total", arcount);
		return JSON.toJSONString(hashMap);
	}

	@Override
	public String delarid(String poid) {
		if(null==poid||poid.length()==0){
			return "delarerror,aaa";
		}
		Posts articlid = darpost.articlid(Integer.parseInt(poid));
		if(null == articlid){
			return "delarok,aaa";
		}else{
			String pstatus = articlid.getPostatus();	
			Set<Term_relationships> term_relationships = articlid.getTerm_relationships();
			Set<Term_relationships> set = new HashSet<Term_relationships>();
			int len = term_relationships.size();
			Term_taxonomy[] tt = new Term_taxonomy[len];
			int ii = 0;
			Term_taxonomy tt1 = null;
			
			for(Term_relationships tr:term_relationships){
				Term_taxonomy taxonomyid = tr.getTaxonomyid();
				tt[ii]=taxonomyid;
				ii++;
				
				tt1 = new Term_taxonomy();
				tt1.setTermtaxonomyid(taxonomyid.getTermtaxonomyid());
				tr.setTaxonomyid(tt1);
				set.add(tr);
			}
			
			//将分类或tags数据减一
			subtermsone(tt);
			//删除文章
			darpost.deletearid(articlid.getPoid());
			return "delarok,"+pstatus;
		}
	}

	@Override
	public String updateArticleFast(HttpServletRequest request) {
		String status = request.getParameter("__status");
		if(status.equals("update")){
			//获得posts
			int poid = Integer.parseInt(request.getParameter("arid"));
			Posts articlid = darpost.articlid(poid);
			articlid.setPotitle(request.getParameter("arname"));
			articlid.setPostatus(request.getParameter("arstatus"));
			articlid.setPocommentstatus(request.getParameter("commentstatus"));
			articlid.setPopass(request.getParameter("pass"));
			articlid.setPodate(request.getParameter("ardate"));
			articlid.setUserid(Integer.parseInt(request.getParameter("aruid")));
			darpost.addUserTicle(articlid);
			return "uparok";
		}else{
			return "uparok";
		}
	}

	//编辑文章所需要的参数（管理员）
	@Override
	public Map<String, Object> getarticleMap(String arid) {
		Map<String,Object> map = new HashMap<String,Object>();
		Posts post = darpost.articlid(Integer.parseInt(arid));
		int poid = post.getPoid();
		map.put("arid", poid);//文章的id
		map.put("artitle", post.getPotitle());		//文章的title
		map.put("ardate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(post.getPodate()));//文章的时间
		map.put("content", post.getPocontent());//文章的内容

		//获得分类和tag
		Iterator<Term_relationships> term_relationship = post.getTerm_relationships().iterator();

		//获得postmeta 已加入缓存
		List<Postmeta> pmlist = darpost.postmeta(poid);
				
		for(Postmeta p:pmlist){
			if(p.getMetakey().equals("views")||p.getMetakey().equals("description")||p.getMetakey().equals("keywords")||p.getMetakey().equals("link")){
				map.put(p.getMetakey(), p.getMetavalue());
			}
		}
		
		//创建两个list
		List<Map<String,Object>> lists1 = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> lists2 = new ArrayList<Map<String,Object>>();
		String cates = "";
		while(term_relationship.hasNext()){
			Term_relationships next = term_relationship.next();
			Term_taxonomy taxonomyid = next.getTaxonomyid();
			taxonomyid = dterms.getTerm_taxonomy(taxonomyid.getTermtaxonomyid());
			
			if(taxonomyid.getTaxonomy().equals("category")){
				Map<String,Object> map1 = new HashMap<String,Object>();
				map1.put("termsid", taxonomyid.getTermid().getTermid());
				lists1.add(map1);	
			}else{
				Map<String,Object> map2 = new HashMap<String,Object>();
				cates = cates+taxonomyid.getTermid().getName()+",";
				
				map2.put("category", taxonomyid.getTermid().getName());
				map2.put("slug", Chinese.deChinese(taxonomyid.getTermid().getSlug()));
				lists2.add(map2);	
			}
		}
		cates = ((null==cates||cates.length()==0)?"":(cates.substring(0, cates.length()-1)));
		map.put("categorys", lists1.get(0).get("termsid"));//假定只获得第一个类，之前一个文章存在多个类
		map.put("tags", cates);
		return map;
	}

	@Override
	public void updateArticleguid(int poid) {
		darpost.updateGuid(poid);
	}

	@Override
	public String updateeditnotice(HttpServletRequest request) {
		//获得poid
		String spoid = request.getParameter("arid");
		if(null==spoid||spoid.length()==0){
			return "editerror";
		}
		
		updatenotice(request,spoid);
		
		return "editok";
	}

	@Override
	public String updatenotice(HttpServletRequest request, String poid) {
		Posts posts = null;
		Set<Postmeta> setpm = new HashSet<Postmeta>();;
			
		if(null==poid || poid.length()==0){
			//表示新增
			posts = new Posts();
			posts.setUserid(Integer.parseInt(request.getParameter("userid")));
			posts.setGuid("http://www.028888.net/?p=");
			posts.setPotitle(request.getParameter("txtName"));		//文章的标题
			posts.setPocontent(request.getParameter("addtext"));	//文章的正文
			posts.setExcerpt(request.getParameter("txtName"));
			posts.setPost_type("notice");
			if(null==request.getParameter("txtDate")||request.getParameter("txtDate").length()==0){
				posts.setPodate(new Date());
				posts.setPomodified(new Date());
			}else{
				posts.setPodate(request.getParameter("txtDate"));	//文章的发布时间
				posts.setPomodified(request.getParameter("txtDate"));
			}
			
			/*
			 * 获得postmeta  有多个 保存再set中
			 * 文章的浏览次数arcount
			 * 文章的关键字arkeywords
			 * 文章的描述ardescription
			 * 文章直达连接arlink
			 */
			//表示新增
			Postmeta arcount = new Postmeta();
			Postmeta arkeywords = new Postmeta();
			Postmeta ardescription = new Postmeta();
			Postmeta arlink = new Postmeta();
			
			arcount.setMetakey("views");
			String parameter = request.getParameter("arcount");

			if(null==parameter||parameter.length()==0){
				parameter="0";
			}
	
			arcount.setMetavalue(parameter);
			arcount.setPostid(posts);
			arkeywords.setMetakey("keywords");
			arkeywords.setMetavalue(request.getParameter("arkeywords"));
			arkeywords.setPostid(posts);
			ardescription.setMetakey("description");
			ardescription.setMetavalue(request.getParameter("ardescription"));
			ardescription.setPostid(posts);
			arlink.setMetakey("link");
			arlink.setMetavalue(request.getParameter("arlink"));
			arlink.setPostid(posts);
			setpm.add(arcount);
			setpm.add(arkeywords);
			setpm.add(ardescription);
			setpm.add(arlink);			
		}else{
			//表示修改
			posts = darpost.articlid(Integer.parseInt(poid));
			posts.setPotitle(request.getParameter("txtName"));		//文章的标题
			posts.setPocontent(request.getParameter("addtext"));	//文章的正文
			posts.setExcerpt(request.getParameter("txtName"));
			if(null==request.getParameter("txtDate")||request.getParameter("txtDate").length()==0){
				//修改时间
				posts.setPomodified(new Date());
			}else{
				posts.setPomodified(request.getParameter("txtDate"));
			}
			
			List<Postmeta> osetpm =  darpost.postmeta(posts.getPoid());
			for(Postmeta pm:osetpm){
				if(pm.getMetakey().equals("keywords")){
					pm.setMetavalue(request.getParameter("arkeywords"));
				}
				if(pm.getMetakey().equals("description")){
					pm.setMetavalue(request.getParameter("ardescription"));
				}
				if(pm.getMetakey().equals("link")){
					pm.setMetavalue(request.getParameter("arlink"));
				}
				if(pm.getMetakey().equals("views")){
					pm.setMetavalue(request.getParameter("arcount"));
				}
				setpm.add(pm);
			}
		}
		posts.setPostmeta(setpm);
		Posts addDraftTicle = darpost.addUserTicle(posts);
		if(addDraftTicle.getPoid()>0){
			return "statustok,"+addDraftTicle.getPoid();
		}else{
			return "statuserror";
		}
	}

	@Override
	public List<Map<String,Object>> getNotice(int noticesize) {
		List<Posts> article;
		List<Map<String,Object>> lists = new ArrayList<Map<String,Object>>();
		
		article = darpost.article_notice(noticesize);

		
		int L_article = article.size();
		for(int i=0;i<L_article;i++){
			Posts post = article.get(i);
			Map<String,Object> map = new HashMap<String,Object>();
			
			int poid = post.getPoid(); 
			//获得postmeta 已加入缓存
			List<Postmeta> pmlist = darpost.postmeta(poid);
			
			for(Postmeta p:pmlist){
				if(p.getMetakey().equals("views")){
					map.put(p.getMetakey(), p.getMetavalue());
					break;
				}
			}
			
			//map.put("arid", poid);//文章的id
			map.put("arname", post.getPotitle());		//文章的名字
			map.put("artitle", post.getPotitle());		//文章的title
			map.put("ardate", yyymmddhhmmss.format(post.getPodate()));//文章的时间
			map.put("ardate1", yyymmdd.format(post.getPodate()));//文章的时间
			//map.put("aruser", post.getUserid());//文章的作者
			map.put("arurl", post.getPodate().toString().replace("-", "_").substring(0, 7)+"_"+post.getPoid()+".html");
			lists.add(map);
		}
		
		return lists;
	}
}
