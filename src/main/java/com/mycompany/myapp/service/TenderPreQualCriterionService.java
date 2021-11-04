package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.TenderPreQualCriterionDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.TenderPreQualCriterion}.
 */
public interface TenderPreQualCriterionService {
    /**
     * Save a tenderPreQualCriterion.
     *
     * @param tenderPreQualCriterionDTO the entity to save.
     * @return the persisted entity.
     */
    TenderPreQualCriterionDTO save(TenderPreQualCriterionDTO tenderPreQualCriterionDTO);

    /**
     * Partially updates a tenderPreQualCriterion.
     *
     * @param tenderPreQualCriterionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TenderPreQualCriterionDTO> partialUpdate(TenderPreQualCriterionDTO tenderPreQualCriterionDTO);

    /**
     * Get all the tenderPreQualCriteria.
     *
     * @return the list of entities.
     */
    List<TenderPreQualCriterionDTO> findAll();

    /**
     * Get the "id" tenderPreQualCriterion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenderPreQualCriterionDTO> findOne(Long id);

    /**
     * Delete the "id" tenderPreQualCriterion.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
