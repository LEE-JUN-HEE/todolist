package kr.or.connect.todo;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource("application.properties")
@ComponentScan
public class AppConfig {
	
	@Value("${spring.datasource.url}")
	String url;
	@Value("${spring.datasource.driver-class-name}")
	String driverClassName;
	@Value("${spring.datasource.username}")
	String username;
	@Value("${spring.datasource.password}")
	String password;
	@Value("${spring.resources.static-locations}")
	String htmlLocation;
	
	@Bean(destroyMethod = "") // Junit 테스트할떄 DBCP 에러관련 해결 방법
	public DataSource dataSource(){
		BasicDataSource dataSource = new BasicDataSource(); 
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	
	@Bean
	public PlatformTransactionManager transactionManger() {
		return new DataSourceTransactionManager(dataSource());
	}
}
