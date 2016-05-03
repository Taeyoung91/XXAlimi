package com.anonyblah.xxalimi.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.anonyblah.xxalimi.vo.User;

@Repository
//@MapperScan(value="com.anonyblah.xxalimi.dao")
//@Configuration
//@Service
public class MySqlUserDao implements UserDao {
	
//	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
		this.sqlSessionFactory = sqlSessionFactory;
	}

//	@Override
	public /*int*/void insert(User user) throws Exception{
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		try {
//			System.out.println("insert" + user.getEmail());
//		
//			int count = sqlSession.insert("com.anonyblah.xxalimi.dao.UserDao.insert", user);
//			sqlSession.commit();
//			
//			return count;
//		}  finally {
//			sqlSession.close();
//		}
		
	}

	@Override
	public int delete(int no) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}
