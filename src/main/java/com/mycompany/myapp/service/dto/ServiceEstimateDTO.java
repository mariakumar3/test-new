package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ServiceEstimate} entity.
 */
public class ServiceEstimateDTO implements Serializable {

    private Long id;

    private String serviceEstimateName;

    private String serviceEstimateNumber;

    private String serviceEstimateDescription;

    private Long deptId;

    private String deptCode;

    private Long locationId;

    private String locationName;

    private Long projectId;

    private String projectName;

    private BigDecimal estimateAmt;

    private String status;

    private String documentReference;

    private BigDecimal budgetAmount;

    private String hoaDepartment;

    private Long hoaDepartmentId;

    private String hoaSector;

    private Long hoaSectorId;

    private String hoaList;

    private Long hoaListId;

    private String hoaLinkedList;

    private Long hoaLinkedListId;

    private Boolean approvedBudgetYn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceEstimateName() {
        return serviceEstimateName;
    }

    public void setServiceEstimateName(String serviceEstimateName) {
        this.serviceEstimateName = serviceEstimateName;
    }

    public String getServiceEstimateNumber() {
        return serviceEstimateNumber;
    }

    public void setServiceEstimateNumber(String serviceEstimateNumber) {
        this.serviceEstimateNumber = serviceEstimateNumber;
    }

    public String getServiceEstimateDescription() {
        return serviceEstimateDescription;
    }

    public void setServiceEstimateDescription(String serviceEstimateDescription) {
        this.serviceEstimateDescription = serviceEstimateDescription;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigDecimal getEstimateAmt() {
        return estimateAmt;
    }

    public void setEstimateAmt(BigDecimal estimateAmt) {
        this.estimateAmt = estimateAmt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDocumentReference() {
        return documentReference;
    }

    public void setDocumentReference(String documentReference) {
        this.documentReference = documentReference;
    }

    public BigDecimal getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(BigDecimal budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public String getHoaDepartment() {
        return hoaDepartment;
    }

    public void setHoaDepartment(String hoaDepartment) {
        this.hoaDepartment = hoaDepartment;
    }

    public Long getHoaDepartmentId() {
        return hoaDepartmentId;
    }

    public void setHoaDepartmentId(Long hoaDepartmentId) {
        this.hoaDepartmentId = hoaDepartmentId;
    }

    public String getHoaSector() {
        return hoaSector;
    }

    public void setHoaSector(String hoaSector) {
        this.hoaSector = hoaSector;
    }

    public Long getHoaSectorId() {
        return hoaSectorId;
    }

    public void setHoaSectorId(Long hoaSectorId) {
        this.hoaSectorId = hoaSectorId;
    }

    public String getHoaList() {
        return hoaList;
    }

    public void setHoaList(String hoaList) {
        this.hoaList = hoaList;
    }

    public Long getHoaListId() {
        return hoaListId;
    }

    public void setHoaListId(Long hoaListId) {
        this.hoaListId = hoaListId;
    }

    public String getHoaLinkedList() {
        return hoaLinkedList;
    }

    public void setHoaLinkedList(String hoaLinkedList) {
        this.hoaLinkedList = hoaLinkedList;
    }

    public Long getHoaLinkedListId() {
        return hoaLinkedListId;
    }

    public void setHoaLinkedListId(Long hoaLinkedListId) {
        this.hoaLinkedListId = hoaLinkedListId;
    }

    public Boolean getApprovedBudgetYn() {
        return approvedBudgetYn;
    }

    public void setApprovedBudgetYn(Boolean approvedBudgetYn) {
        this.approvedBudgetYn = approvedBudgetYn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceEstimateDTO)) {
            return false;
        }

        ServiceEstimateDTO serviceEstimateDTO = (ServiceEstimateDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, serviceEstimateDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceEstimateDTO{" +
            "id=" + getId() +
            ", serviceEstimateName='" + getServiceEstimateName() + "'" +
            ", serviceEstimateNumber='" + getServiceEstimateNumber() + "'" +
            ", serviceEstimateDescription='" + getServiceEstimateDescription() + "'" +
            ", deptId=" + getDeptId() +
            ", deptCode='" + getDeptCode() + "'" +
            ", locationId=" + getLocationId() +
            ", locationName='" + getLocationName() + "'" +
            ", projectId=" + getProjectId() +
            ", projectName='" + getProjectName() + "'" +
            ", estimateAmt=" + getEstimateAmt() +
            ", status='" + getStatus() + "'" +
            ", documentReference='" + getDocumentReference() + "'" +
            ", budgetAmount=" + getBudgetAmount() +
            ", hoaDepartment='" + getHoaDepartment() + "'" +
            ", hoaDepartmentId=" + getHoaDepartmentId() +
            ", hoaSector='" + getHoaSector() + "'" +
            ", hoaSectorId=" + getHoaSectorId() +
            ", hoaList='" + getHoaList() + "'" +
            ", hoaListId=" + getHoaListId() +
            ", hoaLinkedList='" + getHoaLinkedList() + "'" +
            ", hoaLinkedListId=" + getHoaLinkedListId() +
            ", approvedBudgetYn='" + getApprovedBudgetYn() + "'" +
            "}";
    }
}
