package cws.console.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcOperations;

import oracle.jdbc.OracleTypes;

public class CrmTemplate extends CrmBaseTemplate implements CrmRepository {

	private JdbcOperations jdbcOperations;

	@Autowired
	public CrmTemplate(JdbcOperations jdbcOperations) {
		super(jdbcOperations);
		this.jdbcOperations = jdbcOperations;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getSceneList(Map param) {
		String sql = "select * from crm.scene as a where 1=1 ";
		String searchVal = (String) param.get("searchVal");
		if (StringUtils.isNotEmpty(searchVal) && StringUtils.isNotBlank(searchVal)) {
			sql += "and (a.name like '%" + searchVal + "%' or a.desc like '%" + searchVal + "%')";
		}

		return jdbcOperations.queryForList(sql);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List doQueryExe(String sql, List pList) {
		return jdbcOperations.queryForList(sql, pList.toArray());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object doUpdateExe(String sql, List pList) {
		return jdbcOperations.update(sql, pList.toArray());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object doExecuteExe(String sql, List pList) {
		int returnMsgIndex = (int) (pList.size() > 0 ? pList.size() + 1 : 1);		
		
		return jdbcOperations.execute(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				String procSql = "{call "+sql+"}";
				CallableStatement cs = con.prepareCall(procSql);
				setInoutParam(cs,pList,returnMsgIndex);
				return cs;
			}
		}, new CallableStatementCallback() {
			@Override
			public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.execute();
				return cs.getString(returnMsgIndex);
			}
		});
	}
	
	/**
	 * 设置出入参数
	 * @param cs
	 * @param pList
	 * @throws SQLException 
	 */
	@SuppressWarnings("rawtypes")
	private void setInoutParam(CallableStatement cs, List pList, int returnMsgIndex) throws SQLException {
		for(int i=0;i<pList.size();i++) {
			cs.setString(i+1, pList.get(i).toString());
		}
		cs.registerOutParameter(returnMsgIndex, OracleTypes.VARCHAR);
	}
}
