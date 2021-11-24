package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServiceEstimateItemMapperTest {

    private ServiceEstimateItemMapper serviceEstimateItemMapper;

    @BeforeEach
    public void setUp() {
        serviceEstimateItemMapper = new ServiceEstimateItemMapperImpl();
    }
}
