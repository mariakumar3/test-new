package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.TenderEligibilityCriterionDocument} entity.
 */
public class TenderEligibilityCriterionDocumentDTO implements Serializable {

    private Long id;

    private Long nitId;

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
        if (!(o instanceof TenderEligibilityCriterionDocumentDTO)) {
            return false;
        }

        TenderEligibilityCriterionDocumentDTO tenderEligibilityCriterionDocumentDTO = (TenderEligibilityCriterionDocumentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tenderEligibilityCriterionDocumentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderEligibilityCriterionDocumentDTO{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", documentType='" + getDocumentType() + "'" +
            ", documentName='" + getDocumentName() + "'" +
            ", optional='" + getOptional() + "'" +
            "}";
    }
}
