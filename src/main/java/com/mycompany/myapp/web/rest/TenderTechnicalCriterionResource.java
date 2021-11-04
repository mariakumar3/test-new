package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.TenderTechnicalCriterionRepository;
import com.mycompany.myapp.service.TenderTechnicalCriterionService;
import com.mycompany.myapp.service.dto.TenderTechnicalCriterionDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TenderTechnicalCriterion}.
 */
@RestController
@RequestMapping("/api")
public class TenderTechnicalCriterionResource {

    private final Logger log = LoggerFactory.getLogger(TenderTechnicalCriterionResource.class);

    private static final String ENTITY_NAME = "testnewTenderTechnicalCriterion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderTechnicalCriterionService tenderTechnicalCriterionService;

    private final TenderTechnicalCriterionRepository tenderTechnicalCriterionRepository;

    public TenderTechnicalCriterionResource(
        TenderTechnicalCriterionService tenderTechnicalCriterionService,
        TenderTechnicalCriterionRepository tenderTechnicalCriterionRepository
    ) {
        this.tenderTechnicalCriterionService = tenderTechnicalCriterionService;
        this.tenderTechnicalCriterionRepository = tenderTechnicalCriterionRepository;
    }

    /**
     * {@code POST  /tender-technical-criteria} : Create a new tenderTechnicalCriterion.
     *
     * @param tenderTechnicalCriterionDTO the tenderTechnicalCriterionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderTechnicalCriterionDTO, or with status {@code 400 (Bad Request)} if the tenderTechnicalCriterion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-technical-criteria")
    public ResponseEntity<TenderTechnicalCriterionDTO> createTenderTechnicalCriterion(
        @RequestBody TenderTechnicalCriterionDTO tenderTechnicalCriterionDTO
    ) throws URISyntaxException {
        log.debug("REST request to save TenderTechnicalCriterion : {}", tenderTechnicalCriterionDTO);
        if (tenderTechnicalCriterionDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenderTechnicalCriterion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderTechnicalCriterionDTO result = tenderTechnicalCriterionService.save(tenderTechnicalCriterionDTO);
        return ResponseEntity
            .created(new URI("/api/tender-technical-criteria/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-technical-criteria/:id} : Updates an existing tenderTechnicalCriterion.
     *
     * @param id the id of the tenderTechnicalCriterionDTO to save.
     * @param tenderTechnicalCriterionDTO the tenderTechnicalCriterionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderTechnicalCriterionDTO,
     * or with status {@code 400 (Bad Request)} if the tenderTechnicalCriterionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderTechnicalCriterionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-technical-criteria/{id}")
    public ResponseEntity<TenderTechnicalCriterionDTO> updateTenderTechnicalCriterion(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TenderTechnicalCriterionDTO tenderTechnicalCriterionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderTechnicalCriterion : {}, {}", id, tenderTechnicalCriterionDTO);
        if (tenderTechnicalCriterionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderTechnicalCriterionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderTechnicalCriterionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderTechnicalCriterionDTO result = tenderTechnicalCriterionService.save(tenderTechnicalCriterionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderTechnicalCriterionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tender-technical-criteria/:id} : Partial updates given fields of an existing tenderTechnicalCriterion, field will ignore if it is null
     *
     * @param id the id of the tenderTechnicalCriterionDTO to save.
     * @param tenderTechnicalCriterionDTO the tenderTechnicalCriterionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderTechnicalCriterionDTO,
     * or with status {@code 400 (Bad Request)} if the tenderTechnicalCriterionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderTechnicalCriterionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderTechnicalCriterionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-technical-criteria/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TenderTechnicalCriterionDTO> partialUpdateTenderTechnicalCriterion(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TenderTechnicalCriterionDTO tenderTechnicalCriterionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TenderTechnicalCriterion partially : {}, {}", id, tenderTechnicalCriterionDTO);
        if (tenderTechnicalCriterionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderTechnicalCriterionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderTechnicalCriterionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderTechnicalCriterionDTO> result = tenderTechnicalCriterionService.partialUpdate(tenderTechnicalCriterionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderTechnicalCriterionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-technical-criteria} : get all the tenderTechnicalCriteria.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderTechnicalCriteria in body.
     */
    @GetMapping("/tender-technical-criteria")
    public List<TenderTechnicalCriterionDTO> getAllTenderTechnicalCriteria() {
        log.debug("REST request to get all TenderTechnicalCriteria");
        return tenderTechnicalCriterionService.findAll();
    }

    /**
     * {@code GET  /tender-technical-criteria/:id} : get the "id" tenderTechnicalCriterion.
     *
     * @param id the id of the tenderTechnicalCriterionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderTechnicalCriterionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-technical-criteria/{id}")
    public ResponseEntity<TenderTechnicalCriterionDTO> getTenderTechnicalCriterion(@PathVariable Long id) {
        log.debug("REST request to get TenderTechnicalCriterion : {}", id);
        Optional<TenderTechnicalCriterionDTO> tenderTechnicalCriterionDTO = tenderTechnicalCriterionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenderTechnicalCriterionDTO);
    }

    /**
     * {@code DELETE  /tender-technical-criteria/:id} : delete the "id" tenderTechnicalCriterion.
     *
     * @param id the id of the tenderTechnicalCriterionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-technical-criteria/{id}")
    public ResponseEntity<Void> deleteTenderTechnicalCriterion(@PathVariable Long id) {
        log.debug("REST request to delete TenderTechnicalCriterion : {}", id);
        tenderTechnicalCriterionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
