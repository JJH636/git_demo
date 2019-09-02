package cn.service.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.info.InfoMapper;
import cn.pojo.app_info;

@Service
public class InfoServiceImpl implements InfoService {
	
	@Autowired
	private InfoMapper infomapper;

	@Override
	public int PageCount(String softwareName, Integer status, Integer flatformId, Integer categoryLevel1,
			Integer categoryLevel2, Integer categoryLevel3) {
		int num = 0;
		try{
			num = infomapper.PageCount(softwareName, status, flatformId, categoryLevel1, categoryLevel2, categoryLevel3);
		}catch(Exception e){
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public List<app_info> getinfoList(String softwareName, Integer status, Integer flatformId, Integer categoryLevel1,
			Integer categoryLevel2, Integer categoryLevel3, Integer pageIndex, Integer pageSize) {
		List<app_info> list = null;
		try{
			int currentPageNo = (pageIndex - 1) * pageSize;
			list = infomapper.getinfoList(softwareName, status, flatformId, categoryLevel1, categoryLevel2, categoryLevel3, currentPageNo, pageSize);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<app_info> getList() {
		List<app_info> list = null;
		try{
			list = infomapper.getList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean add(app_info info) {
		int num = 0;
		try {
			num = infomapper.add(info);
			if(num > 0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean modify(app_info info) {
		int num = 0;
		try {
			num = infomapper.modify(info);
			if(num > 0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
