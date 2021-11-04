package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.TenderTechnicalCriterionDocument;
import com.mycompany.myapp.repository.TenderTechnicalCriterionDocumentRepository;
import com.mycompany.myapp.service.dto.TenderTechnicalCriterionDocumentDTO;
import com.mycompany.myapp.service.mapper.TenderTechnicalCriterionDocumentMapper;
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
 * Integration tests for the {@link TenderTechnicalCriterionDocumentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TenderTechnicalCriterionDocumentResourceIT {

    private static final Long DEFAULT_NIT_ID = 1L;
    private static final Long UPDATED_NIT_ID = 2L;

    private static final Long DEFAULT_TENDER_TECHNICAL_CRITERION_ID = 1L;
    private static final Long UPDATED_TENDER_TECHNICAL_CRITERION_ID = 2L;

    private static final String DEFAULT_DOCUMENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DOCUMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENT_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_OPTIONAL = false;
    private static final Boolean UPDATED_OPTIONAL = true;

    private static final String ENTITY_API_URL = "/api/tender-technical-criterion-documents";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TenderTechnicalCriterionDocumentRepository tenderTechnicalCriterionDocumentRepository;

    @Autowired
    private TenderTechnicalCriterionDocumentMapper tenderTechnicalCriterionDocumentMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenderTechnicalCriterionDocumentMockMvc;

    private TenderTechnicalCriterionDocument tenderTechnicalCriterionDocument;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderTechnicalCriterionDocument createEntity(EntityManager em) {
        TenderTechnicalCriterionDocument tenderTechnicalCriterionDocument = new TenderTechnicalCriterionDocument()
            .nitId(DEFAULT_NIT_ID)
            .tenderTechnicalCriterionId(DEFAULT_TENDER_TECHNICAL_CRITERION_ID)
            .documentType(DEFAULT_DOCUMENT_TYPE)
            .documentName(DEFAULT_DOCUMENT_NAME)
            .optional(DEFAULT_OPTIONAL);
        return tenderTechnicalCriterionDocument;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderTechnicalCriterionDocument createUpdatedEntity(EntityManager em) {
        TenderTechnicalCriterionDocument tenderTechnicalCriterionDocument = new TenderTechnicalCriterionDocument()
            .nitId(UPDATED_NIT_ID)
            .tenderTechnicalCriterionId(UPDATED_TENDER_TECHNICAL_CRITERION_ID)
            .documentType(UPDATED_DOCUMENT_TYPE)
            .documentName(UPDATED_DOCUMENT_NAME)
            .optional(UPDATED_OPTIONAL);
        return tenderTechnicalCriterionDocument;
    }

    @BeforeEach
    public void initTest() {
        tenderTechnicalCriterionDocument = createEntity(em);
    }

    @Test
    @Transactional
    void createTenderTechnicalCriterionDocument() throws Exception {
        int databaseSizeBeforeCreate = tenderTechnicalCriterionDocumentRepository.findAll().size();
        // Create the TenderTechnicalCriterionDocument
        TenderTechnicalCriterionDocumentDTO tenderTechnicalCriterionDocumentDTO = tenderTechnicalCriterionDocumentMapper.toDto(
            tenderTechnicalCriterionDocument
        );
        restTenderTechnicalCriterionDocumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderTechnicalCriterionDocumentDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TenderTechnicalCriterionDocument in the database
        List<TenderTechnicalCriterionDocument> tenderTechnicalCriterionDocumentList = tenderTechnicalCriterionDocumentRepository.findAll();
        assertThat(tenderTechnicalCriterionDocumentList).hasSize(databaseSizeBeforeCreate + 1);
        TenderTechnicalCriterionDocument testTenderTechnicalCriterionDocument = tenderTechnicalCriterionDocumentList.get(
            tenderTechnicalCriterionDocumentList.size() - 1
        );
        assertThat(testTenderTechnicalCriterionDocument.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderTechnicalCriterionDocument.getTenderTechnicalCriterionId()).isEqualTo(DEFAULT_TENDER_TECHNICAL_CRITERION_ID);
        assertThat(testTenderTechnicalCriterionDocument.getDocumentType()).isEqualTo(DEFAULT_DOCUMENT_TYPE);
        assertThat(testTenderTechnicalCriterionDocument.getDocumentName()).isEqualTo(DEFAULT_DOCUMENT_NAME);
        assertThat(testTenderTechnicalCriterionDocument.getOptional()).isEqualTo(DEFAULT_OPTIONAL);
    }

    @Test
    @Transactional
    void createTenderTechnicalCriterionDocumentWithExistingId() throws Exception {
        // Create the TenderTechnicalCriterionDocument with an existing ID
        tenderTechnicalCriterionDocument.setId(1L);
        TenderTechnicalCriterionDocumentDTO tenderTechnicalCriterionDocumentDTO = tenderTechnicalCriterionDocumentMapper.toDto(
            tenderTechnicalCriterionDocument
        );

        int databaseSizeBeforeCreate = tenderTechnicalCriterionDocumentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderTechnicalCriterionDocumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderTechnicalCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderTechnicalCriterionDocument in the database
        List<TenderTechnicalCriterionDocument> tenderTechnicalCriterionDocumentList = tenderTechnicalCriterionDocumentRepository.findAll();
        assertThat(tenderTechnicalCriterionDocumentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTenderTechnicalCriterionDocuments() throws Exception {
        // Initialize the database
        tenderTechnicalCriterionDocumentRepository.saveAndFlush(tenderTechnicalCriterionDocument);

        // Get all the tenderTechnicalCriterionDocumentList
        restTenderTechnicalCriterionDocumentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenderTechnicalCriterionDocument.getId().intValue())))
            .andExpect(jsonPath("$.[*].nitId").value(hasItem(DEFAULT_NIT_ID.intValue())))
            .andExpect(jsonPath("$.[*].tenderTechnicalCriterionId").value(hasItem(DEFAULT_TENDER_TECHNICAL_CRITERION_ID.intValue())))
            .andExpect(jsonPath("$.[*].documentType").value(hasItem(DEFAULT_DOCUMENT_TYPE)))
            .andExpect(jsonPath("$.[*].documentName").value(hasItem(DEFAULT_DOCUMENT_NAME)))
            .andExpect(jsonPath("$.[*].optional").value(hasItem(DEFAULT_OPTIONAL.booleanValue())));
    }

    @Test
    @Transactional
    void getTenderTechnicalCriterionDocument() throws Exception {
        // Initialize the database
        tenderTechnicalCriterionDocumentRepository.saveAndFlush(tenderTechnicalCriterionDocument);

        // Get the tenderTechnicalCriterionDocument
        restTenderTechnicalCriterionDocumentMockMvc
            .perform(get(ENTITY_API_URL_ID, tenderTechnicalCriterionDocument.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenderTechnicalCriterionDocument.getId().intValue()))
            .andExpect(jsonPath("$.nitId").value(DEFAULT_NIT_ID.intValue()))
            .andExpect(jsonPath("$.tenderTechnicalCriterionId").value(DEFAULT_TENDER_TECHNICAL_CRITERION_ID.intValue()))
            .andExpect(jsonPath("$.documentType").value(DEFAULT_DOCUMENT_TYPE))
            .andExpect(jsonPath("$.documentName").value(DEFAULT_DOCUMENT_NAME))
            .andExpect(jsonPath("$.optional").value(DEFAULT_OPTIONAL.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingTenderTechnicalCriterionDocument() throws Exception {
        // Get the tenderTechnicalCriterionDocument
        restTenderTechnicalCriterionDocumentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTenderTechnicalCriterionDocument() throws Exception {
        // Initialize the database
        tenderTechnicalCriterionDocumentRepository.saveAndFlush(tenderTechnicalCriterionDocument);

        int databaseSizeBeforeUpdate = tenderTechnicalCriterionDocumentRepository.findAll().size();

        // Update the tenderTechnicalCriterionDocument
        TenderTechnicalCriterionDocument updatedTenderTechnicalCriterionDocument = tenderTechnicalCriterionDocumentRepository
            .findById(tenderTechnicalCriterionDocument.getId())
            .get();
        // Disconnect from session so that the updates on updatedTenderTechnicalCriterionDocument are not directly saved in db
        em.detach(updatedTenderTechnicalCriterionDocument);
        updatedTenderTechnicalCriterionDocument
            .nitId(UPDATED_NIT_ID)
            .tenderTechnicalCriterionId(UPDATED_TENDER_TECHNICAL_CRITERION_ID)
            .documentType(UPDATED_DOCUMENT_TYPE)
            .documentName(UPDATED_DOCUMENT_NAME)
            .optional(UPDATED_OPTIONAL);
        TenderTechnicalCriterionDocumentDTO tenderTechnicalCriterionDocumentDTO = tenderTechnicalCriterionDocumentMapper.toDto(
            updatedTenderTechnicalCriterionDocument
        );

        restTenderTechnicalCriterionDocumentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderTechnicalCriterionDocumentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderTechnicalCriterionDocumentDTO))
            )
            .andExpect(status().isOk());

        // Validate the TenderTechnicalCriterionDocument in the database
        List<TenderTechnicalCriterionDocument> tenderTechnicalCriterionDocumentList = tenderTechnicalCriterionDocumentRepository.findAll();
        assertThat(tenderTechnicalCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
        TenderTechnicalCriterionDocument testTenderTechnicalCriterionDocument = tenderTechnicalCriterionDocumentList.get(
            tenderTechnicalCriterionDocumentList.size() - 1
        );
        assertThat(testTenderTechnicalCriterionDocument.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderTechnicalCriterionDocument.getTenderTechnicalCriterionId()).isEqualTo(UPDATED_TENDER_TECHNICAL_CRITERION_ID);
        assertThat(testTenderTechnicalCriterionDocument.getDocumentType()).isEqualTo(UPDATED_DOCUMENT_TYPE);
        assertThat(testTenderTechnicalCriterionDocument.getDocumentName()).isEqualTo(UPDATED_DOCUMENT_NAME);
        assertThat(testTenderTechnicalCriterionDocument.getOptional()).isEqualTo(UPDATED_OPTIONAL);
    }

    @Test
    @Transactional
    void putNonExistingTenderTechnicalCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderTechnicalCriterionDocumentRepository.findAll().size();
        tenderTechnicalCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderTechnicalCriterionDocument
        TenderTechnicalCriterionDocumentDTO tenderTechnicalCriterionDocumentDTO = tenderTechnicalCriterionDocumentMapper.toDto(
            tenderTechnicalCriterionDocument
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderTechnicalCriterionDocumentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderTechnicalCriterionDocumentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderTechnicalCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderTechnicalCriterionDocument in the database
        List<TenderTechnicalCriterionDocument> tenderTechnicalCriterionDocumentList = tenderTechnicalCriterionDocumentRepository.findAll();
        assertThat(tenderTechnicalCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTenderTechnicalCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderTechnicalCriterionDocumentRepository.findAll().size();
        tenderTechnicalCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderTechnicalCriterionDocument
        TenderTechnicalCriterionDocumentDTO tenderTechnicalCriterionDocumentDTO = tenderTechnicalCriterionDocumentMapper.toDto(
            tenderTechnicalCriterionDocument
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderTechnicalCriterionDocumentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderTechnicalCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderTechnicalCriterionDocument in the database
        List<TenderTechnicalCriterionDocument> tenderTechnicalCriterionDocumentList = tenderTechnicalCriterionDocumentRepository.findAll();
        assertThat(tenderTechnicalCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTenderTechnicalCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderTechnicalCriterionDocumentRepository.findAll().size();
        tenderTechnicalCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderTechnicalCriterionDocument
        TenderTechnicalCriterionDocumentDTO tenderTechnicalCriterionDocumentDTO = tenderTechnicalCriterionDocumentMapper.toDto(
            tenderTechnicalCriterionDocument
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderTechnicalCriterionDocumentMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderTechnicalCriterionDocumentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderTechnicalCriterionDocument in the database
        List<TenderTechnicalCriterionDocument> tenderTechnicalCriterionDocumentList = tenderTechnicalCriterionDocumentRepository.findAll();
        assertThat(tenderTechnicalCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTenderTechnicalCriterionDocumentWithPatch() throws Exception {
        // Initialize the database
        tenderTechnicalCriterionDocumentRepository.saveAndFlush(tenderTechnicalCriterionDocument);

        int databaseSizeBeforeUpdate = tenderTechnicalCriterionDocumentRepository.findAll().size();

        // Update the tenderTechnicalCriterionDocument using partial update
        TenderTechnicalCriterionDocument partialUpdatedTenderTechnicalCriterionDocument = new TenderTechnicalCriterionDocument();
        partialUpdatedTenderTechnicalCriterionDocument.setId(tenderTechnicalCriterionDocument.getId());

        partialUpdatedTenderTechnicalCriterionDocument
            .tenderTechnicalCriterionId(UPDATED_TENDER_TECHNICAL_CRITERION_ID)
            .documentType(UPDATED_DOCUMENT_TYPE)
            .optional(UPDATED_OPTIONAL);

        restTenderTechnicalCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderTechnicalCriterionDocument.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderTechnicalCriterionDocument))
            )
            .andExpect(status().isOk());

        // Validate the TenderTechnicalCriterionDocument in the database
        List<TenderTechnicalCriterionDocument> tenderTechnicalCriterionDocumentList = tenderTechnicalCriterionDocumentRepository.findAll();
        assertThat(tenderTechnicalCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
        TenderTechnicalCriterionDocument testTenderTechnicalCriterionDocument = tenderTechnicalCriterionDocumentList.get(
            tenderTechnicalCriterionDocumentList.size() - 1
        );
        assertThat(testTenderTechnicalCriterionDocument.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderTechnicalCriterionDocument.getTenderTechnicalCriterionId()).isEqualTo(UPDATED_TENDER_TECHNICAL_CRITERION_ID);
        assertThat(testTenderTechnicalCriterionDocument.getDocumentType()).isEqualTo(UPDATED_DOCUMENT_TYPE);
        assertThat(testTenderTechnicalCriterionDocument.getDocumentName()).isEqualTo(DEFAULT_DOCUMENT_NAME);
        assertThat(testTenderTechnicalCriterionDocument.getOptional()).isEqualTo(UPDATED_OPTIONAL);
    }

    @Test
    @Transactional
    void fullUpdateTenderTechnicalCriterionDocumentWithPatch() throws Exception {
        // Initialize the database
        tenderTechnicalCriterionDocumentRepository.saveAndFlush(tenderTechnicalCriterionDocument);

        int databaseSizeBeforeUpdate = tenderTechnicalCriterionDocumentRepository.findAll().size();

        // Update the tenderTechnicalCriterionDocument using partial update
        TenderTechnicalCriterionDocument partialUpdatedTenderTechnicalCriterionDocument = new TenderTechnicalCriterionDocument();
        partialUpdatedTenderTechnicalCriterionDocument.setId(tenderTechnicalCriterionDocument.getId());

        partialUpdatedTenderTechnicalCriterionDocument
            .nitId(UPDATED_NIT_ID)
            .tenderTechnicalCriterionId(UPDATED_TENDER_TECHNICAL_CRITERION_ID)
            .documentType(UPDATED_DOCUMENT_TYPE)
            .documentName(UPDATED_DOCUMENT_NAME)
            .optional(UPDATED_OPTIONAL);

        restTenderTechnicalCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderTechnicalCriterionDocument.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderTechnicalCriterionDocument))
            )
            .andExpect(status().isOk());

        // Validate the TenderTechnicalCriterionDocument in the database
        List<TenderTechnicalCriterionDocument> tenderTechnicalCriterionDocumentList = tenderTechnicalCriterionDocumentRepository.findAll();
        assertThat(tenderTechnicalCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
        TenderTechnicalCriterionDocument testTenderTechnicalCriterionDocument = tenderTechnicalCriterionDocumentList.get(
            tenderTechnicalCriterionDocumentList.size() - 1
        );
        assertThat(testTenderTechnicalCriterionDocument.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderTechnicalCriterionDocument.getTenderTechnicalCriterionId()).isEqualTo(UPDATED_TENDER_TECHNICAL_CRITERION_ID);
        assertThat(testTenderTechnicalCriterionDocument.getDocumentType()).isEqualTo(UPDATED_DOCUMENT_TYPE);
        assertThat(testTenderTechnicalCriterionDocument.getDocumentName()).isEqualTo(UPDATED_DOCUMENT_NAME);
        assertThat(testTenderTechnicalCriterionDocument.getOptional()).isEqualTo(UPDATED_OPTIONAL);
    }

    @Test
    @Transactional
    void patchNonExistingTenderTechnicalCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderTechnicalCriterionDocumentRepository.findAll().size();
        tenderTechnicalCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderTechnicalCriterionDocument
        TenderTechnicalCriterionDocumentDTO tenderTechnicalCriterionDocumentDTO = tenderTechnicalCriterionDocumentMapper.toDto(
            tenderTechnicalCriterionDocument
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderTechnicalCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tenderTechnicalCriterionDocumentDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderTechnicalCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderTechnicalCriterionDocument in the database
        List<TenderTechnicalCriterionDocument> tenderTechnicalCriterionDocumentList = tenderTechnicalCriterionDocumentRepository.findAll();
        assertThat(tenderTechnicalCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTenderTechnicalCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderTechnicalCriterionDocumentRepository.findAll().size();
        tenderTechnicalCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderTechnicalCriterionDocument
        TenderTechnicalCriterionDocumentDTO tenderTechnicalCriterionDocumentDTO = tenderTechnicalCriterionDocumentMapper.toDto(
            tenderTechnicalCriterionDocument
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderTechnicalCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderTechnicalCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderTechnicalCriterionDocument in the database
        List<TenderTechnicalCriterionDocument> tenderTechnicalCriterionDocumentList = tenderTechnicalCriterionDocumentRepository.findAll();
        assertThat(tenderTechnicalCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTenderTechnicalCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderTechnicalCriterionDocumentRepository.findAll().size();
        tenderTechnicalCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderTechnicalCriterionDocument
        TenderTechnicalCriterionDocumentDTO tenderTechnicalCriterionDocumentDTO = tenderTechnicalCriterionDocumentMapper.toDto(
            tenderTechnicalCriterionDocument
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderTechnicalCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderTechnicalCriterionDocumentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderTechnicalCriterionDocument in the database
        List<TenderTechnicalCriterionDocument> tenderTechnicalCriterionDocumentList = tenderTechnicalCriterionDocumentRepository.findAll();
        assertThat(tenderTechnicalCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTenderTechnicalCriterionDocument() throws Exception {
        // Initialize the database
        tenderTechnicalCriterionDocumentRepository.saveAndFlush(tenderTechnicalCriterionDocument);

        int databaseSizeBeforeDelete = tenderTechnicalCriterionDocumentRepository.findAll().size();

        // Delete the tenderTechnicalCriterionDocument
        restTenderTechnicalCriterionDocumentMockMvc
            .perform(delete(ENTITY_API_URL_ID, tenderTechnicalCriterionDocument.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenderTechnicalCriterionDocument> tenderTechnicalCriterionDocumentList = tenderTechnicalCriterionDocumentRepository.findAll();
        assertThat(tenderTechnicalCriterionDocumentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
