package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenderTechnicalCriterionDocumentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenderTechnicalCriterionDocument.class);
        TenderTechnicalCriterionDocument tenderTechnicalCriterionDocument1 = new TenderTechnicalCriterionDocument();
        tenderTechnicalCriterionDocument1.setId(1L);
        TenderTechnicalCriterionDocument tenderTechnicalCriterionDocument2 = new TenderTechnicalCriterionDocument();
        tenderTechnicalCriterionDocument2.setId(tenderTechnicalCriterionDocument1.getId());
        assertThat(tenderTechnicalCriterionDocument1).isEqualTo(tenderTechnicalCriterionDocument2);
        tenderTechnicalCriterionDocument2.setId(2L);
        assertThat(tenderTechnicalCriterionDocument1).isNotEqualTo(tenderTechnicalCriterionDocument2);
        tenderTechnicalCriterionDocument1.setId(null);
        assertThat(tenderTechnicalCriterionDocument1).isNotEqualTo(tenderTechnicalCriterionDocument2);
    }
}
