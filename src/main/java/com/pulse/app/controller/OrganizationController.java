package com.pulse.app.controller;

import com.pulse.app.Service.OrganizationService;
import com.pulse.app.dto.request.OrganizationRequest;
import com.pulse.app.dto.response.OrganizationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationResponse> createOrganization(@Valid @RequestBody OrganizationRequest request){
        OrganizationResponse response = organizationService.createOrganization(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationResponse> getOrganization(@PathVariable UUID id){
        OrganizationResponse response = organizationService.getOrganization(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<OrganizationResponse>> getAllOrganization(){
        List<OrganizationResponse> response =
                organizationService.getAllOrganizations();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizationResponse> updateOrganization(@PathVariable UUID id,@Valid @RequestBody OrganizationRequest request){
        OrganizationResponse response = organizationService.updateOrganization(id,request);

        return ResponseEntity.ok(response);
    }


}
