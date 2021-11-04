package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TenderPreQualCriterionMapperTest {

    private TenderPreQualCriterionMapper tenderPreQualCriterionMapper;

    @BeforeEach
    public void setUp() {
        tenderPreQualCriterionMapper = new TenderPreQualCriterionMapperImpl();
    }
}
