package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.TenderTechnicalCriterion;
import com.mycompany.myapp.repository.TenderTechnicalCriterionRepository;
import com.mycompany.myapp.service.dto.TenderTechnicalCriterionDTO;
import com.mycompany.myapp.service.mapper.TenderTechnicalCriterionMapper;
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
 * Integration tests for the {@link TenderTechnicalCriterionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TenderTechnicalCriterionResourceIT {

    private static final Long DEFAULT_NIT_ID = 1L;
    private static final Long UPDATED_NIT_ID = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CRITERION_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CRITERION_CATEGORY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_USER_ADDED = false;
    private static final Boolean UPDATED_IS_USER_ADDED = true;

    private static final String ENTITY_API_URL = "/api/tender-technical-criteria";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TenderTechnicalCriterionRepository tenderTechnicalCriterionRepository;

    @Autowired
    private TenderTechnicalCriterionMapper tenderTechnicalCriterionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenderTechnicalCriterionMockMvc;

    private TenderTechnicalCriterion tenderTechnicalCriterion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderTechnicalCriterion createEntity(EntityManager em) {
        TenderTechnicalCriterion tenderTechnicalCriterion = new TenderTechnicalCriterion()
            .nitId(DEFAULT_NIT_ID)
            .description(DEFAULT_DESCRIPTION)
            .criterionCategory(DEFAULT_CRITERION_CATEGORY)
            .isUserAdded(DEFAULT_IS_USER_ADDED);
        return tenderTechnicalCriterion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderTechnicalCriterion createUpdatedEntity(EntityManager em) {
        TenderTechnicalCriterion tenderTechnicalCriterion = new TenderTechnicalCriterion()
            .nitId(UPDATED_NIT_ID)
            .description(UPDATED_DESCRIPTION)
            .criterionCategory(UPDATED_CRITERION_CATEGORY)
            .isUserAdded(UPDATED_IS_USER_ADDED);
        return tenderTechnicalCriterion;
    }

    @BeforeEach
    public void initTest() {
        tenderTechnicalCriterion = createEntity(em);
    }

    @Test
    @Transactional
    void createTenderTechnicalCriterion() throws Exception {
        int databaseSizeBeforeCreate = tenderTechnicalCriterionRepository.findAll().size();
        // Create the TenderTechnicalCriterion
        TenderTechnicalCriterionDTO tenderTechnicalCriterionDTO = tenderTechnicalCriterionMapper.toDto(tenderTechnicalCriterion);
        restTenderTechnicalCriterionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderTechnicalCriterionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TenderTechnicalCriterion in the database
        List<TenderTechnicalCriterion> tenderTechnicalCriterionList = tenderTechnicalCriterionRepository.findAll();
        assertThat(tenderTechnicalCriterionList).hasSize(databaseSizeBeforeCreate + 1);
        TenderTechnicalCriterion testTenderTechnicalCriterion = tenderTechnicalCriterionList.get(tenderTechnicalCriterionList.size() - 1);
        assertThat(testTenderTechnicalCriterion.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderTechnicalCriterion.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTenderTechnicalCriterion.getCriterionCategory()).isEqualTo(DEFAULT_CRITERION_CATEGORY);
        assertThat(testTenderTechnicalCriterion.getIsUserAdded()).isEqualTo(DEFAULT_IS_USER_ADDED);
    }

    @Test
    @Transactional
    void createTenderTechnicalCriterionWithExistingId() throws Exception {
        // Create the TenderTechnicalCriterion with an existing ID
        tenderTechnicalCriterion.setId(1L);
        TenderTechnicalCriterionDTO tenderTechnicalCriterionDTO = tenderTechnicalCriterionMapper.toDto(tenderTechnicalCriterion);

        int databaseSizeBeforeCreate = tenderTechnicalCriterionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderTechnicalCriterionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderTechnicalCriterionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderTechnicalCriterion in the database
        List<TenderTechnicalCriterion> tenderTechnicalCriterionList = tenderTechnicalCriterionRepository.findAll();
        assertThat(tenderTechnicalCriterionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTenderTechnicalCriteria() throws Exception {
        // Initialize the database
        tenderTechnicalCriterionRepository.saveAndFlush(tenderTechnicalCriterion);

        // Get all the tenderTechnicalCriterionList
        restTenderTechnicalCriterionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenderTechnicalCriterion.getId().intValue())))
            .andExpect(jsonPath("$.[*].nitId").value(hasItem(DEFAULT_NIT_ID.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].criterionCategory").value(hasItem(DEFAULT_CRITERION_CATEGORY)))
            .andExpect(jsonPath("$.[*].isUserAdded").value(hasItem(DEFAULT_IS_USER_ADDED.booleanValue())));
    }

    @Test
    @Transactional
    void getTenderTechnicalCriterion() throws Exception {
        // Initialize the database
        tenderTechnicalCriterionRepository.saveAndFlush(tenderTechnicalCriterion);

        // Get the tenderTechnicalCriterion
        restTenderTechnicalCriterionMockMvc
            .perform(get(ENTITY_API_URL_ID, tenderTechnicalCriterion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenderTechnicalCriterion.getId().intValue()))
            .andExpect(jsonPath("$.nitId").value(DEFAULT_NIT_ID.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.criterionCategory").value(DEFAULT_CRITERION_CATEGORY))
            .andExpect(jsonPath("$.isUserAdded").value(DEFAULT_IS_USER_ADDED.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingTenderTechnicalCriterion() throws Exception {
        // Get the tenderTechnicalCriterion
        restTenderTechnicalCriterionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTenderTechnicalCriterion() throws Exception {
        // Initialize the database
        tenderTechnicalCriterionRepository.saveAndFlush(tenderTechnicalCriterion);

        int databaseSizeBeforeUpdate = tenderTechnicalCriterionRepository.findAll().size();

        // Update the tenderTechnicalCriterion
        TenderTechnicalCriterion updatedTenderTechnicalCriterion = tenderTechnicalCriterionRepository
            .findById(tenderTechnicalCriterion.getId())
            .get();
        // Disconnect from session so that the updates on updatedTenderTechnicalCriterion are not directly saved in db
        em.detach(updatedTenderTechnicalCriterion);
        updatedTenderTechnicalCriterion
            .nitId(UPDATED_NIT_ID)
            .description(UPDATED_DESCRIPTION)
            .criterionCategory(UPDATED_CRITERION_CATEGORY)
            .isUserAdded(UPDATED_IS_USER_ADDED);
        TenderTechnicalCriterionDTO tenderTechnicalCriterionDTO = tenderTechnicalCriterionMapper.toDto(updatedTenderTechnicalCriterion);

        restTenderTechnicalCriterionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderTechnicalCriterionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderTechnicalCriterionDTO))
            )
            .andExpect(status().isOk());

        // Validate the TenderTechnicalCriterion in the database
        List<TenderTechnicalCriterion> tenderTechnicalCriterionList = tenderTechnicalCriterionRepository.findAll();
        assertThat(tenderTechnicalCriterionList).hasSize(databaseSizeBeforeUpdate);
        TenderTechnicalCriterion testTenderTechnicalCriterion = tenderTechnicalCriterionList.get(tenderTechnicalCriterionList.size() - 1);
        assertThat(testTenderTechnicalCriterion.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderTechnicalCriterion.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTenderTechnicalCriterion.getCriterionCategory()).isEqualTo(UPDATED_CRITERION_CATEGORY);
        assertThat(testTenderTechnicalCriterion.getIsUserAdded()).isEqualTo(UPDATED_IS_USER_ADDED);
    }

    @Test
    @Transactional
    void putNonExistingTenderTechnicalCriterion() throws Exception {
        int databaseSizeBeforeUpdate = tenderTechnicalCriterionRepository.findAll().size();
        tenderTechnicalCriterion.setId(count.incrementAndGet());

        // Create the TenderTechnicalCriterion
        TenderTechnicalCriterionDTO tenderTechnicalCriterionDTO = tenderTechnicalCriterionMapper.toDto(tenderTechnicalCriterion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderTechnicalCriterionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderTechnicalCriterionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderTechnicalCriterionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderTechnicalCriterion in the database
        List<TenderTechnicalCriterion> tenderTechnicalCriterionList = tenderTechnicalCriterionRepository.findAll();
        assertThat(tenderTechnicalCriterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTenderTechnicalCriterion() throws Exception {
        int databaseSizeBeforeUpdate = tenderTechnicalCriterionRepository.findAll().size();
        tenderTechnicalCriterion.setId(count.incrementAndGet());

        // Create the TenderTechnicalCriterion
        TenderTechnicalCriterionDTO tenderTechnicalCriterionDTO = tenderTechnicalCriterionMapper.toDto(tenderTechnicalCriterion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderTechnicalCriterionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderTechnicalCriterionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderTechnicalCriterion in the database
        List<TenderTechnicalCriterion> tenderTechnicalCriterionList = tenderTechnicalCriterionRepository.findAll();
        assertThat(tenderTechnicalCriterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTenderTechnicalCriterion() throws Exception {
        int databaseSizeBeforeUpdate = tenderTechnicalCriterionRepository.findAll().size();
        tenderTechnicalCriterion.setId(count.incrementAndGet());

        // Create the TenderTechnicalCriterion
        TenderTechnicalCriterionDTO tenderTechnicalCriterionDTO = tenderTechnicalCriterionMapper.toDto(tenderTechnicalCriterion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderTechnicalCriterionMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderTechnicalCriterionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderTechnicalCriterion in the database
        List<TenderTechnicalCriterion> tenderTechnicalCriterionList = tenderTechnicalCriterionRepository.findAll();
        assertThat(tenderTechnicalCriterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTenderTechnicalCriterionWithPatch() throws Exception {
        // Initialize the database
        tenderTechnicalCriterionRepository.saveAndFlush(tenderTechnicalCriterion);

        int databaseSizeBeforeUpdate = tenderTechnicalCriterionRepository.findAll().size();

        // Update the tenderTechnicalCriterion using partial update
        TenderTechnicalCriterion partialUpdatedTenderTechnicalCriterion = new TenderTechnicalCriterion();
        partialUpdatedTenderTechnicalCriterion.setId(tenderTechnicalCriterion.getId());

        partialUpdatedTenderTechnicalCriterion
            .nitId(UPDATED_NIT_ID)
            .criterionCategory(UPDATED_CRITERION_CATEGORY)
            .isUserAdded(UPDATED_IS_USER_ADDED);

        restTenderTechnicalCriterionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderTechnicalCriterion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderTechnicalCriterion))
            )
            .andExpect(status().isOk());

        // Validate the TenderTechnicalCriterion in the database
        List<TenderTechnicalCriterion> tenderTechnicalCriterionList = tenderTechnicalCriterionRepository.findAll();
        assertThat(tenderTechnicalCriterionList).hasSize(databaseSizeBeforeUpdate);
        TenderTechnicalCriterion testTenderTechnicalCriterion = tenderTechnicalCriterionList.get(tenderTechnicalCriterionList.size() - 1);
        assertThat(testTenderTechnicalCriterion.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderTechnicalCriterion.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTenderTechnicalCriterion.getCriterionCategory()).isEqualTo(UPDATED_CRITERION_CATEGORY);
        assertThat(testTenderTechnicalCriterion.getIsUserAdded()).isEqualTo(UPDATED_IS_USER_ADDED);
    }

    @Test
    @Transactional
    void fullUpdateTenderTechnicalCriterionWithPatch() throws Exception {
        // Initialize the database
        tenderTechnicalCriterionRepository.saveAndFlush(tenderTechnicalCriterion);

        int databaseSizeBeforeUpdate = tenderTechnicalCriterionRepository.findAll().size();

        // Update the tenderTechnicalCriterion using partial update
        TenderTechnicalCriterion partialUpdatedTenderTechnicalCriterion = new TenderTechnicalCriterion();
        partialUpdatedTenderTechnicalCriterion.setId(tenderTechnicalCriterion.getId());

        partialUpdatedTenderTechnicalCriterion
            .nitId(UPDATED_NIT_ID)
            .description(UPDATED_DESCRIPTION)
            .criterionCategory(UPDATED_CRITERION_CATEGORY)
            .isUserAdded(UPDATED_IS_USER_ADDED);

        restTenderTechnicalCriterionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderTechnicalCriterion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderTechnicalCriterion))
            )
            .andExpect(status().isOk());

        // Validate the TenderTechnicalCriterion in the database
        List<TenderTechnicalCriterion> tenderTechnicalCriterionList = tenderTechnicalCriterionRepository.findAll();
        assertThat(tenderTechnicalCriterionList).hasSize(databaseSizeBeforeUpdate);
        TenderTechnicalCriterion testTenderTechnicalCriterion = tenderTechnicalCriterionList.get(tenderTechnicalCriterionList.size() - 1);
        assertThat(testTenderTechnicalCriterion.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderTechnicalCriterion.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTenderTechnicalCriterion.getCriterionCategory()).isEqualTo(UPDATED_CRITERION_CATEGORY);
        assertThat(testTenderTechnicalCriterion.getIsUserAdded()).isEqualTo(UPDATED_IS_USER_ADDED);
    }

    @Test
    @Transactional
    void patchNonExistingTenderTechnicalCriterion() throws Exception {
        int databaseSizeBeforeUpdate = tenderTechnicalCriterionRepository.findAll().size();
        tenderTechnicalCriterion.setId(count.incrementAndGet());

        // Create the TenderTechnicalCriterion
        TenderTechnicalCriterionDTO tenderTechnicalCriterionDTO = tenderTechnicalCriterionMapper.toDto(tenderTechnicalCriterion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderTechnicalCriterionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tenderTechnicalCriterionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderTechnicalCriterionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderTechnicalCriterion in the database
        List<TenderTechnicalCriterion> tenderTechnicalCriterionList = tenderTechnicalCriterionRepository.findAll();
        assertThat(tenderTechnicalCriterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTenderTechnicalCriterion() throws Exception {
        int databaseSizeBeforeUpdate = tenderTechnicalCriterionRepository.findAll().size();
        tenderTechnicalCriterion.setId(count.incrementAndGet());

        // Create the TenderTechnicalCriterion
        TenderTechnicalCriterionDTO tenderTechnicalCriterionDTO = tenderTechnicalCriterionMapper.toDto(tenderTechnicalCriterion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderTechnicalCriterionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderTechnicalCriterionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderTechnicalCriterion in the database
        List<TenderTechnicalCriterion> tenderTechnicalCriterionList = tenderTechnicalCriterionRepository.findAll();
        assertThat(tenderTechnicalCriterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTenderTechnicalCriterion() throws Exception {
        int databaseSizeBeforeUpdate = tenderTechnicalCriterionRepository.findAll().size();
        tenderTechnicalCriterion.setId(count.incrementAndGet());

        // Create the TenderTechnicalCriterion
        TenderTechnicalCriterionDTO tenderTechnicalCriterionDTO = tenderTechnicalCriterionMapper.toDto(tenderTechnicalCriterion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderTechnicalCriterionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderTechnicalCriterionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderTechnicalCriterion in the database
        List<TenderTechnicalCriterion> tenderTechnicalCriterionList = tenderTechnicalCriterionRepository.findAll();
        assertThat(tenderTechnicalCriterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTenderTechnicalCriterion() throws Exception {
        // Initialize the database
        tenderTechnicalCriterionRepository.saveAndFlush(tenderTechnicalCriterion);

        int databaseSizeBeforeDelete = tenderTechnicalCriterionRepository.findAll().size();

        // Delete the tenderTechnicalCriterion
        restTenderTechnicalCriterionMockMvc
            .perform(delete(ENTITY_API_URL_ID, tenderTechnicalCriterion.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenderTechnicalCriterion> tenderTechnicalCriterionList = tenderTechnicalCriterionRepository.findAll();
        assertThat(tenderTechnicalCriterionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
