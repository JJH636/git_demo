package cn.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import cn.pojo.PageSupport;
import cn.pojo.app_category;
import cn.pojo.app_info;
import cn.pojo.data_dictionary;
import cn.pojo.dev_user;
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
		// ����ҳ������
		int pageSize = 5;
		// ��ǰҳ��
		int currentpageNo = 1;
		// ����Ҫȥ��ѯ���ݣ���ô��Ҫȷ������
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
		// ������
		int totalCount = infoservice.PageCount(querySoftwareName, _q, _q1, _q2, _q3, _q4);
		// ��ҳ��
		PageSupport page = new PageSupport();
		page.setCurrentPageNo(currentpageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(totalCount);
		int totalPageCount = page.getTotalPageCount();
		// ������ҳ��βҳ
		if (currentpageNo < 1) {
			currentpageNo = 1;
		} else if (currentpageNo > totalPageCount) {
			currentpageNo = totalPageCount;
		}
		// ��ѯ��ҳ����
		appInfoList = infoservice.getinfoList(querySoftwareName, _q, _q1, _q2, _q3, _q4, currentpageNo, pageSize);
		logger.error(appInfoList);

		model.addAttribute("appInfoList", appInfoList);
		// ��ѯ�������id
		List<data_dictionary> data = dicservice.getlist1();
		List<data_dictionary> data2 = dicservice.getlist2();
		List<app_category> list1 = cateservice.getlist();

		// ��ֵһ��һ���Ĵ���ҳ��
		model.addAttribute("statusList", data);
		model.addAttribute("flatFormList",data2);
		model.addAttribute("categoryLevel1List",list1);
		model.addAttribute("querySoftwareName", querySoftwareName);
		model.addAttribute("pages",page);

		logger.debug("�Ѿ�����Ҫ��ת��ʱ���ˣ�����������");
		return "/developer/appinfolist";
	}
	
	@RequestMapping(value="/categorylevellist")
	@ResponseBody
	public Object selectid(@RequestParam Integer pid){
		if(pid == null){
			pid = 0;
		}
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
	
	
	@RequestMapping(value="/appinfoaddsave",method=RequestMethod.POST)
	public String addUserSave(app_info info,HttpSession session,
			HttpServletRequest request,
			@RequestParam(value="a_logoPicPath",required=false) MultipartFile attach){
		String fileUploadError = null;		//��Ƭ��·��
		String path = null;
		//�ж��ļ��Ƿ�Ϊ��
		if(!attach.isEmpty()){
			//������Ҫ�����·��
			path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
			logger.debug("path========>" + path);
			String oldFileName = attach.getOriginalFilename();		//ԭ�ļ���
			logger.debug("oldFileName========>" + oldFileName);
			String prefix = FilenameUtils.getExtension(oldFileName);	//Դ�ļ���׺
			
			int filesize = 500000;
			logger.debug("oldFileName========>" + attach.getSize());
			if(attach.getSize() > filesize){
				request.setAttribute("uploadFileError", "�ϴ���С���ܳ���500KB");
				System.out.println("ͼƬ����");
				return "/developer/appinfoadd";
			} else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") ||
					prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) {
				//�ļ���������
				String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_Personal.jpg";
				logger.debug("fileName========>" + fileName);
				File targetFile = new File(path,fileName);
				if(!targetFile.exists()){
					targetFile.mkdirs();
				}
				//����
				try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("uploadFileError", "�ļ��ϴ�ʧ�ܣ�");
					System.out.println("�ļ��ϴ�ʧ��");
					return "/developer/appinfoadd";
				}
				//����Ҫ���浽���ݿ��
				fileUploadError = path+File.separator + fileName;
			} else {
				request.setAttribute("uploadFileError", "�ļ���ʽ����ȷ��");
				System.out.println("�ļ���ʽ����ȷ");
				return "/developer/appinfoadd";
			}
			
		}
		//���ñ���ķ�����ʵ�ֱ���
		
		info.setCreatedBy(((dev_user)session.getAttribute("devUser")).getId());
		info.setCreationDate(new Date());
		info.setLogoPicPath(fileUploadError);
		info.setLogoLocPath(path);
		if(infoservice.add(info)){
			return "redirect:/app/list";
		}
		System.out.println("�������");
		return "developer/appinfoadd";
	}
	@RequestMapping(value="/apkexist")
	@ResponseBody
	public Object delbill(@RequestParam String APKName){
		HashMap<String, String> reHashMap = new HashMap<String,String>();
		if(APKName == null || APKName==""){
			reHashMap.put("APKName", "empty");
		}else{
			app_info info = infoservice.getAPK(APKName);
			if(info != null){
				reHashMap.put("APKName", "exist");
			}else{
				reHashMap.put("APKName", "noexist");
			}
		}
		return JSONArray.toJSONString(reHashMap);
	}
	
	@RequestMapping(value="/appinfomodify/{id}",method=RequestMethod.GET)
	public String appinfomodify(@PathVariable Integer id,Model model){
		app_info info = infoservice.getid(id);
		model.addAttribute("appInfo", info);
		return "/developer/appinfomodify";
	}
	 
	@RequestMapping(value="/appinfomodifysave")
	public String appinfomodify1(app_info info, HttpSession session){
		info.setModifyBy(((dev_user)session.getAttribute("devUser")).getId());
		info.setModifyDate(new Date());
		if(infoservice.modify(info)){
			return"redirect:/developer/appinfolist";
		}
		return "/developer/appinfomodify";
	}
}
