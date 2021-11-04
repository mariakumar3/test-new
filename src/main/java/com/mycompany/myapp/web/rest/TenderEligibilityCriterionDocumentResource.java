package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.TenderEligibilityCriterionDocumentRepository;
import com.mycompany.myapp.service.TenderEligibilityCriterionDocumentService;
import com.mycompany.myapp.service.dto.TenderEligibilityCriterionDocumentDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TenderEligibilityCriterionDocument}.
 */
@RestController
@RequestMapping("/api")
public class TenderEligibilityCriterionDocumentResource {

    private final Logger log = LoggerFactory.getLogger(TenderEligibilityCriterionDocumentResource.class);

    private static final String ENTITY_NAME = "testnewTenderEligibilityCriterionDocument";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderEligibilityCriterionDocumentService tenderEligibilityCriterionDocumentService;

    private final TenderEligibilityCriterionDocumentRepository tenderEligibilityCriterionDocumentRepository;

    public TenderEligibilityCriterionDocumentResource(
        TenderEligibilityCriterionDocumentService tenderEligibilityCriterionDocumentService,
        TenderEligibilityCriterionDocumentRepository tenderEligibilityCriterionDocumentRepository
    ) {
        this.tenderEligibilityCriterionDocumentService = tenderEligibilityCriterionDocumentService;
        this.tenderEligibilityCriterionDocumentRepository = tenderEligibilityCriterionDocumentRepository;
    }

    /**
     * {@code POST  /tender-eligibility-criterion-documents} : Create a new tenderEligibilityCriterionDocument.
     *
     * @param tenderEligibilityCriterionDocumentDTO the tenderEligibilityCriterionDocumentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderEligibilityCriterionDocumentDTO, or with status {@code 400 (Bad Request)} if the tenderEligibilityCriterionDocument has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-eligibility-criterion-documents")
    public ResponseEntity<TenderEligibilityCriterionDocumentDTO> createTenderEligibilityCriterionDocument(
        @RequestBody TenderEligibilityCriterionDocumentDTO tenderEligibilityCriterionDocumentDTO
    ) throws URISyntaxException {
        log.debug("REST request to save TenderEligibilityCriterionDocument : {}", tenderEligibilityCriterionDocumentDTO);
        if (tenderEligibilityCriterionDocumentDTO.getId() != null) {
            throw new BadRequestAlertException(
                "A new tenderEligibilityCriterionDocument cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        TenderEligibilityCriterionDocumentDTO result = tenderEligibilityCriterionDocumentService.save(
            tenderEligibilityCriterionDocumentDTO
        );
        return ResponseEntity
            .created(new URI("/api/tender-eligibility-criterion-documents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-eligibility-criterion-documents/:id} : Updates an existing tenderEligibilityCriterionDocument.
     *
     * @param id the id of the tenderEligibilityCriterionDocumentDTO to save.
     * @param tenderEligibilityCriterionDocumentDTO the tenderEligibilityCriterionDocumentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderEligibilityCriterionDocumentDTO,
     * or with status {@code 400 (Bad Request)} if the tenderEligibilityCriterionDocumentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderEligibilityCriterionDocumentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-eligibility-criterion-documents/{id}")
    public ResponseEntity<TenderEligibilityCriterionDocumentDTO> updateTenderEligibilityCriterionDocument(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TenderEligibilityCriterionDocumentDTO tenderEligibilityCriterionDocumentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderEligibilityCriterionDocument : {}, {}", id, tenderEligibilityCriterionDocumentDTO);
        if (tenderEligibilityCriterionDocumentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderEligibilityCriterionDocumentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderEligibilityCriterionDocumentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderEligibilityCriterionDocumentDTO result = tenderEligibilityCriterionDocumentService.save(
            tenderEligibilityCriterionDocumentDTO
        );
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    tenderEligibilityCriterionDocumentDTO.getId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /tender-eligibility-criterion-documents/:id} : Partial updates given fields of an existing tenderEligibilityCriterionDocument, field will ignore if it is null
     *
     * @param id the id of the tenderEligibilityCriterionDocumentDTO to save.
     * @param tenderEligibilityCriterionDocumentDTO the tenderEligibilityCriterionDocumentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderEligibilityCriterionDocumentDTO,
     * or with status {@code 400 (Bad Request)} if the tenderEligibilityCriterionDocumentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderEligibilityCriterionDocumentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderEligibilityCriterionDocumentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-eligibility-criterion-documents/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TenderEligibilityCriterionDocumentDTO> partialUpdateTenderEligibilityCriterionDocument(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TenderEligibilityCriterionDocumentDTO tenderEligibilityCriterionDocumentDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update TenderEligibilityCriterionDocument partially : {}, {}",
            id,
            tenderEligibilityCriterionDocumentDTO
        );
        if (tenderEligibilityCriterionDocumentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderEligibilityCriterionDocumentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderEligibilityCriterionDocumentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderEligibilityCriterionDocumentDTO> result = tenderEligibilityCriterionDocumentService.partialUpdate(
            tenderEligibilityCriterionDocumentDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderEligibilityCriterionDocumentDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-eligibility-criterion-documents} : get all the tenderEligibilityCriterionDocuments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderEligibilityCriterionDocuments in body.
     */
    @GetMapping("/tender-eligibility-criterion-documents")
    public List<TenderEligibilityCriterionDocumentDTO> getAllTenderEligibilityCriterionDocuments() {
        log.debug("REST request to get all TenderEligibilityCriterionDocuments");
        return tenderEligibilityCriterionDocumentService.findAll();
    }

    /**
     * {@code GET  /tender-eligibility-criterion-documents/:id} : get the "id" tenderEligibilityCriterionDocument.
     *
     * @param id the id of the tenderEligibilityCriterionDocumentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderEligibilityCriterionDocumentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-eligibility-criterion-documents/{id}")
    public ResponseEntity<TenderEligibilityCriterionDocumentDTO> getTenderEligibilityCriterionDocument(@PathVariable Long id) {
        log.debug("REST request to get TenderEligibilityCriterionDocument : {}", id);
        Optional<TenderEligibilityCriterionDocumentDTO> tenderEligibilityCriterionDocumentDTO = tenderEligibilityCriterionDocumentService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(tenderEligibilityCriterionDocumentDTO);
    }

    /**
     * {@code DELETE  /tender-eligibility-criterion-documents/:id} : delete the "id" tenderEligibilityCriterionDocument.
     *
     * @param id the id of the tenderEligibilityCriterionDocumentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-eligibility-criterion-documents/{id}")
    public ResponseEntity<Void> deleteTenderEligibilityCriterionDocument(@PathVariable Long id) {
        log.debug("REST request to delete TenderEligibilityCriterionDocument : {}", id);
        tenderEligibilityCriterionDocumentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
