package cn.dao.info;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.app_info;

public interface InfoMapper {
	public int PageCount(@Param("softwareName")String softwareName,@Param("status")Integer status,@Param("flatformId")Integer flatformId
			,@Param("categoryLevel1") Integer categoryLevel1,@Param("categoryLevel2") Integer categoryLevel2,@Param("categoryLevel3") Integer categoryLevel3) throws Exception;
	
	public List<app_info> getinfoList(@Param("softwareName")String softwareName,@Param("status")Integer status,@Param("flatformId")Integer flatformId
			,@Param("categoryLevel1") Integer categoryLevel1,@Param("categoryLevel2") Integer categoryLevel2,@Param("categoryLevel3") Integer categoryLevel3,
			@Param("currentPageNo")Integer currentPageNo,@Param("pageSize")Integer pageSize) throws Exception;
	
	public List<app_info> getList() throws Exception;
	
	public int add(app_info info)throws Exception;
	
	public int modify(app_info info)throws Exception;
	
	public app_info getAPK(@Param("APKName")String APKName)throws Exception;
	
	public app_info getid(@Param("id")Integer id)throws Exception;
}
