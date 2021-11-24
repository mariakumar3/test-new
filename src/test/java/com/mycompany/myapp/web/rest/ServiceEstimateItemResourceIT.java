package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.ServiceEstimateItem;
import com.mycompany.myapp.repository.ServiceEstimateItemRepository;
import com.mycompany.myapp.service.dto.ServiceEstimateItemDTO;
import com.mycompany.myapp.service.mapper.ServiceEstimateItemMapper;
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
 * Integration tests for the {@link ServiceEstimateItemResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ServiceEstimateItemResourceIT {

    private static final Long DEFAULT_SERVICE_ESTIMATE_ID = 1L;
    private static final Long UPDATED_SERVICE_ESTIMATE_ID = 2L;

    private static final String DEFAULT_CATEGORY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_CATEGORY_ITEM_ID = 1L;
    private static final Long UPDATED_CATEGORY_ITEM_ID = 2L;

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SPECIFICATIONS = "AAAAAAAAAA";
    private static final String UPDATED_SPECIFICATIONS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_BUY_BACK_YN = false;
    private static final Boolean UPDATED_BUY_BACK_YN = true;

    private static final Integer DEFAULT_ENTRY_ORDER = 1;
    private static final Integer UPDATED_ENTRY_ORDER = 2;

    private static final String DEFAULT_DENOMINATION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DENOMINATION_TYPE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_QUANTITY = new BigDecimal(1);
    private static final BigDecimal UPDATED_QUANTITY = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VALUE_OF_SERVICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALUE_OF_SERVICE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_EXTEND_OF_CONTRACT = new BigDecimal(1);
    private static final BigDecimal UPDATED_EXTEND_OF_CONTRACT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BIDDING_UNIT = new BigDecimal(1);
    private static final BigDecimal UPDATED_BIDDING_UNIT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ESTIMATE_UNIT_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_ESTIMATE_UNIT_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ESTIMATE_ITEM_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_ESTIMATE_ITEM_PRICE = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/service-estimate-items";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ServiceEstimateItemRepository serviceEstimateItemRepository;

    @Autowired
    private ServiceEstimateItemMapper serviceEstimateItemMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restServiceEstimateItemMockMvc;

    private ServiceEstimateItem serviceEstimateItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceEstimateItem createEntity(EntityManager em) {
        ServiceEstimateItem serviceEstimateItem = new ServiceEstimateItem()
            .serviceEstimateId(DEFAULT_SERVICE_ESTIMATE_ID)
            .categoryName(DEFAULT_CATEGORY_NAME)
            .categoryItemId(DEFAULT_CATEGORY_ITEM_ID)
            .itemName(DEFAULT_ITEM_NAME)
            .itemCode(DEFAULT_ITEM_CODE)
            .specifications(DEFAULT_SPECIFICATIONS)
            .buyBackYn(DEFAULT_BUY_BACK_YN)
            .entryOrder(DEFAULT_ENTRY_ORDER)
            .denominationType(DEFAULT_DENOMINATION_TYPE)
            .quantity(DEFAULT_QUANTITY)
            .valueOfService(DEFAULT_VALUE_OF_SERVICE)
            .extendOfContract(DEFAULT_EXTEND_OF_CONTRACT)
            .biddingUnit(DEFAULT_BIDDING_UNIT)
            .estimateUnitRate(DEFAULT_ESTIMATE_UNIT_RATE)
            .estimateItemPrice(DEFAULT_ESTIMATE_ITEM_PRICE);
        return serviceEstimateItem;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceEstimateItem createUpdatedEntity(EntityManager em) {
        ServiceEstimateItem serviceEstimateItem = new ServiceEstimateItem()
            .serviceEstimateId(UPDATED_SERVICE_ESTIMATE_ID)
            .categoryName(UPDATED_CATEGORY_NAME)
            .categoryItemId(UPDATED_CATEGORY_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .itemCode(UPDATED_ITEM_CODE)
            .specifications(UPDATED_SPECIFICATIONS)
            .buyBackYn(UPDATED_BUY_BACK_YN)
            .entryOrder(UPDATED_ENTRY_ORDER)
            .denominationType(UPDATED_DENOMINATION_TYPE)
            .quantity(UPDATED_QUANTITY)
            .valueOfService(UPDATED_VALUE_OF_SERVICE)
            .extendOfContract(UPDATED_EXTEND_OF_CONTRACT)
            .biddingUnit(UPDATED_BIDDING_UNIT)
            .estimateUnitRate(UPDATED_ESTIMATE_UNIT_RATE)
            .estimateItemPrice(UPDATED_ESTIMATE_ITEM_PRICE);
        return serviceEstimateItem;
    }

    @BeforeEach
    public void initTest() {
        serviceEstimateItem = createEntity(em);
    }

    @Test
    @Transactional
    void createServiceEstimateItem() throws Exception {
        int databaseSizeBeforeCreate = serviceEstimateItemRepository.findAll().size();
        // Create the ServiceEstimateItem
        ServiceEstimateItemDTO serviceEstimateItemDTO = serviceEstimateItemMapper.toDto(serviceEstimateItem);
        restServiceEstimateItemMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceEstimateItemDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ServiceEstimateItem in the database
        List<ServiceEstimateItem> serviceEstimateItemList = serviceEstimateItemRepository.findAll();
        assertThat(serviceEstimateItemList).hasSize(databaseSizeBeforeCreate + 1);
        ServiceEstimateItem testServiceEstimateItem = serviceEstimateItemList.get(serviceEstimateItemList.size() - 1);
        assertThat(testServiceEstimateItem.getServiceEstimateId()).isEqualTo(DEFAULT_SERVICE_ESTIMATE_ID);
        assertThat(testServiceEstimateItem.getCategoryName()).isEqualTo(DEFAULT_CATEGORY_NAME);
        assertThat(testServiceEstimateItem.getCategoryItemId()).isEqualTo(DEFAULT_CATEGORY_ITEM_ID);
        assertThat(testServiceEstimateItem.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testServiceEstimateItem.getItemCode()).isEqualTo(DEFAULT_ITEM_CODE);
        assertThat(testServiceEstimateItem.getSpecifications()).isEqualTo(DEFAULT_SPECIFICATIONS);
        assertThat(testServiceEstimateItem.getBuyBackYn()).isEqualTo(DEFAULT_BUY_BACK_YN);
        assertThat(testServiceEstimateItem.getEntryOrder()).isEqualTo(DEFAULT_ENTRY_ORDER);
        assertThat(testServiceEstimateItem.getDenominationType()).isEqualTo(DEFAULT_DENOMINATION_TYPE);
        assertThat(testServiceEstimateItem.getQuantity()).isEqualByComparingTo(DEFAULT_QUANTITY);
        assertThat(testServiceEstimateItem.getValueOfService()).isEqualByComparingTo(DEFAULT_VALUE_OF_SERVICE);
        assertThat(testServiceEstimateItem.getExtendOfContract()).isEqualByComparingTo(DEFAULT_EXTEND_OF_CONTRACT);
        assertThat(testServiceEstimateItem.getBiddingUnit()).isEqualByComparingTo(DEFAULT_BIDDING_UNIT);
        assertThat(testServiceEstimateItem.getEstimateUnitRate()).isEqualByComparingTo(DEFAULT_ESTIMATE_UNIT_RATE);
        assertThat(testServiceEstimateItem.getEstimateItemPrice()).isEqualByComparingTo(DEFAULT_ESTIMATE_ITEM_PRICE);
    }

    @Test
    @Transactional
    void createServiceEstimateItemWithExistingId() throws Exception {
        // Create the ServiceEstimateItem with an existing ID
        serviceEstimateItem.setId(1L);
        ServiceEstimateItemDTO serviceEstimateItemDTO = serviceEstimateItemMapper.toDto(serviceEstimateItem);

        int databaseSizeBeforeCreate = serviceEstimateItemRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restServiceEstimateItemMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceEstimateItemDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceEstimateItem in the database
        List<ServiceEstimateItem> serviceEstimateItemList = serviceEstimateItemRepository.findAll();
        assertThat(serviceEstimateItemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllServiceEstimateItems() throws Exception {
        // Initialize the database
        serviceEstimateItemRepository.saveAndFlush(serviceEstimateItem);

        // Get all the serviceEstimateItemList
        restServiceEstimateItemMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(serviceEstimateItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].serviceEstimateId").value(hasItem(DEFAULT_SERVICE_ESTIMATE_ID.intValue())))
            .andExpect(jsonPath("$.[*].categoryName").value(hasItem(DEFAULT_CATEGORY_NAME)))
            .andExpect(jsonPath("$.[*].categoryItemId").value(hasItem(DEFAULT_CATEGORY_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].itemCode").value(hasItem(DEFAULT_ITEM_CODE)))
            .andExpect(jsonPath("$.[*].specifications").value(hasItem(DEFAULT_SPECIFICATIONS)))
            .andExpect(jsonPath("$.[*].buyBackYn").value(hasItem(DEFAULT_BUY_BACK_YN.booleanValue())))
            .andExpect(jsonPath("$.[*].entryOrder").value(hasItem(DEFAULT_ENTRY_ORDER)))
            .andExpect(jsonPath("$.[*].denominationType").value(hasItem(DEFAULT_DENOMINATION_TYPE)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(sameNumber(DEFAULT_QUANTITY))))
            .andExpect(jsonPath("$.[*].valueOfService").value(hasItem(sameNumber(DEFAULT_VALUE_OF_SERVICE))))
            .andExpect(jsonPath("$.[*].extendOfContract").value(hasItem(sameNumber(DEFAULT_EXTEND_OF_CONTRACT))))
            .andExpect(jsonPath("$.[*].biddingUnit").value(hasItem(sameNumber(DEFAULT_BIDDING_UNIT))))
            .andExpect(jsonPath("$.[*].estimateUnitRate").value(hasItem(sameNumber(DEFAULT_ESTIMATE_UNIT_RATE))))
            .andExpect(jsonPath("$.[*].estimateItemPrice").value(hasItem(sameNumber(DEFAULT_ESTIMATE_ITEM_PRICE))));
    }

    @Test
    @Transactional
    void getServiceEstimateItem() throws Exception {
        // Initialize the database
        serviceEstimateItemRepository.saveAndFlush(serviceEstimateItem);

        // Get the serviceEstimateItem
        restServiceEstimateItemMockMvc
            .perform(get(ENTITY_API_URL_ID, serviceEstimateItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(serviceEstimateItem.getId().intValue()))
            .andExpect(jsonPath("$.serviceEstimateId").value(DEFAULT_SERVICE_ESTIMATE_ID.intValue()))
            .andExpect(jsonPath("$.categoryName").value(DEFAULT_CATEGORY_NAME))
            .andExpect(jsonPath("$.categoryItemId").value(DEFAULT_CATEGORY_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.itemCode").value(DEFAULT_ITEM_CODE))
            .andExpect(jsonPath("$.specifications").value(DEFAULT_SPECIFICATIONS))
            .andExpect(jsonPath("$.buyBackYn").value(DEFAULT_BUY_BACK_YN.booleanValue()))
            .andExpect(jsonPath("$.entryOrder").value(DEFAULT_ENTRY_ORDER))
            .andExpect(jsonPath("$.denominationType").value(DEFAULT_DENOMINATION_TYPE))
            .andExpect(jsonPath("$.quantity").value(sameNumber(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.valueOfService").value(sameNumber(DEFAULT_VALUE_OF_SERVICE)))
            .andExpect(jsonPath("$.extendOfContract").value(sameNumber(DEFAULT_EXTEND_OF_CONTRACT)))
            .andExpect(jsonPath("$.biddingUnit").value(sameNumber(DEFAULT_BIDDING_UNIT)))
            .andExpect(jsonPath("$.estimateUnitRate").value(sameNumber(DEFAULT_ESTIMATE_UNIT_RATE)))
            .andExpect(jsonPath("$.estimateItemPrice").value(sameNumber(DEFAULT_ESTIMATE_ITEM_PRICE)));
    }

    @Test
    @Transactional
    void getNonExistingServiceEstimateItem() throws Exception {
        // Get the serviceEstimateItem
        restServiceEstimateItemMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewServiceEstimateItem() throws Exception {
        // Initialize the database
        serviceEstimateItemRepository.saveAndFlush(serviceEstimateItem);

        int databaseSizeBeforeUpdate = serviceEstimateItemRepository.findAll().size();

        // Update the serviceEstimateItem
        ServiceEstimateItem updatedServiceEstimateItem = serviceEstimateItemRepository.findById(serviceEstimateItem.getId()).get();
        // Disconnect from session so that the updates on updatedServiceEstimateItem are not directly saved in db
        em.detach(updatedServiceEstimateItem);
        updatedServiceEstimateItem
            .serviceEstimateId(UPDATED_SERVICE_ESTIMATE_ID)
            .categoryName(UPDATED_CATEGORY_NAME)
            .categoryItemId(UPDATED_CATEGORY_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .itemCode(UPDATED_ITEM_CODE)
            .specifications(UPDATED_SPECIFICATIONS)
            .buyBackYn(UPDATED_BUY_BACK_YN)
            .entryOrder(UPDATED_ENTRY_ORDER)
            .denominationType(UPDATED_DENOMINATION_TYPE)
            .quantity(UPDATED_QUANTITY)
            .valueOfService(UPDATED_VALUE_OF_SERVICE)
            .extendOfContract(UPDATED_EXTEND_OF_CONTRACT)
            .biddingUnit(UPDATED_BIDDING_UNIT)
            .estimateUnitRate(UPDATED_ESTIMATE_UNIT_RATE)
            .estimateItemPrice(UPDATED_ESTIMATE_ITEM_PRICE);
        ServiceEstimateItemDTO serviceEstimateItemDTO = serviceEstimateItemMapper.toDto(updatedServiceEstimateItem);

        restServiceEstimateItemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, serviceEstimateItemDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceEstimateItemDTO))
            )
            .andExpect(status().isOk());

        // Validate the ServiceEstimateItem in the database
        List<ServiceEstimateItem> serviceEstimateItemList = serviceEstimateItemRepository.findAll();
        assertThat(serviceEstimateItemList).hasSize(databaseSizeBeforeUpdate);
        ServiceEstimateItem testServiceEstimateItem = serviceEstimateItemList.get(serviceEstimateItemList.size() - 1);
        assertThat(testServiceEstimateItem.getServiceEstimateId()).isEqualTo(UPDATED_SERVICE_ESTIMATE_ID);
        assertThat(testServiceEstimateItem.getCategoryName()).isEqualTo(UPDATED_CATEGORY_NAME);
        assertThat(testServiceEstimateItem.getCategoryItemId()).isEqualTo(UPDATED_CATEGORY_ITEM_ID);
        assertThat(testServiceEstimateItem.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testServiceEstimateItem.getItemCode()).isEqualTo(UPDATED_ITEM_CODE);
        assertThat(testServiceEstimateItem.getSpecifications()).isEqualTo(UPDATED_SPECIFICATIONS);
        assertThat(testServiceEstimateItem.getBuyBackYn()).isEqualTo(UPDATED_BUY_BACK_YN);
        assertThat(testServiceEstimateItem.getEntryOrder()).isEqualTo(UPDATED_ENTRY_ORDER);
        assertThat(testServiceEstimateItem.getDenominationType()).isEqualTo(UPDATED_DENOMINATION_TYPE);
        assertThat(testServiceEstimateItem.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testServiceEstimateItem.getValueOfService()).isEqualTo(UPDATED_VALUE_OF_SERVICE);
        assertThat(testServiceEstimateItem.getExtendOfContract()).isEqualTo(UPDATED_EXTEND_OF_CONTRACT);
        assertThat(testServiceEstimateItem.getBiddingUnit()).isEqualTo(UPDATED_BIDDING_UNIT);
        assertThat(testServiceEstimateItem.getEstimateUnitRate()).isEqualTo(UPDATED_ESTIMATE_UNIT_RATE);
        assertThat(testServiceEstimateItem.getEstimateItemPrice()).isEqualTo(UPDATED_ESTIMATE_ITEM_PRICE);
    }

    @Test
    @Transactional
    void putNonExistingServiceEstimateItem() throws Exception {
        int databaseSizeBeforeUpdate = serviceEstimateItemRepository.findAll().size();
        serviceEstimateItem.setId(count.incrementAndGet());

        // Create the ServiceEstimateItem
        ServiceEstimateItemDTO serviceEstimateItemDTO = serviceEstimateItemMapper.toDto(serviceEstimateItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceEstimateItemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, serviceEstimateItemDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceEstimateItemDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceEstimateItem in the database
        List<ServiceEstimateItem> serviceEstimateItemList = serviceEstimateItemRepository.findAll();
        assertThat(serviceEstimateItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchServiceEstimateItem() throws Exception {
        int databaseSizeBeforeUpdate = serviceEstimateItemRepository.findAll().size();
        serviceEstimateItem.setId(count.incrementAndGet());

        // Create the ServiceEstimateItem
        ServiceEstimateItemDTO serviceEstimateItemDTO = serviceEstimateItemMapper.toDto(serviceEstimateItem);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceEstimateItemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceEstimateItemDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceEstimateItem in the database
        List<ServiceEstimateItem> serviceEstimateItemList = serviceEstimateItemRepository.findAll();
        assertThat(serviceEstimateItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamServiceEstimateItem() throws Exception {
        int databaseSizeBeforeUpdate = serviceEstimateItemRepository.findAll().size();
        serviceEstimateItem.setId(count.incrementAndGet());

        // Create the ServiceEstimateItem
        ServiceEstimateItemDTO serviceEstimateItemDTO = serviceEstimateItemMapper.toDto(serviceEstimateItem);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceEstimateItemMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceEstimateItemDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ServiceEstimateItem in the database
        List<ServiceEstimateItem> serviceEstimateItemList = serviceEstimateItemRepository.findAll();
        assertThat(serviceEstimateItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateServiceEstimateItemWithPatch() throws Exception {
        // Initialize the database
        serviceEstimateItemRepository.saveAndFlush(serviceEstimateItem);

        int databaseSizeBeforeUpdate = serviceEstimateItemRepository.findAll().size();

        // Update the serviceEstimateItem using partial update
        ServiceEstimateItem partialUpdatedServiceEstimateItem = new ServiceEstimateItem();
        partialUpdatedServiceEstimateItem.setId(serviceEstimateItem.getId());

        partialUpdatedServiceEstimateItem
            .categoryName(UPDATED_CATEGORY_NAME)
            .categoryItemId(UPDATED_CATEGORY_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .buyBackYn(UPDATED_BUY_BACK_YN)
            .denominationType(UPDATED_DENOMINATION_TYPE)
            .quantity(UPDATED_QUANTITY)
            .extendOfContract(UPDATED_EXTEND_OF_CONTRACT)
            .biddingUnit(UPDATED_BIDDING_UNIT)
            .estimateUnitRate(UPDATED_ESTIMATE_UNIT_RATE);

        restServiceEstimateItemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedServiceEstimateItem.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedServiceEstimateItem))
            )
            .andExpect(status().isOk());

        // Validate the ServiceEstimateItem in the database
        List<ServiceEstimateItem> serviceEstimateItemList = serviceEstimateItemRepository.findAll();
        assertThat(serviceEstimateItemList).hasSize(databaseSizeBeforeUpdate);
        ServiceEstimateItem testServiceEstimateItem = serviceEstimateItemList.get(serviceEstimateItemList.size() - 1);
        assertThat(testServiceEstimateItem.getServiceEstimateId()).isEqualTo(DEFAULT_SERVICE_ESTIMATE_ID);
        assertThat(testServiceEstimateItem.getCategoryName()).isEqualTo(UPDATED_CATEGORY_NAME);
        assertThat(testServiceEstimateItem.getCategoryItemId()).isEqualTo(UPDATED_CATEGORY_ITEM_ID);
        assertThat(testServiceEstimateItem.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testServiceEstimateItem.getItemCode()).isEqualTo(DEFAULT_ITEM_CODE);
        assertThat(testServiceEstimateItem.getSpecifications()).isEqualTo(DEFAULT_SPECIFICATIONS);
        assertThat(testServiceEstimateItem.getBuyBackYn()).isEqualTo(UPDATED_BUY_BACK_YN);
        assertThat(testServiceEstimateItem.getEntryOrder()).isEqualTo(DEFAULT_ENTRY_ORDER);
        assertThat(testServiceEstimateItem.getDenominationType()).isEqualTo(UPDATED_DENOMINATION_TYPE);
        assertThat(testServiceEstimateItem.getQuantity()).isEqualByComparingTo(UPDATED_QUANTITY);
        assertThat(testServiceEstimateItem.getValueOfService()).isEqualByComparingTo(DEFAULT_VALUE_OF_SERVICE);
        assertThat(testServiceEstimateItem.getExtendOfContract()).isEqualByComparingTo(UPDATED_EXTEND_OF_CONTRACT);
        assertThat(testServiceEstimateItem.getBiddingUnit()).isEqualByComparingTo(UPDATED_BIDDING_UNIT);
        assertThat(testServiceEstimateItem.getEstimateUnitRate()).isEqualByComparingTo(UPDATED_ESTIMATE_UNIT_RATE);
        assertThat(testServiceEstimateItem.getEstimateItemPrice()).isEqualByComparingTo(DEFAULT_ESTIMATE_ITEM_PRICE);
    }

    @Test
    @Transactional
    void fullUpdateServiceEstimateItemWithPatch() throws Exception {
        // Initialize the database
        serviceEstimateItemRepository.saveAndFlush(serviceEstimateItem);

        int databaseSizeBeforeUpdate = serviceEstimateItemRepository.findAll().size();

        // Update the serviceEstimateItem using partial update
        ServiceEstimateItem partialUpdatedServiceEstimateItem = new ServiceEstimateItem();
        partialUpdatedServiceEstimateItem.setId(serviceEstimateItem.getId());

        partialUpdatedServiceEstimateItem
            .serviceEstimateId(UPDATED_SERVICE_ESTIMATE_ID)
            .categoryName(UPDATED_CATEGORY_NAME)
            .categoryItemId(UPDATED_CATEGORY_ITEM_ID)
            .itemName(UPDATED_ITEM_NAME)
            .itemCode(UPDATED_ITEM_CODE)
            .specifications(UPDATED_SPECIFICATIONS)
            .buyBackYn(UPDATED_BUY_BACK_YN)
            .entryOrder(UPDATED_ENTRY_ORDER)
            .denominationType(UPDATED_DENOMINATION_TYPE)
            .quantity(UPDATED_QUANTITY)
            .valueOfService(UPDATED_VALUE_OF_SERVICE)
            .extendOfContract(UPDATED_EXTEND_OF_CONTRACT)
            .biddingUnit(UPDATED_BIDDING_UNIT)
            .estimateUnitRate(UPDATED_ESTIMATE_UNIT_RATE)
            .estimateItemPrice(UPDATED_ESTIMATE_ITEM_PRICE);

        restServiceEstimateItemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedServiceEstimateItem.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedServiceEstimateItem))
            )
            .andExpect(status().isOk());

        // Validate the ServiceEstimateItem in the database
        List<ServiceEstimateItem> serviceEstimateItemList = serviceEstimateItemRepository.findAll();
        assertThat(serviceEstimateItemList).hasSize(databaseSizeBeforeUpdate);
        ServiceEstimateItem testServiceEstimateItem = serviceEstimateItemList.get(serviceEstimateItemList.size() - 1);
        assertThat(testServiceEstimateItem.getServiceEstimateId()).isEqualTo(UPDATED_SERVICE_ESTIMATE_ID);
        assertThat(testServiceEstimateItem.getCategoryName()).isEqualTo(UPDATED_CATEGORY_NAME);
        assertThat(testServiceEstimateItem.getCategoryItemId()).isEqualTo(UPDATED_CATEGORY_ITEM_ID);
        assertThat(testServiceEstimateItem.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testServiceEstimateItem.getItemCode()).isEqualTo(UPDATED_ITEM_CODE);
        assertThat(testServiceEstimateItem.getSpecifications()).isEqualTo(UPDATED_SPECIFICATIONS);
        assertThat(testServiceEstimateItem.getBuyBackYn()).isEqualTo(UPDATED_BUY_BACK_YN);
        assertThat(testServiceEstimateItem.getEntryOrder()).isEqualTo(UPDATED_ENTRY_ORDER);
        assertThat(testServiceEstimateItem.getDenominationType()).isEqualTo(UPDATED_DENOMINATION_TYPE);
        assertThat(testServiceEstimateItem.getQuantity()).isEqualByComparingTo(UPDATED_QUANTITY);
        assertThat(testServiceEstimateItem.getValueOfService()).isEqualByComparingTo(UPDATED_VALUE_OF_SERVICE);
        assertThat(testServiceEstimateItem.getExtendOfContract()).isEqualByComparingTo(UPDATED_EXTEND_OF_CONTRACT);
        assertThat(testServiceEstimateItem.getBiddingUnit()).isEqualByComparingTo(UPDATED_BIDDING_UNIT);
        assertThat(testServiceEstimateItem.getEstimateUnitRate()).isEqualByComparingTo(UPDATED_ESTIMATE_UNIT_RATE);
        assertThat(testServiceEstimateItem.getEstimateItemPrice()).isEqualByComparingTo(UPDATED_ESTIMATE_ITEM_PRICE);
    }

    @Test
    @Transactional
    void patchNonExistingServiceEstimateItem() throws Exception {
        int databaseSizeBeforeUpdate = serviceEstimateItemRepository.findAll().size();
        serviceEstimateItem.setId(count.incrementAndGet());

        // Create the ServiceEstimateItem
        ServiceEstimateItemDTO serviceEstimateItemDTO = serviceEstimateItemMapper.toDto(serviceEstimateItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceEstimateItemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, serviceEstimateItemDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(serviceEstimateItemDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceEstimateItem in the database
        List<ServiceEstimateItem> serviceEstimateItemList = serviceEstimateItemRepository.findAll();
        assertThat(serviceEstimateItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchServiceEstimateItem() throws Exception {
        int databaseSizeBeforeUpdate = serviceEstimateItemRepository.findAll().size();
        serviceEstimateItem.setId(count.incrementAndGet());

        // Create the ServiceEstimateItem
        ServiceEstimateItemDTO serviceEstimateItemDTO = serviceEstimateItemMapper.toDto(serviceEstimateItem);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceEstimateItemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(serviceEstimateItemDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceEstimateItem in the database
        List<ServiceEstimateItem> serviceEstimateItemList = serviceEstimateItemRepository.findAll();
        assertThat(serviceEstimateItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamServiceEstimateItem() throws Exception {
        int databaseSizeBeforeUpdate = serviceEstimateItemRepository.findAll().size();
        serviceEstimateItem.setId(count.incrementAndGet());

        // Create the ServiceEstimateItem
        ServiceEstimateItemDTO serviceEstimateItemDTO = serviceEstimateItemMapper.toDto(serviceEstimateItem);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceEstimateItemMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(serviceEstimateItemDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ServiceEstimateItem in the database
        List<ServiceEstimateItem> serviceEstimateItemList = serviceEstimateItemRepository.findAll();
        assertThat(serviceEstimateItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteServiceEstimateItem() throws Exception {
        // Initialize the database
        serviceEstimateItemRepository.saveAndFlush(serviceEstimateItem);

        int databaseSizeBeforeDelete = serviceEstimateItemRepository.findAll().size();

        // Delete the serviceEstimateItem
        restServiceEstimateItemMockMvc
            .perform(delete(ENTITY_API_URL_ID, serviceEstimateItem.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ServiceEstimateItem> serviceEstimateItemList = serviceEstimateItemRepository.findAll();
        assertThat(serviceEstimateItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
