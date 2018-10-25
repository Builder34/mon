package com.builder.consumer.client.controller.pcenter;

import com.builder.consumer.client.consumer.PcenterConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SysUserController 系统用户接口
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-10-18 14:48:23
 */
@RestController
public class SysUserController {

    @Autowired
    PcenterConsumer pcenterConsumer;

    @GetMapping("/my")
    public String my(){
        return pcenterConsumer.my();
    }
}
