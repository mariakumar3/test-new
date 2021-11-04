package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderPreQualCriterionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderPreQualCriterion.class);
        TenderPreQualCriterion tenderPreQualCriterion1 = new TenderPreQualCriterion();
        tenderPreQualCriterion1.setId(1L);
        TenderPreQualCriterion tenderPreQualCriterion2 = new TenderPreQualCriterion();
        tenderPreQualCriterion2.setId(tenderPreQualCriterion1.getId());
        assertThat(tenderPreQualCriterion1).isEqualTo(tenderPreQualCriterion2);
        tenderPreQualCriterion2.setId(2L);
        assertThat(tenderPreQualCriterion1).isNotEqualTo(tenderPreQualCriterion2);
        tenderPreQualCriterion1.setId(null);
        assertThat(tenderPreQualCriterion1).isNotEqualTo(tenderPreQualCriterion2);
    }
}
