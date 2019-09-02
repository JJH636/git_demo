package cn.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.category.categoryMapper;
import cn.pojo.app_category;

@Service
public class categoryServiceImpl implements categoryService{

	@Autowired
	private categoryMapper mapper;
	
	@Override
	public List<app_category> getlist() {
		List<app_category> list =null;
		try{
			list = mapper.getlist();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<app_category> getlist1(Integer parentId) {
		List<app_category> list =null;
		try{
			list = mapper.getlist1(parentId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
