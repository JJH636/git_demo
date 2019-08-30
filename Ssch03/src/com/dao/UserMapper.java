package com.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pojo.User;

public interface UserMapper {
	//查询用户记录数
	public int count();
	
	//查询所有的方法
	public List<User> selectAll();
	
	//public List<User> getUserList(User user);
	
	public List<User> getUserListByMap(Map<String,String> userMap);
	
	public int add(User user);
	
	public int modify(User user);
	
	public int updatePwd(@Param("id")Integer id, @Param("userPassword")String pwd);
	
	public int deleteUserById(@Param("id")Integer delId);
	
	public List<User> getUserListByRoleId(@Param("userRole")Integer roleId);
	
	public User getAddressListByUserId(@Param("id")Integer userId);
	
	public List<User> getUserList(@Param("userName")String userName,@Param("userRole")Integer roleId);
	
	public List<User> getUserByRoleId_foreach_array(Integer[] roleIds);
	
	public List<User> getUserByRoleId_foreach_list(List<Integer> roleList);
	
	public List<User> getUserByConditionMap_foreach_map(Map<String,Object> conditionMap);
	
	public List<User> getUserByRoleId_foreach_map(Map<String,Object> roleMap);
	
	public List<User> getUserList_choose(@Param("userName")String userName,@Param("userRole")String userRole,@Param("userCode")String userCode,@Param("creationDate")Date creationDate);
	
	public List<User> getUserList1(@Param("userName")String userName,@Param("userRole")Integer userRole,@Param("from")Integer currentPageNo,@Param("pageSize")Integer pageSize);
}
