package com.pulse.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(
        name = "alert_rules",
        indexes = {
                @Index(
                        name = "idx_alert_rule_organization",
                        columnList = "organization_id"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertRule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "rule_id", updatable = false, nullable = false)
    private UUID ruleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organizations organization;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Metric metric;

    @Enumerated(EnumType.STRING)
    @Column(name = "comparison_operator", nullable = false)
    private ComparisonOperator condition;

    @Column(nullable = false, precision = 19, scale = 6)
    private BigDecimal threshold;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Severity severity;

    @Column(name = "is_enabled", nullable = false)
    @Builder.Default
    private boolean isEnabled = true;
}