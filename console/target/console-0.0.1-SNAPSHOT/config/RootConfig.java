package com.linkage.bss.crm.ws.console.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.linkage.bss.crm.ws.console.bmo.BaseBmo;
import com.linkage.bss.crm.ws.console.bmo.BaseBmoImpl;
import com.linkage.bss.crm.ws.console.repository.BaseRepository;
import com.linkage.bss.crm.ws.console.repository.JdbcRepository;
import com.linkage.bss.crm.ws.console.smo.BaseSmo;
import com.linkage.bss.crm.ws.console.smo.BaseSmoImpl;

@Configuration
/*@ComponentScan(basePackageClasses = RootConfig.class,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})*/
public class RootConfig {
	
	@Bean
	public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer() {
		PropertyPlaceholderConfigurer pp = new PropertyPlaceholderConfigurer();
		pp.setPlaceholderPrefix("classpath:");
		pp.setPlaceholderSuffix(".properties");
		System.out.println("prop");
		return pp;
	}
	
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@172.19.17.107:1521:csbtest");
		ds.setUsername("csb");
		ds.setPassword("csb");
		//ds.setUsername("crm_app");
		//ds.setPassword("CRM_APP_101272");
		System.out.println("ds");
		return ds;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean
	public BaseRepository baseRepository(JdbcTemplate jdbcTemplate) {
		return new JdbcRepository(jdbcTemplate);
	}
	
	@Bean
	public BaseBmo baseBmo() {
		return new BaseBmoImpl();
	}
	
	@Bean
	public BaseSmo baseSmo() {
		return new BaseSmoImpl();
	}
	
}