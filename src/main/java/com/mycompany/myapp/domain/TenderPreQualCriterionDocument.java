package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A TenderPreQualCriterionDocument.
 */
@Entity
@Table(name = "tender_pre_qual_criterion_document")
public class TenderPreQualCriterionDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nit_id")
    private Long nitId;

    @Column(name = "tender_technical_criterion_id")
    private Long tenderTechnicalCriterionId;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "document_name")
    private String documentName;

    @Column(name = "optional")
    private Boolean optional;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TenderPreQualCriterionDocument id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNitId() {
        return this.nitId;
    }

    public TenderPreQualCriterionDocument nitId(Long nitId) {
        this.setNitId(nitId);
        return this;
    }

    public void setNitId(Long nitId) {
        this.nitId = nitId;
    }

    public Long getTenderTechnicalCriterionId() {
        return this.tenderTechnicalCriterionId;
    }

    public TenderPreQualCriterionDocument tenderTechnicalCriterionId(Long tenderTechnicalCriterionId) {
        this.setTenderTechnicalCriterionId(tenderTechnicalCriterionId);
        return this;
    }

    public void setTenderTechnicalCriterionId(Long tenderTechnicalCriterionId) {
        this.tenderTechnicalCriterionId = tenderTechnicalCriterionId;
    }

    public String getDocumentType() {
        return this.documentType;
    }

    public TenderPreQualCriterionDocument documentType(String documentType) {
        this.setDocumentType(documentType);
        return this;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentName() {
        return this.documentName;
    }

    public TenderPreQualCriterionDocument documentName(String documentName) {
        this.setDocumentName(documentName);
        return this;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Boolean getOptional() {
        return this.optional;
    }

    public TenderPreQualCriterionDocument optional(Boolean optional) {
        this.setOptional(optional);
        return this;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderPreQualCriterionDocument)) {
            return false;
        }
        return id != null && id.equals(((TenderPreQualCriterionDocument) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderPreQualCriterionDocument{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", tenderTechnicalCriterionId=" + getTenderTechnicalCriterionId() +
            ", documentType='" + getDocumentType() + "'" +
            ", documentName='" + getDocumentName() + "'" +
            ", optional='" + getOptional() + "'" +
            "}";
    }
}
