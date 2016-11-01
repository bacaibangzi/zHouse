package com.sc.cmk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.cmk.dao.ChildrenDao;
import com.sc.cmk.pojo.Children;
import com.sc.framework.base.service.BaseService;
import com.sc.framework.vo.ConditionVO;
import com.sc.framework.vo.Page;

@Service
public class ChildrenService extends BaseService<Children>{
	@Autowired
	ChildrenDao childrenMapper;

	/**
	 * 删除儿童
	 * 
	 * @param vo
	 * @return
	 */
	public boolean deleteChildrenById(ConditionVO vo)  throws Exception{
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		try {
			for (String id : vo.getEntityIds().split(",")) {
				conditionMap.put("sn", id);
				childrenMapper.delete(conditionMap);
			}
		} catch (Exception err) {
			err.printStackTrace();
		}
		return true;
	}
	

	/**
	 * 根据Id得到儿童信息
	 * 
	 * @param vo
	 * @return
	 */
	public Children getChildrenById(ConditionVO vo) throws Exception {
		return childrenMapper.getById(Long.parseLong(vo.getEntityId()));
	}

	/**
	 * 保存或新增儿童
	 * 
	 * @param entity
	 */
	public void saveOrUpdateChildrenInfo(Children entity) throws Exception {
		if (entity.getId() == null) {  
			childrenMapper.insert(entity);
		} else {
			childrenMapper.update(entity);
		}
	}

	/**
	 * 根据条件查询相关儿童
	 * 
	 * @param vo
	 * @return
	 */
	public List<Children> queryChildrensByCondition(ConditionVO vo)  throws Exception{
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		return childrenMapper.findPage(conditionMap);
	}

	/**
	 * 根据条件分页查询
	 * 
	 * @param vo
	 * @param page
	 * @return
	 */
	public Page<Children> queryChildrensForPage(ConditionVO vo,
			Page<Children> page)  throws Exception{
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("userid", vo.getOrgCode());
		return super.queryForPage(childrenMapper, conditionMap, page);
	}
	
	/**
	 * 根据条件分页查询
	 * 
	 * @param vo
	 * @param page
	 * @return
	 */
	public Page<Children> queryChildrensPublishForPage(ConditionVO vo,
			Page<Children> page)  throws Exception{
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("name", vo.getNameFilter());
		return super.queryForPage(childrenMapper, conditionMap, page);
	}
}
