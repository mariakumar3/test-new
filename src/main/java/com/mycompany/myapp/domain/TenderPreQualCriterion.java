package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A TenderPreQualCriterion.
 */
@Entity
@Table(name = "tender_pre_qual_criterion")
public class TenderPreQualCriterion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nit_id")
    private Long nitId;

    @Column(name = "description")
    private String description;

    @Column(name = "criterion_category")
    private String criterionCategory;

    @Column(name = "is_user_added")
    private Boolean isUserAdded;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TenderPreQualCriterion id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNitId() {
        return this.nitId;
    }

    public TenderPreQualCriterion nitId(Long nitId) {
        this.setNitId(nitId);
        return this;
    }

    public void setNitId(Long nitId) {
        this.nitId = nitId;
    }

    public String getDescription() {
        return this.description;
    }

    public TenderPreQualCriterion description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCriterionCategory() {
        return this.criterionCategory;
    }

    public TenderPreQualCriterion criterionCategory(String criterionCategory) {
        this.setCriterionCategory(criterionCategory);
        return this;
    }

    public void setCriterionCategory(String criterionCategory) {
        this.criterionCategory = criterionCategory;
    }

    public Boolean getIsUserAdded() {
        return this.isUserAdded;
    }

    public TenderPreQualCriterion isUserAdded(Boolean isUserAdded) {
        this.setIsUserAdded(isUserAdded);
        return this;
    }

    public void setIsUserAdded(Boolean isUserAdded) {
        this.isUserAdded = isUserAdded;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderPreQualCriterion)) {
            return false;
        }
        return id != null && id.equals(((TenderPreQualCriterion) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderPreQualCriterion{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", description='" + getDescription() + "'" +
            ", criterionCategory='" + getCriterionCategory() + "'" +
            ", isUserAdded='" + getIsUserAdded() + "'" +
            "}";
    }
}
