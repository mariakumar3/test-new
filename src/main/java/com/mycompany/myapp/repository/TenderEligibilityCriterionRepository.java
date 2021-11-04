package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TenderEligibilityCriterion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TenderEligibilityCriterion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenderEligibilityCriterionRepository extends JpaRepository<TenderEligibilityCriterion, Long> {}
