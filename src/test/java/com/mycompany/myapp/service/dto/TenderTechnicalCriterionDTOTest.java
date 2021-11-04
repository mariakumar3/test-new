package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderTechnicalCriterionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderTechnicalCriterionDTO.class);
        TenderTechnicalCriterionDTO tenderTechnicalCriterionDTO1 = new TenderTechnicalCriterionDTO();
        tenderTechnicalCriterionDTO1.setId(1L);
        TenderTechnicalCriterionDTO tenderTechnicalCriterionDTO2 = new TenderTechnicalCriterionDTO();
        assertThat(tenderTechnicalCriterionDTO1).isNotEqualTo(tenderTechnicalCriterionDTO2);
        tenderTechnicalCriterionDTO2.setId(tenderTechnicalCriterionDTO1.getId());
        assertThat(tenderTechnicalCriterionDTO1).isEqualTo(tenderTechnicalCriterionDTO2);
        tenderTechnicalCriterionDTO2.setId(2L);
        assertThat(tenderTechnicalCriterionDTO1).isNotEqualTo(tenderTechnicalCriterionDTO2);
        tenderTechnicalCriterionDTO1.setId(null);
        assertThat(tenderTechnicalCriterionDTO1).isNotEqualTo(tenderTechnicalCriterionDTO2);
    }
}
