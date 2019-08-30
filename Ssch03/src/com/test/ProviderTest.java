package com.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.dao.ProviderMapper;
import com.dao.UserMapper;
import com.pojo.Bill;
import com.pojo.Provider;
import com.pojo.User;

public class ProviderTest {
	private Logger logger = Logger.getLogger(ProviderTest.class);
	
	
	@Test
	public void testAdd(){
		logger.debug("testAdd !==================");
		SqlSession sqlSession = null;
		int count = 0;
		try{
			sqlSession = MyBatisUtil.creaateSqlSession();
			Provider pr = new Provider();
			pr.setProCode("JJ_GY12138");
			pr.setProName("郴州浩克家具");
			pr.setProDesc("初期合作伙伴");
			pr.setProContact("刘东强");
			pr.setProPhone("123456789");
			pr.setProAddress("石榴湾小区");
			pr.setProFax("020-02454631");
			count = sqlSession.getMapper(ProviderMapper.class).add(pr);
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
	public void testUpdatePwd(){
		logger.debug("testUpdatePwd !==================");
		SqlSession sqlSession = null;
		String name = "郴州邵东总部";
		Integer is = 1;
		int count = 0;
		Integer id = 17;
		try{
			sqlSession = MyBatisUtil.creaateSqlSession();
			count = sqlSession.getMapper(ProviderMapper.class).update(id, name,is);
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
		Integer delId = 16;
		try{
			sqlSession = MyBatisUtil.creaateSqlSession();
			count = sqlSession.getMapper(ProviderMapper.class).deleteProById(delId);
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
	public void getAddressListByUserIdTest(){
		SqlSession sqlSession = null;
		Provider pro = null;
		Integer userId = 2;
		try{
			sqlSession = MyBatisUtil.creaateSqlSession();
			pro = sqlSession.getMapper(ProviderMapper.class).getBillListById(userId);
			sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		if(null != pro){
			logger.debug("  供应商id"+pro.getId()+", 编码: "+pro.getProCode()
			+",  名称:"+pro.getProName()+ ",  联系人:"+pro.getProContact()+",  联系电话:"+pro.getProPhone());
			if(pro.getBillList().size() > 0 ){
				for(Bill bill : pro.getBillList()){
					logger.debug("  订单编码:"+bill.getBillCode()+", 商品名称:"+bill.getProductName()
					+", 订单金额:"+bill.getTotalPrice()+",  是否付款:"+bill.getIsPayment());
				}
			}else{
				logger.debug("该用户下无地址列表");
			}
		}else{
			logger.debug("查无此人");
		}
	}
	@Test
	public void getchoose(){
		SqlSession sqlsession = null;
		List<Provider> rList = new ArrayList<Provider>();
		try{
			String proCode = "";
			String proName = "";
			String proContact = "";
			Date creationDate = new SimpleDateFormat("yyyy-MM-dd").parse("2016-10-10");
			sqlsession = MyBatisUtil.creaateSqlSession();
			rList = sqlsession.getMapper(ProviderMapper.class).getProvider_choose(proCode, proName, proContact, creationDate);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlsession);
		}
		logger.debug("rList.size ----->"+rList.size());
		for(Provider provider : rList){
			logger.debug("provider =====> id:"+provider.getId()+", provCode:"+provider.getProCode()+", userName:"+provider.getProName()+", userRole:"+provider.getCreationDate());
		}
	}
	@Test
	public void testGetProviderListFY(){
		SqlSession sqlSession = null;
		List<Provider> rList = new ArrayList<Provider>();
		try{
			sqlSession = MyBatisUtil.creaateSqlSession();
			String userName="";
			Integer pageSize = 2;
			Integer currentPageNo = 0;
			rList = sqlSession.getMapper(ProviderMapper.class).getProviderList(userName,currentPageNo, pageSize);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		logger.debug("userlist.size ----->" +rList.size());
		for(Provider pro: rList){
			logger.debug("testGetUserList ======> id:"+pro.getId()
			+" and usercode"+pro.getProCode()+
			" and userName"+pro.getProName()+
			" and userRoleName"+pro.getProName()+
			" and creationDate"+pro.getCreationDate());
		}
	}
}
