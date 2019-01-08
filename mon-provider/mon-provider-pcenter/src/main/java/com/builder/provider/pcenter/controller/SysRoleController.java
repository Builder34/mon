package com.builder.provider.pcenter.controller;

import com.builder.common.core.BaseController;
import com.builder.common.utils.PageUtils;
import com.builder.common.utils.R;
import com.builder.provider.api.pcenter.entity.SysRoleEntity;
import com.builder.provider.pcenter.service.SysRoleMenuService;
import com.builder.provider.pcenter.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description 角色管理接口
 * @CreateTime 2018-09-27 22:23:22
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;


    @RequestMapping("/list")
    public R list(@RequestBody Map<String, Object> params){
        PageUtils data = sysRoleService.queryJoinPage(params);
        return R.ok().setData(data);
    }
    @RequestMapping("/info/{roleId}")
    public R info(@PathVariable("roleId") Long roleId) {
        SysRoleEntity entity = sysRoleService.selectById(roleId);
        List<Long> menuIdList = sysRoleMenuService.getMenuIdList(roleId);
        entity.setMenuIdList(menuIdList);
        return R.ok().setData(entity);
    }

    @RequestMapping("/add")
    public R add(@RequestBody SysRoleEntity entity) {
        entity.setCreateUserId(getCurrentUserId());
        entity.setUpdateUserId(getCurrentUserId());
        sysRoleService.insert(entity);
        return R.ok();
    }

    @RequestMapping("/modify")
    public R modify(@RequestBody SysRoleEntity entity) {
        entity.setUpdateUserId(getCurrentUserId());
        sysRoleService.updateById(entity);
        return R.ok();
    }

    @RequestMapping("/remove")
    public R remove(@RequestBody Map<String, ArrayList<Long>> params) {
        sysRoleService.deleteBatchIds(params.get("ids"));
        return R.ok();
    }
}
