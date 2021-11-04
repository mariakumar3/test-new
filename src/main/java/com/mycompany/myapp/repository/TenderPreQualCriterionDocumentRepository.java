package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TenderPreQualCriterionDocument;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TenderPreQualCriterionDocument entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenderPreQualCriterionDocumentRepository extends JpaRepository<TenderPreQualCriterionDocument, Long> {}
