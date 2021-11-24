package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DocumentObjectStoreDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DocumentObjectStoreDTO.class);
        DocumentObjectStoreDTO documentObjectStoreDTO1 = new DocumentObjectStoreDTO();
        documentObjectStoreDTO1.setId(1L);
        DocumentObjectStoreDTO documentObjectStoreDTO2 = new DocumentObjectStoreDTO();
        assertThat(documentObjectStoreDTO1).isNotEqualTo(documentObjectStoreDTO2);
        documentObjectStoreDTO2.setId(documentObjectStoreDTO1.getId());
        assertThat(documentObjectStoreDTO1).isEqualTo(documentObjectStoreDTO2);
        documentObjectStoreDTO2.setId(2L);
        assertThat(documentObjectStoreDTO1).isNotEqualTo(documentObjectStoreDTO2);
        documentObjectStoreDTO1.setId(null);
        assertThat(documentObjectStoreDTO1).isNotEqualTo(documentObjectStoreDTO2);
    }
}
