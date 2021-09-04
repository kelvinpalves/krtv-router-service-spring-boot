/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krtv.router.infra.repository;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author kelvin
 */

@Entity
@Table (name = "tasks")
@Data
@NoArgsConstructor
public class RouterTaskDataMapper implements Serializable {
    
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    
    private String protocol;
    private String ip;
    private Integer port;
    private String context;
    private String model;
    private String username;
    private String password;
    private String currentStatus;
    private LocalDateTime createdAt;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private Integer tries = 0;
    private Boolean expired = Boolean.FALSE;

    public String getURL() {
        StringBuilder url = new StringBuilder();
        url.append(protocol)
                .append("://")
                .append(ip)
                .append(":")
                .append(port)
                .append("/")
                .append(context);
        return url.toString();
    }

    public boolean numberOfTriesExceededMaximumAllowed(Integer maximumNumberTries) {
        return tries >= maximumNumberTries;
    }


}
