package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DocumentObjectStoreMapperTest {

    private DocumentObjectStoreMapper documentObjectStoreMapper;

    @BeforeEach
    public void setUp() {
        documentObjectStoreMapper = new DocumentObjectStoreMapperImpl();
    }
}
