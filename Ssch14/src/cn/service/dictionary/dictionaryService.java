package cn.service.dictionary;

import java.util.List;

import cn.pojo.app_info;
import cn.pojo.data_dictionary;

public interface dictionaryService {
	public List<data_dictionary> getlist1();
	
	public List<data_dictionary> getlist2();
	
	public List<data_dictionary> getlist3(String typeCode);
}
