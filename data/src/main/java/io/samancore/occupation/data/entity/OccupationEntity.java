package io.samancore.occupation.data.entity;

import lombok.Getter;
import lombok.Setter;
import io.samancore.occupation.data.entity.common.AuditCommon;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "occupation")
public class OccupationEntity extends AuditCommon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_occupation")
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}
