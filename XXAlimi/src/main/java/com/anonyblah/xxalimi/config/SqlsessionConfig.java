package com.anonyblah.xxalimi.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@MapperScan(value={"com.anonyblah.xxalimi.dao"})
@Configuration
public class SqlsessionConfig {
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);

//		Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath*:spms/dao/*Dao.xml");
		
		return sqlSessionFactoryBean.getObject();
	}

//	@Bean	트랜잭션을 자동으로 할 수 있도록 해주는 Bean
//	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
//
//		return new SqlSessionTemplate(sqlSessionFactory);
//
//	}
}
