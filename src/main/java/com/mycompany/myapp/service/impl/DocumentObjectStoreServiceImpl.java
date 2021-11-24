package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.DocumentObjectStore;
import com.mycompany.myapp.repository.DocumentObjectStoreRepository;
import com.mycompany.myapp.service.DocumentObjectStoreService;
import com.mycompany.myapp.service.dto.DocumentObjectStoreDTO;
import com.mycompany.myapp.service.mapper.DocumentObjectStoreMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DocumentObjectStore}.
 */
@Service
@Transactional
public class DocumentObjectStoreServiceImpl implements DocumentObjectStoreService {

    private final Logger log = LoggerFactory.getLogger(DocumentObjectStoreServiceImpl.class);

    private final DocumentObjectStoreRepository documentObjectStoreRepository;

    private final DocumentObjectStoreMapper documentObjectStoreMapper;

    public DocumentObjectStoreServiceImpl(
        DocumentObjectStoreRepository documentObjectStoreRepository,
        DocumentObjectStoreMapper documentObjectStoreMapper
    ) {
        this.documentObjectStoreRepository = documentObjectStoreRepository;
        this.documentObjectStoreMapper = documentObjectStoreMapper;
    }

    @Override
    public DocumentObjectStoreDTO save(DocumentObjectStoreDTO documentObjectStoreDTO) {
        log.debug("Request to save DocumentObjectStore : {}", documentObjectStoreDTO);
        DocumentObjectStore documentObjectStore = documentObjectStoreMapper.toEntity(documentObjectStoreDTO);
        documentObjectStore = documentObjectStoreRepository.save(documentObjectStore);
        return documentObjectStoreMapper.toDto(documentObjectStore);
    }

    @Override
    public Optional<DocumentObjectStoreDTO> partialUpdate(DocumentObjectStoreDTO documentObjectStoreDTO) {
        log.debug("Request to partially update DocumentObjectStore : {}", documentObjectStoreDTO);

        return documentObjectStoreRepository
            .findById(documentObjectStoreDTO.getId())
            .map(existingDocumentObjectStore -> {
                documentObjectStoreMapper.partialUpdate(existingDocumentObjectStore, documentObjectStoreDTO);

                return existingDocumentObjectStore;
            })
            .map(documentObjectStoreRepository::save)
            .map(documentObjectStoreMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentObjectStoreDTO> findAll() {
        log.debug("Request to get all DocumentObjectStores");
        return documentObjectStoreRepository
            .findAll()
            .stream()
            .map(documentObjectStoreMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DocumentObjectStoreDTO> findOne(Long id) {
        log.debug("Request to get DocumentObjectStore : {}", id);
        return documentObjectStoreRepository.findById(id).map(documentObjectStoreMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DocumentObjectStore : {}", id);
        documentObjectStoreRepository.deleteById(id);
    }
}
