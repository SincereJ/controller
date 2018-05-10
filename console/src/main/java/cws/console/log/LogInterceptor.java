package cws.console.log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cws.console.repository.CrmRepository;

@Aspect
@Component
public class LogInterceptor {
	
	@SuppressWarnings("unused")
	private HttpServletRequest request = null;
	
	@Autowired
	private CrmRepository crmRepository;

	@Pointcut("execution(* *.doExecurte(..))")      
    public void controllerAspect() {  
          
    }  

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@AfterReturning(pointcut="controllerAspect()",returning="result")
    public void after(JoinPoint joinPoint,Object result){  
		
		System.out.println("LogInterceptor.After");
		
		if(isLogTableExists()) {
			request = getHttpServletRequest();
			Map logParam = new HashMap();
			
			try {
				Object[] params = joinPoint.getArgs();
				Map param = params == null ? null : (Map)params[0];
				
				if(param != null) {
					List pNumList = (List) param.get("pNumList");
					Map formScene = (Map) param.get("formScene");
					
					if(formScene != null) {
						logParam.put("pStr", getParamStr(pNumList));
					}
					
					String sceneName = (String)formScene.get("op_type") + "|" + (String)formScene.get("name") + "|" + (String)formScene.get("desc");
					
					logParam.put("staffCode", "");
					logParam.put("sceneName", sceneName);
					logParam.put("ip", "");
					logParam.put("sceneId", (String.valueOf(formScene.get("id"))));
					logParam.put("bak1", "");
					
					crmRepository.saveLog(logParam);
				}
				
			}catch(Exception ex) {
				logParam =  logParam(logParam);
				logParam.put("bak1", ex.getMessage());
				
				ex.printStackTrace();
				crmRepository.saveLog(logParam);
			}	
		}
    } 
	
	/**
	 * 判断日志表是否存在
	 * @return
	 */
	private boolean isLogTableExists() {
		return crmRepository.isLogTableExists() > 0;
	}
	
	/**
	 * 置空日志请求参数map
	 * @param logParam
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map logParam(Map logParam) {
		logParam.put("opName", "");
		logParam.put("opIp", "");
		logParam.put("sceneId", "");
		
		return logParam;
	}
	
	/**
	 * 获得执行类型 insert select delete update 
	 * @param formScene
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	private String getOptype(Map formScene) {
		return (String)formScene.get("op_type") == null  ? "" : (String)formScene.get("op_type");
	}
	
	/**
	 * 将请求list 转换为字符串
	 * @param pNumList
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private String getParamStr(List<Map> pNumList) {
		StringBuffer sb = new StringBuffer("");
		
		for(Map m : pNumList) {
			sb.append(m.get("value") == null ? "" : m.get("value")+ ",");
		}
				
		return sb.toString();
	}
	
	/**
	 * 获得httpServletRequest
	 * @return
	 */
	private HttpServletRequest getHttpServletRequest() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();  
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;  
        HttpServletRequest request = sra.getRequest();
        return request;
	}
	
}
