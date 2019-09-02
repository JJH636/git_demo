package cn.dao.category;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.app_category;

public interface categoryMapper {
	public List<app_category> getlist()throws Exception;
	
	public List<app_category> getlist1(@Param("parentId")Integer parentId)throws Exception;
}
