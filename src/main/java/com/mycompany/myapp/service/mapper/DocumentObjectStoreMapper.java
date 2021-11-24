package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.DocumentObjectStore;
import com.mycompany.myapp.service.dto.DocumentObjectStoreDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DocumentObjectStore} and its DTO {@link DocumentObjectStoreDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DocumentObjectStoreMapper extends EntityMapper<DocumentObjectStoreDTO, DocumentObjectStore> {}
