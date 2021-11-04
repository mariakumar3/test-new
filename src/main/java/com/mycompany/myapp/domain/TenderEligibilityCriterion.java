package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A TenderEligibilityCriterion.
 */
@Entity
@Table(name = "tender_eligibility_criterion")
public class TenderEligibilityCriterion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nit_id")
    private Long nitId;

    @Column(name = "description")
    private String description;

    @Column(name = "is_user_added")
    private Boolean isUserAdded;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TenderEligibilityCriterion id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNitId() {
        return this.nitId;
    }

    public TenderEligibilityCriterion nitId(Long nitId) {
        this.setNitId(nitId);
        return this;
    }

    public void setNitId(Long nitId) {
        this.nitId = nitId;
    }

    public String getDescription() {
        return this.description;
    }

    public TenderEligibilityCriterion description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsUserAdded() {
        return this.isUserAdded;
    }

    public TenderEligibilityCriterion isUserAdded(Boolean isUserAdded) {
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
        if (!(o instanceof TenderEligibilityCriterion)) {
            return false;
        }
        return id != null && id.equals(((TenderEligibilityCriterion) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderEligibilityCriterion{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", description='" + getDescription() + "'" +
            ", isUserAdded='" + getIsUserAdded() + "'" +
            "}";
    }
}
