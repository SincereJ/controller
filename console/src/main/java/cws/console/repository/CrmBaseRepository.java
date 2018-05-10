package cws.console.repository;

import java.util.Map;

public interface CrmBaseRepository {

	
	public int isLogTableExists();
	
	@SuppressWarnings("rawtypes")
	public int saveLog(Map param);
	
	@SuppressWarnings("rawtypes")
	public Object getSceneAuth(Map param);
	
	
}
