package com.nextpizza.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient(
        name = "product-service",
        url = "${application.config.product-service-url}"
)
@Component
public interface ProductProxy {

}
