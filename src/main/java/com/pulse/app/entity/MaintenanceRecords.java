package com.pulse.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "maintenance_records",
        indexes = {
                @Index(
                        name = "idx_maintenance_device",
                        columnList = "device_id"
                ),
                @Index(
                        name = "idx_maintenance_user",
                        columnList = "performed_by_user_id"
                ),
                @Index(
                        name = "idx_maintenance_scheduled",
                        columnList = "scheduled_at"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaintenanceRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "record_id", updatable = false, nullable = false)
    private UUID recordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private Devices device;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performed_by_user_id")
    private Users performedByUser;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "maintenance_type", nullable = false)
    private MaintenanceType maintenanceType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Builder.Default
    private MaintenanceStatus status = MaintenanceStatus.SCHEDULED;

    @Enumerated(EnumType.STRING)
    @Column(name = "outcome")
    private MaintenanceOutcome outcome;

    @Column(name = "scheduled_at", nullable = false)
    private LocalDateTime scheduledAt;

    @Column(name = "performed_at")
    private LocalDateTime performedAt;
}