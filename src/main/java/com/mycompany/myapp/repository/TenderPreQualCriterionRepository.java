package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TenderPreQualCriterion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TenderPreQualCriterion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenderPreQualCriterionRepository extends JpaRepository<TenderPreQualCriterion, Long> {}
