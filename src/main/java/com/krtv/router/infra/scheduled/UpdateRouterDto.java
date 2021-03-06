package com.krtv.router.infra.scheduled;

import com.krtv.router.domain.RouterModel;
import com.krtv.router.infra.repository.RouterTaskDataMapper;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Builder
@Data
public class UpdateRouterDto {
    private final String router;
    private final RouterModel model;
    private final String url;
    private final String username;
    private final String password;
    private final Map<String, String> data = new HashMap<>();

    public void addData(String key, String value) {
        this.data.put(key, value);
    }

    public static UpdateRouterDto create(RouterTaskDataMapper routerTaskDataMapper) {
        return UpdateRouterDto.builder()
                .router(routerTaskDataMapper.getId())
                .username(routerTaskDataMapper.getUsername())
                .password(routerTaskDataMapper.getPassword())
                .model(RouterModel.fromString(routerTaskDataMapper.getModel()))
                .url(routerTaskDataMapper.getURL())
                .build();
    }
}
