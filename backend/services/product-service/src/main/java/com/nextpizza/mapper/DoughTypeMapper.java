package com.nextpizza.mapper;

import com.nextpizza.dto.DoughTypeResponseDto;
import com.nextpizza.model.DoughType;
import org.springframework.stereotype.Component;

@Component
public class DoughTypeMapper {

    public DoughTypeResponseDto fromDoughType(DoughType doughType) {
        return new DoughTypeResponseDto(
                doughType.getId(),
                doughType.getType()
        );
    }

}
