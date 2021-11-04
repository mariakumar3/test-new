package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.TenderPreQualCriterion} entity.
 */
public class TenderPreQualCriterionDTO implements Serializable {

    private Long id;

    private Long nitId;

    private String description;

    private String criterionCategory;

    private Boolean isUserAdded;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCriterionCategory() {
        return criterionCategory;
    }

    public void setCriterionCategory(String criterionCategory) {
        this.criterionCategory = criterionCategory;
    }

    public Boolean getIsUserAdded() {
        return isUserAdded;
    }

    public void setIsUserAdded(Boolean isUserAdded) {
        this.isUserAdded = isUserAdded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenderPreQualCriterionDTO)) {
            return false;
        }

        TenderPreQualCriterionDTO tenderPreQualCriterionDTO = (TenderPreQualCriterionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tenderPreQualCriterionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenderPreQualCriterionDTO{" +
            "id=" + getId() +
            ", nitId=" + getNitId() +
            ", description='" + getDescription() + "'" +
            ", criterionCategory='" + getCriterionCategory() + "'" +
            ", isUserAdded='" + getIsUserAdded() + "'" +
            "}";
    }
}
