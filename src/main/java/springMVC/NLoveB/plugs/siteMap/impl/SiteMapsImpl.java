package springMVC.NLoveB.plugs.siteMap.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import springMVC.NLoveB.dao.iter.DaoArticle_postsIter;
import springMVC.NLoveB.plugs.siteMap.SiteMapsIter;
import springMVC.NLoveB.po.Posts;
import springMVC.NLoveB.utils.EnumPath;
import springMVC.NLoveB.utils.FileUtil;
import springMVC.NLoveB.utils.configProperties;

@Service("siteMapsImpl")
public class SiteMapsImpl implements SiteMapsIter{
	@Resource(name="daoarposts")
	private DaoArticle_postsIter darpost;
	@Override
	public void siteMaps(){
		//得到分页大小
		int pagesize = Integer.parseInt(configProperties.getProp("posts_per_page"));
		//得到总数据大小
		int pagetotal = Integer.parseInt(configProperties.getProp("arcount"));
		//得到总页码数
		int pagetotalsize = (pagetotal/pagesize)*pagesize<pagetotal?(pagetotal/pagesize)+1:(pagetotal/pagesize);
		//清空文档
		String blogPath = EnumPath.webinf.getClassPath().replace("/WEB-INF", "");
		File file = new File(blogPath+"/sitemap.xml");
		file.delete();
		//日期格式化
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Posts> article = null;
		for(int i=0;i<pagetotalsize;i++){
			article = darpost.article(i+1, pagesize, pagetotal);
			try {
				FileUtil.writeln(blogPath+"/sitemap.xml",i==0?"<?xml version=\"1.0\" encoding=\"UTF-8\"?><!-- baidu-sitemap-generator-version=\"1.6.5\" -->"
																			+"<!-- generated-on=\""+sdf.format(date)+"\" -->"
																			+"<urlset xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\" xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">"
																			+"<url><loc>http://www.028888.net</loc><lastmod>"+sdf.format(date)+"</lastmod><changefreq>daily</changefreq><priority>1.0</priority></url>"
																			+Writerurls(article,sdf):Writerurls(article,sdf));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			FileUtil.writeln(blogPath+"/sitemap.xml","</urlset>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String Writerurls(List<Posts> postsList,SimpleDateFormat sdf){
		String s = "";
		for(Posts posts:postsList){
			s=s+"<url><loc>http://www.028888.net/archives/"+posts.getPodate().toString().replace("-", "_").substring(0, 7)+"_"+posts.getPoid()+".html"+"</loc><lastmod>"+sdf.format(posts.getPodate())+"</lastmod><changefreq>monthly</changefreq><priority>0.6</priority></url>";
		}
		return s;
	}
}
