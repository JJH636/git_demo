package com.test;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static SqlSessionFactory factory;
	
	//����Ŀ�Ѽ��ص�ʱ�򣬾�ִ������Ĵ���
	static{
		try {
			//��ȡ���������ļ�������sqlsession
			InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
			//sqlsession�Ĺ�����
			factory = new SqlSessionFactoryBuilder().build(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//����sqlsession
	public static SqlSession creaateSqlSession(){
		return factory.openSession(false);		//���Ϊtrue�����Զ��ύ����
	}
	//�ر�sqlsession
	public static void closeSqlSession(SqlSession sqlSession){
		if(sqlSession != null){
			sqlSession.close();
		}
	}
}
