package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.TenderPreQualCriterionDocumentRepository;
import com.mycompany.myapp.service.TenderPreQualCriterionDocumentService;
import com.mycompany.myapp.service.dto.TenderPreQualCriterionDocumentDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TenderPreQualCriterionDocument}.
 */
@RestController
@RequestMapping("/api")
public class TenderPreQualCriterionDocumentResource {

    private final Logger log = LoggerFactory.getLogger(TenderPreQualCriterionDocumentResource.class);

    private static final String ENTITY_NAME = "testnewTenderPreQualCriterionDocument";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderPreQualCriterionDocumentService tenderPreQualCriterionDocumentService;

    private final TenderPreQualCriterionDocumentRepository tenderPreQualCriterionDocumentRepository;

    public TenderPreQualCriterionDocumentResource(
        TenderPreQualCriterionDocumentService tenderPreQualCriterionDocumentService,
        TenderPreQualCriterionDocumentRepository tenderPreQualCriterionDocumentRepository
    ) {
        this.tenderPreQualCriterionDocumentService = tenderPreQualCriterionDocumentService;
        this.tenderPreQualCriterionDocumentRepository = tenderPreQualCriterionDocumentRepository;
    }

    /**
     * {@code POST  /tender-pre-qual-criterion-documents} : Create a new tenderPreQualCriterionDocument.
     *
     * @param tenderPreQualCriterionDocumentDTO the tenderPreQualCriterionDocumentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderPreQualCriterionDocumentDTO, or with status {@code 400 (Bad Request)} if the tenderPreQualCriterionDocument has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-pre-qual-criterion-documents")
    public ResponseEntity<TenderPreQualCriterionDocumentDTO> createTenderPreQualCriterionDocument(
        @RequestBody TenderPreQualCriterionDocumentDTO tenderPreQualCriterionDocumentDTO
    ) throws URISyntaxException {
        log.debug("REST request to save TenderPreQualCriterionDocument : {}", tenderPreQualCriterionDocumentDTO);
        if (tenderPreQualCriterionDocumentDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenderPreQualCriterionDocument cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderPreQualCriterionDocumentDTO result = tenderPreQualCriterionDocumentService.save(tenderPreQualCriterionDocumentDTO);
        return ResponseEntity
            .created(new URI("/api/tender-pre-qual-criterion-documents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-pre-qual-criterion-documents/:id} : Updates an existing tenderPreQualCriterionDocument.
     *
     * @param id the id of the tenderPreQualCriterionDocumentDTO to save.
     * @param tenderPreQualCriterionDocumentDTO the tenderPreQualCriterionDocumentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderPreQualCriterionDocumentDTO,
     * or with status {@code 400 (Bad Request)} if the tenderPreQualCriterionDocumentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderPreQualCriterionDocumentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-pre-qual-criterion-documents/{id}")
    public ResponseEntity<TenderPreQualCriterionDocumentDTO> updateTenderPreQualCriterionDocument(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TenderPreQualCriterionDocumentDTO tenderPreQualCriterionDocumentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderPreQualCriterionDocument : {}, {}", id, tenderPreQualCriterionDocumentDTO);
        if (tenderPreQualCriterionDocumentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderPreQualCriterionDocumentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderPreQualCriterionDocumentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderPreQualCriterionDocumentDTO result = tenderPreQualCriterionDocumentService.save(tenderPreQualCriterionDocumentDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderPreQualCriterionDocumentDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /tender-pre-qual-criterion-documents/:id} : Partial updates given fields of an existing tenderPreQualCriterionDocument, field will ignore if it is null
     *
     * @param id the id of the tenderPreQualCriterionDocumentDTO to save.
     * @param tenderPreQualCriterionDocumentDTO the tenderPreQualCriterionDocumentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderPreQualCriterionDocumentDTO,
     * or with status {@code 400 (Bad Request)} if the tenderPreQualCriterionDocumentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderPreQualCriterionDocumentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderPreQualCriterionDocumentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-pre-qual-criterion-documents/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TenderPreQualCriterionDocumentDTO> partialUpdateTenderPreQualCriterionDocument(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TenderPreQualCriterionDocumentDTO tenderPreQualCriterionDocumentDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update TenderPreQualCriterionDocument partially : {}, {}",
            id,
            tenderPreQualCriterionDocumentDTO
        );
        if (tenderPreQualCriterionDocumentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderPreQualCriterionDocumentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderPreQualCriterionDocumentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderPreQualCriterionDocumentDTO> result = tenderPreQualCriterionDocumentService.partialUpdate(
            tenderPreQualCriterionDocumentDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderPreQualCriterionDocumentDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-pre-qual-criterion-documents} : get all the tenderPreQualCriterionDocuments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderPreQualCriterionDocuments in body.
     */
    @GetMapping("/tender-pre-qual-criterion-documents")
    public List<TenderPreQualCriterionDocumentDTO> getAllTenderPreQualCriterionDocuments() {
        log.debug("REST request to get all TenderPreQualCriterionDocuments");
        return tenderPreQualCriterionDocumentService.findAll();
    }

    /**
     * {@code GET  /tender-pre-qual-criterion-documents/:id} : get the "id" tenderPreQualCriterionDocument.
     *
     * @param id the id of the tenderPreQualCriterionDocumentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderPreQualCriterionDocumentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-pre-qual-criterion-documents/{id}")
    public ResponseEntity<TenderPreQualCriterionDocumentDTO> getTenderPreQualCriterionDocument(@PathVariable Long id) {
        log.debug("REST request to get TenderPreQualCriterionDocument : {}", id);
        Optional<TenderPreQualCriterionDocumentDTO> tenderPreQualCriterionDocumentDTO = tenderPreQualCriterionDocumentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenderPreQualCriterionDocumentDTO);
    }

    /**
     * {@code DELETE  /tender-pre-qual-criterion-documents/:id} : delete the "id" tenderPreQualCriterionDocument.
     *
     * @param id the id of the tenderPreQualCriterionDocumentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-pre-qual-criterion-documents/{id}")
    public ResponseEntity<Void> deleteTenderPreQualCriterionDocument(@PathVariable Long id) {
        log.debug("REST request to delete TenderPreQualCriterionDocument : {}", id);
        tenderPreQualCriterionDocumentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
