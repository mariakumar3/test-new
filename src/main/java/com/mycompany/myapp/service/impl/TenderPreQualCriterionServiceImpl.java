package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.TenderPreQualCriterion;
import com.mycompany.myapp.repository.TenderPreQualCriterionRepository;
import com.mycompany.myapp.service.TenderPreQualCriterionService;
import com.mycompany.myapp.service.dto.TenderPreQualCriterionDTO;
import com.mycompany.myapp.service.mapper.TenderPreQualCriterionMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderPreQualCriterion}.
 */
@Service
@Transactional
public class TenderPreQualCriterionServiceImpl implements TenderPreQualCriterionService {

    private final Logger log = LoggerFactory.getLogger(TenderPreQualCriterionServiceImpl.class);

    private final TenderPreQualCriterionRepository tenderPreQualCriterionRepository;

    private final TenderPreQualCriterionMapper tenderPreQualCriterionMapper;

    public TenderPreQualCriterionServiceImpl(
        TenderPreQualCriterionRepository tenderPreQualCriterionRepository,
        TenderPreQualCriterionMapper tenderPreQualCriterionMapper
    ) {
        this.tenderPreQualCriterionRepository = tenderPreQualCriterionRepository;
        this.tenderPreQualCriterionMapper = tenderPreQualCriterionMapper;
    }

    @Override
    public TenderPreQualCriterionDTO save(TenderPreQualCriterionDTO tenderPreQualCriterionDTO) {
        log.debug("Request to save TenderPreQualCriterion : {}", tenderPreQualCriterionDTO);
        TenderPreQualCriterion tenderPreQualCriterion = tenderPreQualCriterionMapper.toEntity(tenderPreQualCriterionDTO);
        tenderPreQualCriterion = tenderPreQualCriterionRepository.save(tenderPreQualCriterion);
        return tenderPreQualCriterionMapper.toDto(tenderPreQualCriterion);
    }

    @Override
    public Optional<TenderPreQualCriterionDTO> partialUpdate(TenderPreQualCriterionDTO tenderPreQualCriterionDTO) {
        log.debug("Request to partially update TenderPreQualCriterion : {}", tenderPreQualCriterionDTO);

        return tenderPreQualCriterionRepository
            .findById(tenderPreQualCriterionDTO.getId())
            .map(existingTenderPreQualCriterion -> {
                tenderPreQualCriterionMapper.partialUpdate(existingTenderPreQualCriterion, tenderPreQualCriterionDTO);

                return existingTenderPreQualCriterion;
            })
            .map(tenderPreQualCriterionRepository::save)
            .map(tenderPreQualCriterionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenderPreQualCriterionDTO> findAll() {
        log.debug("Request to get all TenderPreQualCriteria");
        return tenderPreQualCriterionRepository
            .findAll()
            .stream()
            .map(tenderPreQualCriterionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderPreQualCriterionDTO> findOne(Long id) {
        log.debug("Request to get TenderPreQualCriterion : {}", id);
        return tenderPreQualCriterionRepository.findById(id).map(tenderPreQualCriterionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderPreQualCriterion : {}", id);
        tenderPreQualCriterionRepository.deleteById(id);
    }
}
