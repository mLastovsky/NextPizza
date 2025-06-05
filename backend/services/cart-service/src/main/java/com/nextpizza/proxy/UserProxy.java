package com.nextpizza.proxy;

import com.nextpizza.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "user-service",
        url = "${application.user-service.url}"
)
@Component
public interface UserProxy {

    @GetMapping("/{id}")
    UserResponseDto getUserById(@PathVariable String id);

}
