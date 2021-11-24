package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ServiceEstimateDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceEstimateDTO.class);
        ServiceEstimateDTO serviceEstimateDTO1 = new ServiceEstimateDTO();
        serviceEstimateDTO1.setId(1L);
        ServiceEstimateDTO serviceEstimateDTO2 = new ServiceEstimateDTO();
        assertThat(serviceEstimateDTO1).isNotEqualTo(serviceEstimateDTO2);
        serviceEstimateDTO2.setId(serviceEstimateDTO1.getId());
        assertThat(serviceEstimateDTO1).isEqualTo(serviceEstimateDTO2);
        serviceEstimateDTO2.setId(2L);
        assertThat(serviceEstimateDTO1).isNotEqualTo(serviceEstimateDTO2);
        serviceEstimateDTO1.setId(null);
        assertThat(serviceEstimateDTO1).isNotEqualTo(serviceEstimateDTO2);
    }
}
