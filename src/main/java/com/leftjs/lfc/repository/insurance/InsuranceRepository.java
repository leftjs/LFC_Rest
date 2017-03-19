package com.leftjs.lfc.repository.insurance;

import com.leftjs.lfc.model.domain.Insurance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * Created by jason on 2017/3/13.
 */
public interface InsuranceRepository extends PagingAndSortingRepository<Insurance, Long> {

    @Query("select insurance from Insurance insurance join insurance.info info where info.clerk.clerkId = ?1")
    Page<Insurance> findByClerkId(String clerkId, Pageable pageable);

    @Query("select insurance from Insurance insurance join insurance.info info where info.holderName = ?1")
    Page<Insurance> findByHolderName(String holderName, Pageable pageable);

}
