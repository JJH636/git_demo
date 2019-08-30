package com.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.dao.BillMapper;
import com.dao.ProviderMapper;
import com.dao.RoleMapper;
import com.dao.UserMapper;
import com.pojo.Bill;
import com.pojo.Role;
import com.pojo.User;

public class RoleTest {
	
	private Logger logger = Logger.getLogger(RoleTest.class);
	
	@Test
	public void testAdd(){
		logger.debug("testAdd !==================");
		SqlSession sqlSession = null;
		int count = 0;
		try{
			sqlSession = MyBatisUtil.creaateSqlSession();
			Role r = new Role();
			r.setRoleName("��ͨ��");
			r.setCreatedBy(1);
			r.setRoleCode("SMBMS-12138");
			r.setCreationDate("2019-07-29");
			count = sqlSession.getMapper(RoleMapper.class).add(r);
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
			sqlSession.rollback();
			count = 0;
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		logger.debug("��ӳɹ���"+count);
	}
	@Test
	public void testUpdatePwd(){
		logger.debug("testUpdatePwd !==================");
		SqlSession sqlSession = null;
		String name = "����2";
		Integer is = 1;
		int count = 0;
		Integer id = 4;
		try{
			sqlSession = MyBatisUtil.creaateSqlSession();
			count = sqlSession.getMapper(RoleMapper.class).update(id,name,is);
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
			sqlSession.rollback();
			count = 0;
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		logger.debug("�޸ĳɹ���"+count);
	}
	
	@Test
	public void testDeleteUserById(){
		logger.debug("testDeleteUserById !==================");
		SqlSession sqlSession = null;
		int count = 0;
		Integer delId = 4;
		try{
			sqlSession = MyBatisUtil.creaateSqlSession();
			count = sqlSession.getMapper(RoleMapper.class).deleteRoleById(delId);
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
			sqlSession.rollback();
			count = 0;
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		logger.debug("ɾ���ɹ���"+count);
	}
	@Test
	public void testGetUserList1(){
		SqlSession sqlsession = null;
		List<Role> billList = new ArrayList<Role>();
		try{
			sqlsession = MyBatisUtil.creaateSqlSession();
			Integer providerId = 2;
			String name = "��";
			billList = sqlsession.getMapper(RoleMapper.class).getRoleListByProviderId(name,providerId);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlsession);
		}
		for(Role role: billList){
			logger.debug("  ���:"+role.getRoleCode()+"  ְλ:"+role.getRoleName()+"  �Ƿ񴴽�"+role.getCreatedBy()+"  ����ʱ��:"+role.getCreationDate());
		}
	}
	@Test
	public void testGetRoleListFY(){
		SqlSession sqlSession = null;
		List<Role> roleList = new ArrayList<Role>();
		try{
			sqlSession = MyBatisUtil.creaateSqlSession();
			String userName="";
			Integer role = null;
			Integer pageSize = 2;
			Integer currentPageNo = 0;
			roleList = sqlSession.getMapper(RoleMapper.class).getRoleList(userName, role, currentPageNo, pageSize);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		logger.debug("userlist.size ----->" +roleList.size());
		for(Role role:roleList){
			logger.debug("testGetRoleList ======> id:"+role.getId()
			+" and rolecode"+role.getRoleCode()+
			" and roleName"+role.getRoleName()+
			" and rolecreatDate"+role.getCreationDate());
		}
	}
}
