package com.nextpizza.service.impl;

import com.nextpizza.dto.DoughTypeResponseDto;
import com.nextpizza.mapper.DoughTypeMapper;
import com.nextpizza.repository.DoughTypeRepository;
import com.nextpizza.service.DoughTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DoughTypeServiceImpl implements DoughTypeService {

    private final DoughTypeRepository doughTypeRepository;
    private final DoughTypeMapper doughTypeMapper;

    @Override
    public List<DoughTypeResponseDto> getDoughTypes() {
        return doughTypeRepository.findAll().stream()
                .map(doughTypeMapper::fromDoughType)
                .toList();
    }

}
