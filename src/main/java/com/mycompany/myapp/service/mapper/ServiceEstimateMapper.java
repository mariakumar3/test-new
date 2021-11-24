package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.ServiceEstimate;
import com.mycompany.myapp.service.dto.ServiceEstimateDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ServiceEstimate} and its DTO {@link ServiceEstimateDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ServiceEstimateMapper extends EntityMapper<ServiceEstimateDTO, ServiceEstimate> {}
