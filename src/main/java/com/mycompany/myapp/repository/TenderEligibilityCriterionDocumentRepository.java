package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TenderEligibilityCriterionDocument;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TenderEligibilityCriterionDocument entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenderEligibilityCriterionDocumentRepository extends JpaRepository<TenderEligibilityCriterionDocument, Long> {}
