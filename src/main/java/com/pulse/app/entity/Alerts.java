package com.pulse.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "alerts",
        indexes = {
                @Index(
                        name = "idx_alert_device_time",
                        columnList = "device_id, triggered_at"
                ),
                @Index(
                        name = "idx_alert_status_time",
                        columnList = "status, triggered_at"
                ),
                @Index(
                        name = "idx_alert_assigned_user",
                        columnList = "assigned_to_user_id"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alerts {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "alert_id", updatable = false, nullable = false)
    private UUID alertId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private Devices device;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alert_rule_id")
    private AlertRule alertRule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to_user_id")
    private Users assignedToUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resolved_by_user_id")
    private Users resolvedByUser;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "alert_type", nullable = false)
    private AlertType alertType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Severity severity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private AlertStatus status = AlertStatus.OPEN;

    @Column(name = "triggered_at", nullable = false)
    private LocalDateTime triggeredAt;

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;
}