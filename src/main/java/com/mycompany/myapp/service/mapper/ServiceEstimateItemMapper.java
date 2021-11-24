package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.ServiceEstimateItem;
import com.mycompany.myapp.service.dto.ServiceEstimateItemDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ServiceEstimateItem} and its DTO {@link ServiceEstimateItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ServiceEstimateItemMapper extends EntityMapper<ServiceEstimateItemDTO, ServiceEstimateItem> {}
