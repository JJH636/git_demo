package cn.service.dictionary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.dictionary.dictionaryMapper;
import cn.pojo.data_dictionary;
@Service
public class dictionaryServiceImpl implements dictionaryService {

	@Autowired 
	private dictionaryMapper mapper;
	@Override
	public List<data_dictionary> getlist1() {
		List<data_dictionary> list = null;
		try{
			list = mapper.getlist1();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<data_dictionary> getlist2() {
		List<data_dictionary> list = null;
		try{
			list = mapper.getlist2();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<data_dictionary> getlist3(String typeCode) {
		List<data_dictionary> list = null;
		try{
			list = mapper.getlist3(typeCode);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

}
