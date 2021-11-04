package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.TenderTechnicalCriterion;
import com.mycompany.myapp.repository.TenderTechnicalCriterionRepository;
import com.mycompany.myapp.service.TenderTechnicalCriterionService;
import com.mycompany.myapp.service.dto.TenderTechnicalCriterionDTO;
import com.mycompany.myapp.service.mapper.TenderTechnicalCriterionMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderTechnicalCriterion}.
 */
@Service
@Transactional
public class TenderTechnicalCriterionServiceImpl implements TenderTechnicalCriterionService {

    private final Logger log = LoggerFactory.getLogger(TenderTechnicalCriterionServiceImpl.class);

    private final TenderTechnicalCriterionRepository tenderTechnicalCriterionRepository;

    private final TenderTechnicalCriterionMapper tenderTechnicalCriterionMapper;

    public TenderTechnicalCriterionServiceImpl(
        TenderTechnicalCriterionRepository tenderTechnicalCriterionRepository,
        TenderTechnicalCriterionMapper tenderTechnicalCriterionMapper
    ) {
        this.tenderTechnicalCriterionRepository = tenderTechnicalCriterionRepository;
        this.tenderTechnicalCriterionMapper = tenderTechnicalCriterionMapper;
    }

    @Override
    public TenderTechnicalCriterionDTO save(TenderTechnicalCriterionDTO tenderTechnicalCriterionDTO) {
        log.debug("Request to save TenderTechnicalCriterion : {}", tenderTechnicalCriterionDTO);
        TenderTechnicalCriterion tenderTechnicalCriterion = tenderTechnicalCriterionMapper.toEntity(tenderTechnicalCriterionDTO);
        tenderTechnicalCriterion = tenderTechnicalCriterionRepository.save(tenderTechnicalCriterion);
        return tenderTechnicalCriterionMapper.toDto(tenderTechnicalCriterion);
    }

    @Override
    public Optional<TenderTechnicalCriterionDTO> partialUpdate(TenderTechnicalCriterionDTO tenderTechnicalCriterionDTO) {
        log.debug("Request to partially update TenderTechnicalCriterion : {}", tenderTechnicalCriterionDTO);

        return tenderTechnicalCriterionRepository
            .findById(tenderTechnicalCriterionDTO.getId())
            .map(existingTenderTechnicalCriterion -> {
                tenderTechnicalCriterionMapper.partialUpdate(existingTenderTechnicalCriterion, tenderTechnicalCriterionDTO);

                return existingTenderTechnicalCriterion;
            })
            .map(tenderTechnicalCriterionRepository::save)
            .map(tenderTechnicalCriterionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenderTechnicalCriterionDTO> findAll() {
        log.debug("Request to get all TenderTechnicalCriteria");
        return tenderTechnicalCriterionRepository
            .findAll()
            .stream()
            .map(tenderTechnicalCriterionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderTechnicalCriterionDTO> findOne(Long id) {
        log.debug("Request to get TenderTechnicalCriterion : {}", id);
        return tenderTechnicalCriterionRepository.findById(id).map(tenderTechnicalCriterionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderTechnicalCriterion : {}", id);
        tenderTechnicalCriterionRepository.deleteById(id);
    }
}
