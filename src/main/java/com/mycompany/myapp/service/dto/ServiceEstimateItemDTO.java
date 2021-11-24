package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ServiceEstimateItem} entity.
 */
public class ServiceEstimateItemDTO implements Serializable {

    private Long id;

    private Long serviceEstimateId;

    private String categoryName;

    private Long categoryItemId;

    private String itemName;

    private String itemCode;

    private String specifications;

    private Boolean buyBackYn;

    private Integer entryOrder;

    private String denominationType;

    private BigDecimal quantity;

    private BigDecimal valueOfService;

    private BigDecimal extendOfContract;

    private BigDecimal biddingUnit;

    private BigDecimal estimateUnitRate;

    private BigDecimal estimateItemPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServiceEstimateId() {
        return serviceEstimateId;
    }

    public void setServiceEstimateId(Long serviceEstimateId) {
        this.serviceEstimateId = serviceEstimateId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCategoryItemId() {
        return categoryItemId;
    }

    public void setCategoryItemId(Long categoryItemId) {
        this.categoryItemId = categoryItemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public Boolean getBuyBackYn() {
        return buyBackYn;
    }

    public void setBuyBackYn(Boolean buyBackYn) {
        this.buyBackYn = buyBackYn;
    }

    public Integer getEntryOrder() {
        return entryOrder;
    }

    public void setEntryOrder(Integer entryOrder) {
        this.entryOrder = entryOrder;
    }

    public String getDenominationType() {
        return denominationType;
    }

    public void setDenominationType(String denominationType) {
        this.denominationType = denominationType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getValueOfService() {
        return valueOfService;
    }

    public void setValueOfService(BigDecimal valueOfService) {
        this.valueOfService = valueOfService;
    }

    public BigDecimal getExtendOfContract() {
        return extendOfContract;
    }

    public void setExtendOfContract(BigDecimal extendOfContract) {
        this.extendOfContract = extendOfContract;
    }

    public BigDecimal getBiddingUnit() {
        return biddingUnit;
    }

    public void setBiddingUnit(BigDecimal biddingUnit) {
        this.biddingUnit = biddingUnit;
    }

    public BigDecimal getEstimateUnitRate() {
        return estimateUnitRate;
    }

    public void setEstimateUnitRate(BigDecimal estimateUnitRate) {
        this.estimateUnitRate = estimateUnitRate;
    }

    public BigDecimal getEstimateItemPrice() {
        return estimateItemPrice;
    }

    public void setEstimateItemPrice(BigDecimal estimateItemPrice) {
        this.estimateItemPrice = estimateItemPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceEstimateItemDTO)) {
            return false;
        }

        ServiceEstimateItemDTO serviceEstimateItemDTO = (ServiceEstimateItemDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, serviceEstimateItemDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceEstimateItemDTO{" +
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
