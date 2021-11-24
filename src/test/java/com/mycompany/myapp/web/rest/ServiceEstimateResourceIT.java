package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.ServiceEstimate;
import com.mycompany.myapp.repository.ServiceEstimateRepository;
import com.mycompany.myapp.service.dto.ServiceEstimateDTO;
import com.mycompany.myapp.service.mapper.ServiceEstimateMapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ServiceEstimateResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ServiceEstimateResourceIT {

    private static final String DEFAULT_SERVICE_ESTIMATE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_ESTIMATE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_ESTIMATE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_ESTIMATE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_ESTIMATE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_ESTIMATE_DESCRIPTION = "BBBBBBBBBB";

    private static final Long DEFAULT_DEPT_ID = 1L;
    private static final Long UPDATED_DEPT_ID = 2L;

    private static final String DEFAULT_DEPT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DEPT_CODE = "BBBBBBBBBB";

    private static final Long DEFAULT_LOCATION_ID = 1L;
    private static final Long UPDATED_LOCATION_ID = 2L;

    private static final String DEFAULT_LOCATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_PROJECT_ID = 1L;
    private static final Long UPDATED_PROJECT_ID = 2L;

    private static final String DEFAULT_PROJECT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_ESTIMATE_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ESTIMATE_AMT = new BigDecimal(2);

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_DOCUMENT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_REFERENCE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BUDGET_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_BUDGET_AMOUNT = new BigDecimal(2);

    private static final String DEFAULT_HOA_DEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_HOA_DEPARTMENT = "BBBBBBBBBB";

    private static final Long DEFAULT_HOA_DEPARTMENT_ID = 1L;
    private static final Long UPDATED_HOA_DEPARTMENT_ID = 2L;

    private static final String DEFAULT_HOA_SECTOR = "AAAAAAAAAA";
    private static final String UPDATED_HOA_SECTOR = "BBBBBBBBBB";

    private static final Long DEFAULT_HOA_SECTOR_ID = 1L;
    private static final Long UPDATED_HOA_SECTOR_ID = 2L;

    private static final String DEFAULT_HOA_LIST = "AAAAAAAAAA";
    private static final String UPDATED_HOA_LIST = "BBBBBBBBBB";

    private static final Long DEFAULT_HOA_LIST_ID = 1L;
    private static final Long UPDATED_HOA_LIST_ID = 2L;

    private static final String DEFAULT_HOA_LINKED_LIST = "AAAAAAAAAA";
    private static final String UPDATED_HOA_LINKED_LIST = "BBBBBBBBBB";

    private static final Long DEFAULT_HOA_LINKED_LIST_ID = 1L;
    private static final Long UPDATED_HOA_LINKED_LIST_ID = 2L;

    private static final Boolean DEFAULT_APPROVED_BUDGET_YN = false;
    private static final Boolean UPDATED_APPROVED_BUDGET_YN = true;

    private static final String ENTITY_API_URL = "/api/service-estimates";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ServiceEstimateRepository serviceEstimateRepository;

    @Autowired
    private ServiceEstimateMapper serviceEstimateMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restServiceEstimateMockMvc;

    private ServiceEstimate serviceEstimate;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceEstimate createEntity(EntityManager em) {
        ServiceEstimate serviceEstimate = new ServiceEstimate()
            .serviceEstimateName(DEFAULT_SERVICE_ESTIMATE_NAME)
            .serviceEstimateNumber(DEFAULT_SERVICE_ESTIMATE_NUMBER)
            .serviceEstimateDescription(DEFAULT_SERVICE_ESTIMATE_DESCRIPTION)
            .deptId(DEFAULT_DEPT_ID)
            .deptCode(DEFAULT_DEPT_CODE)
            .locationId(DEFAULT_LOCATION_ID)
            .locationName(DEFAULT_LOCATION_NAME)
            .projectId(DEFAULT_PROJECT_ID)
            .projectName(DEFAULT_PROJECT_NAME)
            .estimateAmt(DEFAULT_ESTIMATE_AMT)
            .status(DEFAULT_STATUS)
            .documentReference(DEFAULT_DOCUMENT_REFERENCE)
            .budgetAmount(DEFAULT_BUDGET_AMOUNT)
            .hoaDepartment(DEFAULT_HOA_DEPARTMENT)
            .hoaDepartmentId(DEFAULT_HOA_DEPARTMENT_ID)
            .hoaSector(DEFAULT_HOA_SECTOR)
            .hoaSectorId(DEFAULT_HOA_SECTOR_ID)
            .hoaList(DEFAULT_HOA_LIST)
            .hoaListId(DEFAULT_HOA_LIST_ID)
            .hoaLinkedList(DEFAULT_HOA_LINKED_LIST)
            .hoaLinkedListId(DEFAULT_HOA_LINKED_LIST_ID)
            .approvedBudgetYn(DEFAULT_APPROVED_BUDGET_YN);
        return serviceEstimate;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceEstimate createUpdatedEntity(EntityManager em) {
        ServiceEstimate serviceEstimate = new ServiceEstimate()
            .serviceEstimateName(UPDATED_SERVICE_ESTIMATE_NAME)
            .serviceEstimateNumber(UPDATED_SERVICE_ESTIMATE_NUMBER)
            .serviceEstimateDescription(UPDATED_SERVICE_ESTIMATE_DESCRIPTION)
            .deptId(UPDATED_DEPT_ID)
            .deptCode(UPDATED_DEPT_CODE)
            .locationId(UPDATED_LOCATION_ID)
            .locationName(UPDATED_LOCATION_NAME)
            .projectId(UPDATED_PROJECT_ID)
            .projectName(UPDATED_PROJECT_NAME)
            .estimateAmt(UPDATED_ESTIMATE_AMT)
            .status(UPDATED_STATUS)
            .documentReference(UPDATED_DOCUMENT_REFERENCE)
            .budgetAmount(UPDATED_BUDGET_AMOUNT)
            .hoaDepartment(UPDATED_HOA_DEPARTMENT)
            .hoaDepartmentId(UPDATED_HOA_DEPARTMENT_ID)
            .hoaSector(UPDATED_HOA_SECTOR)
            .hoaSectorId(UPDATED_HOA_SECTOR_ID)
            .hoaList(UPDATED_HOA_LIST)
            .hoaListId(UPDATED_HOA_LIST_ID)
            .hoaLinkedList(UPDATED_HOA_LINKED_LIST)
            .hoaLinkedListId(UPDATED_HOA_LINKED_LIST_ID)
            .approvedBudgetYn(UPDATED_APPROVED_BUDGET_YN);
        return serviceEstimate;
    }

    @BeforeEach
    public void initTest() {
        serviceEstimate = createEntity(em);
    }

    @Test
    @Transactional
    void createServiceEstimate() throws Exception {
        int databaseSizeBeforeCreate = serviceEstimateRepository.findAll().size();
        // Create the ServiceEstimate
        ServiceEstimateDTO serviceEstimateDTO = serviceEstimateMapper.toDto(serviceEstimate);
        restServiceEstimateMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(serviceEstimateDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ServiceEstimate in the database
        List<ServiceEstimate> serviceEstimateList = serviceEstimateRepository.findAll();
        assertThat(serviceEstimateList).hasSize(databaseSizeBeforeCreate + 1);
        ServiceEstimate testServiceEstimate = serviceEstimateList.get(serviceEstimateList.size() - 1);
        assertThat(testServiceEstimate.getServiceEstimateName()).isEqualTo(DEFAULT_SERVICE_ESTIMATE_NAME);
        assertThat(testServiceEstimate.getServiceEstimateNumber()).isEqualTo(DEFAULT_SERVICE_ESTIMATE_NUMBER);
        assertThat(testServiceEstimate.getServiceEstimateDescription()).isEqualTo(DEFAULT_SERVICE_ESTIMATE_DESCRIPTION);
        assertThat(testServiceEstimate.getDeptId()).isEqualTo(DEFAULT_DEPT_ID);
        assertThat(testServiceEstimate.getDeptCode()).isEqualTo(DEFAULT_DEPT_CODE);
        assertThat(testServiceEstimate.getLocationId()).isEqualTo(DEFAULT_LOCATION_ID);
        assertThat(testServiceEstimate.getLocationName()).isEqualTo(DEFAULT_LOCATION_NAME);
        assertThat(testServiceEstimate.getProjectId()).isEqualTo(DEFAULT_PROJECT_ID);
        assertThat(testServiceEstimate.getProjectName()).isEqualTo(DEFAULT_PROJECT_NAME);
        assertThat(testServiceEstimate.getEstimateAmt()).isEqualByComparingTo(DEFAULT_ESTIMATE_AMT);
        assertThat(testServiceEstimate.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testServiceEstimate.getDocumentReference()).isEqualTo(DEFAULT_DOCUMENT_REFERENCE);
        assertThat(testServiceEstimate.getBudgetAmount()).isEqualByComparingTo(DEFAULT_BUDGET_AMOUNT);
        assertThat(testServiceEstimate.getHoaDepartment()).isEqualTo(DEFAULT_HOA_DEPARTMENT);
        assertThat(testServiceEstimate.getHoaDepartmentId()).isEqualTo(DEFAULT_HOA_DEPARTMENT_ID);
        assertThat(testServiceEstimate.getHoaSector()).isEqualTo(DEFAULT_HOA_SECTOR);
        assertThat(testServiceEstimate.getHoaSectorId()).isEqualTo(DEFAULT_HOA_SECTOR_ID);
        assertThat(testServiceEstimate.getHoaList()).isEqualTo(DEFAULT_HOA_LIST);
        assertThat(testServiceEstimate.getHoaListId()).isEqualTo(DEFAULT_HOA_LIST_ID);
        assertThat(testServiceEstimate.getHoaLinkedList()).isEqualTo(DEFAULT_HOA_LINKED_LIST);
        assertThat(testServiceEstimate.getHoaLinkedListId()).isEqualTo(DEFAULT_HOA_LINKED_LIST_ID);
        assertThat(testServiceEstimate.getApprovedBudgetYn()).isEqualTo(DEFAULT_APPROVED_BUDGET_YN);
    }

    @Test
    @Transactional
    void createServiceEstimateWithExistingId() throws Exception {
        // Create the ServiceEstimate with an existing ID
        serviceEstimate.setId(1L);
        ServiceEstimateDTO serviceEstimateDTO = serviceEstimateMapper.toDto(serviceEstimate);

        int databaseSizeBeforeCreate = serviceEstimateRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restServiceEstimateMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(serviceEstimateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceEstimate in the database
        List<ServiceEstimate> serviceEstimateList = serviceEstimateRepository.findAll();
        assertThat(serviceEstimateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllServiceEstimates() throws Exception {
        // Initialize the database
        serviceEstimateRepository.saveAndFlush(serviceEstimate);

        // Get all the serviceEstimateList
        restServiceEstimateMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(serviceEstimate.getId().intValue())))
            .andExpect(jsonPath("$.[*].serviceEstimateName").value(hasItem(DEFAULT_SERVICE_ESTIMATE_NAME)))
            .andExpect(jsonPath("$.[*].serviceEstimateNumber").value(hasItem(DEFAULT_SERVICE_ESTIMATE_NUMBER)))
            .andExpect(jsonPath("$.[*].serviceEstimateDescription").value(hasItem(DEFAULT_SERVICE_ESTIMATE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].deptId").value(hasItem(DEFAULT_DEPT_ID.intValue())))
            .andExpect(jsonPath("$.[*].deptCode").value(hasItem(DEFAULT_DEPT_CODE)))
            .andExpect(jsonPath("$.[*].locationId").value(hasItem(DEFAULT_LOCATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].locationName").value(hasItem(DEFAULT_LOCATION_NAME)))
            .andExpect(jsonPath("$.[*].projectId").value(hasItem(DEFAULT_PROJECT_ID.intValue())))
            .andExpect(jsonPath("$.[*].projectName").value(hasItem(DEFAULT_PROJECT_NAME)))
            .andExpect(jsonPath("$.[*].estimateAmt").value(hasItem(sameNumber(DEFAULT_ESTIMATE_AMT))))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].documentReference").value(hasItem(DEFAULT_DOCUMENT_REFERENCE)))
            .andExpect(jsonPath("$.[*].budgetAmount").value(hasItem(sameNumber(DEFAULT_BUDGET_AMOUNT))))
            .andExpect(jsonPath("$.[*].hoaDepartment").value(hasItem(DEFAULT_HOA_DEPARTMENT)))
            .andExpect(jsonPath("$.[*].hoaDepartmentId").value(hasItem(DEFAULT_HOA_DEPARTMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].hoaSector").value(hasItem(DEFAULT_HOA_SECTOR)))
            .andExpect(jsonPath("$.[*].hoaSectorId").value(hasItem(DEFAULT_HOA_SECTOR_ID.intValue())))
            .andExpect(jsonPath("$.[*].hoaList").value(hasItem(DEFAULT_HOA_LIST)))
            .andExpect(jsonPath("$.[*].hoaListId").value(hasItem(DEFAULT_HOA_LIST_ID.intValue())))
            .andExpect(jsonPath("$.[*].hoaLinkedList").value(hasItem(DEFAULT_HOA_LINKED_LIST)))
            .andExpect(jsonPath("$.[*].hoaLinkedListId").value(hasItem(DEFAULT_HOA_LINKED_LIST_ID.intValue())))
            .andExpect(jsonPath("$.[*].approvedBudgetYn").value(hasItem(DEFAULT_APPROVED_BUDGET_YN.booleanValue())));
    }

    @Test
    @Transactional
    void getServiceEstimate() throws Exception {
        // Initialize the database
        serviceEstimateRepository.saveAndFlush(serviceEstimate);

        // Get the serviceEstimate
        restServiceEstimateMockMvc
            .perform(get(ENTITY_API_URL_ID, serviceEstimate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(serviceEstimate.getId().intValue()))
            .andExpect(jsonPath("$.serviceEstimateName").value(DEFAULT_SERVICE_ESTIMATE_NAME))
            .andExpect(jsonPath("$.serviceEstimateNumber").value(DEFAULT_SERVICE_ESTIMATE_NUMBER))
            .andExpect(jsonPath("$.serviceEstimateDescription").value(DEFAULT_SERVICE_ESTIMATE_DESCRIPTION))
            .andExpect(jsonPath("$.deptId").value(DEFAULT_DEPT_ID.intValue()))
            .andExpect(jsonPath("$.deptCode").value(DEFAULT_DEPT_CODE))
            .andExpect(jsonPath("$.locationId").value(DEFAULT_LOCATION_ID.intValue()))
            .andExpect(jsonPath("$.locationName").value(DEFAULT_LOCATION_NAME))
            .andExpect(jsonPath("$.projectId").value(DEFAULT_PROJECT_ID.intValue()))
            .andExpect(jsonPath("$.projectName").value(DEFAULT_PROJECT_NAME))
            .andExpect(jsonPath("$.estimateAmt").value(sameNumber(DEFAULT_ESTIMATE_AMT)))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.documentReference").value(DEFAULT_DOCUMENT_REFERENCE))
            .andExpect(jsonPath("$.budgetAmount").value(sameNumber(DEFAULT_BUDGET_AMOUNT)))
            .andExpect(jsonPath("$.hoaDepartment").value(DEFAULT_HOA_DEPARTMENT))
            .andExpect(jsonPath("$.hoaDepartmentId").value(DEFAULT_HOA_DEPARTMENT_ID.intValue()))
            .andExpect(jsonPath("$.hoaSector").value(DEFAULT_HOA_SECTOR))
            .andExpect(jsonPath("$.hoaSectorId").value(DEFAULT_HOA_SECTOR_ID.intValue()))
            .andExpect(jsonPath("$.hoaList").value(DEFAULT_HOA_LIST))
            .andExpect(jsonPath("$.hoaListId").value(DEFAULT_HOA_LIST_ID.intValue()))
            .andExpect(jsonPath("$.hoaLinkedList").value(DEFAULT_HOA_LINKED_LIST))
            .andExpect(jsonPath("$.hoaLinkedListId").value(DEFAULT_HOA_LINKED_LIST_ID.intValue()))
            .andExpect(jsonPath("$.approvedBudgetYn").value(DEFAULT_APPROVED_BUDGET_YN.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingServiceEstimate() throws Exception {
        // Get the serviceEstimate
        restServiceEstimateMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewServiceEstimate() throws Exception {
        // Initialize the database
        serviceEstimateRepository.saveAndFlush(serviceEstimate);

        int databaseSizeBeforeUpdate = serviceEstimateRepository.findAll().size();

        // Update the serviceEstimate
        ServiceEstimate updatedServiceEstimate = serviceEstimateRepository.findById(serviceEstimate.getId()).get();
        // Disconnect from session so that the updates on updatedServiceEstimate are not directly saved in db
        em.detach(updatedServiceEstimate);
        updatedServiceEstimate
            .serviceEstimateName(UPDATED_SERVICE_ESTIMATE_NAME)
            .serviceEstimateNumber(UPDATED_SERVICE_ESTIMATE_NUMBER)
            .serviceEstimateDescription(UPDATED_SERVICE_ESTIMATE_DESCRIPTION)
            .deptId(UPDATED_DEPT_ID)
            .deptCode(UPDATED_DEPT_CODE)
            .locationId(UPDATED_LOCATION_ID)
            .locationName(UPDATED_LOCATION_NAME)
            .projectId(UPDATED_PROJECT_ID)
            .projectName(UPDATED_PROJECT_NAME)
            .estimateAmt(UPDATED_ESTIMATE_AMT)
            .status(UPDATED_STATUS)
            .documentReference(UPDATED_DOCUMENT_REFERENCE)
            .budgetAmount(UPDATED_BUDGET_AMOUNT)
            .hoaDepartment(UPDATED_HOA_DEPARTMENT)
            .hoaDepartmentId(UPDATED_HOA_DEPARTMENT_ID)
            .hoaSector(UPDATED_HOA_SECTOR)
            .hoaSectorId(UPDATED_HOA_SECTOR_ID)
            .hoaList(UPDATED_HOA_LIST)
            .hoaListId(UPDATED_HOA_LIST_ID)
            .hoaLinkedList(UPDATED_HOA_LINKED_LIST)
            .hoaLinkedListId(UPDATED_HOA_LINKED_LIST_ID)
            .approvedBudgetYn(UPDATED_APPROVED_BUDGET_YN);
        ServiceEstimateDTO serviceEstimateDTO = serviceEstimateMapper.toDto(updatedServiceEstimate);

        restServiceEstimateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, serviceEstimateDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceEstimateDTO))
            )
            .andExpect(status().isOk());

        // Validate the ServiceEstimate in the database
        List<ServiceEstimate> serviceEstimateList = serviceEstimateRepository.findAll();
        assertThat(serviceEstimateList).hasSize(databaseSizeBeforeUpdate);
        ServiceEstimate testServiceEstimate = serviceEstimateList.get(serviceEstimateList.size() - 1);
        assertThat(testServiceEstimate.getServiceEstimateName()).isEqualTo(UPDATED_SERVICE_ESTIMATE_NAME);
        assertThat(testServiceEstimate.getServiceEstimateNumber()).isEqualTo(UPDATED_SERVICE_ESTIMATE_NUMBER);
        assertThat(testServiceEstimate.getServiceEstimateDescription()).isEqualTo(UPDATED_SERVICE_ESTIMATE_DESCRIPTION);
        assertThat(testServiceEstimate.getDeptId()).isEqualTo(UPDATED_DEPT_ID);
        assertThat(testServiceEstimate.getDeptCode()).isEqualTo(UPDATED_DEPT_CODE);
        assertThat(testServiceEstimate.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
        assertThat(testServiceEstimate.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testServiceEstimate.getProjectId()).isEqualTo(UPDATED_PROJECT_ID);
        assertThat(testServiceEstimate.getProjectName()).isEqualTo(UPDATED_PROJECT_NAME);
        assertThat(testServiceEstimate.getEstimateAmt()).isEqualTo(UPDATED_ESTIMATE_AMT);
        assertThat(testServiceEstimate.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testServiceEstimate.getDocumentReference()).isEqualTo(UPDATED_DOCUMENT_REFERENCE);
        assertThat(testServiceEstimate.getBudgetAmount()).isEqualTo(UPDATED_BUDGET_AMOUNT);
        assertThat(testServiceEstimate.getHoaDepartment()).isEqualTo(UPDATED_HOA_DEPARTMENT);
        assertThat(testServiceEstimate.getHoaDepartmentId()).isEqualTo(UPDATED_HOA_DEPARTMENT_ID);
        assertThat(testServiceEstimate.getHoaSector()).isEqualTo(UPDATED_HOA_SECTOR);
        assertThat(testServiceEstimate.getHoaSectorId()).isEqualTo(UPDATED_HOA_SECTOR_ID);
        assertThat(testServiceEstimate.getHoaList()).isEqualTo(UPDATED_HOA_LIST);
        assertThat(testServiceEstimate.getHoaListId()).isEqualTo(UPDATED_HOA_LIST_ID);
        assertThat(testServiceEstimate.getHoaLinkedList()).isEqualTo(UPDATED_HOA_LINKED_LIST);
        assertThat(testServiceEstimate.getHoaLinkedListId()).isEqualTo(UPDATED_HOA_LINKED_LIST_ID);
        assertThat(testServiceEstimate.getApprovedBudgetYn()).isEqualTo(UPDATED_APPROVED_BUDGET_YN);
    }

    @Test
    @Transactional
    void putNonExistingServiceEstimate() throws Exception {
        int databaseSizeBeforeUpdate = serviceEstimateRepository.findAll().size();
        serviceEstimate.setId(count.incrementAndGet());

        // Create the ServiceEstimate
        ServiceEstimateDTO serviceEstimateDTO = serviceEstimateMapper.toDto(serviceEstimate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceEstimateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, serviceEstimateDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceEstimateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceEstimate in the database
        List<ServiceEstimate> serviceEstimateList = serviceEstimateRepository.findAll();
        assertThat(serviceEstimateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchServiceEstimate() throws Exception {
        int databaseSizeBeforeUpdate = serviceEstimateRepository.findAll().size();
        serviceEstimate.setId(count.incrementAndGet());

        // Create the ServiceEstimate
        ServiceEstimateDTO serviceEstimateDTO = serviceEstimateMapper.toDto(serviceEstimate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceEstimateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceEstimateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceEstimate in the database
        List<ServiceEstimate> serviceEstimateList = serviceEstimateRepository.findAll();
        assertThat(serviceEstimateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamServiceEstimate() throws Exception {
        int databaseSizeBeforeUpdate = serviceEstimateRepository.findAll().size();
        serviceEstimate.setId(count.incrementAndGet());

        // Create the ServiceEstimate
        ServiceEstimateDTO serviceEstimateDTO = serviceEstimateMapper.toDto(serviceEstimate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceEstimateMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(serviceEstimateDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ServiceEstimate in the database
        List<ServiceEstimate> serviceEstimateList = serviceEstimateRepository.findAll();
        assertThat(serviceEstimateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateServiceEstimateWithPatch() throws Exception {
        // Initialize the database
        serviceEstimateRepository.saveAndFlush(serviceEstimate);

        int databaseSizeBeforeUpdate = serviceEstimateRepository.findAll().size();

        // Update the serviceEstimate using partial update
        ServiceEstimate partialUpdatedServiceEstimate = new ServiceEstimate();
        partialUpdatedServiceEstimate.setId(serviceEstimate.getId());

        partialUpdatedServiceEstimate
            .deptCode(UPDATED_DEPT_CODE)
            .locationId(UPDATED_LOCATION_ID)
            .status(UPDATED_STATUS)
            .documentReference(UPDATED_DOCUMENT_REFERENCE)
            .budgetAmount(UPDATED_BUDGET_AMOUNT)
            .hoaDepartmentId(UPDATED_HOA_DEPARTMENT_ID)
            .hoaSector(UPDATED_HOA_SECTOR)
            .hoaSectorId(UPDATED_HOA_SECTOR_ID)
            .hoaList(UPDATED_HOA_LIST)
            .hoaListId(UPDATED_HOA_LIST_ID)
            .hoaLinkedList(UPDATED_HOA_LINKED_LIST)
            .approvedBudgetYn(UPDATED_APPROVED_BUDGET_YN);

        restServiceEstimateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedServiceEstimate.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedServiceEstimate))
            )
            .andExpect(status().isOk());

        // Validate the ServiceEstimate in the database
        List<ServiceEstimate> serviceEstimateList = serviceEstimateRepository.findAll();
        assertThat(serviceEstimateList).hasSize(databaseSizeBeforeUpdate);
        ServiceEstimate testServiceEstimate = serviceEstimateList.get(serviceEstimateList.size() - 1);
        assertThat(testServiceEstimate.getServiceEstimateName()).isEqualTo(DEFAULT_SERVICE_ESTIMATE_NAME);
        assertThat(testServiceEstimate.getServiceEstimateNumber()).isEqualTo(DEFAULT_SERVICE_ESTIMATE_NUMBER);
        assertThat(testServiceEstimate.getServiceEstimateDescription()).isEqualTo(DEFAULT_SERVICE_ESTIMATE_DESCRIPTION);
        assertThat(testServiceEstimate.getDeptId()).isEqualTo(DEFAULT_DEPT_ID);
        assertThat(testServiceEstimate.getDeptCode()).isEqualTo(UPDATED_DEPT_CODE);
        assertThat(testServiceEstimate.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
        assertThat(testServiceEstimate.getLocationName()).isEqualTo(DEFAULT_LOCATION_NAME);
        assertThat(testServiceEstimate.getProjectId()).isEqualTo(DEFAULT_PROJECT_ID);
        assertThat(testServiceEstimate.getProjectName()).isEqualTo(DEFAULT_PROJECT_NAME);
        assertThat(testServiceEstimate.getEstimateAmt()).isEqualByComparingTo(DEFAULT_ESTIMATE_AMT);
        assertThat(testServiceEstimate.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testServiceEstimate.getDocumentReference()).isEqualTo(UPDATED_DOCUMENT_REFERENCE);
        assertThat(testServiceEstimate.getBudgetAmount()).isEqualByComparingTo(UPDATED_BUDGET_AMOUNT);
        assertThat(testServiceEstimate.getHoaDepartment()).isEqualTo(DEFAULT_HOA_DEPARTMENT);
        assertThat(testServiceEstimate.getHoaDepartmentId()).isEqualTo(UPDATED_HOA_DEPARTMENT_ID);
        assertThat(testServiceEstimate.getHoaSector()).isEqualTo(UPDATED_HOA_SECTOR);
        assertThat(testServiceEstimate.getHoaSectorId()).isEqualTo(UPDATED_HOA_SECTOR_ID);
        assertThat(testServiceEstimate.getHoaList()).isEqualTo(UPDATED_HOA_LIST);
        assertThat(testServiceEstimate.getHoaListId()).isEqualTo(UPDATED_HOA_LIST_ID);
        assertThat(testServiceEstimate.getHoaLinkedList()).isEqualTo(UPDATED_HOA_LINKED_LIST);
        assertThat(testServiceEstimate.getHoaLinkedListId()).isEqualTo(DEFAULT_HOA_LINKED_LIST_ID);
        assertThat(testServiceEstimate.getApprovedBudgetYn()).isEqualTo(UPDATED_APPROVED_BUDGET_YN);
    }

    @Test
    @Transactional
    void fullUpdateServiceEstimateWithPatch() throws Exception {
        // Initialize the database
        serviceEstimateRepository.saveAndFlush(serviceEstimate);

        int databaseSizeBeforeUpdate = serviceEstimateRepository.findAll().size();

        // Update the serviceEstimate using partial update
        ServiceEstimate partialUpdatedServiceEstimate = new ServiceEstimate();
        partialUpdatedServiceEstimate.setId(serviceEstimate.getId());

        partialUpdatedServiceEstimate
            .serviceEstimateName(UPDATED_SERVICE_ESTIMATE_NAME)
            .serviceEstimateNumber(UPDATED_SERVICE_ESTIMATE_NUMBER)
            .serviceEstimateDescription(UPDATED_SERVICE_ESTIMATE_DESCRIPTION)
            .deptId(UPDATED_DEPT_ID)
            .deptCode(UPDATED_DEPT_CODE)
            .locationId(UPDATED_LOCATION_ID)
            .locationName(UPDATED_LOCATION_NAME)
            .projectId(UPDATED_PROJECT_ID)
            .projectName(UPDATED_PROJECT_NAME)
            .estimateAmt(UPDATED_ESTIMATE_AMT)
            .status(UPDATED_STATUS)
            .documentReference(UPDATED_DOCUMENT_REFERENCE)
            .budgetAmount(UPDATED_BUDGET_AMOUNT)
            .hoaDepartment(UPDATED_HOA_DEPARTMENT)
            .hoaDepartmentId(UPDATED_HOA_DEPARTMENT_ID)
            .hoaSector(UPDATED_HOA_SECTOR)
            .hoaSectorId(UPDATED_HOA_SECTOR_ID)
            .hoaList(UPDATED_HOA_LIST)
            .hoaListId(UPDATED_HOA_LIST_ID)
            .hoaLinkedList(UPDATED_HOA_LINKED_LIST)
            .hoaLinkedListId(UPDATED_HOA_LINKED_LIST_ID)
            .approvedBudgetYn(UPDATED_APPROVED_BUDGET_YN);

        restServiceEstimateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedServiceEstimate.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedServiceEstimate))
            )
            .andExpect(status().isOk());

        // Validate the ServiceEstimate in the database
        List<ServiceEstimate> serviceEstimateList = serviceEstimateRepository.findAll();
        assertThat(serviceEstimateList).hasSize(databaseSizeBeforeUpdate);
        ServiceEstimate testServiceEstimate = serviceEstimateList.get(serviceEstimateList.size() - 1);
        assertThat(testServiceEstimate.getServiceEstimateName()).isEqualTo(UPDATED_SERVICE_ESTIMATE_NAME);
        assertThat(testServiceEstimate.getServiceEstimateNumber()).isEqualTo(UPDATED_SERVICE_ESTIMATE_NUMBER);
        assertThat(testServiceEstimate.getServiceEstimateDescription()).isEqualTo(UPDATED_SERVICE_ESTIMATE_DESCRIPTION);
        assertThat(testServiceEstimate.getDeptId()).isEqualTo(UPDATED_DEPT_ID);
        assertThat(testServiceEstimate.getDeptCode()).isEqualTo(UPDATED_DEPT_CODE);
        assertThat(testServiceEstimate.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
        assertThat(testServiceEstimate.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testServiceEstimate.getProjectId()).isEqualTo(UPDATED_PROJECT_ID);
        assertThat(testServiceEstimate.getProjectName()).isEqualTo(UPDATED_PROJECT_NAME);
        assertThat(testServiceEstimate.getEstimateAmt()).isEqualByComparingTo(UPDATED_ESTIMATE_AMT);
        assertThat(testServiceEstimate.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testServiceEstimate.getDocumentReference()).isEqualTo(UPDATED_DOCUMENT_REFERENCE);
        assertThat(testServiceEstimate.getBudgetAmount()).isEqualByComparingTo(UPDATED_BUDGET_AMOUNT);
        assertThat(testServiceEstimate.getHoaDepartment()).isEqualTo(UPDATED_HOA_DEPARTMENT);
        assertThat(testServiceEstimate.getHoaDepartmentId()).isEqualTo(UPDATED_HOA_DEPARTMENT_ID);
        assertThat(testServiceEstimate.getHoaSector()).isEqualTo(UPDATED_HOA_SECTOR);
        assertThat(testServiceEstimate.getHoaSectorId()).isEqualTo(UPDATED_HOA_SECTOR_ID);
        assertThat(testServiceEstimate.getHoaList()).isEqualTo(UPDATED_HOA_LIST);
        assertThat(testServiceEstimate.getHoaListId()).isEqualTo(UPDATED_HOA_LIST_ID);
        assertThat(testServiceEstimate.getHoaLinkedList()).isEqualTo(UPDATED_HOA_LINKED_LIST);
        assertThat(testServiceEstimate.getHoaLinkedListId()).isEqualTo(UPDATED_HOA_LINKED_LIST_ID);
        assertThat(testServiceEstimate.getApprovedBudgetYn()).isEqualTo(UPDATED_APPROVED_BUDGET_YN);
    }

    @Test
    @Transactional
    void patchNonExistingServiceEstimate() throws Exception {
        int databaseSizeBeforeUpdate = serviceEstimateRepository.findAll().size();
        serviceEstimate.setId(count.incrementAndGet());

        // Create the ServiceEstimate
        ServiceEstimateDTO serviceEstimateDTO = serviceEstimateMapper.toDto(serviceEstimate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceEstimateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, serviceEstimateDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(serviceEstimateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceEstimate in the database
        List<ServiceEstimate> serviceEstimateList = serviceEstimateRepository.findAll();
        assertThat(serviceEstimateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchServiceEstimate() throws Exception {
        int databaseSizeBeforeUpdate = serviceEstimateRepository.findAll().size();
        serviceEstimate.setId(count.incrementAndGet());

        // Create the ServiceEstimate
        ServiceEstimateDTO serviceEstimateDTO = serviceEstimateMapper.toDto(serviceEstimate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceEstimateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(serviceEstimateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceEstimate in the database
        List<ServiceEstimate> serviceEstimateList = serviceEstimateRepository.findAll();
        assertThat(serviceEstimateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamServiceEstimate() throws Exception {
        int databaseSizeBeforeUpdate = serviceEstimateRepository.findAll().size();
        serviceEstimate.setId(count.incrementAndGet());

        // Create the ServiceEstimate
        ServiceEstimateDTO serviceEstimateDTO = serviceEstimateMapper.toDto(serviceEstimate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceEstimateMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(serviceEstimateDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ServiceEstimate in the database
        List<ServiceEstimate> serviceEstimateList = serviceEstimateRepository.findAll();
        assertThat(serviceEstimateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteServiceEstimate() throws Exception {
        // Initialize the database
        serviceEstimateRepository.saveAndFlush(serviceEstimate);

        int databaseSizeBeforeDelete = serviceEstimateRepository.findAll().size();

        // Delete the serviceEstimate
        restServiceEstimateMockMvc
            .perform(delete(ENTITY_API_URL_ID, serviceEstimate.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ServiceEstimate> serviceEstimateList = serviceEstimateRepository.findAll();
        assertThat(serviceEstimateList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
