package com.pulse.app.dto.request;

import jakarta.validation.constraints.*;

public record OrganizationRequest(
        @NotBlank(message = "Organization name is required")
        @Size(max=255)
        String name,

        @NotBlank(message = "Organization email is required")
        @Email(message = "Provide valid mail")
        String email,

        @NotBlank(message = "Phone number is required")
        @Pattern(
                regexp = "^\\+?[0-9]{10,15}$",
                message = "Invalid phone number"
        )
        String phone
) {
}
