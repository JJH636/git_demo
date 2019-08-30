package com.test;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static SqlSessionFactory factory;
	
	//在项目已加载的时候，就执行下面的代码
	static{
		try {
			//读取核心配置文件，返回sqlsession
			InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
			//sqlsession的工厂类
			factory = new SqlSessionFactoryBuilder().build(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//返回sqlsession
	public static SqlSession creaateSqlSession(){
		return factory.openSession(false);		//如果为true就是自动提交事务
	}
	//关闭sqlsession
	public static void closeSqlSession(SqlSession sqlSession){
		if(sqlSession != null){
			sqlSession.close();
		}
	}
}
