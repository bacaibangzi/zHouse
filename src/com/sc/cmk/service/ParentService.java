package com.sc.cmk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.cmk.dao.ParentDao;
import com.sc.cmk.pojo.Parent;
import com.sc.framework.base.service.BaseService;
import com.sc.framework.vo.ConditionVO;
import com.sc.framework.vo.Page;

@Service
public class ParentService extends BaseService<Parent>{

	@Autowired
	ParentDao parentMapper;

	/**
	 * 删除家长
	 * 
	 * @param vo
	 * @return
	 */
	public boolean deleteParentById(ConditionVO vo)  throws Exception{
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		try {
			for (String id : vo.getEntityIds().split(",")) {
				conditionMap.put("sn", id);
				parentMapper.delete(conditionMap);
			}
		} catch (Exception err) {
			err.printStackTrace();
		}
		return true;
	}


	/**
	 * 根据Id得到家长信息
	 * 
	 * @param vo
	 * @return
	 */
	public Parent getParentById(ConditionVO vo) throws Exception {
		return parentMapper.getById(Long.parseLong(vo.getEntityId()));
	}

	/**
	 * 保存或新增家长
	 * 
	 * @param entity
	 */
	public void saveOrUpdateParentInfo(Parent entity) throws Exception {
		if (entity.getId() == null) {  
			parentMapper.insert(entity);
		} else {
			parentMapper.update(entity);
		}
	}

	/**
	 * 根据条件查询相关家长
	 * 
	 * @param vo
	 * @return
	 */
	public List<Parent> queryParentsByCondition(ConditionVO vo)  throws Exception{
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		return parentMapper.findPage(conditionMap);
	}

	/**
	 * 根据条件分页查询
	 * 
	 * @param vo
	 * @param page
	 * @return
	 */
	public Page<Parent> queryParentsForPage(ConditionVO vo,
			Page<Parent> page)  throws Exception{
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		//conditionMap.put("userid", vo.getOrgCode());
		return super.queryForPage(parentMapper, conditionMap, page);
	}
	
	/**
	 * 根据条件分页查询
	 * 
	 * @param vo
	 * @param page
	 * @return
	 */
	public Page<Parent> queryParentsPublishForPage(ConditionVO vo,
			Page<Parent> page)  throws Exception{
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		//conditionMap.put("state", vo.getValue());
		conditionMap.put("name", vo.getNameFilter());
		return super.queryForPage(parentMapper, conditionMap, page);
	}
}
