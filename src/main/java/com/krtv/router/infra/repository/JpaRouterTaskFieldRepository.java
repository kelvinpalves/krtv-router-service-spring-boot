package com.krtv.router.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaRouterTaskFieldRepository extends JpaRepository<RouterTaskFieldDataMapper, String> {

    List<RouterTaskFieldDataMapper> findAllByRouterId(String routerId);

}
