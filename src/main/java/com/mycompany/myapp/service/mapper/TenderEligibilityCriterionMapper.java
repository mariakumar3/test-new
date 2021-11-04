package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.TenderEligibilityCriterion;
import com.mycompany.myapp.service.dto.TenderEligibilityCriterionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenderEligibilityCriterion} and its DTO {@link TenderEligibilityCriterionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenderEligibilityCriterionMapper extends EntityMapper<TenderEligibilityCriterionDTO, TenderEligibilityCriterion> {}
