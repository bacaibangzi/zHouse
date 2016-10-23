package com.sc.account.action;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sc.framework.base.action.BaseAction;
import com.sc.framework.enums.OrgTypeEnum;
import com.sc.framework.security.util.AccountInfo;
import com.sc.framework.session.util.SessionConstants;
import com.sc.framework.vo.ConditionVO;
import com.sc.framework.vo.Page;
import com.sc.system.pojo.OrgInfo;
import com.sc.system.service.OrgInfoService;

/**
 * 
 * 组织机构action
 * 
 */ 
@Controller
@RequestMapping("/accountOrg")
public class AccountOrgInfoAction extends BaseAction {
	@Autowired
	OrgInfoService orgInfoService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	
	/**
	 * 组织机构主界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/main.htm", method = RequestMethod.GET)
	public String toMain() {
		return "account/accountOrgFrame";
	}

	/**
	 * 组织机构树状Tree结构
	 * 
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/leftTree.htm", method = RequestMethod.GET)
	public String leftTree(@ModelAttribute ConditionVO vo,
			HttpServletRequest request){
		request.setAttribute("vo", vo);

		try {
			// 加载组织机构tree 图片路径
			String path = "../application/images/icon/org_start.png";
			/*
			List<OrgInfo> list = orgInfoService.queryOrgInfosByCondition(vo);
			List<Map<String, Object>> nodeList = getOrgTreeNode(list, path, vo
					.getOrgCode());
			request.setAttribute("nodeList", new Gson().toJson(nodeList));
			*/
			vo.setUserId(((AccountInfo)request.getSession().getAttribute("accountInfo")).getUserId().toString());
			List<OrgInfo> list = orgInfoService.queryOrgInfosByOrgCodeData(vo);
			List<Map<String, Object>> nodeList = getOrgTreeNode(list, path, vo.getOrgCode());
			request.setAttribute("nodeList", new Gson().toJson(nodeList));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "account/accountOrgLeftTree";
	}

	/**
	 * 组织结构列表界面
	 * 
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/center.htm", method = RequestMethod.GET)
	public String center(@ModelAttribute ConditionVO vo,
			HttpServletRequest request) {
		request.setAttribute("vo", vo);
		return "account/accountOrgCenter";
	}

	/**
	 * 组织结构列表加载
	 * 
	 * @param vo
	 * @param page
	 * @param response
	 */
	@RequestMapping(value = "/list.htm", method = RequestMethod.POST)
	@ResponseBody
	public void list(@ModelAttribute ConditionVO vo,
			@ModelAttribute Page<OrgInfo> page, HttpServletResponse response) throws Exception {
		super.setPageInfo(page);
		Page<OrgInfo> list = orgInfoService.queryOrgInfosForPage(vo, page);
		super.readerPage2Json(list, response);

	}

	/**
	 * 组织机构编辑页面
	 * 
	 * @param vo
	 * @param OrgInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/eidt.htm", method = RequestMethod.GET)
	public String eidt(@ModelAttribute ConditionVO vo,
			@ModelAttribute("form") OrgInfo orgInfo, HttpServletRequest request)  throws Exception{
		request.setAttribute("vo", vo);

		if (vo.getEntityId() != null) {
			
			//OrgInfo of = orgInfoService.getOrgInfoById(vo);
			
			BeanUtils
					.copyProperties(orgInfoService.getOrgInfoById(vo), orgInfo);
		}
		return "account/accountOrgEidt";
	}

	/**
	 * 组织机构查看界面
	 * 
	 * @param vo
	 * @param OrgInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/detail.htm", method = RequestMethod.GET)
	public String detail(@ModelAttribute ConditionVO vo,
			@ModelAttribute("form") OrgInfo OrgInfo, HttpServletRequest request)  throws Exception{
		request.setAttribute("vo", vo);
		BeanUtils.copyProperties(orgInfoService.getOrgInfoById(vo), OrgInfo);
		return "account/accountOrgDetail";
	}

	/**
	 * 组织机构保存
	 * 
	 * @param vo
	 * @param OrgInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save.htm", method = RequestMethod.POST)
	public String save(@ModelAttribute ConditionVO vo,
			@ModelAttribute OrgInfo orgInfo, HttpServletRequest request)  throws Exception{
		request.setAttribute("vo", vo);
		// 新增情况下设置上一级的组织编码
		if (orgInfo.getOiId() == null) {
			orgInfo.setOiCode(vo.getCode());
		}
		orgInfoService.saveOrUpdateOrgInfoInfo(orgInfo,((AccountInfo)request.getSession().getAttribute("accountInfo")).getUserId().toString());
		return "account/accountOrgCenter";
	}

	/**
	 * 组织机构删除
	 * 
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delete.htm", method = RequestMethod.GET)
	public String delete(@ModelAttribute ConditionVO vo,
			HttpServletRequest request)  throws Exception{
		request.setAttribute("vo", vo);
		orgInfoService.deleteOrgInfoById(vo);
		return "account/accountOrgCenter";
	}
	/**
	 * 验证节点结构下是否已经存在子机构和用户
	 * 
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkIfCanbeDeleted.htm", method = RequestMethod.POST)
	@ResponseBody
	public String checkIfCanbeDeleted(@ModelAttribute ConditionVO vo,
			HttpServletRequest request){
		String message = null;
		try {
			message = orgInfoService.checkIfCanbeDeleted(vo);
			message = URLEncoder.encode(message, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
	/**
	 * 验证总店数量
	 * 
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkOiType.htm", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkOiType(@ModelAttribute ConditionVO vo,
			HttpServletRequest request)  throws Exception{
		request.setAttribute("vo", vo);
		List<OrgInfo> orgInfoList = orgInfoService.queryOrgInfosByOrgCodeAndOrgType(vo);
		if(orgInfoList.size()>0){
			return false;
		}else{
			return true;
		}
	}

	public List<Map<String, Object>> getOrgTreeNode1(List<OrgInfo> orgList,
			String path, String org) {
		List<OrgInfo> list = orgList;
		List<Map<String, Object>> nodeList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		for (OrgInfo area : list) {
			map = new HashMap<String, Object>();
			map.put("id", area.getOiCode());
			map.put("checkAble", "false");
			map.put("text", area.getOiName());
			map.put("value", area.getOiCode());
			map.put("showcheck", "");
			map.put("complete", true);
			map.put("isexpand", false);
			map.put("checkstate", "0");
			map.put("hasChildren", false);
			// map.put("pid", area.getOiCode());// 父节点，取前4位
			map.put("ChildNodes", new ArrayList());
			map.put("code", area.getOiCode());
			map.put("type", area.getOiType());
			//map.put("imgPath", path);
			if("0000".equals(area.getOiCode())){
				map.put("imgPath", "../application/images/org/top.png");
			}else if("0".equals(area.getOiType())){
				map.put("imgPath", "../application/images/org/gs.png");
			}else if("1".equals(area.getOiType())){
				map.put("imgPath", "../application/images/org/ag.png");
			}else if("2".equals(area.getOiType())){
				map.put("imgPath", "../application/images/org/dis.png");
			}
			if(area.getOiType()!=null){
				map.put("text", area.getOiName()+"<font color=green> ("+OrgTypeEnum.valueForState(Long.parseLong(area.getOiType())).value()+")</font>");
			}
			
			if (org.equals(area.getOiCode())) {
				map.put("pid", "-1");
			} else {
				map.put("pid", area.getOiCode().substring(0,  area.getOiCode().length()-4));
			}

			nodeList.add(map);
		}
		return nodeList;
	}
}
