package com.leftjs.lfc.repository;

import com.leftjs.lfc.model.domain.Clerk;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by jason on 2017/3/11.
 */
public interface ClerkRepository extends PagingAndSortingRepository<Clerk, Long> {
    public Clerk findByClerkIdAndPassword(String clerkId, String password);
    public Clerk findByClerkId(String clerkId);
}
