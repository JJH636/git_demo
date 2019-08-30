package com.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pojo.Bill;

public interface BillMapper {
	public List<Bill> getBillList(Bill bill);
	
	public List<Bill> getBillListByProviderId(@Param("productName")String name,@Param("providerId")Integer providerId);
	
	public List<Bill> getBillListByProviderId_foreach(List<Integer> ProviderList);
	
	public List<Bill> getBillListB_foreach_map(Map<String,Object> billMap);
	
	public List<Bill> getBillList1(@Param("productName")String productName,@Param("from")Integer currentPageNo,@Param("pageSize")Integer pageSize);
}
