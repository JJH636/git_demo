package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.dao.BillMapper;
import com.dao.ProviderMapper;
import com.pojo.Bill;
import com.pojo.Provider;

public class BillTest {
	private Logger logger = Logger.getLogger(BillTest.class);
	
	@Test
	public void testGetUserList(){
		SqlSession sqlsession = null;
		List<Bill> billList = new ArrayList<Bill>();
		try{
			sqlsession = MyBatisUtil.creaateSqlSession();
			Bill bill = new Bill();
			bill.setProductName("洗");
			bill.setProviderId(13);
			billList = sqlsession.getMapper(BillMapper.class).getBillList(bill);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlsession);
		}
		for(Bill bill: billList){
			logger.debug("  订单编码:"+bill.getBillCode()+"  商品名称:"+bill.getProductName()+"  供应商名称:"+bill.getProductName()
			+"  账单金额:"+bill.getTotalPrice()+"  是否付款:"+bill.getIsPayment()+"  创建时间:"+bill.getCreationDate());
		}
	}
	@Test
	public void testGetUserList1(){
		SqlSession sqlsession = null;
		List<Bill> billList = new ArrayList<Bill>();
		try{
			sqlsession = MyBatisUtil.creaateSqlSession();
			Integer providerId = 2;
			String name = "脉";
			billList = sqlsession.getMapper(BillMapper.class).getBillListByProviderId(name,providerId);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlsession);
		}
		for(Bill bill: billList){
			logger.debug("  订单编码:"+bill.getBillCode()+"  商品名称:"+bill.getProductName()+"  供应商名称:"+bill.getProvider().getProName()
			+"  账单金额:"+bill.getTotalPrice()+"  是否付款:"+bill.getIsPayment()+"  创建时间:"+bill.getCreationDate());
		}
	}
	@Test
	public void testGetUserList2(){
		SqlSession sqlsession = null;
		List<Bill> billList = new ArrayList<Bill>();
		List<Integer> ProviderList = new ArrayList<Integer>();
		ProviderList.add(1);
		ProviderList.add(2);
		try{
			sqlsession = MyBatisUtil.creaateSqlSession();
			billList = sqlsession.getMapper(BillMapper.class).getBillListByProviderId_foreach(ProviderList);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlsession);
		}
		for(Bill bill: billList){
			logger.debug("  订单编码:"+bill.getBillCode()+"  商品名称:"+bill.getProductName()
			+"  账单金额:"+bill.getTotalPrice()+"  是否付款:"+bill.getIsPayment()+"  创建时间:"+bill.getCreationDate());
		}
	}
	@Test
	public void testGetUserList3(){
		SqlSession sqlsession = null;
		List<Bill> billList = new ArrayList<Bill>();
		List<Integer> ProviderList = new ArrayList<Integer>();
		ProviderList.add(1);
		ProviderList.add(2);
		Map<String,Object> billMap = new HashMap<String,Object>();
		billMap.put("rKey", ProviderList);
		try{
			sqlsession = MyBatisUtil.creaateSqlSession();
			billList = sqlsession.getMapper(BillMapper.class).getBillListB_foreach_map(billMap);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlsession);
		}
		for(Bill bill: billList){
			logger.debug("  订单编码:"+bill.getBillCode()+"  商品名称:"+bill.getProductName()
			+"  账单金额:"+bill.getTotalPrice()+"  是否付款:"+bill.getIsPayment()+"  创建时间:"+bill.getCreationDate());
		}
	}
	@Test
	public void testGetBillListFY(){
		SqlSession sqlSession = null;
		List<Bill> BillList = new ArrayList<Bill>();
		try{
			sqlSession = MyBatisUtil.creaateSqlSession();
			String userName="";
			Integer pageSize = 4;
			Integer currentPageNo = 0;
			BillList = sqlSession.getMapper(BillMapper.class).getBillList1(userName, currentPageNo, pageSize);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		logger.debug("userlist.size ----->" +BillList.size());
		for(Bill bill: BillList){
			logger.debug("testGetbillList ======> id:"+bill.getId()
			+" and billcode"+bill.getBillCode()+
			" and billName"+bill.getProductName()+
			" and billRoleName"+bill.getProductDesc()+
			" and creationDate"+bill.getCreationDate());
		}
	}
}
