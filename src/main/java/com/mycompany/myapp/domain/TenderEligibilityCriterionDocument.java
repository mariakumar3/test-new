package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A TenderEligibilityCriterionDocument.
 */
@Entity
@Table(name = "tender_eligibility_criterion_document")
public class TenderEligibilityCriterionDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nit_id")
    private Long nitId;

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

    public TenderEligibilityCriterionDocument id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNitId() {
        return this.nitId;
    }

    public TenderEligibilityCriterionDocument nitId(Long nitId) {
        this.setNitId(nitId);
        return this;
    }

    public void setNitId(Long nitId) {
        this.nitId = nitId;
    }

    public String getDocumentType() {
        return this.documentType;
    }

    public TenderEligibilityCriterionDocument documentType(String documentType) {
        this.setDocumentType(documentType);
        return this;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentName() {
        return this.documentName;
    }

    public TenderEligibilityCriterionDocument documentName(String documentName) {
        this.setDocumentName(documentName);
        return this;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Boolean getOptional() {
        return this.optional;
    }

    public TenderEligibilityCriterionDocument optional(Boolean optional) {
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
        if (!(o instanceof TenderEligibilityCriterionDocument)) {
            return false;
        }
        return id != null && id.equals(((TenderEligibilityCriterionDocument) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderEligibilityCriterionDocument{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", documentType='" + getDocumentType() + "'" +
            ", documentName='" + getDocumentName() + "'" +
            ", optional='" + getOptional() + "'" +
            "}";
    }
}
