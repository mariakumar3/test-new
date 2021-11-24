package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ServiceEstimateItemDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ServiceEstimateItem}.
 */
public interface ServiceEstimateItemService {
    /**
     * Save a serviceEstimateItem.
     *
     * @param serviceEstimateItemDTO the entity to save.
     * @return the persisted entity.
     */
    ServiceEstimateItemDTO save(ServiceEstimateItemDTO serviceEstimateItemDTO);

    /**
     * Partially updates a serviceEstimateItem.
     *
     * @param serviceEstimateItemDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ServiceEstimateItemDTO> partialUpdate(ServiceEstimateItemDTO serviceEstimateItemDTO);

    /**
     * Get all the serviceEstimateItems.
     *
     * @return the list of entities.
     */
    List<ServiceEstimateItemDTO> findAll();

    /**
     * Get the "id" serviceEstimateItem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ServiceEstimateItemDTO> findOne(Long id);

    /**
     * Delete the "id" serviceEstimateItem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
