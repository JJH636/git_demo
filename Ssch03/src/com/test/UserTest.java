package com.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.dao.UserMapper;
import com.pojo.Address;
import com.pojo.User;
import com.test.UserTest;


public class UserTest {
	
	private Logger logger = Logger.getLogger(UserTest.class);
	
	@Test
	public void testGetUserList(){
		SqlSession sqlsession = null;
		//Map<String,String> userMap = new HashMap<String,String>();
		List<User> userList = new ArrayList<User>();
		try{
			sqlsession = MyBatisUtil.creaateSqlSession();
			String userName = "赵";
			Integer roleId = 3;
			userList = sqlsession.getMapper(UserMapper.class).getUserList(userName,roleId);
			//userMap.put("uName", "赵");
			//userMap.put("uRole", "3");
			//userList = sqlsession.getMapper(UserMapper.class).getUserListByMap(userMap);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlsession);
		}
		for(User user: userList){
			logger.debug("testGetUserList userCode" + user.getUserCode() + "and userName" + user.getUserName() 
			+"and userRole:" + user.getUserRole() + "and userRoleName:" + user.getUserRoleName()+"and age:" + user.getAge()
			+"and address:   " + user.getAddress());
		}
	}
	/*@Test
	public void countAll(){
		int count = 0;
		//mybatis的核心对象    有它才能实现增删改查
		SqlSession sqlsession = null;
		
		try {
			sqlsession = MyBatisUtil.creaateSqlSession();
			//执行想要执行的sql
			count = sqlsession.selectOne("com.dao.UserMapper.count");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			MyBatisUtil.closeSqlSession(sqlsession);
		}
		
		logger.debug("count ---->" + count);
	}
	
	@Test
	public void selectAll(){
		SqlSession sqlsession = null;
		List<User> list = new ArrayList<User>();
		try {
			//获取sqlsession
			sqlsession = MyBatisUtil.creaateSqlSession();
			//执行   第一种方式
			//list = sqlsession.selectList("com.dao.UserMapper.selectAll");
			
			//第二种执行方式    这种是通过xml的反射机制来实现执行方法
			list = sqlsession.getMapper(UserMapper.class).selectAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlsession.close();
		}
		//打印数据
		for (User user : list) {
			logger.debug("selectAll userCode" + user.getUserCode() + "and userName" + user.getUserName());
		}
	}
	
	
	@Test
	public void testAdd(){
		logger.debug("testAdd !==================");
		SqlSession sqlSession = null;
		int count = 0;
		try{
			sqlSession = MyBatisUtil.creaateSqlSession();
			User user = new User();
			user.setUserCode("test001");
			user.setUserName("测试用户001");
			user.setUserPassword("132456");
			Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse("1984-12-12");
			user.setBirthday(birthday);
			user.setGender(1);
			user.setPhone("1324564987899");
			user.setUserRole(1);
			user.setCreatedBy(1);
			user.setCreationDate(new Date());
			count = sqlSession.getMapper(UserMapper.class).add(user);
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
			sqlSession.rollback();
			count = 0;
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		logger.debug("添加成功！"+count);
	}
	
	@Test
	public void testModify(){
		logger.debug("testModify !==================");
		SqlSession sqlSession = null;
		int count = 0;
		try{
			sqlSession = MyBatisUtil.creaateSqlSession();
			User user = new User();
			user.setId(18);
			user.setUserCode("test001");
			user.setUserName("测试用户001");
			user.setUserPassword("132456");
			Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse("1984-12-12");
			user.setBirthday(birthday);
			user.setGender(1);
			user.setPhone("1324564987899");
			user.setUserRole(1);
			user.setCreatedBy(1);
			user.setCreationDate(new Date());
			count = sqlSession.getMapper(UserMapper.class).add(user);
		}catch(Exception e){
			e.printStackTrace();
			sqlSession.rollback();
			count = 0;
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		logger.debug("修改成功！"+count);
	}
	
	@Test
	public void testUpdatePwd(){
		logger.debug("testUpdatePwd !==================");
		SqlSession sqlSession = null;
		String pwd = "77777777";
		int count = 0;
		Integer id = 1;
		try{
			sqlSession = MyBatisUtil.creaateSqlSession();
			count = sqlSession.getMapper(UserMapper.class).updatePwd(id, pwd);
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
			sqlSession.rollback();
			count = 0;
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		logger.debug("修改成功！"+count);
	}
	
	@Test
	public void testDeleteUserById(){
		logger.debug("testDeleteUserById !==================");
		SqlSession sqlSession = null;
		int count = 0;
		Integer delId = 25;
		try{
			sqlSession = MyBatisUtil.creaateSqlSession();
			count = sqlSession.getMapper(UserMapper.class).deleteUserById(delId);
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
			sqlSession.rollback();
			count = 0;
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		logger.debug("删除成功！"+count);
	}
	
	@Test
	public void getUserListByRoleIdTest(){
		SqlSession sqlSession = null;
		List<User> userlist = new ArrayList<User>();
		Integer roleId = 3;
		try{
			sqlSession = MyBatisUtil.creaateSqlSession();
			userlist = sqlSession.getMapper(UserMapper.class).getUserListByRoleId(roleId);
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		logger.debug("getUserListByRoleIdTest  userList.size:"+userlist.size());
		for(User user:userlist){
			logger.debug("userList ====> userName :"+user.getUserName()+", Role :"+user.getRole().getId()
			+"---"+user.getUserRoleName());
		}
	}
	
	@Test
	public void getAddressListByUserIdTest(){
		SqlSession sqlSession = null;
		List<User> userlist = new ArrayList<User>();
		Integer userId = 1;
		try{
			sqlSession = MyBatisUtil.creaateSqlSession();
			userlist = sqlSession.getMapper(UserMapper.class).getAddressListByUserId(userId);
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		for(User user:userlist){
			logger.debug("userList(include:addresslist) =======> userCode:"+user.getUserCode()+", userName: "+user.getUserName());
			for(Address address : user.getAddressList()){
				logger.debug("address -----> id:"+address.getId()+",contact:"+address.getContact()
				+",addressDesc"+address.getAddressDesc()+",tel"+address.getTel()+",prstCode:"+address.getPostCode());
			}
		}
	}
	
	@Test
	public void getAddressListByUserIdTest(){
		SqlSession sqlSession = null;
		User user = null;
		Integer userId = 1;
		try{
			sqlSession = MyBatisUtil.creaateSqlSession();
			user = sqlSession.getMapper(UserMapper.class).getAddressListByUserId(userId);
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		if(null != user){
			logger.debug("userList(include:addresslist) =======> userCode:"+user.getUserCode()+", userName: "+user.getUserName()
			+",<未做映射字段> userpwd:"+user.getUserPassword());
			if(user.getAddressList().size() > 0 ){
				for(Address address : user.getAddressList()){
					logger.debug("address -----> id:"+address.getId()+",contact:"+address.getContact()
					+",addressDesc"+address.getAddressDesc()+",tel"+address.getTel()+",prstCode:"+address.getPostCode()
					+",<未做映射字段> userId: " + address.getUserId());
				}
			}else{
				logger.debug("该用户下无地址列表");
			}
		}else{
			logger.debug("查无此人");
		}
	}
	*/
	@Test
	public void testGetUserByRoleId_foreach_array(){
		SqlSession sqlsession = null;
		List<User> userList = new ArrayList<User>();
		Integer[] roleIds = {2,3};
		try{
			sqlsession = MyBatisUtil.creaateSqlSession();
			userList = sqlsession.getMapper(UserMapper.class).getUserByRoleId_foreach_array(roleIds);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlsession);
		}
		logger.debug("userList.size ----->"+userList.size());
		for(User user : userList){
			logger.debug("user =====> id:"+user.getId()+", userCode:"+user.getUserCode()+", userName:"+user.getUserName()+", userRole:"+user.getUserRole());
		}
	}
	
	@Test
	public void testGetUserByRoleId_foreach_list(){
		SqlSession sqlsession = null;
		List<User> userList = new ArrayList<User>();
		List<Integer> roleList = new ArrayList<Integer>();
		roleList.add(2);
		roleList.add(3);
		try{
			sqlsession = MyBatisUtil.creaateSqlSession();
			userList = sqlsession.getMapper(UserMapper.class).getUserByRoleId_foreach_list(roleList);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlsession);
		}
		logger.debug("userList.size ----->"+userList.size());
		for(User user : userList){
			logger.debug("user =====> id:"+user.getId()+", userCode:"+user.getUserCode()+", userName:"+user.getUserName()+", userRole:"+user.getUserRole());
		}
	}
	@Test
	public void testGetUserListFY(){
		SqlSession sqlSession = null;
		List<User> userList = new ArrayList<User>();
		try{
			sqlSession = MyBatisUtil.creaateSqlSession();
			String userName="";
			Integer role = null;
			Integer pageSize = 5;
			Integer currentPageNo = 0;
			userList = sqlSession.getMapper(UserMapper.class).getUserList1(userName, role, currentPageNo, pageSize);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		logger.debug("userlist.size ----->" +userList.size());
		for(User user:userList){
			logger.debug("testGetUserList ======> id:"+user.getId()
			+" and usercode"+user.getUserCode()+
			" and userName"+user.getUserName()+
			" and userRole"+user.getUserRole()+
			" and userRoleName"+user.getUserName()+
			" and age"+user.getAge()+
			" and phone"+user.getPhone()+
			" and gender"+user.getGender()+
			" and creationDate"+user.getCreationDate());
		}
	}
}
