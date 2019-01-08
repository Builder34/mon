package com.builder.provider.pcenter.service.impl;


import com.builder.common.utils.service.impl.BaseServiceImpl;
import com.builder.provider.api.pcenter.entity.SysDeptEntity;
import com.builder.provider.pcenter.dao.SysDeptDao;
import com.builder.provider.pcenter.service.SysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Description 部门管理 服务
 * @CreateTime 2018-08-23 22:36:58
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@Service
@Slf4j
public class SysDeptServiceImpl extends BaseServiceImpl<SysDeptDao, SysDeptEntity> implements SysDeptService {

    @Override
    public boolean insert(SysDeptEntity entity) {
        if(entity.getParentId() != null && entity.getParentId() > 0) {
            entity.setLayer(super.baseMapper.getCurrentLayer(entity.getParentId()));
        } else {
            entity.setParentId(0L);
            entity.setLayer(0);
        }
        return super.insert(entity);
    }

    @Override
    public List<SysDeptEntity> getWholeTreeList() {
        long startTime = System.currentTimeMillis();
        List<SysDeptEntity> deptList = super.baseMapper.getDeptFirstLevelList();
        deptList.forEach( dept -> {
            List<SysDeptEntity> childrenList = super.baseMapper.getDeptListByParentId(dept.getDeptId());
            buildDeptTreeList(childrenList);
            dept.setChildren(childrenList);
        });
        long endTime = System.currentTimeMillis();
        log.debug("======build dept tree, use time {} ms",(endTime - startTime));
        return deptList;
    }

    @Override
    public List<SysDeptEntity> getNextDeptList(Long deptId) {
        long startTime = System.currentTimeMillis();
        List<SysDeptEntity> deptList = super.baseMapper.getDeptListByParentId(deptId);
        buildDeptTreeList(deptList);
        long endTime = System.currentTimeMillis();
        log.debug("======build dept tree, use time {} ms",(endTime - startTime));
        return deptList;
    }

    /**
     * <p>遍历部门树节点</p>
     * 递归(最重要在于输入，输出)
     * @param deptList 当前层节点
     * @return 部门结果集合
     * */
    private void buildDeptTreeList(List<SysDeptEntity> deptList) {
        if(deptList != null) {
            for(int i= 0 ; i < deptList.size() ; i++){
                SysDeptEntity entity = deptList.get(i);
                entity.setChildren(super.baseMapper.getDeptListByParentId(entity.getDeptId()));
                if(entity.getChildren() != null || entity.getChildren().size() > 0){
                    //递归查询下一层数据
                    buildDeptTreeList(entity.getChildren());
                }
            }
        }
    }
}
