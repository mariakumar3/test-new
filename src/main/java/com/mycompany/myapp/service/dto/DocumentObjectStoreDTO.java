package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DocumentObjectStore} entity.
 */
public class DocumentObjectStoreDTO implements Serializable {

    private Long id;

    private Long serviceEstimateId;

    private String referenceType;

    private Long referenceId;

    private Boolean activeYn;

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

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public Boolean getActiveYn() {
        return activeYn;
    }

    public void setActiveYn(Boolean activeYn) {
        this.activeYn = activeYn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DocumentObjectStoreDTO)) {
            return false;
        }

        DocumentObjectStoreDTO documentObjectStoreDTO = (DocumentObjectStoreDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, documentObjectStoreDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DocumentObjectStoreDTO{" +
            "id=" + getId() +
            ", serviceEstimateId=" + getServiceEstimateId() +
            ", referenceType='" + getReferenceType() + "'" +
            ", referenceId=" + getReferenceId() +
            ", activeYn='" + getActiveYn() + "'" +
            "}";
    }
}
