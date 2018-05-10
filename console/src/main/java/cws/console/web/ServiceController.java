package cws.console.web;

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

import cws.console.smo.BaseSmo;

@Controller
@RequestMapping("/service")
public class ServiceController {
	
	@Autowired
	private BaseSmo baseSmo;
	
	
	/**
	 * 查询所有接口
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value="/findAllService",method=RequestMethod.POST)
	public List findAllService(@RequestBody HashMap map) {
		System.out.println("findAllService");		
		List rest = baseSmo.findAllService(map);
		return rest;
	}
	
	/**
	 * 修改接口
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value="/doServiceEditSave",method=RequestMethod.POST)
	public String doServiceEditSave(@RequestBody HashMap map) {
		System.out.println("doServiceEditSave");
		String message = "更新失败";
		
		if(map != null) {
			String result = baseSmo.doServiceEditSave(map);
			if(StringUtils.isNotEmpty(result) && StringUtils.isNotBlank(result) && "0".equals(result)) {
				message = "更新成功";
			}
		}
		
		return "[{\"message\":\""+message+"\"}]";
	}
	
	/**
	 * 删除接口
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value="/doSerivceDeleteSave",method=RequestMethod.POST)
	public String doSerivceDeleteSave(@RequestBody HashMap map) {
		System.out.println("doSerivceDeleteSave");
		String message = "删除失败";
		
		if(map != null) {
			String result = baseSmo.doSerivceDeleteSave(map);
			if(StringUtils.isNotEmpty(result) && StringUtils.isNotBlank(result) && "0".equals(result)) {
				message = "删除成功";
			}
		}
		
		return "[{\"message\":\""+message+"\"}]";
	}
	
	/**
	 * 查询接口详细配置属性
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value="/goEditServiceAttrPageAndGetAttrValues",method=RequestMethod.POST)
	public List goEditServiceAttrPageAndGetAttrValues(@RequestBody HashMap map) {
		System.out.println("goEditServiceAttrPageAndGetAttrValues");	
		List resultList = null;
		if(map != null) {
			resultList = baseSmo.findAttrValuesByServiceId(map);
		}
		
		return resultList;
	}
	
	/**
	 * 查询接口详细配置属性
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value="/doServiceAttrValueEditSave",method=RequestMethod.POST)
	public String doServiceAttrValueEditSave(@RequestBody HashMap<String,List> map) {
		System.out.println("doServiceAttrValueEditSave");
		String message = "更新成功";
		
		if(map != null) {
			List lst = map.get("seletedRowAttrValues");
			int result = baseSmo.doServiceAttrValueEditSave(lst);
			if(result == 0) {
				message = "更新失败";
			}
		}
		
		return "[{\"message\":\""+message+"\"}]";	
	}
	
	/**
	 * 进入权限页面 现实平台列表 和 权限列表
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value="/goEditServiceInterfacePageAndGetInterfaceAndCompmentsValues",method=RequestMethod.POST)
	public Map goEditServiceInterfacePageAndGetInterfaceAndCompmentsValues(@RequestBody HashMap map) {
		System.out.println("goEditServiceInterfacePageAndGetInterfaceAndCompmentsValues");
		
		Map returnMap = new HashMap();
		
		List compments = baseSmo.findAllComponents(map);
		List interfaces = baseSmo.findAllInterfaces(map);
		
		returnMap.put("compments", compments);
		returnMap.put("interfaces", interfaces);
		
		return returnMap;
	}
	
	/**
	 * 返回三级能力编码
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@ResponseBody
	@RequestMapping(value="/findServiceThirdCode",method=RequestMethod.POST)	
	public String findServiceThirdCode(@RequestBody HashMap map) {
		System.out.println("findServiceThirdCode");
		
		String thirdCode = baseSmo.findServiceThirdCode(map);
		return "{\"thirdCode\":\""+thirdCode+"\"}";
	}
	
	/**
	 * 检测三级能力编码是否存在
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@ResponseBody
	@RequestMapping(value="/checkServiceThirdCode",method=RequestMethod.POST)	
	public int checkServiceThirdCode(@RequestBody HashMap map) {
		System.out.println("checkServiceThirdCode");
		
		List rst = baseSmo.checkServiceThirdCode(map);
		return rst.size();
	}
	
	/**
	 * 插入新平台权限
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@ResponseBody
	@RequestMapping(value="/insertServiceBizInterface",method=RequestMethod.POST)	
	public String insertServiceBizInterface(@RequestBody HashMap map) {
		System.out.println("insertServiceBizInterface");
		
		String result = baseSmo.insertServiceBizInterface(map);
		String[] resArr = null;
		
		if(StringUtils.isNoneEmpty(result) && StringUtils.isNoneBlank(result)) {
			if(result.indexOf(",") > 0) {
				resArr = result.split(",");
			}
		}
		
		return "{\"BIZ_INTERFACE_ID\":\""+resArr[0]+"\"\"CODE\":\""+resArr[1]+"\",\"NAME\":\""+resArr[2]+"\"}";
	}
	
	/**
	 * 移除平台权限
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@ResponseBody
	@RequestMapping(value="/removeInterface",method=RequestMethod.POST)	
	public String removeInterface(@RequestBody HashMap map) {
		
		String resultSize = baseSmo.removeInterface(map);
		
		return "{\"resultSize\":\""+resultSize+"\"}";
	}
	
	/**
	 * 返回新平台编码并且验证
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value="/findServiceComponentIdAndCheck",method=RequestMethod.POST)	
	public String findServiceComponentIdAndCheck(@RequestBody HashMap map) {
		String msg = "";
		
		String newComponentId = baseSmo.findServiceComponentIdAndCheck(map);
		
		map.put("newComponentId", newComponentId);
		List rest = baseSmo.checkServiceNewComponentId(map);
		
		if(rest != null && rest.size() == 0) {
			msg = "{\"resultCode\":\"0\",\"msg\":\""+newComponentId+"\"}";
		}else {
			msg = "{\"resultCode\":\"1\",\"msg\":\"此次查询新平台号出现错误，请手动查询\"}";
		}
		
		return msg;
	}
	
	/**
	 * 添加新平台
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@ResponseBody
	@RequestMapping(value="/doServiceComponentAddSave",method=RequestMethod.POST)	
	public String doServiceComponentAddSave(@RequestBody HashMap map) {
		String msg = "{\"resultCode\":\"0\",\"msg\":\"添加新平台成功\"}";
		
		int returnSize = baseSmo.doServiceComponentAddSave(map);
		if(returnSize <= 0) {
			msg = "{\"resultCode\":\"1\",\"msg\":\"添加新平台出错\"}";
		}
		
		return msg;
	}
	
	/**
	 * 添加新接口默认23 接入 
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@ResponseBody
	@RequestMapping(value="/doServiceAddSave",method=RequestMethod.POST)	
	public String doServiceAddSave(@RequestBody HashMap map) {
		String msg = "{\"resultCode\":\"0\",\"msg\":\"添加新接口成功\"}";;
		String returnMsg = baseSmo.doServiceAddSave(map);
		if(StringUtils.isNotEmpty(returnMsg) && !returnMsg.equals("0")) {
			msg = "{\"resultCode\":\"1\",\"msg\":\"添加新接口出错\"}";
		}
		return msg;
	}
	

}
