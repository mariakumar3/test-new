package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.TenderTechnicalCriterion;
import com.mycompany.myapp.service.dto.TenderTechnicalCriterionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderTechnicalCriterion} and its DTO {@link TenderTechnicalCriterionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderTechnicalCriterionMapper extends EntityMapper<TenderTechnicalCriterionDTO, TenderTechnicalCriterion> {}
