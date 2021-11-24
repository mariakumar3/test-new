package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.ServiceEstimate;
import com.mycompany.myapp.repository.ServiceEstimateRepository;
import com.mycompany.myapp.service.ServiceEstimateService;
import com.mycompany.myapp.service.dto.ServiceEstimateDTO;
import com.mycompany.myapp.service.mapper.ServiceEstimateMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ServiceEstimate}.
 */
@Service
@Transactional
public class ServiceEstimateServiceImpl implements ServiceEstimateService {

    private final Logger log = LoggerFactory.getLogger(ServiceEstimateServiceImpl.class);

    private final ServiceEstimateRepository serviceEstimateRepository;

    private final ServiceEstimateMapper serviceEstimateMapper;

    public ServiceEstimateServiceImpl(ServiceEstimateRepository serviceEstimateRepository, ServiceEstimateMapper serviceEstimateMapper) {
        this.serviceEstimateRepository = serviceEstimateRepository;
        this.serviceEstimateMapper = serviceEstimateMapper;
    }

    @Override
    public ServiceEstimateDTO save(ServiceEstimateDTO serviceEstimateDTO) {
        log.debug("Request to save ServiceEstimate : {}", serviceEstimateDTO);
        ServiceEstimate serviceEstimate = serviceEstimateMapper.toEntity(serviceEstimateDTO);
        serviceEstimate = serviceEstimateRepository.save(serviceEstimate);
        return serviceEstimateMapper.toDto(serviceEstimate);
    }

    @Override
    public Optional<ServiceEstimateDTO> partialUpdate(ServiceEstimateDTO serviceEstimateDTO) {
        log.debug("Request to partially update ServiceEstimate : {}", serviceEstimateDTO);

        return serviceEstimateRepository
            .findById(serviceEstimateDTO.getId())
            .map(existingServiceEstimate -> {
                serviceEstimateMapper.partialUpdate(existingServiceEstimate, serviceEstimateDTO);

                return existingServiceEstimate;
            })
            .map(serviceEstimateRepository::save)
            .map(serviceEstimateMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceEstimateDTO> findAll() {
        log.debug("Request to get all ServiceEstimates");
        return serviceEstimateRepository
            .findAll()
            .stream()
            .map(serviceEstimateMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServiceEstimateDTO> findOne(Long id) {
        log.debug("Request to get ServiceEstimate : {}", id);
        return serviceEstimateRepository.findById(id).map(serviceEstimateMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ServiceEstimate : {}", id);
        serviceEstimateRepository.deleteById(id);
    }
}
