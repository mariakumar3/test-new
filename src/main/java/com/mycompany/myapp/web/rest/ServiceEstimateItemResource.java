package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.ServiceEstimateItemRepository;
import com.mycompany.myapp.service.ServiceEstimateItemService;
import com.mycompany.myapp.service.dto.ServiceEstimateItemDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ServiceEstimateItem}.
 */
@RestController
@RequestMapping("/api")
public class ServiceEstimateItemResource {

    private final Logger log = LoggerFactory.getLogger(ServiceEstimateItemResource.class);

    private static final String ENTITY_NAME = "testnewServiceEstimateItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ServiceEstimateItemService serviceEstimateItemService;

    private final ServiceEstimateItemRepository serviceEstimateItemRepository;

    public ServiceEstimateItemResource(
        ServiceEstimateItemService serviceEstimateItemService,
        ServiceEstimateItemRepository serviceEstimateItemRepository
    ) {
        this.serviceEstimateItemService = serviceEstimateItemService;
        this.serviceEstimateItemRepository = serviceEstimateItemRepository;
    }

    /**
     * {@code POST  /service-estimate-items} : Create a new serviceEstimateItem.
     *
     * @param serviceEstimateItemDTO the serviceEstimateItemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new serviceEstimateItemDTO, or with status {@code 400 (Bad Request)} if the serviceEstimateItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/service-estimate-items")
    public ResponseEntity<ServiceEstimateItemDTO> createServiceEstimateItem(@RequestBody ServiceEstimateItemDTO serviceEstimateItemDTO)
        throws URISyntaxException {
        log.debug("REST request to save ServiceEstimateItem : {}", serviceEstimateItemDTO);
        if (serviceEstimateItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new serviceEstimateItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServiceEstimateItemDTO result = serviceEstimateItemService.save(serviceEstimateItemDTO);
        return ResponseEntity
            .created(new URI("/api/service-estimate-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /service-estimate-items/:id} : Updates an existing serviceEstimateItem.
     *
     * @param id the id of the serviceEstimateItemDTO to save.
     * @param serviceEstimateItemDTO the serviceEstimateItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serviceEstimateItemDTO,
     * or with status {@code 400 (Bad Request)} if the serviceEstimateItemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the serviceEstimateItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/service-estimate-items/{id}")
    public ResponseEntity<ServiceEstimateItemDTO> updateServiceEstimateItem(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ServiceEstimateItemDTO serviceEstimateItemDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ServiceEstimateItem : {}, {}", id, serviceEstimateItemDTO);
        if (serviceEstimateItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, serviceEstimateItemDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!serviceEstimateItemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ServiceEstimateItemDTO result = serviceEstimateItemService.save(serviceEstimateItemDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, serviceEstimateItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /service-estimate-items/:id} : Partial updates given fields of an existing serviceEstimateItem, field will ignore if it is null
     *
     * @param id the id of the serviceEstimateItemDTO to save.
     * @param serviceEstimateItemDTO the serviceEstimateItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serviceEstimateItemDTO,
     * or with status {@code 400 (Bad Request)} if the serviceEstimateItemDTO is not valid,
     * or with status {@code 404 (Not Found)} if the serviceEstimateItemDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the serviceEstimateItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/service-estimate-items/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ServiceEstimateItemDTO> partialUpdateServiceEstimateItem(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ServiceEstimateItemDTO serviceEstimateItemDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ServiceEstimateItem partially : {}, {}", id, serviceEstimateItemDTO);
        if (serviceEstimateItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, serviceEstimateItemDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!serviceEstimateItemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ServiceEstimateItemDTO> result = serviceEstimateItemService.partialUpdate(serviceEstimateItemDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, serviceEstimateItemDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /service-estimate-items} : get all the serviceEstimateItems.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of serviceEstimateItems in body.
     */
    @GetMapping("/service-estimate-items")
    public List<ServiceEstimateItemDTO> getAllServiceEstimateItems() {
        log.debug("REST request to get all ServiceEstimateItems");
        return serviceEstimateItemService.findAll();
    }

    /**
     * {@code GET  /service-estimate-items/:id} : get the "id" serviceEstimateItem.
     *
     * @param id the id of the serviceEstimateItemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the serviceEstimateItemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/service-estimate-items/{id}")
    public ResponseEntity<ServiceEstimateItemDTO> getServiceEstimateItem(@PathVariable Long id) {
        log.debug("REST request to get ServiceEstimateItem : {}", id);
        Optional<ServiceEstimateItemDTO> serviceEstimateItemDTO = serviceEstimateItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(serviceEstimateItemDTO);
    }

    /**
     * {@code DELETE  /service-estimate-items/:id} : delete the "id" serviceEstimateItem.
     *
     * @param id the id of the serviceEstimateItemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/service-estimate-items/{id}")
    public ResponseEntity<Void> deleteServiceEstimateItem(@PathVariable Long id) {
        log.debug("REST request to delete ServiceEstimateItem : {}", id);
        serviceEstimateItemService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
