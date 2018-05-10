package cws.console.bmo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cws.console.repository.CrmRepository;

public class CrmBmoImpl implements CrmBmo {

	@Autowired
	private CrmRepository crmRepository;
	
	
	@SuppressWarnings("rawtypes")
	public List getSceneList(Map param) {
		return crmRepository.getSceneList(param);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List doQueryExe(String sql,List pList) {
		return crmRepository.doQueryExe(sql,pList);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object doUpdateExe(String sql, List pList) {
		return crmRepository.doUpdateExe(sql, pList);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object doExecuteExe(String sql, List pList) {
		return crmRepository.doExecuteExe(sql, pList);
	}
	
}
