package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.TenderPreQualCriterionDocument;
import com.mycompany.myapp.repository.TenderPreQualCriterionDocumentRepository;
import com.mycompany.myapp.service.dto.TenderPreQualCriterionDocumentDTO;
import com.mycompany.myapp.service.mapper.TenderPreQualCriterionDocumentMapper;
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
 * Integration tests for the {@link TenderPreQualCriterionDocumentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TenderPreQualCriterionDocumentResourceIT {

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

    private static final String ENTITY_API_URL = "/api/tender-pre-qual-criterion-documents";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TenderPreQualCriterionDocumentRepository tenderPreQualCriterionDocumentRepository;

    @Autowired
    private TenderPreQualCriterionDocumentMapper tenderPreQualCriterionDocumentMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenderPreQualCriterionDocumentMockMvc;

    private TenderPreQualCriterionDocument tenderPreQualCriterionDocument;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderPreQualCriterionDocument createEntity(EntityManager em) {
        TenderPreQualCriterionDocument tenderPreQualCriterionDocument = new TenderPreQualCriterionDocument()
            .nitId(DEFAULT_NIT_ID)
            .tenderTechnicalCriterionId(DEFAULT_TENDER_TECHNICAL_CRITERION_ID)
            .documentType(DEFAULT_DOCUMENT_TYPE)
            .documentName(DEFAULT_DOCUMENT_NAME)
            .optional(DEFAULT_OPTIONAL);
        return tenderPreQualCriterionDocument;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenderPreQualCriterionDocument createUpdatedEntity(EntityManager em) {
        TenderPreQualCriterionDocument tenderPreQualCriterionDocument = new TenderPreQualCriterionDocument()
            .nitId(UPDATED_NIT_ID)
            .tenderTechnicalCriterionId(UPDATED_TENDER_TECHNICAL_CRITERION_ID)
            .documentType(UPDATED_DOCUMENT_TYPE)
            .documentName(UPDATED_DOCUMENT_NAME)
            .optional(UPDATED_OPTIONAL);
        return tenderPreQualCriterionDocument;
    }

    @BeforeEach
    public void initTest() {
        tenderPreQualCriterionDocument = createEntity(em);
    }

    @Test
    @Transactional
    void createTenderPreQualCriterionDocument() throws Exception {
        int databaseSizeBeforeCreate = tenderPreQualCriterionDocumentRepository.findAll().size();
        // Create the TenderPreQualCriterionDocument
        TenderPreQualCriterionDocumentDTO tenderPreQualCriterionDocumentDTO = tenderPreQualCriterionDocumentMapper.toDto(
            tenderPreQualCriterionDocument
        );
        restTenderPreQualCriterionDocumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderPreQualCriterionDocumentDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TenderPreQualCriterionDocument in the database
        List<TenderPreQualCriterionDocument> tenderPreQualCriterionDocumentList = tenderPreQualCriterionDocumentRepository.findAll();
        assertThat(tenderPreQualCriterionDocumentList).hasSize(databaseSizeBeforeCreate + 1);
        TenderPreQualCriterionDocument testTenderPreQualCriterionDocument = tenderPreQualCriterionDocumentList.get(
            tenderPreQualCriterionDocumentList.size() - 1
        );
        assertThat(testTenderPreQualCriterionDocument.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderPreQualCriterionDocument.getTenderTechnicalCriterionId()).isEqualTo(DEFAULT_TENDER_TECHNICAL_CRITERION_ID);
        assertThat(testTenderPreQualCriterionDocument.getDocumentType()).isEqualTo(DEFAULT_DOCUMENT_TYPE);
        assertThat(testTenderPreQualCriterionDocument.getDocumentName()).isEqualTo(DEFAULT_DOCUMENT_NAME);
        assertThat(testTenderPreQualCriterionDocument.getOptional()).isEqualTo(DEFAULT_OPTIONAL);
    }

    @Test
    @Transactional
    void createTenderPreQualCriterionDocumentWithExistingId() throws Exception {
        // Create the TenderPreQualCriterionDocument with an existing ID
        tenderPreQualCriterionDocument.setId(1L);
        TenderPreQualCriterionDocumentDTO tenderPreQualCriterionDocumentDTO = tenderPreQualCriterionDocumentMapper.toDto(
            tenderPreQualCriterionDocument
        );

        int databaseSizeBeforeCreate = tenderPreQualCriterionDocumentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderPreQualCriterionDocumentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderPreQualCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderPreQualCriterionDocument in the database
        List<TenderPreQualCriterionDocument> tenderPreQualCriterionDocumentList = tenderPreQualCriterionDocumentRepository.findAll();
        assertThat(tenderPreQualCriterionDocumentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTenderPreQualCriterionDocuments() throws Exception {
        // Initialize the database
        tenderPreQualCriterionDocumentRepository.saveAndFlush(tenderPreQualCriterionDocument);

        // Get all the tenderPreQualCriterionDocumentList
        restTenderPreQualCriterionDocumentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenderPreQualCriterionDocument.getId().intValue())))
            .andExpect(jsonPath("$.[*].nitId").value(hasItem(DEFAULT_NIT_ID.intValue())))
            .andExpect(jsonPath("$.[*].tenderTechnicalCriterionId").value(hasItem(DEFAULT_TENDER_TECHNICAL_CRITERION_ID.intValue())))
            .andExpect(jsonPath("$.[*].documentType").value(hasItem(DEFAULT_DOCUMENT_TYPE)))
            .andExpect(jsonPath("$.[*].documentName").value(hasItem(DEFAULT_DOCUMENT_NAME)))
            .andExpect(jsonPath("$.[*].optional").value(hasItem(DEFAULT_OPTIONAL.booleanValue())));
    }

    @Test
    @Transactional
    void getTenderPreQualCriterionDocument() throws Exception {
        // Initialize the database
        tenderPreQualCriterionDocumentRepository.saveAndFlush(tenderPreQualCriterionDocument);

        // Get the tenderPreQualCriterionDocument
        restTenderPreQualCriterionDocumentMockMvc
            .perform(get(ENTITY_API_URL_ID, tenderPreQualCriterionDocument.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenderPreQualCriterionDocument.getId().intValue()))
            .andExpect(jsonPath("$.nitId").value(DEFAULT_NIT_ID.intValue()))
            .andExpect(jsonPath("$.tenderTechnicalCriterionId").value(DEFAULT_TENDER_TECHNICAL_CRITERION_ID.intValue()))
            .andExpect(jsonPath("$.documentType").value(DEFAULT_DOCUMENT_TYPE))
            .andExpect(jsonPath("$.documentName").value(DEFAULT_DOCUMENT_NAME))
            .andExpect(jsonPath("$.optional").value(DEFAULT_OPTIONAL.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingTenderPreQualCriterionDocument() throws Exception {
        // Get the tenderPreQualCriterionDocument
        restTenderPreQualCriterionDocumentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTenderPreQualCriterionDocument() throws Exception {
        // Initialize the database
        tenderPreQualCriterionDocumentRepository.saveAndFlush(tenderPreQualCriterionDocument);

        int databaseSizeBeforeUpdate = tenderPreQualCriterionDocumentRepository.findAll().size();

        // Update the tenderPreQualCriterionDocument
        TenderPreQualCriterionDocument updatedTenderPreQualCriterionDocument = tenderPreQualCriterionDocumentRepository
            .findById(tenderPreQualCriterionDocument.getId())
            .get();
        // Disconnect from session so that the updates on updatedTenderPreQualCriterionDocument are not directly saved in db
        em.detach(updatedTenderPreQualCriterionDocument);
        updatedTenderPreQualCriterionDocument
            .nitId(UPDATED_NIT_ID)
            .tenderTechnicalCriterionId(UPDATED_TENDER_TECHNICAL_CRITERION_ID)
            .documentType(UPDATED_DOCUMENT_TYPE)
            .documentName(UPDATED_DOCUMENT_NAME)
            .optional(UPDATED_OPTIONAL);
        TenderPreQualCriterionDocumentDTO tenderPreQualCriterionDocumentDTO = tenderPreQualCriterionDocumentMapper.toDto(
            updatedTenderPreQualCriterionDocument
        );

        restTenderPreQualCriterionDocumentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderPreQualCriterionDocumentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderPreQualCriterionDocumentDTO))
            )
            .andExpect(status().isOk());

        // Validate the TenderPreQualCriterionDocument in the database
        List<TenderPreQualCriterionDocument> tenderPreQualCriterionDocumentList = tenderPreQualCriterionDocumentRepository.findAll();
        assertThat(tenderPreQualCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
        TenderPreQualCriterionDocument testTenderPreQualCriterionDocument = tenderPreQualCriterionDocumentList.get(
            tenderPreQualCriterionDocumentList.size() - 1
        );
        assertThat(testTenderPreQualCriterionDocument.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderPreQualCriterionDocument.getTenderTechnicalCriterionId()).isEqualTo(UPDATED_TENDER_TECHNICAL_CRITERION_ID);
        assertThat(testTenderPreQualCriterionDocument.getDocumentType()).isEqualTo(UPDATED_DOCUMENT_TYPE);
        assertThat(testTenderPreQualCriterionDocument.getDocumentName()).isEqualTo(UPDATED_DOCUMENT_NAME);
        assertThat(testTenderPreQualCriterionDocument.getOptional()).isEqualTo(UPDATED_OPTIONAL);
    }

    @Test
    @Transactional
    void putNonExistingTenderPreQualCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderPreQualCriterionDocumentRepository.findAll().size();
        tenderPreQualCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderPreQualCriterionDocument
        TenderPreQualCriterionDocumentDTO tenderPreQualCriterionDocumentDTO = tenderPreQualCriterionDocumentMapper.toDto(
            tenderPreQualCriterionDocument
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderPreQualCriterionDocumentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tenderPreQualCriterionDocumentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderPreQualCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderPreQualCriterionDocument in the database
        List<TenderPreQualCriterionDocument> tenderPreQualCriterionDocumentList = tenderPreQualCriterionDocumentRepository.findAll();
        assertThat(tenderPreQualCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTenderPreQualCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderPreQualCriterionDocumentRepository.findAll().size();
        tenderPreQualCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderPreQualCriterionDocument
        TenderPreQualCriterionDocumentDTO tenderPreQualCriterionDocumentDTO = tenderPreQualCriterionDocumentMapper.toDto(
            tenderPreQualCriterionDocument
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderPreQualCriterionDocumentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderPreQualCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderPreQualCriterionDocument in the database
        List<TenderPreQualCriterionDocument> tenderPreQualCriterionDocumentList = tenderPreQualCriterionDocumentRepository.findAll();
        assertThat(tenderPreQualCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTenderPreQualCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderPreQualCriterionDocumentRepository.findAll().size();
        tenderPreQualCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderPreQualCriterionDocument
        TenderPreQualCriterionDocumentDTO tenderPreQualCriterionDocumentDTO = tenderPreQualCriterionDocumentMapper.toDto(
            tenderPreQualCriterionDocument
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderPreQualCriterionDocumentMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tenderPreQualCriterionDocumentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderPreQualCriterionDocument in the database
        List<TenderPreQualCriterionDocument> tenderPreQualCriterionDocumentList = tenderPreQualCriterionDocumentRepository.findAll();
        assertThat(tenderPreQualCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTenderPreQualCriterionDocumentWithPatch() throws Exception {
        // Initialize the database
        tenderPreQualCriterionDocumentRepository.saveAndFlush(tenderPreQualCriterionDocument);

        int databaseSizeBeforeUpdate = tenderPreQualCriterionDocumentRepository.findAll().size();

        // Update the tenderPreQualCriterionDocument using partial update
        TenderPreQualCriterionDocument partialUpdatedTenderPreQualCriterionDocument = new TenderPreQualCriterionDocument();
        partialUpdatedTenderPreQualCriterionDocument.setId(tenderPreQualCriterionDocument.getId());

        partialUpdatedTenderPreQualCriterionDocument.optional(UPDATED_OPTIONAL);

        restTenderPreQualCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderPreQualCriterionDocument.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderPreQualCriterionDocument))
            )
            .andExpect(status().isOk());

        // Validate the TenderPreQualCriterionDocument in the database
        List<TenderPreQualCriterionDocument> tenderPreQualCriterionDocumentList = tenderPreQualCriterionDocumentRepository.findAll();
        assertThat(tenderPreQualCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
        TenderPreQualCriterionDocument testTenderPreQualCriterionDocument = tenderPreQualCriterionDocumentList.get(
            tenderPreQualCriterionDocumentList.size() - 1
        );
        assertThat(testTenderPreQualCriterionDocument.getNitId()).isEqualTo(DEFAULT_NIT_ID);
        assertThat(testTenderPreQualCriterionDocument.getTenderTechnicalCriterionId()).isEqualTo(DEFAULT_TENDER_TECHNICAL_CRITERION_ID);
        assertThat(testTenderPreQualCriterionDocument.getDocumentType()).isEqualTo(DEFAULT_DOCUMENT_TYPE);
        assertThat(testTenderPreQualCriterionDocument.getDocumentName()).isEqualTo(DEFAULT_DOCUMENT_NAME);
        assertThat(testTenderPreQualCriterionDocument.getOptional()).isEqualTo(UPDATED_OPTIONAL);
    }

    @Test
    @Transactional
    void fullUpdateTenderPreQualCriterionDocumentWithPatch() throws Exception {
        // Initialize the database
        tenderPreQualCriterionDocumentRepository.saveAndFlush(tenderPreQualCriterionDocument);

        int databaseSizeBeforeUpdate = tenderPreQualCriterionDocumentRepository.findAll().size();

        // Update the tenderPreQualCriterionDocument using partial update
        TenderPreQualCriterionDocument partialUpdatedTenderPreQualCriterionDocument = new TenderPreQualCriterionDocument();
        partialUpdatedTenderPreQualCriterionDocument.setId(tenderPreQualCriterionDocument.getId());

        partialUpdatedTenderPreQualCriterionDocument
            .nitId(UPDATED_NIT_ID)
            .tenderTechnicalCriterionId(UPDATED_TENDER_TECHNICAL_CRITERION_ID)
            .documentType(UPDATED_DOCUMENT_TYPE)
            .documentName(UPDATED_DOCUMENT_NAME)
            .optional(UPDATED_OPTIONAL);

        restTenderPreQualCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTenderPreQualCriterionDocument.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTenderPreQualCriterionDocument))
            )
            .andExpect(status().isOk());

        // Validate the TenderPreQualCriterionDocument in the database
        List<TenderPreQualCriterionDocument> tenderPreQualCriterionDocumentList = tenderPreQualCriterionDocumentRepository.findAll();
        assertThat(tenderPreQualCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
        TenderPreQualCriterionDocument testTenderPreQualCriterionDocument = tenderPreQualCriterionDocumentList.get(
            tenderPreQualCriterionDocumentList.size() - 1
        );
        assertThat(testTenderPreQualCriterionDocument.getNitId()).isEqualTo(UPDATED_NIT_ID);
        assertThat(testTenderPreQualCriterionDocument.getTenderTechnicalCriterionId()).isEqualTo(UPDATED_TENDER_TECHNICAL_CRITERION_ID);
        assertThat(testTenderPreQualCriterionDocument.getDocumentType()).isEqualTo(UPDATED_DOCUMENT_TYPE);
        assertThat(testTenderPreQualCriterionDocument.getDocumentName()).isEqualTo(UPDATED_DOCUMENT_NAME);
        assertThat(testTenderPreQualCriterionDocument.getOptional()).isEqualTo(UPDATED_OPTIONAL);
    }

    @Test
    @Transactional
    void patchNonExistingTenderPreQualCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderPreQualCriterionDocumentRepository.findAll().size();
        tenderPreQualCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderPreQualCriterionDocument
        TenderPreQualCriterionDocumentDTO tenderPreQualCriterionDocumentDTO = tenderPreQualCriterionDocumentMapper.toDto(
            tenderPreQualCriterionDocument
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderPreQualCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tenderPreQualCriterionDocumentDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderPreQualCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderPreQualCriterionDocument in the database
        List<TenderPreQualCriterionDocument> tenderPreQualCriterionDocumentList = tenderPreQualCriterionDocumentRepository.findAll();
        assertThat(tenderPreQualCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTenderPreQualCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderPreQualCriterionDocumentRepository.findAll().size();
        tenderPreQualCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderPreQualCriterionDocument
        TenderPreQualCriterionDocumentDTO tenderPreQualCriterionDocumentDTO = tenderPreQualCriterionDocumentMapper.toDto(
            tenderPreQualCriterionDocument
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderPreQualCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderPreQualCriterionDocumentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TenderPreQualCriterionDocument in the database
        List<TenderPreQualCriterionDocument> tenderPreQualCriterionDocumentList = tenderPreQualCriterionDocumentRepository.findAll();
        assertThat(tenderPreQualCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTenderPreQualCriterionDocument() throws Exception {
        int databaseSizeBeforeUpdate = tenderPreQualCriterionDocumentRepository.findAll().size();
        tenderPreQualCriterionDocument.setId(count.incrementAndGet());

        // Create the TenderPreQualCriterionDocument
        TenderPreQualCriterionDocumentDTO tenderPreQualCriterionDocumentDTO = tenderPreQualCriterionDocumentMapper.toDto(
            tenderPreQualCriterionDocument
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTenderPreQualCriterionDocumentMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tenderPreQualCriterionDocumentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TenderPreQualCriterionDocument in the database
        List<TenderPreQualCriterionDocument> tenderPreQualCriterionDocumentList = tenderPreQualCriterionDocumentRepository.findAll();
        assertThat(tenderPreQualCriterionDocumentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTenderPreQualCriterionDocument() throws Exception {
        // Initialize the database
        tenderPreQualCriterionDocumentRepository.saveAndFlush(tenderPreQualCriterionDocument);

        int databaseSizeBeforeDelete = tenderPreQualCriterionDocumentRepository.findAll().size();

        // Delete the tenderPreQualCriterionDocument
        restTenderPreQualCriterionDocumentMockMvc
            .perform(delete(ENTITY_API_URL_ID, tenderPreQualCriterionDocument.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenderPreQualCriterionDocument> tenderPreQualCriterionDocumentList = tenderPreQualCriterionDocumentRepository.findAll();
        assertThat(tenderPreQualCriterionDocumentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
