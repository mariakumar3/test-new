package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ServiceEstimateItemTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceEstimateItem.class);
        ServiceEstimateItem serviceEstimateItem1 = new ServiceEstimateItem();
        serviceEstimateItem1.setId(1L);
        ServiceEstimateItem serviceEstimateItem2 = new ServiceEstimateItem();
        serviceEstimateItem2.setId(serviceEstimateItem1.getId());
        assertThat(serviceEstimateItem1).isEqualTo(serviceEstimateItem2);
        serviceEstimateItem2.setId(2L);
        assertThat(serviceEstimateItem1).isNotEqualTo(serviceEstimateItem2);
        serviceEstimateItem1.setId(null);
        assertThat(serviceEstimateItem1).isNotEqualTo(serviceEstimateItem2);
    }
}
