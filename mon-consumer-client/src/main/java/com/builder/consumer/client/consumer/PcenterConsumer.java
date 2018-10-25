package com.builder.consumer.client.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * PcenterConsumer 权限中心服务消费者
 *
 * @author <a href="mailto:lcbiao34@gmail.com">Builder34</a>
 * @date 2018-10-18 14:43:18
 */
@FeignClient("mon-provider-pcenter")
public interface PcenterConsumer {

    @GetMapping("/test.do")
    String my();
}
