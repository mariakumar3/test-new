package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ServiceEstimateItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ServiceEstimateItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServiceEstimateItemRepository extends JpaRepository<ServiceEstimateItem, Long> {}
