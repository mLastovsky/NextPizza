package com.nextpizza.proxy;

import com.nextpizza.dto.ProductItemResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(
        name = "product-service",
        url = "${application.product-item-service.url}"
)
@Component
public interface ProductItemProxy {

    @GetMapping("/{id}/price")
    BigDecimal getProductPriceById(@PathVariable Long id);

    @GetMapping("/{id}")
    ProductItemResponseDto getProductItemById(@PathVariable Long id);

}
