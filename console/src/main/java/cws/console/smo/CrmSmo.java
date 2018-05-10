package cws.console.smo;

import java.util.List;
import java.util.Map;

public interface CrmSmo {

	
	@SuppressWarnings("rawtypes")
	public List getSceneList(Map param);
	
	@SuppressWarnings("rawtypes")
	public List doQueryExe(String sql,List pList);
	
	@SuppressWarnings("rawtypes")
	public Object doUpdateExe(String sql,List pList);
	
	@SuppressWarnings("rawtypes")
	public Object doExecuteExe(String sql, List pList);
}
