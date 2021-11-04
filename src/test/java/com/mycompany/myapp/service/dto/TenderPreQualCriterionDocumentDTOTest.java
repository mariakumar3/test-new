package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderPreQualCriterionDocumentDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderPreQualCriterionDocumentDTO.class);
        TenderPreQualCriterionDocumentDTO tenderPreQualCriterionDocumentDTO1 = new TenderPreQualCriterionDocumentDTO();
        tenderPreQualCriterionDocumentDTO1.setId(1L);
        TenderPreQualCriterionDocumentDTO tenderPreQualCriterionDocumentDTO2 = new TenderPreQualCriterionDocumentDTO();
        assertThat(tenderPreQualCriterionDocumentDTO1).isNotEqualTo(tenderPreQualCriterionDocumentDTO2);
        tenderPreQualCriterionDocumentDTO2.setId(tenderPreQualCriterionDocumentDTO1.getId());
        assertThat(tenderPreQualCriterionDocumentDTO1).isEqualTo(tenderPreQualCriterionDocumentDTO2);
        tenderPreQualCriterionDocumentDTO2.setId(2L);
        assertThat(tenderPreQualCriterionDocumentDTO1).isNotEqualTo(tenderPreQualCriterionDocumentDTO2);
        tenderPreQualCriterionDocumentDTO1.setId(null);
        assertThat(tenderPreQualCriterionDocumentDTO1).isNotEqualTo(tenderPreQualCriterionDocumentDTO2);
    }
}
