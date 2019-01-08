package com.builder.provider.pcenter.controller;

import com.builder.common.core.BaseController;
import com.builder.common.utils.R;
import com.builder.provider.api.pcenter.entity.SysDeptEntity;
import com.builder.provider.pcenter.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SysDeptController 部门管理
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-11-26 12:54:28
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController extends BaseController {

    @Autowired
    private SysDeptService sysDeptService;

    @RequestMapping("/treeList")
    public R treeList() {
        List<SysDeptEntity> list = sysDeptService.getWholeTreeList();
        return R.ok().setData(list);
    }

    @RequestMapping("/add")
    public R add(@RequestBody SysDeptEntity entity) {
        sysDeptService.insert(entity);
        return R.ok();
    }

    @RequestMapping("/modify")
    public R modify(@RequestBody SysDeptEntity entity) {
        sysDeptService.updateById(entity);
        return R.ok();
    }

    @RequestMapping("/remove")
    public R remove(@RequestBody Map<String, ArrayList<Long>> params) {
        sysDeptService.deleteBatchIds(params.get("deptIds"));
        return R.ok();
    }
}
