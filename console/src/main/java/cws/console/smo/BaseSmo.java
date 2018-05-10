package cws.console.smo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BaseSmo {
	
	public Object findOne();

	
	@SuppressWarnings("rawtypes")
	public List findList();
	
	@SuppressWarnings("rawtypes")
	public List findAllService(Map param);
	
	@SuppressWarnings("rawtypes")
	public String doServiceEditSave(Map param);
	
	@SuppressWarnings("rawtypes")
	public String doSerivceDeleteSave(Map param);
	
	@SuppressWarnings("rawtypes")
	public List findAttrValuesByServiceId(Map param);
	
	@SuppressWarnings("rawtypes")
	public int doServiceAttrValueEditSave(List<HashMap> list);
	
	@SuppressWarnings("rawtypes")
	public List findAllComponents(Map param);
	
	@SuppressWarnings("rawtypes")
	public List findAllInterfaces(Map param);
	
	@SuppressWarnings("rawtypes")
	public String findServiceThirdCode(Map param);
	
	@SuppressWarnings("rawtypes")
	public List checkServiceThirdCode(Map param);
	
	@SuppressWarnings("rawtypes")
	public String insertServiceBizInterface(Map param);
	
	@SuppressWarnings("rawtypes")
	public String removeInterface(Map param);
	
	@SuppressWarnings("rawtypes")
	public String findServiceComponentIdAndCheck(Map param);
	
	@SuppressWarnings("rawtypes")
	public List checkServiceNewComponentId(Map param);
	
	@SuppressWarnings("rawtypes")
	public int doServiceComponentAddSave(Map param);
	
	@SuppressWarnings("rawtypes")
	public String doServiceAddSave(Map param);
	
	
}
