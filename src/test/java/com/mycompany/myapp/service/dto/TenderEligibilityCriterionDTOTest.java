package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderEligibilityCriterionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderEligibilityCriterionDTO.class);
        TenderEligibilityCriterionDTO tenderEligibilityCriterionDTO1 = new TenderEligibilityCriterionDTO();
        tenderEligibilityCriterionDTO1.setId(1L);
        TenderEligibilityCriterionDTO tenderEligibilityCriterionDTO2 = new TenderEligibilityCriterionDTO();
        assertThat(tenderEligibilityCriterionDTO1).isNotEqualTo(tenderEligibilityCriterionDTO2);
        tenderEligibilityCriterionDTO2.setId(tenderEligibilityCriterionDTO1.getId());
        assertThat(tenderEligibilityCriterionDTO1).isEqualTo(tenderEligibilityCriterionDTO2);
        tenderEligibilityCriterionDTO2.setId(2L);
        assertThat(tenderEligibilityCriterionDTO1).isNotEqualTo(tenderEligibilityCriterionDTO2);
        tenderEligibilityCriterionDTO1.setId(null);
        assertThat(tenderEligibilityCriterionDTO1).isNotEqualTo(tenderEligibilityCriterionDTO2);
    }
}
