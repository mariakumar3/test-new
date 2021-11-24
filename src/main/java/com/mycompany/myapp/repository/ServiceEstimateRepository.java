package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ServiceEstimate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ServiceEstimate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServiceEstimateRepository extends JpaRepository<ServiceEstimate, Long> {}
