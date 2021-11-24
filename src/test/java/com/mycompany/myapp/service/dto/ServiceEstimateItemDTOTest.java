package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ServiceEstimateItemDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceEstimateItemDTO.class);
        ServiceEstimateItemDTO serviceEstimateItemDTO1 = new ServiceEstimateItemDTO();
        serviceEstimateItemDTO1.setId(1L);
        ServiceEstimateItemDTO serviceEstimateItemDTO2 = new ServiceEstimateItemDTO();
        assertThat(serviceEstimateItemDTO1).isNotEqualTo(serviceEstimateItemDTO2);
        serviceEstimateItemDTO2.setId(serviceEstimateItemDTO1.getId());
        assertThat(serviceEstimateItemDTO1).isEqualTo(serviceEstimateItemDTO2);
        serviceEstimateItemDTO2.setId(2L);
        assertThat(serviceEstimateItemDTO1).isNotEqualTo(serviceEstimateItemDTO2);
        serviceEstimateItemDTO1.setId(null);
        assertThat(serviceEstimateItemDTO1).isNotEqualTo(serviceEstimateItemDTO2);
    }
}
