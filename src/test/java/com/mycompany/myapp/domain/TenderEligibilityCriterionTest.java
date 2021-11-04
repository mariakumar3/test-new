package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderEligibilityCriterionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderEligibilityCriterion.class);
        TenderEligibilityCriterion tenderEligibilityCriterion1 = new TenderEligibilityCriterion();
        tenderEligibilityCriterion1.setId(1L);
        TenderEligibilityCriterion tenderEligibilityCriterion2 = new TenderEligibilityCriterion();
        tenderEligibilityCriterion2.setId(tenderEligibilityCriterion1.getId());
        assertThat(tenderEligibilityCriterion1).isEqualTo(tenderEligibilityCriterion2);
        tenderEligibilityCriterion2.setId(2L);
        assertThat(tenderEligibilityCriterion1).isNotEqualTo(tenderEligibilityCriterion2);
        tenderEligibilityCriterion1.setId(null);
        assertThat(tenderEligibilityCriterion1).isNotEqualTo(tenderEligibilityCriterion2);
    }
}
