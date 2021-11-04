package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderPreQualCriterionDocumentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderPreQualCriterionDocument.class);
        TenderPreQualCriterionDocument tenderPreQualCriterionDocument1 = new TenderPreQualCriterionDocument();
        tenderPreQualCriterionDocument1.setId(1L);
        TenderPreQualCriterionDocument tenderPreQualCriterionDocument2 = new TenderPreQualCriterionDocument();
        tenderPreQualCriterionDocument2.setId(tenderPreQualCriterionDocument1.getId());
        assertThat(tenderPreQualCriterionDocument1).isEqualTo(tenderPreQualCriterionDocument2);
        tenderPreQualCriterionDocument2.setId(2L);
        assertThat(tenderPreQualCriterionDocument1).isNotEqualTo(tenderPreQualCriterionDocument2);
        tenderPreQualCriterionDocument1.setId(null);
        assertThat(tenderPreQualCriterionDocument1).isNotEqualTo(tenderPreQualCriterionDocument2);
    }
}
