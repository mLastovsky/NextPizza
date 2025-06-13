package com.nextpizza.controller;

import com.nextpizza.dto.DoughTypeResponseDto;
import com.nextpizza.service.DoughTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dough-types")
@RequiredArgsConstructor
public class DoughTypeController {

    private final DoughTypeService doughTypeService;

    @GetMapping
    public ResponseEntity<List<DoughTypeResponseDto>> getDoughTypes() {
        return ResponseEntity.ok(doughTypeService.getDoughTypes());
    }

}
