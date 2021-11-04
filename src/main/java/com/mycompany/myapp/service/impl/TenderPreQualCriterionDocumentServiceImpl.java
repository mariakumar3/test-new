package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.TenderPreQualCriterionDocument;
import com.mycompany.myapp.repository.TenderPreQualCriterionDocumentRepository;
import com.mycompany.myapp.service.TenderPreQualCriterionDocumentService;
import com.mycompany.myapp.service.dto.TenderPreQualCriterionDocumentDTO;
import com.mycompany.myapp.service.mapper.TenderPreQualCriterionDocumentMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderPreQualCriterionDocument}.
 */
@Service
@Transactional
public class TenderPreQualCriterionDocumentServiceImpl implements TenderPreQualCriterionDocumentService {

    private final Logger log = LoggerFactory.getLogger(TenderPreQualCriterionDocumentServiceImpl.class);

    private final TenderPreQualCriterionDocumentRepository tenderPreQualCriterionDocumentRepository;

    private final TenderPreQualCriterionDocumentMapper tenderPreQualCriterionDocumentMapper;

    public TenderPreQualCriterionDocumentServiceImpl(
        TenderPreQualCriterionDocumentRepository tenderPreQualCriterionDocumentRepository,
        TenderPreQualCriterionDocumentMapper tenderPreQualCriterionDocumentMapper
    ) {
        this.tenderPreQualCriterionDocumentRepository = tenderPreQualCriterionDocumentRepository;
        this.tenderPreQualCriterionDocumentMapper = tenderPreQualCriterionDocumentMapper;
    }

    @Override
    public TenderPreQualCriterionDocumentDTO save(TenderPreQualCriterionDocumentDTO tenderPreQualCriterionDocumentDTO) {
        log.debug("Request to save TenderPreQualCriterionDocument : {}", tenderPreQualCriterionDocumentDTO);
        TenderPreQualCriterionDocument tenderPreQualCriterionDocument = tenderPreQualCriterionDocumentMapper.toEntity(
            tenderPreQualCriterionDocumentDTO
        );
        tenderPreQualCriterionDocument = tenderPreQualCriterionDocumentRepository.save(tenderPreQualCriterionDocument);
        return tenderPreQualCriterionDocumentMapper.toDto(tenderPreQualCriterionDocument);
    }

    @Override
    public Optional<TenderPreQualCriterionDocumentDTO> partialUpdate(TenderPreQualCriterionDocumentDTO tenderPreQualCriterionDocumentDTO) {
        log.debug("Request to partially update TenderPreQualCriterionDocument : {}", tenderPreQualCriterionDocumentDTO);

        return tenderPreQualCriterionDocumentRepository
            .findById(tenderPreQualCriterionDocumentDTO.getId())
            .map(existingTenderPreQualCriterionDocument -> {
                tenderPreQualCriterionDocumentMapper.partialUpdate(
                    existingTenderPreQualCriterionDocument,
                    tenderPreQualCriterionDocumentDTO
                );

                return existingTenderPreQualCriterionDocument;
            })
            .map(tenderPreQualCriterionDocumentRepository::save)
            .map(tenderPreQualCriterionDocumentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenderPreQualCriterionDocumentDTO> findAll() {
        log.debug("Request to get all TenderPreQualCriterionDocuments");
        return tenderPreQualCriterionDocumentRepository
            .findAll()
            .stream()
            .map(tenderPreQualCriterionDocumentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderPreQualCriterionDocumentDTO> findOne(Long id) {
        log.debug("Request to get TenderPreQualCriterionDocument : {}", id);
        return tenderPreQualCriterionDocumentRepository.findById(id).map(tenderPreQualCriterionDocumentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderPreQualCriterionDocument : {}", id);
        tenderPreQualCriterionDocumentRepository.deleteById(id);
    }
}
