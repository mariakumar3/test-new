package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.DocumentObjectStore;
import com.mycompany.myapp.repository.DocumentObjectStoreRepository;
import com.mycompany.myapp.service.dto.DocumentObjectStoreDTO;
import com.mycompany.myapp.service.mapper.DocumentObjectStoreMapper;
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
 * Integration tests for the {@link DocumentObjectStoreResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DocumentObjectStoreResourceIT {

    private static final Long DEFAULT_SERVICE_ESTIMATE_ID = 1L;
    private static final Long UPDATED_SERVICE_ESTIMATE_ID = 2L;

    private static final String DEFAULT_REFERENCE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_REFERENCE_ID = 1L;
    private static final Long UPDATED_REFERENCE_ID = 2L;

    private static final Boolean DEFAULT_ACTIVE_YN = false;
    private static final Boolean UPDATED_ACTIVE_YN = true;

    private static final String ENTITY_API_URL = "/api/document-object-stores";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DocumentObjectStoreRepository documentObjectStoreRepository;

    @Autowired
    private DocumentObjectStoreMapper documentObjectStoreMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDocumentObjectStoreMockMvc;

    private DocumentObjectStore documentObjectStore;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DocumentObjectStore createEntity(EntityManager em) {
        DocumentObjectStore documentObjectStore = new DocumentObjectStore()
            .serviceEstimateId(DEFAULT_SERVICE_ESTIMATE_ID)
            .referenceType(DEFAULT_REFERENCE_TYPE)
            .referenceId(DEFAULT_REFERENCE_ID)
            .activeYn(DEFAULT_ACTIVE_YN);
        return documentObjectStore;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DocumentObjectStore createUpdatedEntity(EntityManager em) {
        DocumentObjectStore documentObjectStore = new DocumentObjectStore()
            .serviceEstimateId(UPDATED_SERVICE_ESTIMATE_ID)
            .referenceType(UPDATED_REFERENCE_TYPE)
            .referenceId(UPDATED_REFERENCE_ID)
            .activeYn(UPDATED_ACTIVE_YN);
        return documentObjectStore;
    }

    @BeforeEach
    public void initTest() {
        documentObjectStore = createEntity(em);
    }

    @Test
    @Transactional
    void createDocumentObjectStore() throws Exception {
        int databaseSizeBeforeCreate = documentObjectStoreRepository.findAll().size();
        // Create the DocumentObjectStore
        DocumentObjectStoreDTO documentObjectStoreDTO = documentObjectStoreMapper.toDto(documentObjectStore);
        restDocumentObjectStoreMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentObjectStoreDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DocumentObjectStore in the database
        List<DocumentObjectStore> documentObjectStoreList = documentObjectStoreRepository.findAll();
        assertThat(documentObjectStoreList).hasSize(databaseSizeBeforeCreate + 1);
        DocumentObjectStore testDocumentObjectStore = documentObjectStoreList.get(documentObjectStoreList.size() - 1);
        assertThat(testDocumentObjectStore.getServiceEstimateId()).isEqualTo(DEFAULT_SERVICE_ESTIMATE_ID);
        assertThat(testDocumentObjectStore.getReferenceType()).isEqualTo(DEFAULT_REFERENCE_TYPE);
        assertThat(testDocumentObjectStore.getReferenceId()).isEqualTo(DEFAULT_REFERENCE_ID);
        assertThat(testDocumentObjectStore.getActiveYn()).isEqualTo(DEFAULT_ACTIVE_YN);
    }

    @Test
    @Transactional
    void createDocumentObjectStoreWithExistingId() throws Exception {
        // Create the DocumentObjectStore with an existing ID
        documentObjectStore.setId(1L);
        DocumentObjectStoreDTO documentObjectStoreDTO = documentObjectStoreMapper.toDto(documentObjectStore);

        int databaseSizeBeforeCreate = documentObjectStoreRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDocumentObjectStoreMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentObjectStoreDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DocumentObjectStore in the database
        List<DocumentObjectStore> documentObjectStoreList = documentObjectStoreRepository.findAll();
        assertThat(documentObjectStoreList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDocumentObjectStores() throws Exception {
        // Initialize the database
        documentObjectStoreRepository.saveAndFlush(documentObjectStore);

        // Get all the documentObjectStoreList
        restDocumentObjectStoreMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(documentObjectStore.getId().intValue())))
            .andExpect(jsonPath("$.[*].serviceEstimateId").value(hasItem(DEFAULT_SERVICE_ESTIMATE_ID.intValue())))
            .andExpect(jsonPath("$.[*].referenceType").value(hasItem(DEFAULT_REFERENCE_TYPE)))
            .andExpect(jsonPath("$.[*].referenceId").value(hasItem(DEFAULT_REFERENCE_ID.intValue())))
            .andExpect(jsonPath("$.[*].activeYn").value(hasItem(DEFAULT_ACTIVE_YN.booleanValue())));
    }

    @Test
    @Transactional
    void getDocumentObjectStore() throws Exception {
        // Initialize the database
        documentObjectStoreRepository.saveAndFlush(documentObjectStore);

        // Get the documentObjectStore
        restDocumentObjectStoreMockMvc
            .perform(get(ENTITY_API_URL_ID, documentObjectStore.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(documentObjectStore.getId().intValue()))
            .andExpect(jsonPath("$.serviceEstimateId").value(DEFAULT_SERVICE_ESTIMATE_ID.intValue()))
            .andExpect(jsonPath("$.referenceType").value(DEFAULT_REFERENCE_TYPE))
            .andExpect(jsonPath("$.referenceId").value(DEFAULT_REFERENCE_ID.intValue()))
            .andExpect(jsonPath("$.activeYn").value(DEFAULT_ACTIVE_YN.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingDocumentObjectStore() throws Exception {
        // Get the documentObjectStore
        restDocumentObjectStoreMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDocumentObjectStore() throws Exception {
        // Initialize the database
        documentObjectStoreRepository.saveAndFlush(documentObjectStore);

        int databaseSizeBeforeUpdate = documentObjectStoreRepository.findAll().size();

        // Update the documentObjectStore
        DocumentObjectStore updatedDocumentObjectStore = documentObjectStoreRepository.findById(documentObjectStore.getId()).get();
        // Disconnect from session so that the updates on updatedDocumentObjectStore are not directly saved in db
        em.detach(updatedDocumentObjectStore);
        updatedDocumentObjectStore
            .serviceEstimateId(UPDATED_SERVICE_ESTIMATE_ID)
            .referenceType(UPDATED_REFERENCE_TYPE)
            .referenceId(UPDATED_REFERENCE_ID)
            .activeYn(UPDATED_ACTIVE_YN);
        DocumentObjectStoreDTO documentObjectStoreDTO = documentObjectStoreMapper.toDto(updatedDocumentObjectStore);

        restDocumentObjectStoreMockMvc
            .perform(
                put(ENTITY_API_URL_ID, documentObjectStoreDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentObjectStoreDTO))
            )
            .andExpect(status().isOk());

        // Validate the DocumentObjectStore in the database
        List<DocumentObjectStore> documentObjectStoreList = documentObjectStoreRepository.findAll();
        assertThat(documentObjectStoreList).hasSize(databaseSizeBeforeUpdate);
        DocumentObjectStore testDocumentObjectStore = documentObjectStoreList.get(documentObjectStoreList.size() - 1);
        assertThat(testDocumentObjectStore.getServiceEstimateId()).isEqualTo(UPDATED_SERVICE_ESTIMATE_ID);
        assertThat(testDocumentObjectStore.getReferenceType()).isEqualTo(UPDATED_REFERENCE_TYPE);
        assertThat(testDocumentObjectStore.getReferenceId()).isEqualTo(UPDATED_REFERENCE_ID);
        assertThat(testDocumentObjectStore.getActiveYn()).isEqualTo(UPDATED_ACTIVE_YN);
    }

    @Test
    @Transactional
    void putNonExistingDocumentObjectStore() throws Exception {
        int databaseSizeBeforeUpdate = documentObjectStoreRepository.findAll().size();
        documentObjectStore.setId(count.incrementAndGet());

        // Create the DocumentObjectStore
        DocumentObjectStoreDTO documentObjectStoreDTO = documentObjectStoreMapper.toDto(documentObjectStore);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDocumentObjectStoreMockMvc
            .perform(
                put(ENTITY_API_URL_ID, documentObjectStoreDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentObjectStoreDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DocumentObjectStore in the database
        List<DocumentObjectStore> documentObjectStoreList = documentObjectStoreRepository.findAll();
        assertThat(documentObjectStoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDocumentObjectStore() throws Exception {
        int databaseSizeBeforeUpdate = documentObjectStoreRepository.findAll().size();
        documentObjectStore.setId(count.incrementAndGet());

        // Create the DocumentObjectStore
        DocumentObjectStoreDTO documentObjectStoreDTO = documentObjectStoreMapper.toDto(documentObjectStore);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentObjectStoreMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentObjectStoreDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DocumentObjectStore in the database
        List<DocumentObjectStore> documentObjectStoreList = documentObjectStoreRepository.findAll();
        assertThat(documentObjectStoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDocumentObjectStore() throws Exception {
        int databaseSizeBeforeUpdate = documentObjectStoreRepository.findAll().size();
        documentObjectStore.setId(count.incrementAndGet());

        // Create the DocumentObjectStore
        DocumentObjectStoreDTO documentObjectStoreDTO = documentObjectStoreMapper.toDto(documentObjectStore);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentObjectStoreMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentObjectStoreDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DocumentObjectStore in the database
        List<DocumentObjectStore> documentObjectStoreList = documentObjectStoreRepository.findAll();
        assertThat(documentObjectStoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDocumentObjectStoreWithPatch() throws Exception {
        // Initialize the database
        documentObjectStoreRepository.saveAndFlush(documentObjectStore);

        int databaseSizeBeforeUpdate = documentObjectStoreRepository.findAll().size();

        // Update the documentObjectStore using partial update
        DocumentObjectStore partialUpdatedDocumentObjectStore = new DocumentObjectStore();
        partialUpdatedDocumentObjectStore.setId(documentObjectStore.getId());

        partialUpdatedDocumentObjectStore.serviceEstimateId(UPDATED_SERVICE_ESTIMATE_ID);

        restDocumentObjectStoreMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDocumentObjectStore.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDocumentObjectStore))
            )
            .andExpect(status().isOk());

        // Validate the DocumentObjectStore in the database
        List<DocumentObjectStore> documentObjectStoreList = documentObjectStoreRepository.findAll();
        assertThat(documentObjectStoreList).hasSize(databaseSizeBeforeUpdate);
        DocumentObjectStore testDocumentObjectStore = documentObjectStoreList.get(documentObjectStoreList.size() - 1);
        assertThat(testDocumentObjectStore.getServiceEstimateId()).isEqualTo(UPDATED_SERVICE_ESTIMATE_ID);
        assertThat(testDocumentObjectStore.getReferenceType()).isEqualTo(DEFAULT_REFERENCE_TYPE);
        assertThat(testDocumentObjectStore.getReferenceId()).isEqualTo(DEFAULT_REFERENCE_ID);
        assertThat(testDocumentObjectStore.getActiveYn()).isEqualTo(DEFAULT_ACTIVE_YN);
    }

    @Test
    @Transactional
    void fullUpdateDocumentObjectStoreWithPatch() throws Exception {
        // Initialize the database
        documentObjectStoreRepository.saveAndFlush(documentObjectStore);

        int databaseSizeBeforeUpdate = documentObjectStoreRepository.findAll().size();

        // Update the documentObjectStore using partial update
        DocumentObjectStore partialUpdatedDocumentObjectStore = new DocumentObjectStore();
        partialUpdatedDocumentObjectStore.setId(documentObjectStore.getId());

        partialUpdatedDocumentObjectStore
            .serviceEstimateId(UPDATED_SERVICE_ESTIMATE_ID)
            .referenceType(UPDATED_REFERENCE_TYPE)
            .referenceId(UPDATED_REFERENCE_ID)
            .activeYn(UPDATED_ACTIVE_YN);

        restDocumentObjectStoreMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDocumentObjectStore.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDocumentObjectStore))
            )
            .andExpect(status().isOk());

        // Validate the DocumentObjectStore in the database
        List<DocumentObjectStore> documentObjectStoreList = documentObjectStoreRepository.findAll();
        assertThat(documentObjectStoreList).hasSize(databaseSizeBeforeUpdate);
        DocumentObjectStore testDocumentObjectStore = documentObjectStoreList.get(documentObjectStoreList.size() - 1);
        assertThat(testDocumentObjectStore.getServiceEstimateId()).isEqualTo(UPDATED_SERVICE_ESTIMATE_ID);
        assertThat(testDocumentObjectStore.getReferenceType()).isEqualTo(UPDATED_REFERENCE_TYPE);
        assertThat(testDocumentObjectStore.getReferenceId()).isEqualTo(UPDATED_REFERENCE_ID);
        assertThat(testDocumentObjectStore.getActiveYn()).isEqualTo(UPDATED_ACTIVE_YN);
    }

    @Test
    @Transactional
    void patchNonExistingDocumentObjectStore() throws Exception {
        int databaseSizeBeforeUpdate = documentObjectStoreRepository.findAll().size();
        documentObjectStore.setId(count.incrementAndGet());

        // Create the DocumentObjectStore
        DocumentObjectStoreDTO documentObjectStoreDTO = documentObjectStoreMapper.toDto(documentObjectStore);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDocumentObjectStoreMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, documentObjectStoreDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(documentObjectStoreDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DocumentObjectStore in the database
        List<DocumentObjectStore> documentObjectStoreList = documentObjectStoreRepository.findAll();
        assertThat(documentObjectStoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDocumentObjectStore() throws Exception {
        int databaseSizeBeforeUpdate = documentObjectStoreRepository.findAll().size();
        documentObjectStore.setId(count.incrementAndGet());

        // Create the DocumentObjectStore
        DocumentObjectStoreDTO documentObjectStoreDTO = documentObjectStoreMapper.toDto(documentObjectStore);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentObjectStoreMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(documentObjectStoreDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DocumentObjectStore in the database
        List<DocumentObjectStore> documentObjectStoreList = documentObjectStoreRepository.findAll();
        assertThat(documentObjectStoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDocumentObjectStore() throws Exception {
        int databaseSizeBeforeUpdate = documentObjectStoreRepository.findAll().size();
        documentObjectStore.setId(count.incrementAndGet());

        // Create the DocumentObjectStore
        DocumentObjectStoreDTO documentObjectStoreDTO = documentObjectStoreMapper.toDto(documentObjectStore);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentObjectStoreMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(documentObjectStoreDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DocumentObjectStore in the database
        List<DocumentObjectStore> documentObjectStoreList = documentObjectStoreRepository.findAll();
        assertThat(documentObjectStoreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDocumentObjectStore() throws Exception {
        // Initialize the database
        documentObjectStoreRepository.saveAndFlush(documentObjectStore);

        int databaseSizeBeforeDelete = documentObjectStoreRepository.findAll().size();

        // Delete the documentObjectStore
        restDocumentObjectStoreMockMvc
            .perform(delete(ENTITY_API_URL_ID, documentObjectStore.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DocumentObjectStore> documentObjectStoreList = documentObjectStoreRepository.findAll();
        assertThat(documentObjectStoreList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
