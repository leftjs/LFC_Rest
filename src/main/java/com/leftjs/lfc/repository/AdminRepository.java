package com.leftjs.lfc.repository;

import com.leftjs.lfc.model.domain.Admin;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by jason on 2017/3/12.
 */
public interface AdminRepository extends PagingAndSortingRepository<Admin, Long> {
    public Admin findByUsernameAndPassword(String username, String password);
}
