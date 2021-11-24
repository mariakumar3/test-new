package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * A ServiceEstimate.
 */
@Entity
@Table(name = "service_estimate")
public class ServiceEstimate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "service_estimate_name")
    private String serviceEstimateName;

    @Column(name = "service_estimate_number")
    private String serviceEstimateNumber;

    @Column(name = "service_estimate_description")
    private String serviceEstimateDescription;

    @Column(name = "dept_id")
    private Long deptId;

    @Column(name = "dept_code")
    private String deptCode;

    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "estimate_amt", precision = 21, scale = 2)
    private BigDecimal estimateAmt;

    @Column(name = "status")
    private String status;

    @Column(name = "document_reference")
    private String documentReference;

    @Column(name = "budget_amount", precision = 21, scale = 2)
    private BigDecimal budgetAmount;

    @Column(name = "hoa_department")
    private String hoaDepartment;

    @Column(name = "hoa_department_id")
    private Long hoaDepartmentId;

    @Column(name = "hoa_sector")
    private String hoaSector;

    @Column(name = "hoa_sector_id")
    private Long hoaSectorId;

    @Column(name = "hoa_list")
    private String hoaList;

    @Column(name = "hoa_list_id")
    private Long hoaListId;

    @Column(name = "hoa_linked_list")
    private String hoaLinkedList;

    @Column(name = "hoa_linked_list_id")
    private Long hoaLinkedListId;

    @Column(name = "approved_budget_yn")
    private Boolean approvedBudgetYn;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ServiceEstimate id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceEstimateName() {
        return this.serviceEstimateName;
    }

    public ServiceEstimate serviceEstimateName(String serviceEstimateName) {
        this.setServiceEstimateName(serviceEstimateName);
        return this;
    }

    public void setServiceEstimateName(String serviceEstimateName) {
        this.serviceEstimateName = serviceEstimateName;
    }

    public String getServiceEstimateNumber() {
        return this.serviceEstimateNumber;
    }

    public ServiceEstimate serviceEstimateNumber(String serviceEstimateNumber) {
        this.setServiceEstimateNumber(serviceEstimateNumber);
        return this;
    }

    public void setServiceEstimateNumber(String serviceEstimateNumber) {
        this.serviceEstimateNumber = serviceEstimateNumber;
    }

    public String getServiceEstimateDescription() {
        return this.serviceEstimateDescription;
    }

    public ServiceEstimate serviceEstimateDescription(String serviceEstimateDescription) {
        this.setServiceEstimateDescription(serviceEstimateDescription);
        return this;
    }

    public void setServiceEstimateDescription(String serviceEstimateDescription) {
        this.serviceEstimateDescription = serviceEstimateDescription;
    }

    public Long getDeptId() {
        return this.deptId;
    }

    public ServiceEstimate deptId(Long deptId) {
        this.setDeptId(deptId);
        return this;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptCode() {
        return this.deptCode;
    }

    public ServiceEstimate deptCode(String deptCode) {
        this.setDeptCode(deptCode);
        return this;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public Long getLocationId() {
        return this.locationId;
    }

    public ServiceEstimate locationId(Long locationId) {
        this.setLocationId(locationId);
        return this;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public ServiceEstimate locationName(String locationName) {
        this.setLocationName(locationName);
        return this;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public ServiceEstimate projectId(Long projectId) {
        this.setProjectId(projectId);
        return this;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public ServiceEstimate projectName(String projectName) {
        this.setProjectName(projectName);
        return this;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigDecimal getEstimateAmt() {
        return this.estimateAmt;
    }

    public ServiceEstimate estimateAmt(BigDecimal estimateAmt) {
        this.setEstimateAmt(estimateAmt);
        return this;
    }

    public void setEstimateAmt(BigDecimal estimateAmt) {
        this.estimateAmt = estimateAmt;
    }

    public String getStatus() {
        return this.status;
    }

    public ServiceEstimate status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDocumentReference() {
        return this.documentReference;
    }

    public ServiceEstimate documentReference(String documentReference) {
        this.setDocumentReference(documentReference);
        return this;
    }

    public void setDocumentReference(String documentReference) {
        this.documentReference = documentReference;
    }

    public BigDecimal getBudgetAmount() {
        return this.budgetAmount;
    }

    public ServiceEstimate budgetAmount(BigDecimal budgetAmount) {
        this.setBudgetAmount(budgetAmount);
        return this;
    }

    public void setBudgetAmount(BigDecimal budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public String getHoaDepartment() {
        return this.hoaDepartment;
    }

    public ServiceEstimate hoaDepartment(String hoaDepartment) {
        this.setHoaDepartment(hoaDepartment);
        return this;
    }

    public void setHoaDepartment(String hoaDepartment) {
        this.hoaDepartment = hoaDepartment;
    }

    public Long getHoaDepartmentId() {
        return this.hoaDepartmentId;
    }

    public ServiceEstimate hoaDepartmentId(Long hoaDepartmentId) {
        this.setHoaDepartmentId(hoaDepartmentId);
        return this;
    }

    public void setHoaDepartmentId(Long hoaDepartmentId) {
        this.hoaDepartmentId = hoaDepartmentId;
    }

    public String getHoaSector() {
        return this.hoaSector;
    }

    public ServiceEstimate hoaSector(String hoaSector) {
        this.setHoaSector(hoaSector);
        return this;
    }

    public void setHoaSector(String hoaSector) {
        this.hoaSector = hoaSector;
    }

    public Long getHoaSectorId() {
        return this.hoaSectorId;
    }

    public ServiceEstimate hoaSectorId(Long hoaSectorId) {
        this.setHoaSectorId(hoaSectorId);
        return this;
    }

    public void setHoaSectorId(Long hoaSectorId) {
        this.hoaSectorId = hoaSectorId;
    }

    public String getHoaList() {
        return this.hoaList;
    }

    public ServiceEstimate hoaList(String hoaList) {
        this.setHoaList(hoaList);
        return this;
    }

    public void setHoaList(String hoaList) {
        this.hoaList = hoaList;
    }

    public Long getHoaListId() {
        return this.hoaListId;
    }

    public ServiceEstimate hoaListId(Long hoaListId) {
        this.setHoaListId(hoaListId);
        return this;
    }

    public void setHoaListId(Long hoaListId) {
        this.hoaListId = hoaListId;
    }

    public String getHoaLinkedList() {
        return this.hoaLinkedList;
    }

    public ServiceEstimate hoaLinkedList(String hoaLinkedList) {
        this.setHoaLinkedList(hoaLinkedList);
        return this;
    }

    public void setHoaLinkedList(String hoaLinkedList) {
        this.hoaLinkedList = hoaLinkedList;
    }

    public Long getHoaLinkedListId() {
        return this.hoaLinkedListId;
    }

    public ServiceEstimate hoaLinkedListId(Long hoaLinkedListId) {
        this.setHoaLinkedListId(hoaLinkedListId);
        return this;
    }

    public void setHoaLinkedListId(Long hoaLinkedListId) {
        this.hoaLinkedListId = hoaLinkedListId;
    }

    public Boolean getApprovedBudgetYn() {
        return this.approvedBudgetYn;
    }

    public ServiceEstimate approvedBudgetYn(Boolean approvedBudgetYn) {
        this.setApprovedBudgetYn(approvedBudgetYn);
        return this;
    }

    public void setApprovedBudgetYn(Boolean approvedBudgetYn) {
        this.approvedBudgetYn = approvedBudgetYn;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceEstimate)) {
            return false;
        }
        return id != null && id.equals(((ServiceEstimate) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceEstimate{" +
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
