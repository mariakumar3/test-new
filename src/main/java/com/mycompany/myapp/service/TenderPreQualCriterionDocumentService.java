package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.TenderPreQualCriterionDocumentDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.TenderPreQualCriterionDocument}.
 */
public interface TenderPreQualCriterionDocumentService {
    /**
     * Save a tenderPreQualCriterionDocument.
     *
     * @param tenderPreQualCriterionDocumentDTO the entity to save.
     * @return the persisted entity.
     */
    TenderPreQualCriterionDocumentDTO save(TenderPreQualCriterionDocumentDTO tenderPreQualCriterionDocumentDTO);

    /**
     * Partially updates a tenderPreQualCriterionDocument.
     *
     * @param tenderPreQualCriterionDocumentDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TenderPreQualCriterionDocumentDTO> partialUpdate(TenderPreQualCriterionDocumentDTO tenderPreQualCriterionDocumentDTO);

    /**
     * Get all the tenderPreQualCriterionDocuments.
     *
     * @return the list of entities.
     */
    List<TenderPreQualCriterionDocumentDTO> findAll();

    /**
     * Get the "id" tenderPreQualCriterionDocument.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenderPreQualCriterionDocumentDTO> findOne(Long id);

    /**
     * Delete the "id" tenderPreQualCriterionDocument.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
