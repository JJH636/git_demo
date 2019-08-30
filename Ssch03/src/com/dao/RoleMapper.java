package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pojo.Role;

public interface RoleMapper {
	public int add(Role role);
	
	public int update(@Param("id")Integer id, @Param("roleName")String name, @Param("createdBy")Integer is);
	
	public int deleteRoleById(@Param("id")Integer delId);
	
	public List<Role> getRoleListByProviderId(@Param("roleName")String name,@Param("id")Integer providerId);
	
	public List<Role> getRoleList(@Param("roleName")String roleName,@Param("userRole")Integer userRole,@Param("from")Integer currentPageNo,@Param("pageSize")Integer pageSize);
}
