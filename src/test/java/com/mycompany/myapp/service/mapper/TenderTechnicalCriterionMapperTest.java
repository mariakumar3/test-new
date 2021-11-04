package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TenderTechnicalCriterionMapperTest {

    private TenderTechnicalCriterionMapper tenderTechnicalCriterionMapper;

    @BeforeEach
    public void setUp() {
        tenderTechnicalCriterionMapper = new TenderTechnicalCriterionMapperImpl();
    }
}
