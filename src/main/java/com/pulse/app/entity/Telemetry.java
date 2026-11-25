package com.pulse.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "telemetry",
        indexes = {
                @Index(
                        name = "idx_telemetry_device_time",
                        columnList = "device_id, recorded_at"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Telemetry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "telemetry_id", updatable = false, nullable = false)
    private UUID telemetryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private Devices device;

    @Column(nullable = false)
    private Double temperature;

    @Column(nullable = false)
    private Double vibration;

    @Column(nullable = false)
    private Integer rpm;

    @Column(name = "power_kw", nullable = false)
    private Double powerKw;

    @Column(name = "recorded_at", nullable = false)
    private LocalDateTime recordedAt;
}