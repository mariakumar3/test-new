package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.TenderEligibilityCriterionDocumentDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.TenderEligibilityCriterionDocument}.
 */
public interface TenderEligibilityCriterionDocumentService {
    /**
     * Save a tenderEligibilityCriterionDocument.
     *
     * @param tenderEligibilityCriterionDocumentDTO the entity to save.
     * @return the persisted entity.
     */
    TenderEligibilityCriterionDocumentDTO save(TenderEligibilityCriterionDocumentDTO tenderEligibilityCriterionDocumentDTO);

    /**
     * Partially updates a tenderEligibilityCriterionDocument.
     *
     * @param tenderEligibilityCriterionDocumentDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TenderEligibilityCriterionDocumentDTO> partialUpdate(
        TenderEligibilityCriterionDocumentDTO tenderEligibilityCriterionDocumentDTO
    );

    /**
     * Get all the tenderEligibilityCriterionDocuments.
     *
     * @return the list of entities.
     */
    List<TenderEligibilityCriterionDocumentDTO> findAll();

    /**
     * Get the "id" tenderEligibilityCriterionDocument.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenderEligibilityCriterionDocumentDTO> findOne(Long id);

    /**
     * Delete the "id" tenderEligibilityCriterionDocument.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
