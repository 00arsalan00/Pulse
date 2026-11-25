package com.pulse.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="alert_action", indexes = {
    @Index(name = "idx_alert_action_alert", columnList = "alert_id"),
    @Index(name = "idx_alert_action_user", columnList = "user_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertAction {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID action_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alert_id", nullable = false)
    private Alerts alert;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private Users users;

    @Enumerated(EnumType.STRING)
    private AlertActionType action;

    @Column(nullable = true)
    private String notes;

    @Column(nullable = false)
    private LocalDateTime actionAt;


}
