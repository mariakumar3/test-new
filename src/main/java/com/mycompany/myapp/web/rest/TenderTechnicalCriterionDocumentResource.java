package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.TenderTechnicalCriterionDocumentRepository;
import com.mycompany.myapp.service.TenderTechnicalCriterionDocumentService;
import com.mycompany.myapp.service.dto.TenderTechnicalCriterionDocumentDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TenderTechnicalCriterionDocument}.
 */
@RestController
@RequestMapping("/api")
public class TenderTechnicalCriterionDocumentResource {

    private final Logger log = LoggerFactory.getLogger(TenderTechnicalCriterionDocumentResource.class);

    private static final String ENTITY_NAME = "testnewTenderTechnicalCriterionDocument";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderTechnicalCriterionDocumentService tenderTechnicalCriterionDocumentService;

    private final TenderTechnicalCriterionDocumentRepository tenderTechnicalCriterionDocumentRepository;

    public TenderTechnicalCriterionDocumentResource(
        TenderTechnicalCriterionDocumentService tenderTechnicalCriterionDocumentService,
        TenderTechnicalCriterionDocumentRepository tenderTechnicalCriterionDocumentRepository
    ) {
        this.tenderTechnicalCriterionDocumentService = tenderTechnicalCriterionDocumentService;
        this.tenderTechnicalCriterionDocumentRepository = tenderTechnicalCriterionDocumentRepository;
    }

    /**
     * {@code POST  /tender-technical-criterion-documents} : Create a new tenderTechnicalCriterionDocument.
     *
     * @param tenderTechnicalCriterionDocumentDTO the tenderTechnicalCriterionDocumentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderTechnicalCriterionDocumentDTO, or with status {@code 400 (Bad Request)} if the tenderTechnicalCriterionDocument has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-technical-criterion-documents")
    public ResponseEntity<TenderTechnicalCriterionDocumentDTO> createTenderTechnicalCriterionDocument(
        @RequestBody TenderTechnicalCriterionDocumentDTO tenderTechnicalCriterionDocumentDTO
    ) throws URISyntaxException {
        log.debug("REST request to save TenderTechnicalCriterionDocument : {}", tenderTechnicalCriterionDocumentDTO);
        if (tenderTechnicalCriterionDocumentDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenderTechnicalCriterionDocument cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderTechnicalCriterionDocumentDTO result = tenderTechnicalCriterionDocumentService.save(tenderTechnicalCriterionDocumentDTO);
        return ResponseEntity
            .created(new URI("/api/tender-technical-criterion-documents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-technical-criterion-documents/:id} : Updates an existing tenderTechnicalCriterionDocument.
     *
     * @param id the id of the tenderTechnicalCriterionDocumentDTO to save.
     * @param tenderTechnicalCriterionDocumentDTO the tenderTechnicalCriterionDocumentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderTechnicalCriterionDocumentDTO,
     * or with status {@code 400 (Bad Request)} if the tenderTechnicalCriterionDocumentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderTechnicalCriterionDocumentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-technical-criterion-documents/{id}")
    public ResponseEntity<TenderTechnicalCriterionDocumentDTO> updateTenderTechnicalCriterionDocument(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TenderTechnicalCriterionDocumentDTO tenderTechnicalCriterionDocumentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderTechnicalCriterionDocument : {}, {}", id, tenderTechnicalCriterionDocumentDTO);
        if (tenderTechnicalCriterionDocumentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderTechnicalCriterionDocumentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderTechnicalCriterionDocumentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderTechnicalCriterionDocumentDTO result = tenderTechnicalCriterionDocumentService.save(tenderTechnicalCriterionDocumentDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    tenderTechnicalCriterionDocumentDTO.getId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /tender-technical-criterion-documents/:id} : Partial updates given fields of an existing tenderTechnicalCriterionDocument, field will ignore if it is null
     *
     * @param id the id of the tenderTechnicalCriterionDocumentDTO to save.
     * @param tenderTechnicalCriterionDocumentDTO the tenderTechnicalCriterionDocumentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderTechnicalCriterionDocumentDTO,
     * or with status {@code 400 (Bad Request)} if the tenderTechnicalCriterionDocumentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderTechnicalCriterionDocumentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderTechnicalCriterionDocumentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-technical-criterion-documents/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TenderTechnicalCriterionDocumentDTO> partialUpdateTenderTechnicalCriterionDocument(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TenderTechnicalCriterionDocumentDTO tenderTechnicalCriterionDocumentDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update TenderTechnicalCriterionDocument partially : {}, {}",
            id,
            tenderTechnicalCriterionDocumentDTO
        );
        if (tenderTechnicalCriterionDocumentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderTechnicalCriterionDocumentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderTechnicalCriterionDocumentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderTechnicalCriterionDocumentDTO> result = tenderTechnicalCriterionDocumentService.partialUpdate(
            tenderTechnicalCriterionDocumentDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderTechnicalCriterionDocumentDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-technical-criterion-documents} : get all the tenderTechnicalCriterionDocuments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderTechnicalCriterionDocuments in body.
     */
    @GetMapping("/tender-technical-criterion-documents")
    public List<TenderTechnicalCriterionDocumentDTO> getAllTenderTechnicalCriterionDocuments() {
        log.debug("REST request to get all TenderTechnicalCriterionDocuments");
        return tenderTechnicalCriterionDocumentService.findAll();
    }

    /**
     * {@code GET  /tender-technical-criterion-documents/:id} : get the "id" tenderTechnicalCriterionDocument.
     *
     * @param id the id of the tenderTechnicalCriterionDocumentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderTechnicalCriterionDocumentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-technical-criterion-documents/{id}")
    public ResponseEntity<TenderTechnicalCriterionDocumentDTO> getTenderTechnicalCriterionDocument(@PathVariable Long id) {
        log.debug("REST request to get TenderTechnicalCriterionDocument : {}", id);
        Optional<TenderTechnicalCriterionDocumentDTO> tenderTechnicalCriterionDocumentDTO = tenderTechnicalCriterionDocumentService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(tenderTechnicalCriterionDocumentDTO);
    }

    /**
     * {@code DELETE  /tender-technical-criterion-documents/:id} : delete the "id" tenderTechnicalCriterionDocument.
     *
     * @param id the id of the tenderTechnicalCriterionDocumentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-technical-criterion-documents/{id}")
    public ResponseEntity<Void> deleteTenderTechnicalCriterionDocument(@PathVariable Long id) {
        log.debug("REST request to delete TenderTechnicalCriterionDocument : {}", id);
        tenderTechnicalCriterionDocumentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
