package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.TenderEligibilityCriterionDocument;
import com.mycompany.myapp.repository.TenderEligibilityCriterionDocumentRepository;
import com.mycompany.myapp.service.dto.TenderEligibilityCriterionDocumentDTO;
import com.mycompany.myapp.service.mapper.TenderEligibilityCriterionDocumentMapper;
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
 * Integration tests for the {@link TenderEligibilityCriterionDocumentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TenderEligibilityCriterionDocumentResourceIT {

    private static final Long DEFAULT_NIT_ID = 1L;
    private static final Long UPDATED_NIT_ID = 2L;

    private static final String DEFAULT_DOCUMENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DOCUMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_OPTIONAL = false;
    private static final Boolean UPDATED_OPTIONAL = true;

    private static final String ENTITY_API_URL = "/api/tender-eligibility-criterion-documents";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TenderEligibilityCriterionDocumentRepository tenderEligibilityCriterionDocumentRepository;

    @Autowired
    private TenderEligibilityCriterionDocumentMapper tenderEligibilityCriterionDocumentMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenderEligibilityCriterionDocumentMockMvc;

    private TenderEligibilityCriterionDocument tenderEligibilityCriterionDocument;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderEligibilityCriterionDocument createEntity(EntityManager em) {
        TenderEligibilityCriterionDocument tenderEligibilityCriterionDocument = new TenderEligibilityCriterionDocument()
            .nitId(DEFAULT_NIT_ID)
            .documentType(DEFAULT_DOCUMENT_TYPE)
            .documentName(DEFAULT_DOCUMENT_NAME)
            .optional(DEFAULT_OPTIONAL);
        return tenderEligibilityCriterionDocument;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderEligibilityCriterionDocument createUpdatedEntity(EntityManager em) {
        TenderEligibilityCriterionDocument tenderEligibilityCriterionDocument = new TenderEligibilityCriterionDocument()
            .nitId(UPDATED_NIT_ID)
            .documentType(UPDATED_DOCUMENT_TYPE)
            .documentName(UPDATED_DOCUMENT_NAME)
            .optional(UPDATED_OPTIONAL);
        return tenderEligibilityCriterionDocument;
    }

    @BeforeEach
    public void initTest() {
        tenderEligibilityCriterionDocument = createEntity(em);
    }

    @Test
    @Transactional
    void createTenderEligibilityCriterionDocument() throws Exception {
        int databaseSizeBeforeCreate = tenderEligibilityCriterionDocumentRepository.findAll().size();
        // Create the TenderEligibilityCriterionDocument
        TenderEligibilityCriterionDocumentDTO tenderEligibilityCriterionDocumentDTO = tenderEligibilityCriterionDocumentMapper.toDto(
            tenderEligibilityCriterionDocument
        );
        restTenderEligibilityCriterionDocumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderEligibilityCriterionDocumentDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TenderEligibilityCriterionDocument in the database
        List<TenderEligibilityCriterionDocument> tenderEligibilityCriterionDocumentList = tenderEligibilityCriterionDocumentRepository.findAll();
        assertThat(tenderEligibilityCriterionDocumentList).hasSize(databaseSizeBeforeCreate + 1);
        TenderEligibilityCriterionDocument testTenderEligibilityCriterionDocument = tenderEligibilityCriterionDocumentList.get(
            tenderEligibilityCriterionDocumentList.size() - 1
        );
        assertThat(testTenderEligibilityCriterionDocument.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderEligibilityCriterionDocument.getDocumentType()).isEqualTo(DEFAULT_DOCUMENT_TYPE);
        assertThat(testTenderEligibilityCriterionDocument.getDocumentName()).isEqualTo(DEFAULT_DOCUMENT_NAME);
        assertThat(testTenderEligibilityCriterionDocument.getOptional()).isEqualTo(DEFAULT_OPTIONAL);
    }

    @Test
    @Transactional
    void createTenderEligibilityCriterionDocumentWithExistingId() throws Exception {
        // Create the TenderEligibilityCriterionDocument with an existing ID
        tenderEligibilityCriterionDocument.setId(1L);
        TenderEligibilityCriterionDocumentDTO tenderEligibilityCriterionDocumentDTO = tenderEligibilityCriterionDocumentMapper.toDto(
            tenderEligibilityCriterionDocument
        );

        int databaseSizeBeforeCreate = tenderEligibilityCriterionDocumentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderEligibilityCriterionDocumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderEligibilityCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderEligibilityCriterionDocument in the database
        List<TenderEligibilityCriterionDocument> tenderEligibilityCriterionDocumentList = tenderEligibilityCriterionDocumentRepository.findAll();
        assertThat(tenderEligibilityCriterionDocumentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTenderEligibilityCriterionDocuments() throws Exception {
        // Initialize the database
        tenderEligibilityCriterionDocumentRepository.saveAndFlush(tenderEligibilityCriterionDocument);

        // Get all the tenderEligibilityCriterionDocumentList
        restTenderEligibilityCriterionDocumentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenderEligibilityCriterionDocument.getId().intValue())))
            .andExpect(jsonPath("$.[*].nitId").value(hasItem(DEFAULT_NIT_ID.intValue())))
            .andExpect(jsonPath("$.[*].documentType").value(hasItem(DEFAULT_DOCUMENT_TYPE)))
            .andExpect(jsonPath("$.[*].documentName").value(hasItem(DEFAULT_DOCUMENT_NAME)))
            .andExpect(jsonPath("$.[*].optional").value(hasItem(DEFAULT_OPTIONAL.booleanValue())));
    }

    @Test
    @Transactional
    void getTenderEligibilityCriterionDocument() throws Exception {
        // Initialize the database
        tenderEligibilityCriterionDocumentRepository.saveAndFlush(tenderEligibilityCriterionDocument);

        // Get the tenderEligibilityCriterionDocument
        restTenderEligibilityCriterionDocumentMockMvc
            .perform(get(ENTITY_API_URL_ID, tenderEligibilityCriterionDocument.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenderEligibilityCriterionDocument.getId().intValue()))
            .andExpect(jsonPath("$.nitId").value(DEFAULT_NIT_ID.intValue()))
            .andExpect(jsonPath("$.documentType").value(DEFAULT_DOCUMENT_TYPE))
            .andExpect(jsonPath("$.documentName").value(DEFAULT_DOCUMENT_NAME))
            .andExpect(jsonPath("$.optional").value(DEFAULT_OPTIONAL.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingTenderEligibilityCriterionDocument() throws Exception {
        // Get the tenderEligibilityCriterionDocument
        restTenderEligibilityCriterionDocumentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTenderEligibilityCriterionDocument() throws Exception {
        // Initialize the database
        tenderEligibilityCriterionDocumentRepository.saveAndFlush(tenderEligibilityCriterionDocument);

        int databaseSizeBeforeUpdate = tenderEligibilityCriterionDocumentRepository.findAll().size();

        // Update the tenderEligibilityCriterionDocument
        TenderEligibilityCriterionDocument updatedTenderEligibilityCriterionDocument = tenderEligibilityCriterionDocumentRepository
            .findById(tenderEligibilityCriterionDocument.getId())
            .get();
        // Disconnect from session so that the updates on updatedTenderEligibilityCriterionDocument are not directly saved in db
        em.detach(updatedTenderEligibilityCriterionDocument);
        updatedTenderEligibilityCriterionDocument
            .nitId(UPDATED_NIT_ID)
            .documentType(UPDATED_DOCUMENT_TYPE)
            .documentName(UPDATED_DOCUMENT_NAME)
            .optional(UPDATED_OPTIONAL);
        TenderEligibilityCriterionDocumentDTO tenderEligibilityCriterionDocumentDTO = tenderEligibilityCriterionDocumentMapper.toDto(
            updatedTenderEligibilityCriterionDocument
        );

        restTenderEligibilityCriterionDocumentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderEligibilityCriterionDocumentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderEligibilityCriterionDocumentDTO))
            )
            .andExpect(status().isOk());

        // Validate the TenderEligibilityCriterionDocument in the database
        List<TenderEligibilityCriterionDocument> tenderEligibilityCriterionDocumentList = tenderEligibilityCriterionDocumentRepository.findAll();
        assertThat(tenderEligibilityCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
        TenderEligibilityCriterionDocument testTenderEligibilityCriterionDocument = tenderEligibilityCriterionDocumentList.get(
            tenderEligibilityCriterionDocumentList.size() - 1
        );
        assertThat(testTenderEligibilityCriterionDocument.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderEligibilityCriterionDocument.getDocumentType()).isEqualTo(UPDATED_DOCUMENT_TYPE);
        assertThat(testTenderEligibilityCriterionDocument.getDocumentName()).isEqualTo(UPDATED_DOCUMENT_NAME);
        assertThat(testTenderEligibilityCriterionDocument.getOptional()).isEqualTo(UPDATED_OPTIONAL);
    }

    @Test
    @Transactional
    void putNonExistingTenderEligibilityCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderEligibilityCriterionDocumentRepository.findAll().size();
        tenderEligibilityCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderEligibilityCriterionDocument
        TenderEligibilityCriterionDocumentDTO tenderEligibilityCriterionDocumentDTO = tenderEligibilityCriterionDocumentMapper.toDto(
            tenderEligibilityCriterionDocument
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderEligibilityCriterionDocumentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderEligibilityCriterionDocumentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderEligibilityCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderEligibilityCriterionDocument in the database
        List<TenderEligibilityCriterionDocument> tenderEligibilityCriterionDocumentList = tenderEligibilityCriterionDocumentRepository.findAll();
        assertThat(tenderEligibilityCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTenderEligibilityCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderEligibilityCriterionDocumentRepository.findAll().size();
        tenderEligibilityCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderEligibilityCriterionDocument
        TenderEligibilityCriterionDocumentDTO tenderEligibilityCriterionDocumentDTO = tenderEligibilityCriterionDocumentMapper.toDto(
            tenderEligibilityCriterionDocument
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderEligibilityCriterionDocumentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderEligibilityCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderEligibilityCriterionDocument in the database
        List<TenderEligibilityCriterionDocument> tenderEligibilityCriterionDocumentList = tenderEligibilityCriterionDocumentRepository.findAll();
        assertThat(tenderEligibilityCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTenderEligibilityCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderEligibilityCriterionDocumentRepository.findAll().size();
        tenderEligibilityCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderEligibilityCriterionDocument
        TenderEligibilityCriterionDocumentDTO tenderEligibilityCriterionDocumentDTO = tenderEligibilityCriterionDocumentMapper.toDto(
            tenderEligibilityCriterionDocument
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderEligibilityCriterionDocumentMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderEligibilityCriterionDocumentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderEligibilityCriterionDocument in the database
        List<TenderEligibilityCriterionDocument> tenderEligibilityCriterionDocumentList = tenderEligibilityCriterionDocumentRepository.findAll();
        assertThat(tenderEligibilityCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTenderEligibilityCriterionDocumentWithPatch() throws Exception {
        // Initialize the database
        tenderEligibilityCriterionDocumentRepository.saveAndFlush(tenderEligibilityCriterionDocument);

        int databaseSizeBeforeUpdate = tenderEligibilityCriterionDocumentRepository.findAll().size();

        // Update the tenderEligibilityCriterionDocument using partial update
        TenderEligibilityCriterionDocument partialUpdatedTenderEligibilityCriterionDocument = new TenderEligibilityCriterionDocument();
        partialUpdatedTenderEligibilityCriterionDocument.setId(tenderEligibilityCriterionDocument.getId());

        partialUpdatedTenderEligibilityCriterionDocument
            .nitId(UPDATED_NIT_ID)
            .documentType(UPDATED_DOCUMENT_TYPE)
            .optional(UPDATED_OPTIONAL);

        restTenderEligibilityCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderEligibilityCriterionDocument.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderEligibilityCriterionDocument))
            )
            .andExpect(status().isOk());

        // Validate the TenderEligibilityCriterionDocument in the database
        List<TenderEligibilityCriterionDocument> tenderEligibilityCriterionDocumentList = tenderEligibilityCriterionDocumentRepository.findAll();
        assertThat(tenderEligibilityCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
        TenderEligibilityCriterionDocument testTenderEligibilityCriterionDocument = tenderEligibilityCriterionDocumentList.get(
            tenderEligibilityCriterionDocumentList.size() - 1
        );
        assertThat(testTenderEligibilityCriterionDocument.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderEligibilityCriterionDocument.getDocumentType()).isEqualTo(UPDATED_DOCUMENT_TYPE);
        assertThat(testTenderEligibilityCriterionDocument.getDocumentName()).isEqualTo(DEFAULT_DOCUMENT_NAME);
        assertThat(testTenderEligibilityCriterionDocument.getOptional()).isEqualTo(UPDATED_OPTIONAL);
    }

    @Test
    @Transactional
    void fullUpdateTenderEligibilityCriterionDocumentWithPatch() throws Exception {
        // Initialize the database
        tenderEligibilityCriterionDocumentRepository.saveAndFlush(tenderEligibilityCriterionDocument);

        int databaseSizeBeforeUpdate = tenderEligibilityCriterionDocumentRepository.findAll().size();

        // Update the tenderEligibilityCriterionDocument using partial update
        TenderEligibilityCriterionDocument partialUpdatedTenderEligibilityCriterionDocument = new TenderEligibilityCriterionDocument();
        partialUpdatedTenderEligibilityCriterionDocument.setId(tenderEligibilityCriterionDocument.getId());

        partialUpdatedTenderEligibilityCriterionDocument
            .nitId(UPDATED_NIT_ID)
            .documentType(UPDATED_DOCUMENT_TYPE)
            .documentName(UPDATED_DOCUMENT_NAME)
            .optional(UPDATED_OPTIONAL);

        restTenderEligibilityCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderEligibilityCriterionDocument.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderEligibilityCriterionDocument))
            )
            .andExpect(status().isOk());

        // Validate the TenderEligibilityCriterionDocument in the database
        List<TenderEligibilityCriterionDocument> tenderEligibilityCriterionDocumentList = tenderEligibilityCriterionDocumentRepository.findAll();
        assertThat(tenderEligibilityCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
        TenderEligibilityCriterionDocument testTenderEligibilityCriterionDocument = tenderEligibilityCriterionDocumentList.get(
            tenderEligibilityCriterionDocumentList.size() - 1
        );
        assertThat(testTenderEligibilityCriterionDocument.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderEligibilityCriterionDocument.getDocumentType()).isEqualTo(UPDATED_DOCUMENT_TYPE);
        assertThat(testTenderEligibilityCriterionDocument.getDocumentName()).isEqualTo(UPDATED_DOCUMENT_NAME);
        assertThat(testTenderEligibilityCriterionDocument.getOptional()).isEqualTo(UPDATED_OPTIONAL);
    }

    @Test
    @Transactional
    void patchNonExistingTenderEligibilityCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderEligibilityCriterionDocumentRepository.findAll().size();
        tenderEligibilityCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderEligibilityCriterionDocument
        TenderEligibilityCriterionDocumentDTO tenderEligibilityCriterionDocumentDTO = tenderEligibilityCriterionDocumentMapper.toDto(
            tenderEligibilityCriterionDocument
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderEligibilityCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tenderEligibilityCriterionDocumentDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderEligibilityCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderEligibilityCriterionDocument in the database
        List<TenderEligibilityCriterionDocument> tenderEligibilityCriterionDocumentList = tenderEligibilityCriterionDocumentRepository.findAll();
        assertThat(tenderEligibilityCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTenderEligibilityCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderEligibilityCriterionDocumentRepository.findAll().size();
        tenderEligibilityCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderEligibilityCriterionDocument
        TenderEligibilityCriterionDocumentDTO tenderEligibilityCriterionDocumentDTO = tenderEligibilityCriterionDocumentMapper.toDto(
            tenderEligibilityCriterionDocument
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderEligibilityCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderEligibilityCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderEligibilityCriterionDocument in the database
        List<TenderEligibilityCriterionDocument> tenderEligibilityCriterionDocumentList = tenderEligibilityCriterionDocumentRepository.findAll();
        assertThat(tenderEligibilityCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTenderEligibilityCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderEligibilityCriterionDocumentRepository.findAll().size();
        tenderEligibilityCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderEligibilityCriterionDocument
        TenderEligibilityCriterionDocumentDTO tenderEligibilityCriterionDocumentDTO = tenderEligibilityCriterionDocumentMapper.toDto(
            tenderEligibilityCriterionDocument
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderEligibilityCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderEligibilityCriterionDocumentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderEligibilityCriterionDocument in the database
        List<TenderEligibilityCriterionDocument> tenderEligibilityCriterionDocumentList = tenderEligibilityCriterionDocumentRepository.findAll();
        assertThat(tenderEligibilityCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTenderEligibilityCriterionDocument() throws Exception {
        // Initialize the database
        tenderEligibilityCriterionDocumentRepository.saveAndFlush(tenderEligibilityCriterionDocument);

        int databaseSizeBeforeDelete = tenderEligibilityCriterionDocumentRepository.findAll().size();

        // Delete the tenderEligibilityCriterionDocument
        restTenderEligibilityCriterionDocumentMockMvc
            .perform(delete(ENTITY_API_URL_ID, tenderEligibilityCriterionDocument.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenderEligibilityCriterionDocument> tenderEligibilityCriterionDocumentList = tenderEligibilityCriterionDocumentRepository.findAll();
        assertThat(tenderEligibilityCriterionDocumentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
