package cws.console.smo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cws.console.bmo.CrmBmo;

public class CrmSmoImpl implements CrmSmo {

	@Autowired
	private CrmBmo crmBmo;
	
	@SuppressWarnings("rawtypes")
	@Override
	public List getSceneList(Map param) {
		return crmBmo.getSceneList(param);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List doQueryExe(String sql,List pList) {
		return crmBmo.doQueryExe(sql,pList);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object doUpdateExe(String sql, List pList) {
		return crmBmo.doUpdateExe(sql, pList);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object doExecuteExe(String sql, List pList) {
		return crmBmo.doExecuteExe(sql, pList);
	}
	

}
