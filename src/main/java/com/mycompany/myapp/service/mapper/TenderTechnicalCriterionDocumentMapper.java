package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.TenderTechnicalCriterionDocument;
import com.mycompany.myapp.service.dto.TenderTechnicalCriterionDocumentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderTechnicalCriterionDocument} and its DTO {@link TenderTechnicalCriterionDocumentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderTechnicalCriterionDocumentMapper
    extends EntityMapper<TenderTechnicalCriterionDocumentDTO, TenderTechnicalCriterionDocument> {}
