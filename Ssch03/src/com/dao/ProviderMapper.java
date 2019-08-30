package com.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pojo.Provider;

public interface ProviderMapper {
	public int add(Provider pro);
	
	public int update(@Param("id")Integer id, @Param("proName")String name, @Param("createdBy")Integer is);
	
	public int deleteProById(@Param("id")Integer delId);
	
	public Provider getBillListById(@Param("id")Integer id);
	
	public List<Provider> getProvider_choose(@Param("proCode")String proCode,@Param("proName")String proName,@Param("proContact")String proContact,@Param("creationDate")Date creationDate);
	
	public List<Provider> getProviderList(@Param("proName")String proName,@Param("from")Integer currentPageNo,@Param("pageSize")Integer pageSize);
}
