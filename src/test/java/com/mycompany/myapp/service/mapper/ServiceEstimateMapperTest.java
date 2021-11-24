package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServiceEstimateMapperTest {

    private ServiceEstimateMapper serviceEstimateMapper;

    @BeforeEach
    public void setUp() {
        serviceEstimateMapper = new ServiceEstimateMapperImpl();
    }
}
