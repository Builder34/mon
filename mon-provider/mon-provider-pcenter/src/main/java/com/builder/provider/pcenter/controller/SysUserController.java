package com.builder.provider.pcenter.controller;

import com.builder.common.core.dto.LoginAuthDto;
import com.builder.provider.api.pcenter.entity.SysMenuEntity;
import com.builder.provider.api.pcenter.entity.SysUserEntity;
import com.builder.common.utils.PageUtils;
import com.builder.common.utils.R;
import com.builder.common.core.BaseController;
import com.builder.provider.pcenter.service.SysMenuService;
import com.builder.provider.pcenter.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description SysUserController
 * @CreateTime 2018-08-17 16:23:22
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录的用户信息
     * */
    @RequestMapping("/my")
    public R info(){
        LoginAuthDto user = getCurrentUser();
        List<SysMenuEntity> navMenuList= sysMenuService.getNavMenuListByUserId(getCurrentUserId());
        R result = R.ok().put("user", user);
        result.put("navMenus", navMenuList);
        return result;
    }

    @RequestMapping("/list")
    public R list(@RequestBody Map<String, Object> params){
        PageUtils data = sysUserService.queryPage(params);
        return R.ok().setData(data);
    }
    @RequestMapping("/info/{userId}")
    public R list(@PathVariable Long userId){
        SysUserEntity entity = sysUserService.selectById(userId);
        //去掉密码相关信息
        entity.setPassword("");
        entity.setSalt("");
        return R.ok().setData(entity);
    }

    @RequestMapping("/add")
    public R add(@RequestBody SysUserEntity entity) {
        entity.setCreateUserId(getCurrentUserId());
        return sysUserService.insertCustom(entity);
    }

    @RequestMapping("/modify")
    public R modify(@RequestBody SysUserEntity entity) {
        entity.setUpdateUserId(getCurrentUserId());
        sysUserService.updateById(entity);
        return R.ok();
    }

    @RequestMapping("/remove")
    public R remove(@RequestBody Map<String, ArrayList<Long>> params) {
        sysUserService.deleteBatchIds(params.get("ids"));
        return R.ok();
    }
}
