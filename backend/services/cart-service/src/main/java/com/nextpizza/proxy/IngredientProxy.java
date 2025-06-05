package com.nextpizza.proxy;

import com.nextpizza.dto.IngredientResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(
        name = "ingredient-service",
        url = "${application.ingredient-service.url}"
)
@Component
public interface IngredientProxy {

    @GetMapping("/{id}/price")
    BigDecimal getIngredientPriceById(@PathVariable Long id);

    @GetMapping
    List<IngredientResponseDto> getIngredientsByIds(@RequestBody List<Long> ids);

}
