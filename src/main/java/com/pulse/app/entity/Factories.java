package com.pulse.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(
        name = "factories",
        indexes = {
                @Index(
                        name = "idx_factory_organization",
                        columnList = "organization_id"
                )
        },
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_factory_organization_name",
                        columnNames = {"organization_id", "name"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factories {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "factory_id", updatable = false, nullable = false)
    private UUID factoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organizations organization;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    // Example: Asia/Kolkata
    @Column(name = "time_zone", nullable = false, length = 50)
    private String timeZone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FactoryStatus status;
}