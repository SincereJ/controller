package cws.console.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cws.console.smo.BaseSmo;
import cws.console.util.ExpConfig;
import cws.console.util.ExpDescription;
import cws.console.util.ExpFactory;

@Controller
public class HomeController {

	@Autowired
	private BaseSmo baseSmo;
	
	@SuppressWarnings("unused")
	private ExpFactory expFactory = new ExpFactory();
	
	/*@RequestMapping(value="/index",method=RequestMethod.GET)
	public String goHome() {
		System.out.println("index");
		//return "index.html";
		return "/index.html";
	}
	*/
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value="/ajax",method=RequestMethod.POST)
	public Map ajax(@RequestBody HashMap map){
		System.out.println("ajax");
		return map;
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value="/findAllService",method=RequestMethod.POST)
	public List findAllService(@RequestBody HashMap map) {
		System.out.println("findAllService");
		
		List rest = baseSmo.findAllService(map);
		return rest;
	}
	
	
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String home(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		System.out.println("Home");
		
		//List rest = new ArrayList();
		//rest.add(createData());
		
		//HashMap hs = (HashMap) baseSmo.findOne();
		//String[] fields = new String[] {"a","a","a","a"};
		//String[] colName = new String[] {"A","B","C","D"};
		//HttpServletResponse response = RequestContextHolder.getRequestAttributes().getResponse();
		
	/*	List rest = baseSmo.findList();
		
		List rs = ExpDataUtil.splitList(rest);
		
		ServletOutputStream out = resp.getOutputStream();
		//ExpDescription expDesc = ExpDesFactory.getDEscription("���Ա��", "����sheet", "1");
		ExpDescription expDesc = ExpDesFactory.getDEscription("2");
		
		setResponseHeader(resp,expDesc);
		
		expFactory.doExport(out, rs, expDesc, rest.size());*/
		
		
		
		//System.out.println(hs);
		
		return "home";
		//return null;
	}
	
	@SuppressWarnings("unused")
	private void setResponseHeader(HttpServletResponse response,ExpDescription expDesc) throws UnsupportedEncodingException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentTime = sf.format(new Date());
		
		String fileName = expDesc.getTableName() + "-" + currentTime + ExpConfig.DEFAULT_EXT_NAME;
        String headStr = "attachment; filename=" + new String(fileName.getBytes("gb2312"),"ISO8859-1");
        
        response.reset();
        response.setHeader("Content-Type","application/force-download");   
        response.setHeader("Content-Type","application/vnd.ms-excel");   
        response.setHeader("Content-disposition", headStr);
        
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List createData() {
		List lst1 = new ArrayList();
		lst1.add(getMapData());
		lst1.add(getMapData());
		lst1.add(getMapData());
		lst1.add(getMapData());
		
		return lst1;
	}
	
	public HashMap<String, Object> getMapData() {
		String[] cols = new String[] {"a","b","c","d"};
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(String s : cols){
			map.put(s, s);
		}
		
		return map;
	}
	
}
