package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.TenderEligibilityCriterionDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.TenderEligibilityCriterion}.
 */
public interface TenderEligibilityCriterionService {
    /**
     * Save a tenderEligibilityCriterion.
     *
     * @param tenderEligibilityCriterionDTO the entity to save.
     * @return the persisted entity.
     */
    TenderEligibilityCriterionDTO save(TenderEligibilityCriterionDTO tenderEligibilityCriterionDTO);

    /**
     * Partially updates a tenderEligibilityCriterion.
     *
     * @param tenderEligibilityCriterionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TenderEligibilityCriterionDTO> partialUpdate(TenderEligibilityCriterionDTO tenderEligibilityCriterionDTO);

    /**
     * Get all the tenderEligibilityCriteria.
     *
     * @return the list of entities.
     */
    List<TenderEligibilityCriterionDTO> findAll();

    /**
     * Get the "id" tenderEligibilityCriterion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenderEligibilityCriterionDTO> findOne(Long id);

    /**
     * Delete the "id" tenderEligibilityCriterion.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
