package com.sc.system.action;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.sc.framework.security.util.AccountInfo;
import com.sc.framework.session.util.SessionConstants;
import com.sc.framework.vo.ConditionVO;
import com.sc.framework.vo.Page;
import com.sc.system.pojo.OrgInfo;
import com.sc.system.service.OrgInfoService;

/**
 * 
 * 经销商组织机构action
 * 
 */
@Controller
@RequestMapping("/agOrg")
public class AgOrgInfoAction extends BaseAction {
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
		return "system/agOrgFrame";
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
			HttpServletRequest request)  throws Exception{
		request.setAttribute("vo", vo);

		// 加载组织机构tree 图片路径
		String path = "../application/images/icon/org_start.png";
		vo.setUserId(((AccountInfo)request.getSession().getAttribute("accountInfo")).getUserId().toString());
		List<OrgInfo> list = orgInfoService.queryOrgInfosByOrgCodeData(vo);
		List<Map<String, Object>> nodeList = getOrgTreeNode(list, path, vo
				.getOrgCode());
		request.setAttribute("nodeList", new Gson().toJson(nodeList));

		return "system/agOrgLeftTree";
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
		return "system/agOrgCenter";
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
		vo.setOrgType("1");
		Page<OrgInfo> list = orgInfoService.queryAgOrgInfosForPage(vo, page);
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
		request.setAttribute("orgTypeMap", SessionConstants.org_type);
		if (vo.getEntityId() != null) {
			
			OrgInfo of = orgInfoService.getOrgInfoById(vo);
			System.out.println("id : "+of.getIdStr());
			
			BeanUtils
					.copyProperties(orgInfoService.getOrgInfoById(vo), orgInfo);
		}
		return "system/agOrgEidt";
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
		return "system/agOrgDetail";
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
		return "system/agOrgCenter";
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
		return "system/agOrgCenter";
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
}
