package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderTechnicalCriterionDocumentDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderTechnicalCriterionDocumentDTO.class);
        TenderTechnicalCriterionDocumentDTO tenderTechnicalCriterionDocumentDTO1 = new TenderTechnicalCriterionDocumentDTO();
        tenderTechnicalCriterionDocumentDTO1.setId(1L);
        TenderTechnicalCriterionDocumentDTO tenderTechnicalCriterionDocumentDTO2 = new TenderTechnicalCriterionDocumentDTO();
        assertThat(tenderTechnicalCriterionDocumentDTO1).isNotEqualTo(tenderTechnicalCriterionDocumentDTO2);
        tenderTechnicalCriterionDocumentDTO2.setId(tenderTechnicalCriterionDocumentDTO1.getId());
        assertThat(tenderTechnicalCriterionDocumentDTO1).isEqualTo(tenderTechnicalCriterionDocumentDTO2);
        tenderTechnicalCriterionDocumentDTO2.setId(2L);
        assertThat(tenderTechnicalCriterionDocumentDTO1).isNotEqualTo(tenderTechnicalCriterionDocumentDTO2);
        tenderTechnicalCriterionDocumentDTO1.setId(null);
        assertThat(tenderTechnicalCriterionDocumentDTO1).isNotEqualTo(tenderTechnicalCriterionDocumentDTO2);
    }
}
