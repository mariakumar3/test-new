package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderPreQualCriterionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderPreQualCriterionDTO.class);
        TenderPreQualCriterionDTO tenderPreQualCriterionDTO1 = new TenderPreQualCriterionDTO();
        tenderPreQualCriterionDTO1.setId(1L);
        TenderPreQualCriterionDTO tenderPreQualCriterionDTO2 = new TenderPreQualCriterionDTO();
        assertThat(tenderPreQualCriterionDTO1).isNotEqualTo(tenderPreQualCriterionDTO2);
        tenderPreQualCriterionDTO2.setId(tenderPreQualCriterionDTO1.getId());
        assertThat(tenderPreQualCriterionDTO1).isEqualTo(tenderPreQualCriterionDTO2);
        tenderPreQualCriterionDTO2.setId(2L);
        assertThat(tenderPreQualCriterionDTO1).isNotEqualTo(tenderPreQualCriterionDTO2);
        tenderPreQualCriterionDTO1.setId(null);
        assertThat(tenderPreQualCriterionDTO1).isNotEqualTo(tenderPreQualCriterionDTO2);
    }
}
