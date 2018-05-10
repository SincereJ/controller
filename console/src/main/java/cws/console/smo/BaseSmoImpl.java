package cws.console.smo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cws.console.bmo.BaseBmo;

public class BaseSmoImpl implements BaseSmo {

	@Autowired
	private BaseBmo baseBmo;
	
	@Override
	public Object findOne() {
		return baseBmo.findOne();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List findList() {
		return baseBmo.findList();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findAllService(Map param) {
		return baseBmo.findAllService(param);
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	@Override
	public String doServiceEditSave(Map param) {
		return baseBmo.doServiceEditSave(param);
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	@Override
	public String doSerivceDeleteSave(Map param) {
		return baseBmo.doSerivceDeleteSave(param);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List findAttrValuesByServiceId(Map param) {
		return baseBmo.findAttrValuesByServiceId(param);
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	@Override
	public int doServiceAttrValueEditSave(List<HashMap> list) {
		return baseBmo.doServiceAttrValueEditSave(list);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List findAllComponents(Map param) {
		return baseBmo.findAllComponents(param);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List findAllInterfaces(Map param) {
		return baseBmo.findAllInterfaces(param);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public String findServiceThirdCode(Map param) {
		return baseBmo.findServiceThirdCode(param);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List checkServiceThirdCode(Map param) {
		return baseBmo.checkServiceThirdCode(param);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public String insertServiceBizInterface(Map param) {
		return baseBmo.insertServiceBizInterface(param);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public String removeInterface(Map param) {
		return baseBmo.removeInterface(param);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public String findServiceComponentIdAndCheck(Map param) {
		return baseBmo.findServiceComponentIdAndCheck(param);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List checkServiceNewComponentId(Map param) {
		return baseBmo.checkServiceNewComponentId(param);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public int doServiceComponentAddSave(Map param) {		
		return baseBmo.doServiceComponentAddSave(param);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public String doServiceAddSave(Map param) {
		return baseBmo.doServiceAddSave(param);
	}
}
