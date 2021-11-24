/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krtv.router.infra.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kelvin
 */

@Repository
public interface JpaRouterTaskRepository extends JpaRepository<RouterTaskDataMapper, String> {

        Page<RouterTaskDataMapper> findAllByStartedAtIsNull(Pageable pageable);

        Page<RouterTaskDataMapper> findAll(Specification<RouterTaskDataMapper> specification, Pageable pageable);

}
