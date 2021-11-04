package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.TenderPreQualCriterion;
import com.mycompany.myapp.repository.TenderPreQualCriterionRepository;
import com.mycompany.myapp.service.dto.TenderPreQualCriterionDTO;
import com.mycompany.myapp.service.mapper.TenderPreQualCriterionMapper;
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
 * Integration tests for the {@link TenderPreQualCriterionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TenderPreQualCriterionResourceIT {

    private static final Long DEFAULT_NIT_ID = 1L;
    private static final Long UPDATED_NIT_ID = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CRITERION_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CRITERION_CATEGORY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_USER_ADDED = false;
    private static final Boolean UPDATED_IS_USER_ADDED = true;

    private static final String ENTITY_API_URL = "/api/tender-pre-qual-criteria";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TenderPreQualCriterionRepository tenderPreQualCriterionRepository;

    @Autowired
    private TenderPreQualCriterionMapper tenderPreQualCriterionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenderPreQualCriterionMockMvc;

    private TenderPreQualCriterion tenderPreQualCriterion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderPreQualCriterion createEntity(EntityManager em) {
        TenderPreQualCriterion tenderPreQualCriterion = new TenderPreQualCriterion()
            .nitId(DEFAULT_NIT_ID)
            .description(DEFAULT_DESCRIPTION)
            .criterionCategory(DEFAULT_CRITERION_CATEGORY)
            .isUserAdded(DEFAULT_IS_USER_ADDED);
        return tenderPreQualCriterion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderPreQualCriterion createUpdatedEntity(EntityManager em) {
        TenderPreQualCriterion tenderPreQualCriterion = new TenderPreQualCriterion()
            .nitId(UPDATED_NIT_ID)
            .description(UPDATED_DESCRIPTION)
            .criterionCategory(UPDATED_CRITERION_CATEGORY)
            .isUserAdded(UPDATED_IS_USER_ADDED);
        return tenderPreQualCriterion;
    }

    @BeforeEach
    public void initTest() {
        tenderPreQualCriterion = createEntity(em);
    }

    @Test
    @Transactional
    void createTenderPreQualCriterion() throws Exception {
        int databaseSizeBeforeCreate = tenderPreQualCriterionRepository.findAll().size();
        // Create the TenderPreQualCriterion
        TenderPreQualCriterionDTO tenderPreQualCriterionDTO = tenderPreQualCriterionMapper.toDto(tenderPreQualCriterion);
        restTenderPreQualCriterionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderPreQualCriterionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TenderPreQualCriterion in the database
        List<TenderPreQualCriterion> tenderPreQualCriterionList = tenderPreQualCriterionRepository.findAll();
        assertThat(tenderPreQualCriterionList).hasSize(databaseSizeBeforeCreate + 1);
        TenderPreQualCriterion testTenderPreQualCriterion = tenderPreQualCriterionList.get(tenderPreQualCriterionList.size() - 1);
        assertThat(testTenderPreQualCriterion.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderPreQualCriterion.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTenderPreQualCriterion.getCriterionCategory()).isEqualTo(DEFAULT_CRITERION_CATEGORY);
        assertThat(testTenderPreQualCriterion.getIsUserAdded()).isEqualTo(DEFAULT_IS_USER_ADDED);
    }

    @Test
    @Transactional
    void createTenderPreQualCriterionWithExistingId() throws Exception {
        // Create the TenderPreQualCriterion with an existing ID
        tenderPreQualCriterion.setId(1L);
        TenderPreQualCriterionDTO tenderPreQualCriterionDTO = tenderPreQualCriterionMapper.toDto(tenderPreQualCriterion);

        int databaseSizeBeforeCreate = tenderPreQualCriterionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderPreQualCriterionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderPreQualCriterionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderPreQualCriterion in the database
        List<TenderPreQualCriterion> tenderPreQualCriterionList = tenderPreQualCriterionRepository.findAll();
        assertThat(tenderPreQualCriterionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTenderPreQualCriteria() throws Exception {
        // Initialize the database
        tenderPreQualCriterionRepository.saveAndFlush(tenderPreQualCriterion);

        // Get all the tenderPreQualCriterionList
        restTenderPreQualCriterionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenderPreQualCriterion.getId().intValue())))
            .andExpect(jsonPath("$.[*].nitId").value(hasItem(DEFAULT_NIT_ID.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].criterionCategory").value(hasItem(DEFAULT_CRITERION_CATEGORY)))
            .andExpect(jsonPath("$.[*].isUserAdded").value(hasItem(DEFAULT_IS_USER_ADDED.booleanValue())));
    }

    @Test
    @Transactional
    void getTenderPreQualCriterion() throws Exception {
        // Initialize the database
        tenderPreQualCriterionRepository.saveAndFlush(tenderPreQualCriterion);

        // Get the tenderPreQualCriterion
        restTenderPreQualCriterionMockMvc
            .perform(get(ENTITY_API_URL_ID, tenderPreQualCriterion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenderPreQualCriterion.getId().intValue()))
            .andExpect(jsonPath("$.nitId").value(DEFAULT_NIT_ID.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.criterionCategory").value(DEFAULT_CRITERION_CATEGORY))
            .andExpect(jsonPath("$.isUserAdded").value(DEFAULT_IS_USER_ADDED.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingTenderPreQualCriterion() throws Exception {
        // Get the tenderPreQualCriterion
        restTenderPreQualCriterionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTenderPreQualCriterion() throws Exception {
        // Initialize the database
        tenderPreQualCriterionRepository.saveAndFlush(tenderPreQualCriterion);

        int databaseSizeBeforeUpdate = tenderPreQualCriterionRepository.findAll().size();

        // Update the tenderPreQualCriterion
        TenderPreQualCriterion updatedTenderPreQualCriterion = tenderPreQualCriterionRepository
            .findById(tenderPreQualCriterion.getId())
            .get();
        // Disconnect from session so that the updates on updatedTenderPreQualCriterion are not directly saved in db
        em.detach(updatedTenderPreQualCriterion);
        updatedTenderPreQualCriterion
            .nitId(UPDATED_NIT_ID)
            .description(UPDATED_DESCRIPTION)
            .criterionCategory(UPDATED_CRITERION_CATEGORY)
            .isUserAdded(UPDATED_IS_USER_ADDED);
        TenderPreQualCriterionDTO tenderPreQualCriterionDTO = tenderPreQualCriterionMapper.toDto(updatedTenderPreQualCriterion);

        restTenderPreQualCriterionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderPreQualCriterionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderPreQualCriterionDTO))
            )
            .andExpect(status().isOk());

        // Validate the TenderPreQualCriterion in the database
        List<TenderPreQualCriterion> tenderPreQualCriterionList = tenderPreQualCriterionRepository.findAll();
        assertThat(tenderPreQualCriterionList).hasSize(databaseSizeBeforeUpdate);
        TenderPreQualCriterion testTenderPreQualCriterion = tenderPreQualCriterionList.get(tenderPreQualCriterionList.size() - 1);
        assertThat(testTenderPreQualCriterion.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderPreQualCriterion.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTenderPreQualCriterion.getCriterionCategory()).isEqualTo(UPDATED_CRITERION_CATEGORY);
        assertThat(testTenderPreQualCriterion.getIsUserAdded()).isEqualTo(UPDATED_IS_USER_ADDED);
    }

    @Test
    @Transactional
    void putNonExistingTenderPreQualCriterion() throws Exception {
        int databaseSizeBeforeUpdate = tenderPreQualCriterionRepository.findAll().size();
        tenderPreQualCriterion.setId(count.incrementAndGet());

        // Create the TenderPreQualCriterion
        TenderPreQualCriterionDTO tenderPreQualCriterionDTO = tenderPreQualCriterionMapper.toDto(tenderPreQualCriterion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderPreQualCriterionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderPreQualCriterionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderPreQualCriterionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderPreQualCriterion in the database
        List<TenderPreQualCriterion> tenderPreQualCriterionList = tenderPreQualCriterionRepository.findAll();
        assertThat(tenderPreQualCriterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTenderPreQualCriterion() throws Exception {
        int databaseSizeBeforeUpdate = tenderPreQualCriterionRepository.findAll().size();
        tenderPreQualCriterion.setId(count.incrementAndGet());

        // Create the TenderPreQualCriterion
        TenderPreQualCriterionDTO tenderPreQualCriterionDTO = tenderPreQualCriterionMapper.toDto(tenderPreQualCriterion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderPreQualCriterionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderPreQualCriterionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderPreQualCriterion in the database
        List<TenderPreQualCriterion> tenderPreQualCriterionList = tenderPreQualCriterionRepository.findAll();
        assertThat(tenderPreQualCriterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTenderPreQualCriterion() throws Exception {
        int databaseSizeBeforeUpdate = tenderPreQualCriterionRepository.findAll().size();
        tenderPreQualCriterion.setId(count.incrementAndGet());

        // Create the TenderPreQualCriterion
        TenderPreQualCriterionDTO tenderPreQualCriterionDTO = tenderPreQualCriterionMapper.toDto(tenderPreQualCriterion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderPreQualCriterionMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderPreQualCriterionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderPreQualCriterion in the database
        List<TenderPreQualCriterion> tenderPreQualCriterionList = tenderPreQualCriterionRepository.findAll();
        assertThat(tenderPreQualCriterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTenderPreQualCriterionWithPatch() throws Exception {
        // Initialize the database
        tenderPreQualCriterionRepository.saveAndFlush(tenderPreQualCriterion);

        int databaseSizeBeforeUpdate = tenderPreQualCriterionRepository.findAll().size();

        // Update the tenderPreQualCriterion using partial update
        TenderPreQualCriterion partialUpdatedTenderPreQualCriterion = new TenderPreQualCriterion();
        partialUpdatedTenderPreQualCriterion.setId(tenderPreQualCriterion.getId());

        partialUpdatedTenderPreQualCriterion
            .nitId(UPDATED_NIT_ID)
            .criterionCategory(UPDATED_CRITERION_CATEGORY)
            .isUserAdded(UPDATED_IS_USER_ADDED);

        restTenderPreQualCriterionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderPreQualCriterion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderPreQualCriterion))
            )
            .andExpect(status().isOk());

        // Validate the TenderPreQualCriterion in the database
        List<TenderPreQualCriterion> tenderPreQualCriterionList = tenderPreQualCriterionRepository.findAll();
        assertThat(tenderPreQualCriterionList).hasSize(databaseSizeBeforeUpdate);
        TenderPreQualCriterion testTenderPreQualCriterion = tenderPreQualCriterionList.get(tenderPreQualCriterionList.size() - 1);
        assertThat(testTenderPreQualCriterion.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderPreQualCriterion.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTenderPreQualCriterion.getCriterionCategory()).isEqualTo(UPDATED_CRITERION_CATEGORY);
        assertThat(testTenderPreQualCriterion.getIsUserAdded()).isEqualTo(UPDATED_IS_USER_ADDED);
    }

    @Test
    @Transactional
    void fullUpdateTenderPreQualCriterionWithPatch() throws Exception {
        // Initialize the database
        tenderPreQualCriterionRepository.saveAndFlush(tenderPreQualCriterion);

        int databaseSizeBeforeUpdate = tenderPreQualCriterionRepository.findAll().size();

        // Update the tenderPreQualCriterion using partial update
        TenderPreQualCriterion partialUpdatedTenderPreQualCriterion = new TenderPreQualCriterion();
        partialUpdatedTenderPreQualCriterion.setId(tenderPreQualCriterion.getId());

        partialUpdatedTenderPreQualCriterion
            .nitId(UPDATED_NIT_ID)
            .description(UPDATED_DESCRIPTION)
            .criterionCategory(UPDATED_CRITERION_CATEGORY)
            .isUserAdded(UPDATED_IS_USER_ADDED);

        restTenderPreQualCriterionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderPreQualCriterion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderPreQualCriterion))
            )
            .andExpect(status().isOk());

        // Validate the TenderPreQualCriterion in the database
        List<TenderPreQualCriterion> tenderPreQualCriterionList = tenderPreQualCriterionRepository.findAll();
        assertThat(tenderPreQualCriterionList).hasSize(databaseSizeBeforeUpdate);
        TenderPreQualCriterion testTenderPreQualCriterion = tenderPreQualCriterionList.get(tenderPreQualCriterionList.size() - 1);
        assertThat(testTenderPreQualCriterion.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderPreQualCriterion.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTenderPreQualCriterion.getCriterionCategory()).isEqualTo(UPDATED_CRITERION_CATEGORY);
        assertThat(testTenderPreQualCriterion.getIsUserAdded()).isEqualTo(UPDATED_IS_USER_ADDED);
    }

    @Test
    @Transactional
    void patchNonExistingTenderPreQualCriterion() throws Exception {
        int databaseSizeBeforeUpdate = tenderPreQualCriterionRepository.findAll().size();
        tenderPreQualCriterion.setId(count.incrementAndGet());

        // Create the TenderPreQualCriterion
        TenderPreQualCriterionDTO tenderPreQualCriterionDTO = tenderPreQualCriterionMapper.toDto(tenderPreQualCriterion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderPreQualCriterionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tenderPreQualCriterionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderPreQualCriterionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderPreQualCriterion in the database
        List<TenderPreQualCriterion> tenderPreQualCriterionList = tenderPreQualCriterionRepository.findAll();
        assertThat(tenderPreQualCriterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTenderPreQualCriterion() throws Exception {
        int databaseSizeBeforeUpdate = tenderPreQualCriterionRepository.findAll().size();
        tenderPreQualCriterion.setId(count.incrementAndGet());

        // Create the TenderPreQualCriterion
        TenderPreQualCriterionDTO tenderPreQualCriterionDTO = tenderPreQualCriterionMapper.toDto(tenderPreQualCriterion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderPreQualCriterionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderPreQualCriterionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderPreQualCriterion in the database
        List<TenderPreQualCriterion> tenderPreQualCriterionList = tenderPreQualCriterionRepository.findAll();
        assertThat(tenderPreQualCriterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTenderPreQualCriterion() throws Exception {
        int databaseSizeBeforeUpdate = tenderPreQualCriterionRepository.findAll().size();
        tenderPreQualCriterion.setId(count.incrementAndGet());

        // Create the TenderPreQualCriterion
        TenderPreQualCriterionDTO tenderPreQualCriterionDTO = tenderPreQualCriterionMapper.toDto(tenderPreQualCriterion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderPreQualCriterionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderPreQualCriterionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderPreQualCriterion in the database
        List<TenderPreQualCriterion> tenderPreQualCriterionList = tenderPreQualCriterionRepository.findAll();
        assertThat(tenderPreQualCriterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTenderPreQualCriterion() throws Exception {
        // Initialize the database
        tenderPreQualCriterionRepository.saveAndFlush(tenderPreQualCriterion);

        int databaseSizeBeforeDelete = tenderPreQualCriterionRepository.findAll().size();

        // Delete the tenderPreQualCriterion
        restTenderPreQualCriterionMockMvc
            .perform(delete(ENTITY_API_URL_ID, tenderPreQualCriterion.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenderPreQualCriterion> tenderPreQualCriterionList = tenderPreQualCriterionRepository.findAll();
        assertThat(tenderPreQualCriterionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
