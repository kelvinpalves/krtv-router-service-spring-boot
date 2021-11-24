package com.krtv.router.infra.repository.specification;

import com.krtv.router.infra.repository.RouterTaskDataMapper;
import com.krtv.router.infra.rest.TaskSearchRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Data
@RequiredArgsConstructor
public class TaskFilterSpecification implements Specification<RouterTaskDataMapper> {

    private final TaskSearchRequest request;

    @Override
    public Predicate toPredicate(Root<RouterTaskDataMapper> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate result = criteriaBuilder.conjunction();

        if (request.getIp() != null) {
            result = criteriaBuilder.and(result, criteriaBuilder.equal(root.get("ip"), this.request.getIp()));
        }

        if (request.getPort() != null) {
            result = criteriaBuilder.and(result, criteriaBuilder.equal(root.get("port"), this.request.getPort()));
        }

        return result;
    }
}
