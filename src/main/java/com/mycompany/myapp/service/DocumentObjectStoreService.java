package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DocumentObjectStoreDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DocumentObjectStore}.
 */
public interface DocumentObjectStoreService {
    /**
     * Save a documentObjectStore.
     *
     * @param documentObjectStoreDTO the entity to save.
     * @return the persisted entity.
     */
    DocumentObjectStoreDTO save(DocumentObjectStoreDTO documentObjectStoreDTO);

    /**
     * Partially updates a documentObjectStore.
     *
     * @param documentObjectStoreDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DocumentObjectStoreDTO> partialUpdate(DocumentObjectStoreDTO documentObjectStoreDTO);

    /**
     * Get all the documentObjectStores.
     *
     * @return the list of entities.
     */
    List<DocumentObjectStoreDTO> findAll();

    /**
     * Get the "id" documentObjectStore.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DocumentObjectStoreDTO> findOne(Long id);

    /**
     * Delete the "id" documentObjectStore.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
