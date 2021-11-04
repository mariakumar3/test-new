package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.TenderEligibilityCriterionRepository;
import com.mycompany.myapp.service.TenderEligibilityCriterionService;
import com.mycompany.myapp.service.dto.TenderEligibilityCriterionDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TenderEligibilityCriterion}.
 */
@RestController
@RequestMapping("/api")
public class TenderEligibilityCriterionResource {

    private final Logger log = LoggerFactory.getLogger(TenderEligibilityCriterionResource.class);

    private static final String ENTITY_NAME = "testnewTenderEligibilityCriterion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenderEligibilityCriterionService tenderEligibilityCriterionService;

    private final TenderEligibilityCriterionRepository tenderEligibilityCriterionRepository;

    public TenderEligibilityCriterionResource(
        TenderEligibilityCriterionService tenderEligibilityCriterionService,
        TenderEligibilityCriterionRepository tenderEligibilityCriterionRepository
    ) {
        this.tenderEligibilityCriterionService = tenderEligibilityCriterionService;
        this.tenderEligibilityCriterionRepository = tenderEligibilityCriterionRepository;
    }

    /**
     * {@code POST  /tender-eligibility-criteria} : Create a new tenderEligibilityCriterion.
     *
     * @param tenderEligibilityCriterionDTO the tenderEligibilityCriterionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenderEligibilityCriterionDTO, or with status {@code 400 (Bad Request)} if the tenderEligibilityCriterion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tender-eligibility-criteria")
    public ResponseEntity<TenderEligibilityCriterionDTO> createTenderEligibilityCriterion(
        @RequestBody TenderEligibilityCriterionDTO tenderEligibilityCriterionDTO
    ) throws URISyntaxException {
        log.debug("REST request to save TenderEligibilityCriterion : {}", tenderEligibilityCriterionDTO);
        if (tenderEligibilityCriterionDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenderEligibilityCriterion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenderEligibilityCriterionDTO result = tenderEligibilityCriterionService.save(tenderEligibilityCriterionDTO);
        return ResponseEntity
            .created(new URI("/api/tender-eligibility-criteria/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tender-eligibility-criteria/:id} : Updates an existing tenderEligibilityCriterion.
     *
     * @param id the id of the tenderEligibilityCriterionDTO to save.
     * @param tenderEligibilityCriterionDTO the tenderEligibilityCriterionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderEligibilityCriterionDTO,
     * or with status {@code 400 (Bad Request)} if the tenderEligibilityCriterionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenderEligibilityCriterionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tender-eligibility-criteria/{id}")
    public ResponseEntity<TenderEligibilityCriterionDTO> updateTenderEligibilityCriterion(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TenderEligibilityCriterionDTO tenderEligibilityCriterionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenderEligibilityCriterion : {}, {}", id, tenderEligibilityCriterionDTO);
        if (tenderEligibilityCriterionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderEligibilityCriterionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderEligibilityCriterionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TenderEligibilityCriterionDTO result = tenderEligibilityCriterionService.save(tenderEligibilityCriterionDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderEligibilityCriterionDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /tender-eligibility-criteria/:id} : Partial updates given fields of an existing tenderEligibilityCriterion, field will ignore if it is null
     *
     * @param id the id of the tenderEligibilityCriterionDTO to save.
     * @param tenderEligibilityCriterionDTO the tenderEligibilityCriterionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenderEligibilityCriterionDTO,
     * or with status {@code 400 (Bad Request)} if the tenderEligibilityCriterionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenderEligibilityCriterionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenderEligibilityCriterionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tender-eligibility-criteria/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TenderEligibilityCriterionDTO> partialUpdateTenderEligibilityCriterion(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TenderEligibilityCriterionDTO tenderEligibilityCriterionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TenderEligibilityCriterion partially : {}, {}", id, tenderEligibilityCriterionDTO);
        if (tenderEligibilityCriterionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenderEligibilityCriterionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tenderEligibilityCriterionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TenderEligibilityCriterionDTO> result = tenderEligibilityCriterionService.partialUpdate(tenderEligibilityCriterionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenderEligibilityCriterionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tender-eligibility-criteria} : get all the tenderEligibilityCriteria.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenderEligibilityCriteria in body.
     */
    @GetMapping("/tender-eligibility-criteria")
    public List<TenderEligibilityCriterionDTO> getAllTenderEligibilityCriteria() {
        log.debug("REST request to get all TenderEligibilityCriteria");
        return tenderEligibilityCriterionService.findAll();
    }

    /**
     * {@code GET  /tender-eligibility-criteria/:id} : get the "id" tenderEligibilityCriterion.
     *
     * @param id the id of the tenderEligibilityCriterionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenderEligibilityCriterionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tender-eligibility-criteria/{id}")
    public ResponseEntity<TenderEligibilityCriterionDTO> getTenderEligibilityCriterion(@PathVariable Long id) {
        log.debug("REST request to get TenderEligibilityCriterion : {}", id);
        Optional<TenderEligibilityCriterionDTO> tenderEligibilityCriterionDTO = tenderEligibilityCriterionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenderEligibilityCriterionDTO);
    }

    /**
     * {@code DELETE  /tender-eligibility-criteria/:id} : delete the "id" tenderEligibilityCriterion.
     *
     * @param id the id of the tenderEligibilityCriterionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tender-eligibility-criteria/{id}")
    public ResponseEntity<Void> deleteTenderEligibilityCriterion(@PathVariable Long id) {
        log.debug("REST request to delete TenderEligibilityCriterion : {}", id);
        tenderEligibilityCriterionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
