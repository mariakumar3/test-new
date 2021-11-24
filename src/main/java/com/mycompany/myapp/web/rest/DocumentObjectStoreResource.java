package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.DocumentObjectStoreRepository;
import com.mycompany.myapp.service.DocumentObjectStoreService;
import com.mycompany.myapp.service.dto.DocumentObjectStoreDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.DocumentObjectStore}.
 */
@RestController
@RequestMapping("/api")
public class DocumentObjectStoreResource {

    private final Logger log = LoggerFactory.getLogger(DocumentObjectStoreResource.class);

    private static final String ENTITY_NAME = "testnewDocumentObjectStore";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DocumentObjectStoreService documentObjectStoreService;

    private final DocumentObjectStoreRepository documentObjectStoreRepository;

    public DocumentObjectStoreResource(
        DocumentObjectStoreService documentObjectStoreService,
        DocumentObjectStoreRepository documentObjectStoreRepository
    ) {
        this.documentObjectStoreService = documentObjectStoreService;
        this.documentObjectStoreRepository = documentObjectStoreRepository;
    }

    /**
     * {@code POST  /document-object-stores} : Create a new documentObjectStore.
     *
     * @param documentObjectStoreDTO the documentObjectStoreDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new documentObjectStoreDTO, or with status {@code 400 (Bad Request)} if the documentObjectStore has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/document-object-stores")
    public ResponseEntity<DocumentObjectStoreDTO> createDocumentObjectStore(@RequestBody DocumentObjectStoreDTO documentObjectStoreDTO)
        throws URISyntaxException {
        log.debug("REST request to save DocumentObjectStore : {}", documentObjectStoreDTO);
        if (documentObjectStoreDTO.getId() != null) {
            throw new BadRequestAlertException("A new documentObjectStore cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DocumentObjectStoreDTO result = documentObjectStoreService.save(documentObjectStoreDTO);
        return ResponseEntity
            .created(new URI("/api/document-object-stores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /document-object-stores/:id} : Updates an existing documentObjectStore.
     *
     * @param id the id of the documentObjectStoreDTO to save.
     * @param documentObjectStoreDTO the documentObjectStoreDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documentObjectStoreDTO,
     * or with status {@code 400 (Bad Request)} if the documentObjectStoreDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the documentObjectStoreDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/document-object-stores/{id}")
    public ResponseEntity<DocumentObjectStoreDTO> updateDocumentObjectStore(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DocumentObjectStoreDTO documentObjectStoreDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DocumentObjectStore : {}, {}", id, documentObjectStoreDTO);
        if (documentObjectStoreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, documentObjectStoreDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!documentObjectStoreRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DocumentObjectStoreDTO result = documentObjectStoreService.save(documentObjectStoreDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, documentObjectStoreDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /document-object-stores/:id} : Partial updates given fields of an existing documentObjectStore, field will ignore if it is null
     *
     * @param id the id of the documentObjectStoreDTO to save.
     * @param documentObjectStoreDTO the documentObjectStoreDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documentObjectStoreDTO,
     * or with status {@code 400 (Bad Request)} if the documentObjectStoreDTO is not valid,
     * or with status {@code 404 (Not Found)} if the documentObjectStoreDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the documentObjectStoreDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/document-object-stores/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DocumentObjectStoreDTO> partialUpdateDocumentObjectStore(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DocumentObjectStoreDTO documentObjectStoreDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DocumentObjectStore partially : {}, {}", id, documentObjectStoreDTO);
        if (documentObjectStoreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, documentObjectStoreDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!documentObjectStoreRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DocumentObjectStoreDTO> result = documentObjectStoreService.partialUpdate(documentObjectStoreDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, documentObjectStoreDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /document-object-stores} : get all the documentObjectStores.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of documentObjectStores in body.
     */
    @GetMapping("/document-object-stores")
    public List<DocumentObjectStoreDTO> getAllDocumentObjectStores() {
        log.debug("REST request to get all DocumentObjectStores");
        return documentObjectStoreService.findAll();
    }

    /**
     * {@code GET  /document-object-stores/:id} : get the "id" documentObjectStore.
     *
     * @param id the id of the documentObjectStoreDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the documentObjectStoreDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/document-object-stores/{id}")
    public ResponseEntity<DocumentObjectStoreDTO> getDocumentObjectStore(@PathVariable Long id) {
        log.debug("REST request to get DocumentObjectStore : {}", id);
        Optional<DocumentObjectStoreDTO> documentObjectStoreDTO = documentObjectStoreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(documentObjectStoreDTO);
    }

    /**
     * {@code DELETE  /document-object-stores/:id} : delete the "id" documentObjectStore.
     *
     * @param id the id of the documentObjectStoreDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/document-object-stores/{id}")
    public ResponseEntity<Void> deleteDocumentObjectStore(@PathVariable Long id) {
        log.debug("REST request to delete DocumentObjectStore : {}", id);
        documentObjectStoreService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
