package cws.console.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementSetter;

public class CrmBaseTemplate implements CrmBaseRepository{

	private JdbcOperations jdbcOperations;

	@Autowired
	public CrmBaseTemplate(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}
	
	@Override
	public int isLogTableExists() {
		String sql = "SELECT table_name FROM information_schema.TABLES WHERE table_name = 'scene_log'";
		return jdbcOperations.queryForList(sql).size();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public int saveLog(Map param) {
		String sql = "insert into crm.scene_log"
				   + "(staff_code,ip,scene_id,scene_name,p_str,create_time,bak_1,bak_2) "
				   + "values ("
				   + "?,?,?,?,?,SYSDATE,?,''"
				   + ")";
		return jdbcOperations.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				String staffCode = (String) param.get("staffCode");
				String ip = (String) param.get("ip");
				String sceneId = (String) param.get("sceneId");
				String sceneName  = String.valueOf(param.get("sceneName"));
				String pStr  = (String)param.get("pStr");
				String bak1  = (String)param.get("bak1");
				
				ps.setString(1, staffCode);  
	            ps.setString(2, ip);  
	            ps.setString(3, sceneId);  
	            ps.setString(4, sceneName);
	            ps.setString(5, pStr);
	            ps.setString(6, bak1);
			}			
		});
	}
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public Object getSceneAuth(Map param) {
		String sql = "select * from crm.scene_auth sa where  sa.scene_id = "
				+ "'" + Integer.parseInt((String) param.get("sceneId")) + "'"
				+ " and sa.staff_code = "
				+ "'" + (String)param.get("staffCode") + "'"
				+ " and sa.ip = "
				+ "'" + (String)param.get("ip") + "'";
		
		//System.out.println(sql);
		
		return jdbcOperations.queryForList(sql);
	}
	
}
