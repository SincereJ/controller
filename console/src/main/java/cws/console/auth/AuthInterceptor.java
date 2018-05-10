package cws.console.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cws.console.repository.CrmRepository;

public class AuthInterceptor implements HandlerInterceptor {

	@Autowired
	private CrmRepository crmRepository;

	/**
	 * 前台页面显示 alert 内容
	 * 
	 * @param response
	 * @param msg
	 * @throws IOException
	 */
	private void responseWriter(HttpServletResponse response, String msg) throws IOException {
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
	    PrintWriter out = response.getWriter();
		out.println("{\"resultCode\":\"1\",\"msg\":\""+msg+"\"}");
	}

	/**
	 * 获得临时测试用户的信息 正式环境 请删除
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map getDemoUser() {
		Map user = new HashMap();
		user.put("userId", "ceshi");
		user.put("staffCode", "ceshi");
		user.put("userName", "ceshi");
		user.put("ip", "127.0.0.1");

		return user;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		Map user = getDemoUser();
		Map authParam = null;

		System.out.println("AuthInterceptor");
		try {
			authParam = new HashMap();
			String sceneId = request.getParameter("scene_id");

			if (StringUtils.isNotBlank(sceneId) && StringUtils.isNotEmpty(sceneId)) {
				authParam.put("sceneId", sceneId);
			} else {
				responseWriter(response, "场景编号为空");
				return false;
			}

			String staffCode = (String) user.get("staffCode");
			if (StringUtils.isNotBlank(staffCode) && StringUtils.isNotEmpty(staffCode)) {
				authParam.put("staffCode", staffCode);
			} else {
				responseWriter(response, "员工编号为空");
				return false;
			}

			String ip = (String) user.get("ip");
			if (StringUtils.isNotBlank(ip) && StringUtils.isNotEmpty(ip)) {
				authParam.put("ip", ip);
			} else {
				responseWriter(response, "登录员工ip地址为空");
				return false;
			}

			List resultAuthList = (List) crmRepository.getSceneAuth(authParam);
			if (resultAuthList == null || resultAuthList.size() == 0) {
				responseWriter(response, "该用户没有在安全控制表中记录");
				return false;
			}

			if (resultAuthList.size() > 1) {
				responseWriter(response, "该用户没有在安全控制表中记录重复，请检查去重复");
				return false;
			}

			Map resultAuth = (Map) resultAuthList.get(0);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startDate = sdf.parse(resultAuth.get("start_date").toString());
			Date endDate = sdf.parse(resultAuth.get("end_date").toString());
			Date nowDate = new Date();
			
			if(startDate.before(nowDate) && nowDate.before(endDate)) {
				
			}else {
				responseWriter(response, "该用户不在有效期内");
				return false;
			}			

		} catch (Exception ex) {
			ex.printStackTrace();
			responseWriter(response, "error: " + ex.getMessage());
			return false;
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion");
	}

	public byte[] getRequestPostBytes(HttpServletRequest request) throws IOException {
		int contentLength = request.getContentLength();
		if (contentLength < 0) {
			return null;
		}
		byte buffer[] = new byte[contentLength];
		for (int i = 0; i < contentLength;) {

			int readlen = request.getInputStream().read(buffer, i, contentLength - i);
			if (readlen == -1) {
				break;
			}
			i += readlen;
		}
		return buffer;
	}

	public String getRequestPostStr(HttpServletRequest request) throws IOException {
		byte buffer[] = getRequestPostBytes(request);
		String charEncoding = request.getCharacterEncoding();
		if (charEncoding == null) {
			charEncoding = "UTF-8";
		}
		return new String(buffer, charEncoding);
	}

}
