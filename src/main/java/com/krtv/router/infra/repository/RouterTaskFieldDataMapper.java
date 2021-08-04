package com.krtv.router.infra.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "fields")
@Data
@NoArgsConstructor
public class RouterTaskFieldDataMapper {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    public RouterTaskFieldDataMapper(RouterTaskDataMapper router, String key, String value) {
        this.router = router;
        this.key = key;
        this.value = value;
    }

    @ManyToOne(optional = false)
    private RouterTaskDataMapper router;

    @Column(name = "field_key")
    private String key;

    @Column(name = "field_value")
    private String value;

}
