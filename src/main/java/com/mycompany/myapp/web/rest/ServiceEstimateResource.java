package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.ServiceEstimateRepository;
import com.mycompany.myapp.service.ServiceEstimateService;
import com.mycompany.myapp.service.dto.ServiceEstimateDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ServiceEstimate}.
 */
@RestController
@RequestMapping("/api")
public class ServiceEstimateResource {

    private final Logger log = LoggerFactory.getLogger(ServiceEstimateResource.class);

    private static final String ENTITY_NAME = "testnewServiceEstimate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ServiceEstimateService serviceEstimateService;

    private final ServiceEstimateRepository serviceEstimateRepository;

    public ServiceEstimateResource(ServiceEstimateService serviceEstimateService, ServiceEstimateRepository serviceEstimateRepository) {
        this.serviceEstimateService = serviceEstimateService;
        this.serviceEstimateRepository = serviceEstimateRepository;
    }

    /**
     * {@code POST  /service-estimates} : Create a new serviceEstimate.
     *
     * @param serviceEstimateDTO the serviceEstimateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new serviceEstimateDTO, or with status {@code 400 (Bad Request)} if the serviceEstimate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/service-estimates")
    public ResponseEntity<ServiceEstimateDTO> createServiceEstimate(@RequestBody ServiceEstimateDTO serviceEstimateDTO)
        throws URISyntaxException {
        log.debug("REST request to save ServiceEstimate : {}", serviceEstimateDTO);
        if (serviceEstimateDTO.getId() != null) {
            throw new BadRequestAlertException("A new serviceEstimate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServiceEstimateDTO result = serviceEstimateService.save(serviceEstimateDTO);
        return ResponseEntity
            .created(new URI("/api/service-estimates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /service-estimates/:id} : Updates an existing serviceEstimate.
     *
     * @param id the id of the serviceEstimateDTO to save.
     * @param serviceEstimateDTO the serviceEstimateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serviceEstimateDTO,
     * or with status {@code 400 (Bad Request)} if the serviceEstimateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the serviceEstimateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/service-estimates/{id}")
    public ResponseEntity<ServiceEstimateDTO> updateServiceEstimate(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ServiceEstimateDTO serviceEstimateDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ServiceEstimate : {}, {}", id, serviceEstimateDTO);
        if (serviceEstimateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, serviceEstimateDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!serviceEstimateRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ServiceEstimateDTO result = serviceEstimateService.save(serviceEstimateDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, serviceEstimateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /service-estimates/:id} : Partial updates given fields of an existing serviceEstimate, field will ignore if it is null
     *
     * @param id the id of the serviceEstimateDTO to save.
     * @param serviceEstimateDTO the serviceEstimateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serviceEstimateDTO,
     * or with status {@code 400 (Bad Request)} if the serviceEstimateDTO is not valid,
     * or with status {@code 404 (Not Found)} if the serviceEstimateDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the serviceEstimateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/service-estimates/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ServiceEstimateDTO> partialUpdateServiceEstimate(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ServiceEstimateDTO serviceEstimateDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ServiceEstimate partially : {}, {}", id, serviceEstimateDTO);
        if (serviceEstimateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, serviceEstimateDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!serviceEstimateRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ServiceEstimateDTO> result = serviceEstimateService.partialUpdate(serviceEstimateDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, serviceEstimateDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /service-estimates} : get all the serviceEstimates.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of serviceEstimates in body.
     */
    @GetMapping("/service-estimates")
    public List<ServiceEstimateDTO> getAllServiceEstimates() {
        log.debug("REST request to get all ServiceEstimates");
        return serviceEstimateService.findAll();
    }

    /**
     * {@code GET  /service-estimates/:id} : get the "id" serviceEstimate.
     *
     * @param id the id of the serviceEstimateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the serviceEstimateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/service-estimates/{id}")
    public ResponseEntity<ServiceEstimateDTO> getServiceEstimate(@PathVariable Long id) {
        log.debug("REST request to get ServiceEstimate : {}", id);
        Optional<ServiceEstimateDTO> serviceEstimateDTO = serviceEstimateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(serviceEstimateDTO);
    }

    /**
     * {@code DELETE  /service-estimates/:id} : delete the "id" serviceEstimate.
     *
     * @param id the id of the serviceEstimateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/service-estimates/{id}")
    public ResponseEntity<Void> deleteServiceEstimate(@PathVariable Long id) {
        log.debug("REST request to delete ServiceEstimate : {}", id);
        serviceEstimateService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
