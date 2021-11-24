package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ServiceEstimateDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ServiceEstimate}.
 */
public interface ServiceEstimateService {
    /**
     * Save a serviceEstimate.
     *
     * @param serviceEstimateDTO the entity to save.
     * @return the persisted entity.
     */
    ServiceEstimateDTO save(ServiceEstimateDTO serviceEstimateDTO);

    /**
     * Partially updates a serviceEstimate.
     *
     * @param serviceEstimateDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ServiceEstimateDTO> partialUpdate(ServiceEstimateDTO serviceEstimateDTO);

    /**
     * Get all the serviceEstimates.
     *
     * @return the list of entities.
     */
    List<ServiceEstimateDTO> findAll();

    /**
     * Get the "id" serviceEstimate.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ServiceEstimateDTO> findOne(Long id);

    /**
     * Delete the "id" serviceEstimate.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
