package com.nextpizza.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient(
        name = "user-service",
        url = "${application.config.user-service-url}"
)
@Component
public interface UserProxy {

}
