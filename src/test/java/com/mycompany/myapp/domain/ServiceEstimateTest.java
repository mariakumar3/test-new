package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ServiceEstimateTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceEstimate.class);
        ServiceEstimate serviceEstimate1 = new ServiceEstimate();
        serviceEstimate1.setId(1L);
        ServiceEstimate serviceEstimate2 = new ServiceEstimate();
        serviceEstimate2.setId(serviceEstimate1.getId());
        assertThat(serviceEstimate1).isEqualTo(serviceEstimate2);
        serviceEstimate2.setId(2L);
        assertThat(serviceEstimate1).isNotEqualTo(serviceEstimate2);
        serviceEstimate1.setId(null);
        assertThat(serviceEstimate1).isNotEqualTo(serviceEstimate2);
    }
}
