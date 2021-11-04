package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.TenderEligibilityCriterionDocument;
import com.mycompany.myapp.repository.TenderEligibilityCriterionDocumentRepository;
import com.mycompany.myapp.service.TenderEligibilityCriterionDocumentService;
import com.mycompany.myapp.service.dto.TenderEligibilityCriterionDocumentDTO;
import com.mycompany.myapp.service.mapper.TenderEligibilityCriterionDocumentMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderEligibilityCriterionDocument}.
 */
@Service
@Transactional
public class TenderEligibilityCriterionDocumentServiceImpl implements TenderEligibilityCriterionDocumentService {

    private final Logger log = LoggerFactory.getLogger(TenderEligibilityCriterionDocumentServiceImpl.class);

    private final TenderEligibilityCriterionDocumentRepository tenderEligibilityCriterionDocumentRepository;

    private final TenderEligibilityCriterionDocumentMapper tenderEligibilityCriterionDocumentMapper;

    public TenderEligibilityCriterionDocumentServiceImpl(
        TenderEligibilityCriterionDocumentRepository tenderEligibilityCriterionDocumentRepository,
        TenderEligibilityCriterionDocumentMapper tenderEligibilityCriterionDocumentMapper
    ) {
        this.tenderEligibilityCriterionDocumentRepository = tenderEligibilityCriterionDocumentRepository;
        this.tenderEligibilityCriterionDocumentMapper = tenderEligibilityCriterionDocumentMapper;
    }

    @Override
    public TenderEligibilityCriterionDocumentDTO save(TenderEligibilityCriterionDocumentDTO tenderEligibilityCriterionDocumentDTO) {
        log.debug("Request to save TenderEligibilityCriterionDocument : {}", tenderEligibilityCriterionDocumentDTO);
        TenderEligibilityCriterionDocument tenderEligibilityCriterionDocument = tenderEligibilityCriterionDocumentMapper.toEntity(
            tenderEligibilityCriterionDocumentDTO
        );
        tenderEligibilityCriterionDocument = tenderEligibilityCriterionDocumentRepository.save(tenderEligibilityCriterionDocument);
        return tenderEligibilityCriterionDocumentMapper.toDto(tenderEligibilityCriterionDocument);
    }

    @Override
    public Optional<TenderEligibilityCriterionDocumentDTO> partialUpdate(
        TenderEligibilityCriterionDocumentDTO tenderEligibilityCriterionDocumentDTO
    ) {
        log.debug("Request to partially update TenderEligibilityCriterionDocument : {}", tenderEligibilityCriterionDocumentDTO);

        return tenderEligibilityCriterionDocumentRepository
            .findById(tenderEligibilityCriterionDocumentDTO.getId())
            .map(existingTenderEligibilityCriterionDocument -> {
                tenderEligibilityCriterionDocumentMapper.partialUpdate(
                    existingTenderEligibilityCriterionDocument,
                    tenderEligibilityCriterionDocumentDTO
                );

                return existingTenderEligibilityCriterionDocument;
            })
            .map(tenderEligibilityCriterionDocumentRepository::save)
            .map(tenderEligibilityCriterionDocumentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenderEligibilityCriterionDocumentDTO> findAll() {
        log.debug("Request to get all TenderEligibilityCriterionDocuments");
        return tenderEligibilityCriterionDocumentRepository
            .findAll()
            .stream()
            .map(tenderEligibilityCriterionDocumentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderEligibilityCriterionDocumentDTO> findOne(Long id) {
        log.debug("Request to get TenderEligibilityCriterionDocument : {}", id);
        return tenderEligibilityCriterionDocumentRepository.findById(id).map(tenderEligibilityCriterionDocumentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderEligibilityCriterionDocument : {}", id);
        tenderEligibilityCriterionDocumentRepository.deleteById(id);
    }
}
