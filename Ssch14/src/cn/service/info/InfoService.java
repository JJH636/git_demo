package cn.service.info;

import java.util.List;

import cn.pojo.app_info;

public interface InfoService {
	public int PageCount(String softwareName,Integer status,Integer flatformId
			, Integer categoryLevel1,Integer categoryLevel2,Integer categoryLevel3);
	
	public List<app_info> getinfoList(String softwareName,Integer status,Integer flatformId
			, Integer categoryLevel1, Integer categoryLevel2,Integer categoryLevel3,
			Integer pageIndex,Integer pageSize);
	
	public List<app_info> getList();
}
