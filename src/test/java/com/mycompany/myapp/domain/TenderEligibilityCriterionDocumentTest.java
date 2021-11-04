package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderEligibilityCriterionDocumentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderEligibilityCriterionDocument.class);
        TenderEligibilityCriterionDocument tenderEligibilityCriterionDocument1 = new TenderEligibilityCriterionDocument();
        tenderEligibilityCriterionDocument1.setId(1L);
        TenderEligibilityCriterionDocument tenderEligibilityCriterionDocument2 = new TenderEligibilityCriterionDocument();
        tenderEligibilityCriterionDocument2.setId(tenderEligibilityCriterionDocument1.getId());
        assertThat(tenderEligibilityCriterionDocument1).isEqualTo(tenderEligibilityCriterionDocument2);
        tenderEligibilityCriterionDocument2.setId(2L);
        assertThat(tenderEligibilityCriterionDocument1).isNotEqualTo(tenderEligibilityCriterionDocument2);
        tenderEligibilityCriterionDocument1.setId(null);
        assertThat(tenderEligibilityCriterionDocument1).isNotEqualTo(tenderEligibilityCriterionDocument2);
    }
}
