package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DocumentObjectStore;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the DocumentObjectStore entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentObjectStoreRepository extends JpaRepository<DocumentObjectStore, Long> {}
