package com.builder.provider.pcenter;

import com.builder.common.core.DataSourceNames;
import com.builder.common.core.annotation.DataSource;
import com.builder.common.utils.PageUtils;
import com.builder.provider.pcenter.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description TestService
 * @CreateTime 2018-08-06 14:36:21
 * @Author builder34
 * @Contactemail lcbiao34@gmail.com
 */
@Service
public class TestService {
    @Autowired
    private SysMenuService sysMenuService;

    @DataSource(name = DataSourceNames.FIRST)
    public PageUtils test(){
        Map<String,Object> params = new HashMap<>();
        return sysMenuService.queryPage(params);
    }
}
