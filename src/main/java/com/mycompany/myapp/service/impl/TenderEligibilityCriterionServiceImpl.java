package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.TenderEligibilityCriterion;
import com.mycompany.myapp.repository.TenderEligibilityCriterionRepository;
import com.mycompany.myapp.service.TenderEligibilityCriterionService;
import com.mycompany.myapp.service.dto.TenderEligibilityCriterionDTO;
import com.mycompany.myapp.service.mapper.TenderEligibilityCriterionMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderEligibilityCriterion}.
 */
@Service
@Transactional
public class TenderEligibilityCriterionServiceImpl implements TenderEligibilityCriterionService {

    private final Logger log = LoggerFactory.getLogger(TenderEligibilityCriterionServiceImpl.class);

    private final TenderEligibilityCriterionRepository tenderEligibilityCriterionRepository;

    private final TenderEligibilityCriterionMapper tenderEligibilityCriterionMapper;

    public TenderEligibilityCriterionServiceImpl(
        TenderEligibilityCriterionRepository tenderEligibilityCriterionRepository,
        TenderEligibilityCriterionMapper tenderEligibilityCriterionMapper
    ) {
        this.tenderEligibilityCriterionRepository = tenderEligibilityCriterionRepository;
        this.tenderEligibilityCriterionMapper = tenderEligibilityCriterionMapper;
    }

    @Override
    public TenderEligibilityCriterionDTO save(TenderEligibilityCriterionDTO tenderEligibilityCriterionDTO) {
        log.debug("Request to save TenderEligibilityCriterion : {}", tenderEligibilityCriterionDTO);
        TenderEligibilityCriterion tenderEligibilityCriterion = tenderEligibilityCriterionMapper.toEntity(tenderEligibilityCriterionDTO);
        tenderEligibilityCriterion = tenderEligibilityCriterionRepository.save(tenderEligibilityCriterion);
        return tenderEligibilityCriterionMapper.toDto(tenderEligibilityCriterion);
    }

    @Override
    public Optional<TenderEligibilityCriterionDTO> partialUpdate(TenderEligibilityCriterionDTO tenderEligibilityCriterionDTO) {
        log.debug("Request to partially update TenderEligibilityCriterion : {}", tenderEligibilityCriterionDTO);

        return tenderEligibilityCriterionRepository
            .findById(tenderEligibilityCriterionDTO.getId())
            .map(existingTenderEligibilityCriterion -> {
                tenderEligibilityCriterionMapper.partialUpdate(existingTenderEligibilityCriterion, tenderEligibilityCriterionDTO);

                return existingTenderEligibilityCriterion;
            })
            .map(tenderEligibilityCriterionRepository::save)
            .map(tenderEligibilityCriterionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenderEligibilityCriterionDTO> findAll() {
        log.debug("Request to get all TenderEligibilityCriteria");
        return tenderEligibilityCriterionRepository
            .findAll()
            .stream()
            .map(tenderEligibilityCriterionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderEligibilityCriterionDTO> findOne(Long id) {
        log.debug("Request to get TenderEligibilityCriterion : {}", id);
        return tenderEligibilityCriterionRepository.findById(id).map(tenderEligibilityCriterionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderEligibilityCriterion : {}", id);
        tenderEligibilityCriterionRepository.deleteById(id);
    }
}
