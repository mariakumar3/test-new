package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.TenderEligibilityCriterion;
import com.mycompany.myapp.repository.TenderEligibilityCriterionRepository;
import com.mycompany.myapp.service.dto.TenderEligibilityCriterionDTO;
import com.mycompany.myapp.service.mapper.TenderEligibilityCriterionMapper;
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
 * Integration tests for the {@link TenderEligibilityCriterionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TenderEligibilityCriterionResourceIT {

    private static final Long DEFAULT_NIT_ID = 1L;
    private static final Long UPDATED_NIT_ID = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_USER_ADDED = false;
    private static final Boolean UPDATED_IS_USER_ADDED = true;

    private static final String ENTITY_API_URL = "/api/tender-eligibility-criteria";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TenderEligibilityCriterionRepository tenderEligibilityCriterionRepository;

    @Autowired
    private TenderEligibilityCriterionMapper tenderEligibilityCriterionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenderEligibilityCriterionMockMvc;

    private TenderEligibilityCriterion tenderEligibilityCriterion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderEligibilityCriterion createEntity(EntityManager em) {
        TenderEligibilityCriterion tenderEligibilityCriterion = new TenderEligibilityCriterion()
            .nitId(DEFAULT_NIT_ID)
            .description(DEFAULT_DESCRIPTION)
            .isUserAdded(DEFAULT_IS_USER_ADDED);
        return tenderEligibilityCriterion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderEligibilityCriterion createUpdatedEntity(EntityManager em) {
        TenderEligibilityCriterion tenderEligibilityCriterion = new TenderEligibilityCriterion()
            .nitId(UPDATED_NIT_ID)
            .description(UPDATED_DESCRIPTION)
            .isUserAdded(UPDATED_IS_USER_ADDED);
        return tenderEligibilityCriterion;
    }

    @BeforeEach
    public void initTest() {
        tenderEligibilityCriterion = createEntity(em);
    }

    @Test
    @Transactional
    void createTenderEligibilityCriterion() throws Exception {
        int databaseSizeBeforeCreate = tenderEligibilityCriterionRepository.findAll().size();
        // Create the TenderEligibilityCriterion
        TenderEligibilityCriterionDTO tenderEligibilityCriterionDTO = tenderEligibilityCriterionMapper.toDto(tenderEligibilityCriterion);
        restTenderEligibilityCriterionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderEligibilityCriterionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TenderEligibilityCriterion in the database
        List<TenderEligibilityCriterion> tenderEligibilityCriterionList = tenderEligibilityCriterionRepository.findAll();
        assertThat(tenderEligibilityCriterionList).hasSize(databaseSizeBeforeCreate + 1);
        TenderEligibilityCriterion testTenderEligibilityCriterion = tenderEligibilityCriterionList.get(
            tenderEligibilityCriterionList.size() - 1
        );
        assertThat(testTenderEligibilityCriterion.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderEligibilityCriterion.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTenderEligibilityCriterion.getIsUserAdded()).isEqualTo(DEFAULT_IS_USER_ADDED);
    }

    @Test
    @Transactional
    void createTenderEligibilityCriterionWithExistingId() throws Exception {
        // Create the TenderEligibilityCriterion with an existing ID
        tenderEligibilityCriterion.setId(1L);
        TenderEligibilityCriterionDTO tenderEligibilityCriterionDTO = tenderEligibilityCriterionMapper.toDto(tenderEligibilityCriterion);

        int databaseSizeBeforeCreate = tenderEligibilityCriterionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderEligibilityCriterionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderEligibilityCriterionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderEligibilityCriterion in the database
        List<TenderEligibilityCriterion> tenderEligibilityCriterionList = tenderEligibilityCriterionRepository.findAll();
        assertThat(tenderEligibilityCriterionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTenderEligibilityCriteria() throws Exception {
        // Initialize the database
        tenderEligibilityCriterionRepository.saveAndFlush(tenderEligibilityCriterion);

        // Get all the tenderEligibilityCriterionList
        restTenderEligibilityCriterionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenderEligibilityCriterion.getId().intValue())))
            .andExpect(jsonPath("$.[*].nitId").value(hasItem(DEFAULT_NIT_ID.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].isUserAdded").value(hasItem(DEFAULT_IS_USER_ADDED.booleanValue())));
    }

    @Test
    @Transactional
    void getTenderEligibilityCriterion() throws Exception {
        // Initialize the database
        tenderEligibilityCriterionRepository.saveAndFlush(tenderEligibilityCriterion);

        // Get the tenderEligibilityCriterion
        restTenderEligibilityCriterionMockMvc
            .perform(get(ENTITY_API_URL_ID, tenderEligibilityCriterion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenderEligibilityCriterion.getId().intValue()))
            .andExpect(jsonPath("$.nitId").value(DEFAULT_NIT_ID.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.isUserAdded").value(DEFAULT_IS_USER_ADDED.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingTenderEligibilityCriterion() throws Exception {
        // Get the tenderEligibilityCriterion
        restTenderEligibilityCriterionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTenderEligibilityCriterion() throws Exception {
        // Initialize the database
        tenderEligibilityCriterionRepository.saveAndFlush(tenderEligibilityCriterion);

        int databaseSizeBeforeUpdate = tenderEligibilityCriterionRepository.findAll().size();

        // Update the tenderEligibilityCriterion
        TenderEligibilityCriterion updatedTenderEligibilityCriterion = tenderEligibilityCriterionRepository
            .findById(tenderEligibilityCriterion.getId())
            .get();
        // Disconnect from session so that the updates on updatedTenderEligibilityCriterion are not directly saved in db
        em.detach(updatedTenderEligibilityCriterion);
        updatedTenderEligibilityCriterion.nitId(UPDATED_NIT_ID).description(UPDATED_DESCRIPTION).isUserAdded(UPDATED_IS_USER_ADDED);
        TenderEligibilityCriterionDTO tenderEligibilityCriterionDTO = tenderEligibilityCriterionMapper.toDto(
            updatedTenderEligibilityCriterion
        );

        restTenderEligibilityCriterionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderEligibilityCriterionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderEligibilityCriterionDTO))
            )
            .andExpect(status().isOk());

        // Validate the TenderEligibilityCriterion in the database
        List<TenderEligibilityCriterion> tenderEligibilityCriterionList = tenderEligibilityCriterionRepository.findAll();
        assertThat(tenderEligibilityCriterionList).hasSize(databaseSizeBeforeUpdate);
        TenderEligibilityCriterion testTenderEligibilityCriterion = tenderEligibilityCriterionList.get(
            tenderEligibilityCriterionList.size() - 1
        );
        assertThat(testTenderEligibilityCriterion.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderEligibilityCriterion.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTenderEligibilityCriterion.getIsUserAdded()).isEqualTo(UPDATED_IS_USER_ADDED);
    }

    @Test
    @Transactional
    void putNonExistingTenderEligibilityCriterion() throws Exception {
        int databaseSizeBeforeUpdate = tenderEligibilityCriterionRepository.findAll().size();
        tenderEligibilityCriterion.setId(count.incrementAndGet());

        // Create the TenderEligibilityCriterion
        TenderEligibilityCriterionDTO tenderEligibilityCriterionDTO = tenderEligibilityCriterionMapper.toDto(tenderEligibilityCriterion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderEligibilityCriterionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderEligibilityCriterionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderEligibilityCriterionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderEligibilityCriterion in the database
        List<TenderEligibilityCriterion> tenderEligibilityCriterionList = tenderEligibilityCriterionRepository.findAll();
        assertThat(tenderEligibilityCriterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTenderEligibilityCriterion() throws Exception {
        int databaseSizeBeforeUpdate = tenderEligibilityCriterionRepository.findAll().size();
        tenderEligibilityCriterion.setId(count.incrementAndGet());

        // Create the TenderEligibilityCriterion
        TenderEligibilityCriterionDTO tenderEligibilityCriterionDTO = tenderEligibilityCriterionMapper.toDto(tenderEligibilityCriterion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderEligibilityCriterionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderEligibilityCriterionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderEligibilityCriterion in the database
        List<TenderEligibilityCriterion> tenderEligibilityCriterionList = tenderEligibilityCriterionRepository.findAll();
        assertThat(tenderEligibilityCriterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTenderEligibilityCriterion() throws Exception {
        int databaseSizeBeforeUpdate = tenderEligibilityCriterionRepository.findAll().size();
        tenderEligibilityCriterion.setId(count.incrementAndGet());

        // Create the TenderEligibilityCriterion
        TenderEligibilityCriterionDTO tenderEligibilityCriterionDTO = tenderEligibilityCriterionMapper.toDto(tenderEligibilityCriterion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderEligibilityCriterionMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderEligibilityCriterionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderEligibilityCriterion in the database
        List<TenderEligibilityCriterion> tenderEligibilityCriterionList = tenderEligibilityCriterionRepository.findAll();
        assertThat(tenderEligibilityCriterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTenderEligibilityCriterionWithPatch() throws Exception {
        // Initialize the database
        tenderEligibilityCriterionRepository.saveAndFlush(tenderEligibilityCriterion);

        int databaseSizeBeforeUpdate = tenderEligibilityCriterionRepository.findAll().size();

        // Update the tenderEligibilityCriterion using partial update
        TenderEligibilityCriterion partialUpdatedTenderEligibilityCriterion = new TenderEligibilityCriterion();
        partialUpdatedTenderEligibilityCriterion.setId(tenderEligibilityCriterion.getId());

        partialUpdatedTenderEligibilityCriterion.nitId(UPDATED_NIT_ID).description(UPDATED_DESCRIPTION).isUserAdded(UPDATED_IS_USER_ADDED);

        restTenderEligibilityCriterionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderEligibilityCriterion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderEligibilityCriterion))
            )
            .andExpect(status().isOk());

        // Validate the TenderEligibilityCriterion in the database
        List<TenderEligibilityCriterion> tenderEligibilityCriterionList = tenderEligibilityCriterionRepository.findAll();
        assertThat(tenderEligibilityCriterionList).hasSize(databaseSizeBeforeUpdate);
        TenderEligibilityCriterion testTenderEligibilityCriterion = tenderEligibilityCriterionList.get(
            tenderEligibilityCriterionList.size() - 1
        );
        assertThat(testTenderEligibilityCriterion.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderEligibilityCriterion.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTenderEligibilityCriterion.getIsUserAdded()).isEqualTo(UPDATED_IS_USER_ADDED);
    }

    @Test
    @Transactional
    void fullUpdateTenderEligibilityCriterionWithPatch() throws Exception {
        // Initialize the database
        tenderEligibilityCriterionRepository.saveAndFlush(tenderEligibilityCriterion);

        int databaseSizeBeforeUpdate = tenderEligibilityCriterionRepository.findAll().size();

        // Update the tenderEligibilityCriterion using partial update
        TenderEligibilityCriterion partialUpdatedTenderEligibilityCriterion = new TenderEligibilityCriterion();
        partialUpdatedTenderEligibilityCriterion.setId(tenderEligibilityCriterion.getId());

        partialUpdatedTenderEligibilityCriterion.nitId(UPDATED_NIT_ID).description(UPDATED_DESCRIPTION).isUserAdded(UPDATED_IS_USER_ADDED);

        restTenderEligibilityCriterionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderEligibilityCriterion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderEligibilityCriterion))
            )
            .andExpect(status().isOk());

        // Validate the TenderEligibilityCriterion in the database
        List<TenderEligibilityCriterion> tenderEligibilityCriterionList = tenderEligibilityCriterionRepository.findAll();
        assertThat(tenderEligibilityCriterionList).hasSize(databaseSizeBeforeUpdate);
        TenderEligibilityCriterion testTenderEligibilityCriterion = tenderEligibilityCriterionList.get(
            tenderEligibilityCriterionList.size() - 1
        );
        assertThat(testTenderEligibilityCriterion.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderEligibilityCriterion.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTenderEligibilityCriterion.getIsUserAdded()).isEqualTo(UPDATED_IS_USER_ADDED);
    }

    @Test
    @Transactional
    void patchNonExistingTenderEligibilityCriterion() throws Exception {
        int databaseSizeBeforeUpdate = tenderEligibilityCriterionRepository.findAll().size();
        tenderEligibilityCriterion.setId(count.incrementAndGet());

        // Create the TenderEligibilityCriterion
        TenderEligibilityCriterionDTO tenderEligibilityCriterionDTO = tenderEligibilityCriterionMapper.toDto(tenderEligibilityCriterion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderEligibilityCriterionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tenderEligibilityCriterionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderEligibilityCriterionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderEligibilityCriterion in the database
        List<TenderEligibilityCriterion> tenderEligibilityCriterionList = tenderEligibilityCriterionRepository.findAll();
        assertThat(tenderEligibilityCriterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTenderEligibilityCriterion() throws Exception {
        int databaseSizeBeforeUpdate = tenderEligibilityCriterionRepository.findAll().size();
        tenderEligibilityCriterion.setId(count.incrementAndGet());

        // Create the TenderEligibilityCriterion
        TenderEligibilityCriterionDTO tenderEligibilityCriterionDTO = tenderEligibilityCriterionMapper.toDto(tenderEligibilityCriterion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderEligibilityCriterionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderEligibilityCriterionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderEligibilityCriterion in the database
        List<TenderEligibilityCriterion> tenderEligibilityCriterionList = tenderEligibilityCriterionRepository.findAll();
        assertThat(tenderEligibilityCriterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTenderEligibilityCriterion() throws Exception {
        int databaseSizeBeforeUpdate = tenderEligibilityCriterionRepository.findAll().size();
        tenderEligibilityCriterion.setId(count.incrementAndGet());

        // Create the TenderEligibilityCriterion
        TenderEligibilityCriterionDTO tenderEligibilityCriterionDTO = tenderEligibilityCriterionMapper.toDto(tenderEligibilityCriterion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderEligibilityCriterionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderEligibilityCriterionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderEligibilityCriterion in the database
        List<TenderEligibilityCriterion> tenderEligibilityCriterionList = tenderEligibilityCriterionRepository.findAll();
        assertThat(tenderEligibilityCriterionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTenderEligibilityCriterion() throws Exception {
        // Initialize the database
        tenderEligibilityCriterionRepository.saveAndFlush(tenderEligibilityCriterion);

        int databaseSizeBeforeDelete = tenderEligibilityCriterionRepository.findAll().size();

        // Delete the tenderEligibilityCriterion
        restTenderEligibilityCriterionMockMvc
            .perform(delete(ENTITY_API_URL_ID, tenderEligibilityCriterion.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenderEligibilityCriterion> tenderEligibilityCriterionList = tenderEligibilityCriterionRepository.findAll();
        assertThat(tenderEligibilityCriterionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
