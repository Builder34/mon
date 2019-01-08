package com.builder.provider.pcenter.service;

import com.builder.common.utils.service.BaseService;
import com.builder.provider.api.pcenter.entity.SysDeptEntity;

import java.util.List;

/**
 * @Description SysDeptService
 * @CreateTime 2018-08-23 22:36:34
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
public interface SysDeptService extends BaseService<SysDeptEntity> {

    /**
     * 获取全部部门数据，组合成树
     * @return 部门树节点列表
     * */
    List<SysDeptEntity> getWholeTreeList();

    /**
     * 获取下一层部门数据
     * @param deptId 部门id
     * @return 部门数据
     * */
    List<SysDeptEntity> getNextDeptList(Long deptId);
}
