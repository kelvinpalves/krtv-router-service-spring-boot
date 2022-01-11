package com.krtv.router.infra.rest;


import com.krtv.router.domain.RouterModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("system")
public class SystemController {

    @GetMapping("/info")
    public SystemResponseDto get() {
        SystemResponseDto systemResponseDto = new SystemResponseDto();
        systemResponseDto.setVersion("1.3.0");
        systemResponseDto.setModels(Stream.of(RouterModel.values()).map(RouterModel::name).collect(Collectors.toList()));
        return systemResponseDto;
    }

}
