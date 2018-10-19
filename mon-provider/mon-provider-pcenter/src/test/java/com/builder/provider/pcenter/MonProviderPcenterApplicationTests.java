package com.builder.provider.pcenter;

import com.builder.common.entity.pcenter.SysMenuEntity;
import com.builder.common.utils.JacksonUtil;
import com.builder.common.utils.PageUtils;
import com.builder.provider.pcenter.service.SysMenuService;
import com.builder.provider.pcenter.util.SpringContextUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MonProviderPcenterApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(MonProviderPcenterApplicationTests.class);

    @Autowired
    private SysMenuService sysMenuService;

    @Test
    public void queryMenu() {
        PageUtils data = ((TestService) SpringContextUtils.getBean("testService")).test();
        LOGGER.info("data json: {}", JacksonUtil.encode2(data));
    }

    @Test
    public void queryMenuList(){
        List<SysMenuEntity> list = sysMenuService.getMenuList(1L,1L);
        LOGGER.info("list json: {}", JacksonUtil.encode2(list));
    }

    @Test
    public void wordTest(){
        String tableName = "tb_sys_user";
        String className = WordUtils.capitalizeFully(tableName.replace("tb_", ""),
                new char[]{'_'}).replaceAll("_","");
        String instanceName = StringUtils.uncapitalize(className);
        LOGGER.info("capitalizeFully tableName:{}, to className: {}, to instanceName: {}",
                tableName, className, instanceName);
    }
}
