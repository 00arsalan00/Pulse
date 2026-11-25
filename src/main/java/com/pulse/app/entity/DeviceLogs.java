package com.pulse.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "device_logs",
        indexes = {
                @Index(
                        name = "idx_device_logs_device_time",
                        columnList = "device_id, recorded_at"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "log_id", updatable = false, nullable = false)
    private UUID logId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private Devices device;

    @Enumerated(EnumType.STRING)
    @Column(name = "log_level", nullable = false)
    private LogLevel logLevel;

    @Column(nullable = false)
    private String message;

    @Column(name = "recorded_at", nullable = false)
    private LocalDateTime recordedAt;
}