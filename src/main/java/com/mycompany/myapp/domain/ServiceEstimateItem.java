package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * A ServiceEstimateItem.
 */
@Entity
@Table(name = "service_estimate_item")
public class ServiceEstimateItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "service_estimate_id")
    private Long serviceEstimateId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_item_id")
    private Long categoryItemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "specifications")
    private String specifications;

    @Column(name = "buy_back_yn")
    private Boolean buyBackYn;

    @Column(name = "entry_order")
    private Integer entryOrder;

    @Column(name = "denomination_type")
    private String denominationType;

    @Column(name = "quantity", precision = 21, scale = 2)
    private BigDecimal quantity;

    @Column(name = "value_of_service", precision = 21, scale = 2)
    private BigDecimal valueOfService;

    @Column(name = "extend_of_contract", precision = 21, scale = 2)
    private BigDecimal extendOfContract;

    @Column(name = "bidding_unit", precision = 21, scale = 2)
    private BigDecimal biddingUnit;

    @Column(name = "estimate_unit_rate", precision = 21, scale = 2)
    private BigDecimal estimateUnitRate;

    @Column(name = "estimate_item_price", precision = 21, scale = 2)
    private BigDecimal estimateItemPrice;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ServiceEstimateItem id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServiceEstimateId() {
        return this.serviceEstimateId;
    }

    public ServiceEstimateItem serviceEstimateId(Long serviceEstimateId) {
        this.setServiceEstimateId(serviceEstimateId);
        return this;
    }

    public void setServiceEstimateId(Long serviceEstimateId) {
        this.serviceEstimateId = serviceEstimateId;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public ServiceEstimateItem categoryName(String categoryName) {
        this.setCategoryName(categoryName);
        return this;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCategoryItemId() {
        return this.categoryItemId;
    }

    public ServiceEstimateItem categoryItemId(Long categoryItemId) {
        this.setCategoryItemId(categoryItemId);
        return this;
    }

    public void setCategoryItemId(Long categoryItemId) {
        this.categoryItemId = categoryItemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public ServiceEstimateItem itemName(String itemName) {
        this.setItemName(itemName);
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return this.itemCode;
    }

    public ServiceEstimateItem itemCode(String itemCode) {
        this.setItemCode(itemCode);
        return this;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getSpecifications() {
        return this.specifications;
    }

    public ServiceEstimateItem specifications(String specifications) {
        this.setSpecifications(specifications);
        return this;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public Boolean getBuyBackYn() {
        return this.buyBackYn;
    }

    public ServiceEstimateItem buyBackYn(Boolean buyBackYn) {
        this.setBuyBackYn(buyBackYn);
        return this;
    }

    public void setBuyBackYn(Boolean buyBackYn) {
        this.buyBackYn = buyBackYn;
    }

    public Integer getEntryOrder() {
        return this.entryOrder;
    }

    public ServiceEstimateItem entryOrder(Integer entryOrder) {
        this.setEntryOrder(entryOrder);
        return this;
    }

    public void setEntryOrder(Integer entryOrder) {
        this.entryOrder = entryOrder;
    }

    public String getDenominationType() {
        return this.denominationType;
    }

    public ServiceEstimateItem denominationType(String denominationType) {
        this.setDenominationType(denominationType);
        return this;
    }

    public void setDenominationType(String denominationType) {
        this.denominationType = denominationType;
    }

    public BigDecimal getQuantity() {
        return this.quantity;
    }

    public ServiceEstimateItem quantity(BigDecimal quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getValueOfService() {
        return this.valueOfService;
    }

    public ServiceEstimateItem valueOfService(BigDecimal valueOfService) {
        this.setValueOfService(valueOfService);
        return this;
    }

    public void setValueOfService(BigDecimal valueOfService) {
        this.valueOfService = valueOfService;
    }

    public BigDecimal getExtendOfContract() {
        return this.extendOfContract;
    }

    public ServiceEstimateItem extendOfContract(BigDecimal extendOfContract) {
        this.setExtendOfContract(extendOfContract);
        return this;
    }

    public void setExtendOfContract(BigDecimal extendOfContract) {
        this.extendOfContract = extendOfContract;
    }

    public BigDecimal getBiddingUnit() {
        return this.biddingUnit;
    }

    public ServiceEstimateItem biddingUnit(BigDecimal biddingUnit) {
        this.setBiddingUnit(biddingUnit);
        return this;
    }

    public void setBiddingUnit(BigDecimal biddingUnit) {
        this.biddingUnit = biddingUnit;
    }

    public BigDecimal getEstimateUnitRate() {
        return this.estimateUnitRate;
    }

    public ServiceEstimateItem estimateUnitRate(BigDecimal estimateUnitRate) {
        this.setEstimateUnitRate(estimateUnitRate);
        return this;
    }

    public void setEstimateUnitRate(BigDecimal estimateUnitRate) {
        this.estimateUnitRate = estimateUnitRate;
    }

    public BigDecimal getEstimateItemPrice() {
        return this.estimateItemPrice;
    }

    public ServiceEstimateItem estimateItemPrice(BigDecimal estimateItemPrice) {
        this.setEstimateItemPrice(estimateItemPrice);
        return this;
    }

    public void setEstimateItemPrice(BigDecimal estimateItemPrice) {
        this.estimateItemPrice = estimateItemPrice;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceEstimateItem)) {
            return false;
        }
        return id != null && id.equals(((ServiceEstimateItem) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceEstimateItem{" +
            "id=" + getId() +
            ", serviceEstimateId=" + getServiceEstimateId() +
            ", categoryName='" + getCategoryName() + "'" +
            ", categoryItemId=" + getCategoryItemId() +
            ", itemName='" + getItemName() + "'" +
            ", itemCode='" + getItemCode() + "'" +
            ", specifications='" + getSpecifications() + "'" +
            ", buyBackYn='" + getBuyBackYn() + "'" +
            ", entryOrder=" + getEntryOrder() +
            ", denominationType='" + getDenominationType() + "'" +
            ", quantity=" + getQuantity() +
            ", valueOfService=" + getValueOfService() +
            ", extendOfContract=" + getExtendOfContract() +
            ", biddingUnit=" + getBiddingUnit() +
            ", estimateUnitRate=" + getEstimateUnitRate() +
            ", estimateItemPrice=" + getEstimateItemPrice() +
            "}";
    }
}
