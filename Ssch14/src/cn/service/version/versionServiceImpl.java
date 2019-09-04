package cn.service.version;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.version.versionMapper;
import cn.pojo.app_version;

@Service
public class versionServiceImpl implements versionService {

	@Autowired
	private versionMapper mapper;
	@Override
	public boolean add(app_version version) {
		int num = 0;
		try {
			num = mapper.add(version);
			if(num > 0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<app_version> getid(Integer appId) {
		List<app_version> ver = null;
		try{
			ver = mapper.getid(appId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ver;
	}
}
