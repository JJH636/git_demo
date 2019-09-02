package cn.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.pojo.PageSupport;
import cn.pojo.app_category;
import cn.pojo.app_info;
import cn.pojo.data_dictionary;
import cn.service.category.categoryService;
import cn.service.dictionary.dictionaryService;
import cn.service.info.InfoService;

@Controller
@RequestMapping("/app")
public class InfoController {
	private Logger logger=Logger.getLogger(InfoController.class);
	
	@Autowired
	private InfoService infoservice;
	
	@Autowired
	private dictionaryService dicservice;
	
	@Autowired
	private categoryService cateservice;
	
	@RequestMapping(value="/list")
	public String getPageList(Model model,
			@RequestParam(value = "querySoftwareName", required = false) String querySoftwareName,
			@RequestParam(value = "queryStatus", required = false) String queryStatus,
			@RequestParam(value = "queryFlatformId", required = false) String queryFlatformId,
			@RequestParam(value = "queryCategoryLevel1", required = false) String queryCategoryLevel1,
			@RequestParam(value = "queryCategoryLevel2", required = false) String queryCategoryLevel2,
			@RequestParam(value = "queryCategoryLevel3", required = false) String queryCategoryLevel3,
			@RequestParam(value = "pageIndex", required = false) String pageIndex) throws Exception {
		logger.info("getinfoList ---- > querySoftwareName: " + querySoftwareName);
		logger.info("getinfoList ---- > queryStatus: " + queryStatus);
		logger.info("getinfoList ---- > queryFlatformId: " + queryFlatformId);
		logger.info("getinfoList ---- > pageIndex: " + pageIndex);
		int _q = 0; //status
		int _q1 = 0; //flatformId
		int _q2 = 0; //Level1
		int _q3 = 0; //Level2
		int _q4 = 0; //Level3
		List<app_info> appInfoList = null;
		// 设置页面容量
		int pageSize = 5;
		// 当前页码
		int currentpageNo = 1;
		// 我们要去查询数据，那么就要确定条件
		if (querySoftwareName == null) {
			querySoftwareName = "";
		}
		if (queryStatus != null && !queryStatus.equals("")) {
			_q = Integer.parseInt(queryStatus);
		}
		if (queryFlatformId != null && !queryFlatformId.equals("")) {
			_q1 = Integer.parseInt(queryFlatformId);
		}
		if (queryCategoryLevel1 != null && !queryCategoryLevel1.equals("")) {
			_q2 = Integer.parseInt(queryCategoryLevel1);
		}
		if (queryCategoryLevel2 != null && !queryCategoryLevel2.equals("")) {
			_q3 = Integer.parseInt(queryCategoryLevel2);
		}
		if (queryCategoryLevel3 != null && !queryCategoryLevel3.equals("")) {
			_q4 = Integer.parseInt(queryCategoryLevel3);
		}
		if (pageIndex != null) {
			currentpageNo = Integer.parseInt(pageIndex);
		}
		// 总数量
		int totalCount = infoservice.PageCount(querySoftwareName, _q, _q1, _q2, _q3, _q4);
		// 总页数
		PageSupport page = new PageSupport();
		page.setCurrentPageNo(currentpageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(totalCount);
		int totalPageCount = page.getTotalPageCount();
		// 控制首页和尾页
		if (currentpageNo < 1) {
			currentpageNo = 1;
		} else if (currentpageNo > totalPageCount) {
			currentpageNo = totalPageCount;
		}
		// 查询分页数据
		appInfoList = infoservice.getinfoList(querySoftwareName, _q, _q1, _q2, _q3, _q4, currentpageNo, pageSize);
		logger.error(appInfoList);

		model.addAttribute("appInfoList", appInfoList);
		// 查询其他类的id
		List<data_dictionary> data = dicservice.getlist1();
		List<data_dictionary> data2 = dicservice.getlist2();
		List<app_category> list1 = cateservice.getlist();

		// 把值一个一个的传给页面
		model.addAttribute("statusList", data);
		model.addAttribute("flatFormList",data2);
		model.addAttribute("categoryLevel1List",list1);
		model.addAttribute("querySoftwareName", querySoftwareName);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentpageNo);
		model.addAttribute("totalPageCount", totalPageCount);

		logger.debug("已经到了要跳转的时候了！！。。。。");
		return "/developer/appinfolist";
	}
	
	@RequestMapping(value="/categorylevellist")
	@ResponseBody
	public Object selectid(@RequestParam Integer pid){
		return JSON.toJSON(cateservice.getlist1(pid));
	}
	
	@RequestMapping(value="/appinfoadd")
	public String add(){
		return "/developer/appinfoadd";
	}
	
	@RequestMapping(value="/datadictionarylist")
	@ResponseBody
	public Object selectPT(@RequestParam String tcode){
		return JSON.toJSON(dicservice.getlist3(tcode));
	}
}
