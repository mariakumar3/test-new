package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DocumentObjectStoreTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DocumentObjectStore.class);
        DocumentObjectStore documentObjectStore1 = new DocumentObjectStore();
        documentObjectStore1.setId(1L);
        DocumentObjectStore documentObjectStore2 = new DocumentObjectStore();
        documentObjectStore2.setId(documentObjectStore1.getId());
        assertThat(documentObjectStore1).isEqualTo(documentObjectStore2);
        documentObjectStore2.setId(2L);
        assertThat(documentObjectStore1).isNotEqualTo(documentObjectStore2);
        documentObjectStore1.setId(null);
        assertThat(documentObjectStore1).isNotEqualTo(documentObjectStore2);
    }
}
