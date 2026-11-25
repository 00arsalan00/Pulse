package com.pulse.app.dto.response;


import com.pulse.app.entity.OrganizationStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrganizationResponse(UUID organizationId,
                                   String name,
                                   String email,
                                   String phone,
                                   OrganizationStatus status,
                                   LocalDateTime createdAt) {
}
