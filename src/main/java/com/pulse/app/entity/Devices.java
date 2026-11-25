package com.pulse.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "devices",
        indexes = {
                @Index(name = "idx_device_factory", columnList = "factory_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Devices {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "device_id", updatable = false, nullable = false)
    private UUID deviceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factory_id", nullable = false)
    private Factories factory;

    @Column(name = "device_code", nullable = false, unique = true)
    private String deviceCode;

    @Column(name = "device_type", nullable = false)
    private String deviceType;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String purpose;

    @Column(name = "physical_location")
    private String physicalLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "connectivity_status")
    private Connectivity connectivityStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "health_status")
    private Health healthStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "operational_status")
    private OperationalStatus operationalStatus;

    @Column(name = "installed_at", nullable = false)
    private LocalDateTime installedAt;
}