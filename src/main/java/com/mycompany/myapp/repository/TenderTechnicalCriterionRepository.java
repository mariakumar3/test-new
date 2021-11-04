package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TenderTechnicalCriterion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TenderTechnicalCriterion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenderTechnicalCriterionRepository extends JpaRepository<TenderTechnicalCriterion, Long> {}
