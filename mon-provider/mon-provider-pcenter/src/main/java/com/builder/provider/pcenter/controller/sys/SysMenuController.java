package com.builder.provider.pcenter.controller.sys;


import com.builder.common.entity.pcenter.SysMenuEntity;
import com.builder.common.utils.R;
import com.builder.provider.pcenter.controller.BaseController;
import com.builder.provider.pcenter.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description 菜单管理 接口
 * @CreateTime 2018-08-24 00:34:27
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {
    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("/navList")
    public R nav(@RequestBody Map<String, String> params) {
        Long userId = Long.valueOf(params.get("userId"));
        String navMenuName = params.get("navMenuName");
        Long navMenuId = sysMenuService.getMenuIdByName(navMenuName);
        List<SysMenuEntity> data = sysMenuService.getMenuList(userId, navMenuId);
        return R.ok().setData(data);
    }

    @RequestMapping("/treeList")
    public R treeList() {
        List<SysMenuEntity> list = sysMenuService.getTreeList();
        return R.ok().setData(list);
    }

    @RequestMapping("/add")
    public R add(@RequestBody SysMenuEntity entity) {
        sysMenuService.insert(entity);
        return R.ok();
    }

    @RequestMapping("/modify")
    public R modify(@RequestBody SysMenuEntity entity) {
        sysMenuService.updateById(entity);
        return R.ok();
    }

    @RequestMapping("/remove")
    public R remove(@RequestBody Map<String, ArrayList<Long>> params) {
        sysMenuService.deleteBatchIds(params.get("menuIds"));
        return R.ok();
    }
}
