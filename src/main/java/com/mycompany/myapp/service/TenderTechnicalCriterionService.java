package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.TenderTechnicalCriterionDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.TenderTechnicalCriterion}.
 */
public interface TenderTechnicalCriterionService {
    /**
     * Save a tenderTechnicalCriterion.
     *
     * @param tenderTechnicalCriterionDTO the entity to save.
     * @return the persisted entity.
     */
    TenderTechnicalCriterionDTO save(TenderTechnicalCriterionDTO tenderTechnicalCriterionDTO);

    /**
     * Partially updates a tenderTechnicalCriterion.
     *
     * @param tenderTechnicalCriterionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TenderTechnicalCriterionDTO> partialUpdate(TenderTechnicalCriterionDTO tenderTechnicalCriterionDTO);

    /**
     * Get all the tenderTechnicalCriteria.
     *
     * @return the list of entities.
     */
    List<TenderTechnicalCriterionDTO> findAll();

    /**
     * Get the "id" tenderTechnicalCriterion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenderTechnicalCriterionDTO> findOne(Long id);

    /**
     * Delete the "id" tenderTechnicalCriterion.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
