package com.pulse.app.Repository;

import com.pulse.app.entity.Organizations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrganizationRepository extends JpaRepository<Organizations, UUID> {
    boolean existsByEmail(String name);
    boolean existsByPhone(String phone);
}
