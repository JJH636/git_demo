package cn.service.version;

import java.math.BigInteger;
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

	@Override
	public boolean mofidy(app_version version) {
		int num = 0;
		try {
			num = mapper.mofidy(version);
			if(num > 0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public app_version getBYid(Integer appId) {
		app_version ver = null;
		try{
			ver = mapper.getBYid(appId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ver;
	}

	@Override
	public boolean mofidyzip(BigInteger id) {
		int num = 0;
		try {
			num = mapper.mofidyzip(id);
			if(num > 0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
