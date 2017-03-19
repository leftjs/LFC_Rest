package com.leftjs.lfc.repository.insurance;

import com.leftjs.lfc.model.domain.InsuranceInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by jason on 2017/3/13.
 */
public interface InfoRepository extends PagingAndSortingRepository<InsuranceInfo, Long> {
}
