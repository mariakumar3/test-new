package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.ServiceEstimateItem;
import com.mycompany.myapp.repository.ServiceEstimateItemRepository;
import com.mycompany.myapp.service.ServiceEstimateItemService;
import com.mycompany.myapp.service.dto.ServiceEstimateItemDTO;
import com.mycompany.myapp.service.mapper.ServiceEstimateItemMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ServiceEstimateItem}.
 */
@Service
@Transactional
public class ServiceEstimateItemServiceImpl implements ServiceEstimateItemService {

    private final Logger log = LoggerFactory.getLogger(ServiceEstimateItemServiceImpl.class);

    private final ServiceEstimateItemRepository serviceEstimateItemRepository;

    private final ServiceEstimateItemMapper serviceEstimateItemMapper;

    public ServiceEstimateItemServiceImpl(
        ServiceEstimateItemRepository serviceEstimateItemRepository,
        ServiceEstimateItemMapper serviceEstimateItemMapper
    ) {
        this.serviceEstimateItemRepository = serviceEstimateItemRepository;
        this.serviceEstimateItemMapper = serviceEstimateItemMapper;
    }

    @Override
    public ServiceEstimateItemDTO save(ServiceEstimateItemDTO serviceEstimateItemDTO) {
        log.debug("Request to save ServiceEstimateItem : {}", serviceEstimateItemDTO);
        ServiceEstimateItem serviceEstimateItem = serviceEstimateItemMapper.toEntity(serviceEstimateItemDTO);
        serviceEstimateItem = serviceEstimateItemRepository.save(serviceEstimateItem);
        return serviceEstimateItemMapper.toDto(serviceEstimateItem);
    }

    @Override
    public Optional<ServiceEstimateItemDTO> partialUpdate(ServiceEstimateItemDTO serviceEstimateItemDTO) {
        log.debug("Request to partially update ServiceEstimateItem : {}", serviceEstimateItemDTO);

        return serviceEstimateItemRepository
            .findById(serviceEstimateItemDTO.getId())
            .map(existingServiceEstimateItem -> {
                serviceEstimateItemMapper.partialUpdate(existingServiceEstimateItem, serviceEstimateItemDTO);

                return existingServiceEstimateItem;
            })
            .map(serviceEstimateItemRepository::save)
            .map(serviceEstimateItemMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceEstimateItemDTO> findAll() {
        log.debug("Request to get all ServiceEstimateItems");
        return serviceEstimateItemRepository
            .findAll()
            .stream()
            .map(serviceEstimateItemMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServiceEstimateItemDTO> findOne(Long id) {
        log.debug("Request to get ServiceEstimateItem : {}", id);
        return serviceEstimateItemRepository.findById(id).map(serviceEstimateItemMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ServiceEstimateItem : {}", id);
        serviceEstimateItemRepository.deleteById(id);
    }
}
