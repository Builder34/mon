package com.builder.provider.pcenter.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.builder.provider.api.pcenter.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Description 系统角色Dao
 * @CreateTime 2018-08-23 17:33:43
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@Mapper
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenuEntity> {

    /**
     * 批量插入
     * @param map {roleId:String, menuIdList:List<Long>, updateUserId:Long} 参数Map
     * */
    void batchInsert(Map<String, Object> map);
    /**
     * 查询权限菜单id列表
     * @param roleId 角色id
     * @return 菜单id列表
     * */
    List<Long> getMenuIdList(Long roleId);
}
