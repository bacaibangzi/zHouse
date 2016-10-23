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
 * 门店机构action
 * 
 */
@Controller
@RequestMapping("/disOrg")
public class DisOrgInfoAction extends BaseAction {
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
		return "system/disOrgFrame";
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

		return "system/disOrgLeftTree";
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
		return "system/disOrgCenter";
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
		vo.setValue("2");
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
		if(vo.getEntityId()!=null){
			if(vo.getOrgType().equals("2")||vo.getOrgType().equals("3")){//餐饮连锁和超市连锁
				SessionConstants.org_type.clear();
				SessionConstants.org_type.put(2L, "餐饮连锁");
				SessionConstants.org_type.put(3L, "超市连锁");
				request.setAttribute("orgTypeMap", SessionConstants.org_type);
			}else if(vo.getOrgType().equals("7")||vo.getOrgType().equals("8")){//餐饮单店和超市单店
				SessionConstants.org_type.clear();
				SessionConstants.org_type.put(7L, "餐饮单店");
				SessionConstants.org_type.put(8L, "超市单店");
				request.setAttribute("orgTypeMap", SessionConstants.org_type);
			}
		}else{
			if(vo.getOrgType().equals("1")){
				SessionConstants.org_type.remove(0L);
				SessionConstants.org_type.remove(1L);
				SessionConstants.org_type.remove(4L);
				SessionConstants.org_type.remove(5L);
				SessionConstants.org_type.remove(6L);
				SessionConstants.org_type.put(2L, "餐饮连锁");
				SessionConstants.org_type.put(3L, "超市连锁");
				SessionConstants.org_type.put(7L, "餐饮单店");
				SessionConstants.org_type.put(8L, "超市单店");
				request.setAttribute("orgTypeMap", SessionConstants.org_type);
			}
		}
		if (vo.getEntityId() != null) {
			
			OrgInfo of = orgInfoService.getOrgInfoById(vo);
			System.out.println("id : "+of.getIdStr());
			
			BeanUtils
					.copyProperties(orgInfoService.getOrgInfoById(vo), orgInfo);
		}
		return "system/disOrgEidt";
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
		return "system/disOrgDetail";
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
		return "system/disOrgCenter";
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
		return "system/disOrgCenter";
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
