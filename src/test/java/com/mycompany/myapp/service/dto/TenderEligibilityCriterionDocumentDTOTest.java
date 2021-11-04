package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderEligibilityCriterionDocumentDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderEligibilityCriterionDocumentDTO.class);
        TenderEligibilityCriterionDocumentDTO tenderEligibilityCriterionDocumentDTO1 = new TenderEligibilityCriterionDocumentDTO();
        tenderEligibilityCriterionDocumentDTO1.setId(1L);
        TenderEligibilityCriterionDocumentDTO tenderEligibilityCriterionDocumentDTO2 = new TenderEligibilityCriterionDocumentDTO();
        assertThat(tenderEligibilityCriterionDocumentDTO1).isNotEqualTo(tenderEligibilityCriterionDocumentDTO2);
        tenderEligibilityCriterionDocumentDTO2.setId(tenderEligibilityCriterionDocumentDTO1.getId());
        assertThat(tenderEligibilityCriterionDocumentDTO1).isEqualTo(tenderEligibilityCriterionDocumentDTO2);
        tenderEligibilityCriterionDocumentDTO2.setId(2L);
        assertThat(tenderEligibilityCriterionDocumentDTO1).isNotEqualTo(tenderEligibilityCriterionDocumentDTO2);
        tenderEligibilityCriterionDocumentDTO1.setId(null);
        assertThat(tenderEligibilityCriterionDocumentDTO1).isNotEqualTo(tenderEligibilityCriterionDocumentDTO2);
    }
}
