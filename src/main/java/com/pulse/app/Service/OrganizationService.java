package com.pulse.app.Service;

import com.pulse.app.dto.request.OrganizationRequest;
import com.pulse.app.dto.response.OrganizationResponse;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface OrganizationService {
    OrganizationResponse createOrganization(@Valid OrganizationRequest request);

    OrganizationResponse getOrganization(UUID id);

    List<OrganizationResponse> getAllOrganizations();

    OrganizationResponse updateOrganization(UUID id, @Valid OrganizationRequest request);
}
