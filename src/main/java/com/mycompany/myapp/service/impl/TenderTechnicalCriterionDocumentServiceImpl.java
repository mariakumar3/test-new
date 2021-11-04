package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.TenderTechnicalCriterionDocument;
import com.mycompany.myapp.repository.TenderTechnicalCriterionDocumentRepository;
import com.mycompany.myapp.service.TenderTechnicalCriterionDocumentService;
import com.mycompany.myapp.service.dto.TenderTechnicalCriterionDocumentDTO;
import com.mycompany.myapp.service.mapper.TenderTechnicalCriterionDocumentMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TenderTechnicalCriterionDocument}.
 */
@Service
@Transactional
public class TenderTechnicalCriterionDocumentServiceImpl implements TenderTechnicalCriterionDocumentService {

    private final Logger log = LoggerFactory.getLogger(TenderTechnicalCriterionDocumentServiceImpl.class);

    private final TenderTechnicalCriterionDocumentRepository tenderTechnicalCriterionDocumentRepository;

    private final TenderTechnicalCriterionDocumentMapper tenderTechnicalCriterionDocumentMapper;

    public TenderTechnicalCriterionDocumentServiceImpl(
        TenderTechnicalCriterionDocumentRepository tenderTechnicalCriterionDocumentRepository,
        TenderTechnicalCriterionDocumentMapper tenderTechnicalCriterionDocumentMapper
    ) {
        this.tenderTechnicalCriterionDocumentRepository = tenderTechnicalCriterionDocumentRepository;
        this.tenderTechnicalCriterionDocumentMapper = tenderTechnicalCriterionDocumentMapper;
    }

    @Override
    public TenderTechnicalCriterionDocumentDTO save(TenderTechnicalCriterionDocumentDTO tenderTechnicalCriterionDocumentDTO) {
        log.debug("Request to save TenderTechnicalCriterionDocument : {}", tenderTechnicalCriterionDocumentDTO);
        TenderTechnicalCriterionDocument tenderTechnicalCriterionDocument = tenderTechnicalCriterionDocumentMapper.toEntity(
            tenderTechnicalCriterionDocumentDTO
        );
        tenderTechnicalCriterionDocument = tenderTechnicalCriterionDocumentRepository.save(tenderTechnicalCriterionDocument);
        return tenderTechnicalCriterionDocumentMapper.toDto(tenderTechnicalCriterionDocument);
    }

    @Override
    public Optional<TenderTechnicalCriterionDocumentDTO> partialUpdate(
        TenderTechnicalCriterionDocumentDTO tenderTechnicalCriterionDocumentDTO
    ) {
        log.debug("Request to partially update TenderTechnicalCriterionDocument : {}", tenderTechnicalCriterionDocumentDTO);

        return tenderTechnicalCriterionDocumentRepository
            .findById(tenderTechnicalCriterionDocumentDTO.getId())
            .map(existingTenderTechnicalCriterionDocument -> {
                tenderTechnicalCriterionDocumentMapper.partialUpdate(
                    existingTenderTechnicalCriterionDocument,
                    tenderTechnicalCriterionDocumentDTO
                );

                return existingTenderTechnicalCriterionDocument;
            })
            .map(tenderTechnicalCriterionDocumentRepository::save)
            .map(tenderTechnicalCriterionDocumentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TenderTechnicalCriterionDocumentDTO> findAll() {
        log.debug("Request to get all TenderTechnicalCriterionDocuments");
        return tenderTechnicalCriterionDocumentRepository
            .findAll()
            .stream()
            .map(tenderTechnicalCriterionDocumentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TenderTechnicalCriterionDocumentDTO> findOne(Long id) {
        log.debug("Request to get TenderTechnicalCriterionDocument : {}", id);
        return tenderTechnicalCriterionDocumentRepository.findById(id).map(tenderTechnicalCriterionDocumentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenderTechnicalCriterionDocument : {}", id);
        tenderTechnicalCriterionDocumentRepository.deleteById(id);
    }
}
