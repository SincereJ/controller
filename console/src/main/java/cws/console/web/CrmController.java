package cws.console.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cws.console.smo.CrmSmo;

@Controller
@RequestMapping("/crm")
public class CrmController {

	@Autowired
	private CrmSmo crmSmo ;
	
	/**
	 * 根据条件筛选场景
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/getSceneList",method=RequestMethod.POST)
	@ResponseBody
	public List getSceneList(@RequestBody HashMap map) {
		System.out.println("getSceneList");
		return crmSmo.getSceneList(map);
	}
	
	
	/**
	 * 正式提交 执行sql
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/doExecurte",method=RequestMethod.POST)
	@ResponseBody
	public Object doExecurte(@RequestBody HashMap map) {
		System.out.println("doExecurte");
		String msg = "{\"resultCode\":\"0\",\"msg\":\"\"}";
		
		//System.out.println(map);
		try {
			if(map != null) {
				Map sceneMap = (Map) map.get("formScene");
				List pNumList = (List) map.get("pNumList");
				String optype = (String) sceneMap.get("op_type");
				String sqlType = (String) sceneMap.get("sql_type");
				String sql = (String) sceneMap.get("sql");
				int pNum = Integer.valueOf(sceneMap.get("p_num").toString());
				
				//判断入参数量
				if(pNum != pNumList.size()) {
					msg = "{\"resultCode\":\"1\",\"msg\":\"入参数量错误\"}";
					return msg;
				}
				
				//判断sqltype
				if(StringUtils.isEmpty(sqlType) || StringUtils.isBlank(sqlType)) {
					msg = "{\"resultCode\":\"1\",\"msg\":\"sqlType出现错误:"+sqlType+"\"}";
					return msg;
				}
				
				//判断optype
				if(StringUtils.isEmpty(optype) || StringUtils.isBlank(optype)) {
					msg = "{\"resultCode\":\"1\",\"msg\":\"opType出现错误:"+optype+"\"}";
					return msg;
				}
				
				//替换sql 
				//sql = replaceSqlParam(sql,pNumList);
				
				//整里参数
				List paramList = getParamList(pNumList);
				
				if(StringUtils.isNotEmpty(sqlType) && StringUtils.isNotBlank(sqlType)) {
					if(sqlType.equals("sql")) {
						return doExecuteSql(sql,optype,paramList);
					}else if(sqlType.equals("proc")) {
						String returnMsg = (String) doExecuteProc(sql,paramList);
						return "{\"resultCode\":\"proc\",\"msg\":\""+returnMsg+"\"}";
					}else {
						return msg = "{\"resultCode\":\"1\",\"msg\":\"sqlType出现错误:"+sqlType+"\"}";
					}
				}
			}
		}catch(Exception ex) {
			msg = "{\"resultCode\":\"1\",\"msg\":\"sqlType出现错误:"+ex.getMessage()+"\"}";
			ex.printStackTrace();
		}
		
		return msg;
	}
	
	/**
	 * 执行sql类型的 语句包括 insert update delete select
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	private Object doExecuteSql(String sql,String opType,List pList) {
		//List resultList = null;
		if(opType.equals("select")) {
			return crmSmo.doQueryExe(sql,pList);
		}else {
			return crmSmo.doUpdateExe(sql, pList);
		}		
	}
	
	/**
	 * 执行proc类型的sql语句
	 * @param sql
	 * @param pList
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	private Object doExecuteProc(String sql , List pList) {
		return crmSmo.doExecuteExe(sql,pList);
	}
	
	/**
	 * 将sql里的问号 替换成参数
	 * @param sql
	 * @param pNumList
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unused"})
	private String replaceSqlParam(String sql, List pNumList) {
		for(int i=0;i<pNumList.size();i++) {
			Map m = (Map) pNumList.get(i);
			String val = "'"+(String) m.get(i).toString()+"'";
			sql = sql.replaceFirst("\\?".toString(),val );
		}
		
		return sql;
	}
	
	/**
	 * 获得参数列表
	 * @param pNumList
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked"})
	private List getParamList(List pNumList) {
		List paramList = new ArrayList();
		for(int i=0;i<pNumList.size();i++) {
			Map m = (Map) pNumList.get(i);
			String val = (String) m.get("value").toString();
			paramList.add(val);
		}
		
		return paramList;
	}
	
}
