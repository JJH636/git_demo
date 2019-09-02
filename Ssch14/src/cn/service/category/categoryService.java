package cn.service.category;

import java.util.List;

import cn.pojo.app_category;

public interface categoryService {
	public List<app_category> getlist();
	
	public List<app_category> getlist1(Integer parentId);
}
