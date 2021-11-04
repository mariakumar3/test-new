package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.TenderPreQualCriterionDocument;
import com.mycompany.myapp.service.dto.TenderPreQualCriterionDocumentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderPreQualCriterionDocument} and its DTO {@link TenderPreQualCriterionDocumentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderPreQualCriterionDocumentMapper
    extends EntityMapper<TenderPreQualCriterionDocumentDTO, TenderPreQualCriterionDocument> {}
