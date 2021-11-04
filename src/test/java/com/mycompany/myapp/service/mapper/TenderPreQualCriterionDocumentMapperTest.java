package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TenderPreQualCriterionDocumentMapperTest {

    private TenderPreQualCriterionDocumentMapper tenderPreQualCriterionDocumentMapper;

    @BeforeEach
    public void setUp() {
        tenderPreQualCriterionDocumentMapper = new TenderPreQualCriterionDocumentMapperImpl();
    }
}
