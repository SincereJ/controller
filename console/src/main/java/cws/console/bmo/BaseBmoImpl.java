package cws.console.bmo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cws.console.repository.BaseRepository;

public class BaseBmoImpl implements BaseBmo{

	@Autowired
	private BaseRepository baseRepository;
	
	public Object findOne() {
		return baseRepository.findOne();
		
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List findList() {
		return (List) baseRepository.findList();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findAllService(Map param) {
		return (List) baseRepository.findAllService(param);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String doServiceEditSave(Map param) {
		return baseRepository.doServiceEditSave(param);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String doSerivceDeleteSave(Map param) {
		return baseRepository.doSerivceDeleteSave(param);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List findAttrValuesByServiceId(Map param) {
		return baseRepository.findAttrValuesByServiceId(param);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public int doServiceAttrValueEditSave(List<HashMap> list) {
		return baseRepository.doServiceAttrValueEditSave(list);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List findAllComponents(Map param) {
		return baseRepository.findAllComponents(param);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List findAllInterfaces(Map param) {
		return baseRepository.findAllInterfaces(param);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String findServiceThirdCode(Map param) {
		return baseRepository.findServiceThirdCode(param);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List checkServiceThirdCode(Map param) {
		return baseRepository.checkServiceThirdCode(param);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public String insertServiceBizInterface(Map param) {
		return baseRepository.insertServiceBizInterface(param);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public String removeInterface(Map param) {
		return baseRepository.removeInterface(param);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public String findServiceComponentIdAndCheck(Map param) {
		return baseRepository.findServiceComponentIdAndCheck(param);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List checkServiceNewComponentId(Map param) {
		return baseRepository.checkServiceNewComponentId(param);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int doServiceComponentAddSave(Map param) {
		return baseRepository.doServiceComponentAddSave(param);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public String doServiceAddSave(Map param) {
		return baseRepository.doServiceAddSave(param);
	}
}
