package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.TenderPreQualCriterionRepository;
import com.mycompany.myapp.service.TenderPreQualCriterionService;
import com.mycompany.myapp.service.dto.TenderPreQualCriterionDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TenderPreQualCriterion}.
 */
@RestController
@RequestMapping("/api")
public class TenderPreQualCriterionResource {

    private final Logger log = LoggerFactory.getLogger(TenderPreQualCriterionResource.class);

    private static final String ENTITY_NAME = "testnewTenderPreQualCriterion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderPreQualCriterionService tenderPreQualCriterionService;

    private final TenderPreQualCriterionRepository tenderPreQualCriterionRepository;

    public TenderPreQualCriterionResource(
        TenderPreQualCriterionService tenderPreQualCriterionService,
        TenderPreQualCriterionRepository tenderPreQualCriterionRepository
    ) {
        this.tenderPreQualCriterionService = tenderPreQualCriterionService;
        this.tenderPreQualCriterionRepository = tenderPreQualCriterionRepository;
    }

    /**
     * {@code POST  /tender-pre-qual-criteria} : Create a new tenderPreQualCriterion.
     *
     * @param tenderPreQualCriterionDTO the tenderPreQualCriterionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderPreQualCriterionDTO, or with status {@code 400 (Bad Request)} if the tenderPreQualCriterion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-pre-qual-criteria")
    public ResponseEntity<TenderPreQualCriterionDTO> createTenderPreQualCriterion(
        @RequestBody TenderPreQualCriterionDTO tenderPreQualCriterionDTO
    ) throws URISyntaxException {
        log.debug("REST request to save TenderPreQualCriterion : {}", tenderPreQualCriterionDTO);
        if (tenderPreQualCriterionDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenderPreQualCriterion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderPreQualCriterionDTO result = tenderPreQualCriterionService.save(tenderPreQualCriterionDTO);
        return ResponseEntity
            .created(new URI("/api/tender-pre-qual-criteria/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-pre-qual-criteria/:id} : Updates an existing tenderPreQualCriterion.
     *
     * @param id the id of the tenderPreQualCriterionDTO to save.
     * @param tenderPreQualCriterionDTO the tenderPreQualCriterionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderPreQualCriterionDTO,
     * or with status {@code 400 (Bad Request)} if the tenderPreQualCriterionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderPreQualCriterionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-pre-qual-criteria/{id}")
    public ResponseEntity<TenderPreQualCriterionDTO> updateTenderPreQualCriterion(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TenderPreQualCriterionDTO tenderPreQualCriterionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderPreQualCriterion : {}, {}", id, tenderPreQualCriterionDTO);
        if (tenderPreQualCriterionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderPreQualCriterionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderPreQualCriterionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderPreQualCriterionDTO result = tenderPreQualCriterionService.save(tenderPreQualCriterionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderPreQualCriterionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tender-pre-qual-criteria/:id} : Partial updates given fields of an existing tenderPreQualCriterion, field will ignore if it is null
     *
     * @param id the id of the tenderPreQualCriterionDTO to save.
     * @param tenderPreQualCriterionDTO the tenderPreQualCriterionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderPreQualCriterionDTO,
     * or with status {@code 400 (Bad Request)} if the tenderPreQualCriterionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderPreQualCriterionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderPreQualCriterionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-pre-qual-criteria/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TenderPreQualCriterionDTO> partialUpdateTenderPreQualCriterion(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TenderPreQualCriterionDTO tenderPreQualCriterionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TenderPreQualCriterion partially : {}, {}", id, tenderPreQualCriterionDTO);
        if (tenderPreQualCriterionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderPreQualCriterionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderPreQualCriterionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderPreQualCriterionDTO> result = tenderPreQualCriterionService.partialUpdate(tenderPreQualCriterionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderPreQualCriterionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-pre-qual-criteria} : get all the tenderPreQualCriteria.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderPreQualCriteria in body.
     */
    @GetMapping("/tender-pre-qual-criteria")
    public List<TenderPreQualCriterionDTO> getAllTenderPreQualCriteria() {
        log.debug("REST request to get all TenderPreQualCriteria");
        return tenderPreQualCriterionService.findAll();
    }

    /**
     * {@code GET  /tender-pre-qual-criteria/:id} : get the "id" tenderPreQualCriterion.
     *
     * @param id the id of the tenderPreQualCriterionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderPreQualCriterionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-pre-qual-criteria/{id}")
    public ResponseEntity<TenderPreQualCriterionDTO> getTenderPreQualCriterion(@PathVariable Long id) {
        log.debug("REST request to get TenderPreQualCriterion : {}", id);
        Optional<TenderPreQualCriterionDTO> tenderPreQualCriterionDTO = tenderPreQualCriterionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenderPreQualCriterionDTO);
    }

    /**
     * {@code DELETE  /tender-pre-qual-criteria/:id} : delete the "id" tenderPreQualCriterion.
     *
     * @param id the id of the tenderPreQualCriterionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-pre-qual-criteria/{id}")
    public ResponseEntity<Void> deleteTenderPreQualCriterion(@PathVariable Long id) {
        log.debug("REST request to delete TenderPreQualCriterion : {}", id);
        tenderPreQualCriterionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
