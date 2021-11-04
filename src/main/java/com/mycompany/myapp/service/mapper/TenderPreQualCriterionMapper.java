package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.TenderPreQualCriterion;
import com.mycompany.myapp.service.dto.TenderPreQualCriterionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderPreQualCriterion} and its DTO {@link TenderPreQualCriterionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderPreQualCriterionMapper extends EntityMapper<TenderPreQualCriterionDTO, TenderPreQualCriterion> {}
