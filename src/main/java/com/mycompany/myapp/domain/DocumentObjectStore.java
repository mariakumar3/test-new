package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A DocumentObjectStore.
 */
@Entity
@Table(name = "document_object_store")
public class DocumentObjectStore implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "service_estimate_id")
    private Long serviceEstimateId;

    @Column(name = "reference_type")
    private String referenceType;

    @Column(name = "reference_id")
    private Long referenceId;

    @Column(name = "active_yn")
    private Boolean activeYn;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DocumentObjectStore id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServiceEstimateId() {
        return this.serviceEstimateId;
    }

    public DocumentObjectStore serviceEstimateId(Long serviceEstimateId) {
        this.setServiceEstimateId(serviceEstimateId);
        return this;
    }

    public void setServiceEstimateId(Long serviceEstimateId) {
        this.serviceEstimateId = serviceEstimateId;
    }

    public String getReferenceType() {
        return this.referenceType;
    }

    public DocumentObjectStore referenceType(String referenceType) {
        this.setReferenceType(referenceType);
        return this;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public Long getReferenceId() {
        return this.referenceId;
    }

    public DocumentObjectStore referenceId(Long referenceId) {
        this.setReferenceId(referenceId);
        return this;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public Boolean getActiveYn() {
        return this.activeYn;
    }

    public DocumentObjectStore activeYn(Boolean activeYn) {
        this.setActiveYn(activeYn);
        return this;
    }

    public void setActiveYn(Boolean activeYn) {
        this.activeYn = activeYn;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DocumentObjectStore)) {
            return false;
        }
        return id != null && id.equals(((DocumentObjectStore) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DocumentObjectStore{" +
            "id=" + getId() +
            ", serviceEstimateId=" + getServiceEstimateId() +
            ", referenceType='" + getReferenceType() + "'" +
            ", referenceId=" + getReferenceId() +
            ", activeYn='" + getActiveYn() + "'" +
            "}";
    }
}
