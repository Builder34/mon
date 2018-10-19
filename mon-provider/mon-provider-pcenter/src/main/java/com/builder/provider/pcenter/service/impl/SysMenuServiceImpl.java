package com.builder.provider.pcenter.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.builder.common.entity.pcenter.SysMenuEntity;
import com.builder.common.utils.PageUtils;
import com.builder.common.utils.Query;
import com.builder.common.utils.constant.Constant;
import com.builder.common.utils.service.impl.BaseServiceImpl;
import com.builder.provider.pcenter.dao.SysMenuDao;
import com.builder.provider.pcenter.service.SysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description 系统菜单服务
 * @CreateTime 2018-09-04 11:36:58
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysMenuServiceImpl.class);
    @Override
    public List<SysMenuEntity> getNavMenuListByUserId(Long userId) {
        return super.baseMapper.getNavMenuListByUserId(userId);
    }

    @Override
    public Long getMenuIdByName(String menuName) {
        SysMenuEntity params = new SysMenuEntity();
        params.setName(menuName);
        SysMenuEntity  entity = super.baseMapper.selectOne(params);
        return entity == null ? -1L : entity.getMenuId() ;
    }

    @Override
    public List<SysMenuEntity> getMenuList(Long userId, Long navMenuId) {
        long startTime = System.currentTimeMillis();
        List<SysMenuEntity> moduleMenuList = userId == -1L ? super.baseMapper.getMenuListAll(navMenuId) : super.baseMapper.getMenuList(userId, navMenuId);
        getAllMenuList(moduleMenuList, userId);
        long endTime = System.currentTimeMillis();
        LOGGER.debug("======build menu tree, use time {} ms",(endTime - startTime));
        return moduleMenuList;
    }

    @Override
    public List<SysMenuEntity> getTreeList() {
        List<SysMenuEntity> menuList = super.baseMapper.getNavMenuListAll();
        for(int i = 0 ; i<menuList.size() ; i++){
            SysMenuEntity menu = menuList.get(i);
            menu.setChildren(this.getMenuList(-1L, menu.getMenuId()));
        }
        return menuList;
    }

    @Override
    public PageUtils selectPage(Map<String, Object> params) {
        Query query = new Query<SysMenuEntity>(params);
        Page<SysMenuEntity> page = query.getPage();
        //菜单通过排序号排序
        page.setOrderByField("order_num");
        page.setAsc(true);
        EntityWrapper<SysMenuEntity> ew = new EntityWrapper<>();
        Integer parentId = params.get("parentId") == null ? 0 : (Integer) params.get("parentId");
        ew.eq("parent_id",parentId);
        if(StringUtils.isNotBlank((String)query.get("kwd"))){
            ew.like("name", (String)query.get("kwd")).or().like("url", (String)query.get("kwd"));
        }
        page = this.selectPage(page, ew);

        return new PageUtils(page);
    }


    @Override
    public boolean insert(SysMenuEntity entity) {
        if(entity.getParentId() != null && entity.getParentId() > 0){
            entity.setLayer(super.baseMapper.getCurrentLayer(entity.getParentId()));
        }else {
            entity.setParentId(0L);
            entity.setLayer(0);
        }
        return super.insert(entity);
    }

    @Override
    public boolean updateById(SysMenuEntity entity) {
        if(entity.getParentId() != null && entity.getParentId() > 0){
            entity.setLayer(super.baseMapper.getCurrentLayer(entity.getParentId()));
        }else {
            entity.setParentId(0L);
            entity.setLayer(0);
        }
        return super.updateById(entity);
    }

    /**
     * <p>遍历菜单树</p>
     * 递归(最重要在于输入，输出)
     * @param moduleMenuList 首层菜单数据
     * @param userId 用户id 当userId=-1时为查询所有菜单
     * @return 菜单结果集合
     * */
    private List<SysMenuEntity> getAllMenuList(List<SysMenuEntity> moduleMenuList, Long userId) {
        for(int i= 0 ; i < moduleMenuList.size() ; i++){
            SysMenuEntity entity = moduleMenuList.get(i);
            //需要是模块、目录类型的节点才认为有下一层节点
            if(entity.getType() == Constant.MenuType.MODULE.getValue() || entity.getType() == Constant.MenuType.CATALOG.getValue()){
                if(userId == -1L){
                    entity.setChildren(super.baseMapper.getMenuListAll(entity.getMenuId()));
                }else {
                    entity.setChildren(super.baseMapper.getMenuList(userId, entity.getMenuId()));
                }
                //递归
                getAllMenuList(entity.getChildren(), userId);
            }
        }
        return moduleMenuList;
    }
}
