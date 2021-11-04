package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.TenderEligibilityCriterionDocument;
import com.mycompany.myapp.service.dto.TenderEligibilityCriterionDocumentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderEligibilityCriterionDocument} and its DTO {@link TenderEligibilityCriterionDocumentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderEligibilityCriterionDocumentMapper
    extends EntityMapper<TenderEligibilityCriterionDocumentDTO, TenderEligibilityCriterionDocument> {}
