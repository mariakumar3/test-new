package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TenderTechnicalCriterionDocument;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TenderTechnicalCriterionDocument entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenderTechnicalCriterionDocumentRepository extends JpaRepository<TenderTechnicalCriterionDocument, Long> {}
