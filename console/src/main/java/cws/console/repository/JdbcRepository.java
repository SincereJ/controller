package cws.console.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;

@Repository
public class JdbcRepository implements BaseRepository{
	
	@SuppressWarnings("unused")
	private JdbcOperations jdbcOperations;

	@Autowired
	public JdbcRepository(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}
	
	@Override
	public Object findOne() {
		return jdbcOperations.queryForMap("select * from csb.service cs where cs.service_id = '1296'");
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List findList() {
		return jdbcOperations.queryForList("select cs.service_id as a,cs.service_cn_name as b,cs.service_code as c,cs.create_date as d from csb.service cs");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findAllService(Map param) {
		String sql = "select cs.service_id serviceId , "
				+ "cs.biz_function_id bizFunctionId , "
				+ "cs.service_en_name serviceEnName, "
				+ "cs.service_cn_name serviceCnName "
				+ "from csb.service cs where 1=1 ";
		
		if(param != null) {
			String searchVal = (String) param.get("searchVal");
			if(StringUtils.isNotBlank(searchVal) && StringUtils.isNotEmpty(searchVal)) {
				sql += "and ("
						//+ "cs.service_id='" + searchVal + "' or "
						+ "cs.service_en_name like '%" + searchVal + "%' or "
						+ "cs.service_cn_name like '%" + searchVal + "%'"
						+ ")";
			}
		}
		
		sql+= "order by cs.create_date desc";
		return jdbcOperations.queryForList(sql);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String doServiceEditSave(Map param) {
		String sql = "{call CTG_LOGS_TABLE_CREATE.UPDATA_SERVICE(?,?,?,?)}";
		String result = (String) jdbcOperations.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				CallableStatement cs = con.prepareCall(sql); 
				cs.setString(1, (String) param.get("SERVICEID"));
				cs.setString(2, (String) param.get("SERVICEENNAME"));
				cs.setString(3, (String) param.get("SERVICECNNAME"));
				cs.registerOutParameter(4,OracleTypes.VARCHAR);
				return cs;
			}
		},new CallableStatementCallback() {
			@Override
			public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.execute();
				return cs.getObject(4);
			}
			
		});
		
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String doSerivceDeleteSave(Map param) {
		String sql = "{call CTG_LOGS_TABLE_CREATE.DELETE_SERVICE(?,?)}";
		String result = (String) jdbcOperations.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				CallableStatement cs = con.prepareCall(sql); 
				cs.setString(1, (String) param.get("SERVICEID"));
				cs.registerOutParameter(2,OracleTypes.VARCHAR);
				return cs;
			}
		},new CallableStatementCallback() {
			@Override
			public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.execute();
				return cs.getObject(2);
			}
			
		});
		
		return result;
	}
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public List findAttrValuesByServiceId(Map param) {
		String serviceId = String.valueOf(param.get("SERVICEID"));
		String sql = "";
		sql += "select "
			 + "ar.endpoint_attr_value_id, ar.endpoint_id,ar.endpoint_spec_attr_id,ar.attr_value "
			 + "from csb.endpoint_attr_value ar "
			 + "where exists( "
			 + "select 1 from csb.endpoint ep where ep.endpoint_id = ar.endpoint_id "
			 + "and ep.service_id = '"+serviceId+"' "
			 + ")";

		return jdbcOperations.queryForList(sql);
	}
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public int doServiceAttrValueEditSave(List<HashMap> list) {
		String sql = "update csb.endpoint_attr_value ar "
				   + "set ar.attr_value = ? "
				   + "where ar.endpoint_attr_value_id = ?";
		
		jdbcOperations.batchUpdate(sql, new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				HashMap map  = list.get(i);
				String attrId = (String.valueOf(map.get("ENDPOINT_ATTR_VALUE_ID")));
				String attrVal = (String) map.get("ATTR_VALUE") ;
				
			    ps.setString(1, attrVal);  
                ps.setString(2, attrId);  
			}

			@Override
			public int getBatchSize() {
				return list.size();
			}
		});
		
		return 4;
	}
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public List findAllComponents(Map param) {
		String sql = "select cp.component_id,cp.name from ctg.component cp where 1=1";
		return jdbcOperations.queryForList(sql);
	}
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public List findAllInterfaces(Map param) {
		String sql = "select bi.biz_interface_id,bi.code,bi.name from ctg.biz_interface bi where bi.service_id = ? order by bi.code desc";
		String serviceId = String.valueOf(param.get("SERVICEID"));
		return jdbcOperations.queryForList(sql, serviceId);
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public String findServiceThirdCode(Map param) {
		String sql = "select ddc.nextCode from ( "
				   + "select to_number(doc.subcode)+1 ,doc.code, to_number(doc.lst) + 1, lpad(to_number(doc.lst) + 1,9,'0') as nextCode from ( "
				   + "select "
				   + "substr(bi.code,length(bi.code)-2) as subcode, "
				   + "substr(bi.code,instr(bi.code,'F') +1) as lst, "
				   + "bi.code "
				   + "from ctg.biz_interface bi where 1=1 and instr(bi.code,'F') > 0 "
				   + ") doc "
				   + "where doc.subcode != '555' "
				   + "order by doc.subcode desc ) ddc "
				   + "where rownum < 2";
		
		return jdbcOperations.queryForObject(sql, String.class);
	}
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public List checkServiceThirdCode(Map param) {
		String thirdCode = (String) param.get("thirdCode");
		String sql = "select '1' from ctg.biz_interface bi where instr(bi.code,?) > 0" ;
		return jdbcOperations.queryForList(sql, thirdCode);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String insertServiceBizInterface(Map param) {
		String sql = "{call CTG_LOGS_TABLE_CREATE.INSERT_BIZ_INTERFACE(?,?,?,?,?,?)}";
		String result = (String) jdbcOperations.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				CallableStatement cs = con.prepareCall(sql); 
				cs.setString(1, (String) param.get("serviceId"));
				cs.setString(2, (String) param.get("secondCode"));
				cs.setString(3, (String) param.get("thirdCode"));
				cs.setString(4, (String) param.get("contractVersion"));
				cs.setString(5, (String) param.get("interfaceF"));
				cs.registerOutParameter(6,OracleTypes.VARCHAR);
				return cs;
			}
		},new CallableStatementCallback() {
			@Override
			public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.execute();
				return cs.getObject(6);
			}
			
		});
		
		return result;
	}
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public String removeInterface(Map param) {
		String sql = "";
		String bizInterfaceId = (String) param.get("BIZ_INTERFACE_ID");
		int returnSize = jdbcOperations.update(sql, bizInterfaceId);
		
		return returnSize + "";
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public String findServiceComponentIdAndCheck(Map param) {
		String sql = "select to_char(to_number(max(cp.component_id) + 1)) from ctg.component cp";
		String newComponentId = jdbcOperations.queryForObject(sql,String.class);
		return newComponentId;
	}
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public List checkServiceNewComponentId(Map param) {
		String newComponentId = (String) param.get("newComponentId");
		String sql = "select '1' from ctg.component cp where cp.component_id = ?";
		return jdbcOperations.queryForList(sql, newComponentId);
	}
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public int doServiceComponentAddSave(Map param) {
		String sql = "insert into ctg.component "
				   + "select ?,609001,?,?,1,'','A',sysdate,'N',sysdate,'Y','Y','N','123','N','Y',10000,'北京' "
				   + "from dual ";
		return jdbcOperations.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				String compId = (String) param.get("componentId");
				String compName = (String) param.get("componentName");
				ps.setString(1, compId);  
	            ps.setString(2, compId);  
	            ps.setString(3, compName);  
			}			
		});
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String doServiceAddSave(Map param) {
		String sql = "{call CTG_LOGS_TABLE_CREATE.CREATE_SERVICE(?,?,?,?,?,?,?,?,?,?,?)}";
		String result = (String) jdbcOperations.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				CallableStatement cs = con.prepareCall(sql); 
				cs.setString(1, (String) param.get("serviceId"));
				cs.setString(2, (String) param.get("serviceId"));
				cs.setString(3, (String) param.get("serviceName"));
				cs.setString(4, (String) param.get("serviceUrl"));
				cs.setString(5, (String) param.get("serviceReq"));
				cs.setString(6, (String) param.get("serviceTmo"));
				cs.setString(7, (String) param.get("serviceTwoCode"));
				cs.setString(8, (String) param.get("servicecontractVersion"));
				cs.setString(9, (String) param.get("serviceDfFromCompCode"));
				cs.setString(10, (String) param.get("serviceDfToCompCode"));
				cs.registerOutParameter(11,OracleTypes.VARCHAR);
				return cs;
			}
		},new CallableStatementCallback() {
			@Override
			public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.execute();
				return cs.getObject(11);
			}
			
		});
		
		return result;
	}
	
}
