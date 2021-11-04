package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.TenderTechnicalCriterionDocument} entity.
 */
public class TenderTechnicalCriterionDocumentDTO implements Serializable {

    private Long id;

    private Long nitId;

    private Long tenderTechnicalCriterionId;

    private String documentType;

    private String documentName;

    private Boolean optional;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNitId() {
        return nitId;
    }

    public void setNitId(Long nitId) {
        this.nitId = nitId;
    }

    public Long getTenderTechnicalCriterionId() {
        return tenderTechnicalCriterionId;
    }

    public void setTenderTechnicalCriterionId(Long tenderTechnicalCriterionId) {
        this.tenderTechnicalCriterionId = tenderTechnicalCriterionId;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Boolean getOptional() {
        return optional;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderTechnicalCriterionDocumentDTO)) {
            return false;
        }

        TenderTechnicalCriterionDocumentDTO tenderTechnicalCriterionDocumentDTO = (TenderTechnicalCriterionDocumentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tenderTechnicalCriterionDocumentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderTechnicalCriterionDocumentDTO{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", tenderTechnicalCriterionId=" + getTenderTechnicalCriterionId() +
            ", documentType='" + getDocumentType() + "'" +
            ", documentName='" + getDocumentName() + "'" +
            ", optional='" + getOptional() + "'" +
            "}";
    }
}
