package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.TenderTechnicalCriterionDocumentDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.TenderTechnicalCriterionDocument}.
 */
public interface TenderTechnicalCriterionDocumentService {
    /**
     * Save a tenderTechnicalCriterionDocument.
     *
     * @param tenderTechnicalCriterionDocumentDTO the entity to save.
     * @return the persisted entity.
     */
    TenderTechnicalCriterionDocumentDTO save(TenderTechnicalCriterionDocumentDTO tenderTechnicalCriterionDocumentDTO);

    /**
     * Partially updates a tenderTechnicalCriterionDocument.
     *
     * @param tenderTechnicalCriterionDocumentDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TenderTechnicalCriterionDocumentDTO> partialUpdate(TenderTechnicalCriterionDocumentDTO tenderTechnicalCriterionDocumentDTO);

    /**
     * Get all the tenderTechnicalCriterionDocuments.
     *
     * @return the list of entities.
     */
    List<TenderTechnicalCriterionDocumentDTO> findAll();

    /**
     * Get the "id" tenderTechnicalCriterionDocument.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenderTechnicalCriterionDocumentDTO> findOne(Long id);

    /**
     * Delete the "id" tenderTechnicalCriterionDocument.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
