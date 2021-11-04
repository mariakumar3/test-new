package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderTechnicalCriterionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderTechnicalCriterion.class);
        TenderTechnicalCriterion tenderTechnicalCriterion1 = new TenderTechnicalCriterion();
        tenderTechnicalCriterion1.setId(1L);
        TenderTechnicalCriterion tenderTechnicalCriterion2 = new TenderTechnicalCriterion();
        tenderTechnicalCriterion2.setId(tenderTechnicalCriterion1.getId());
        assertThat(tenderTechnicalCriterion1).isEqualTo(tenderTechnicalCriterion2);
        tenderTechnicalCriterion2.setId(2L);
        assertThat(tenderTechnicalCriterion1).isNotEqualTo(tenderTechnicalCriterion2);
        tenderTechnicalCriterion1.setId(null);
        assertThat(tenderTechnicalCriterion1).isNotEqualTo(tenderTechnicalCriterion2);
    }
}
