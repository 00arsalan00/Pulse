package com.pulse.app.Service;

import com.pulse.app.Repository.OrganizationRepository;
import com.pulse.app.dto.request.OrganizationRequest;
import com.pulse.app.dto.response.OrganizationResponse;
import com.pulse.app.entity.OrganizationStatus;
import com.pulse.app.entity.Organizations;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrganizationServiceImpl implements OrganizationService{
    private final OrganizationRepository organizationRepository;

    @Override
    public OrganizationResponse createOrganization(OrganizationRequest request){
        if(organizationRepository.existsByEmail(request.email())){
            throw new IllegalArgumentException(
                    "Organization email already exists"
            );
        }

        if (organizationRepository.existsByPhone(request.phone())) {
            throw new IllegalArgumentException(
                    "Organization phone already exists"
            );
        }

        Organizations organization = Organizations.builder()
                .name(request.name().trim())
                .email(request.email().trim().toLowerCase())
                .phone(request.phone().trim())
                .status(OrganizationStatus.ACTIVE)
                .build();

        Organizations savedOrganization =
                organizationRepository.save(organization);

        return mapToResponse(savedOrganization);

    }

    @Override
    @Transactional(readOnly = true)
    public OrganizationResponse getOrganization(UUID id) {

        Organizations organization = organizationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Organization not found with ID: " + id
                ));

        return mapToResponse(organization);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationResponse> getAllOrganizations() {

        return organizationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public OrganizationResponse updateOrganization(
            UUID id,
            OrganizationRequest request
    ) {

        Organizations organization = organizationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Organization not found with ID: " + id
                ));

        String email = request.email().trim().toLowerCase();
        String phone = request.phone().trim();

        if (!organization.getEmail().equalsIgnoreCase(email)
                && organizationRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                    "Organization email already exists"
            );
        }

        if (!organization.getPhone().equals(phone)
                && organizationRepository.existsByPhone(phone)) {
            throw new IllegalArgumentException(
                    "Organization phone already exists"
            );
        }

        organization.setName(request.name().trim());
        organization.setEmail(email);
        organization.setPhone(phone);

        Organizations updatedOrganization =
                organizationRepository.save(organization);

        return mapToResponse(updatedOrganization);
    }

    private OrganizationResponse mapToResponse(Organizations organization) {

        return new OrganizationResponse(
                organization.getOrganizationId(),
                organization.getName(),
                organization.getEmail(),
                organization.getPhone(),
                organization.getStatus(),
                organization.getCreatedAt()
        );
    }



}
