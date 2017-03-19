package com.leftjs.lfc.utils;

import com.leftjs.lfc.model.domain.Clerk;
import com.leftjs.lfc.repository.ClerkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jason on 2017/3/14.
 */
@Component
public class ClerkTest {
    @Autowired
    private ClerkRepository clerkRepository;

    public Clerk getClerkByClertId(String clerkId) {
        System.out.println("--><<><");
        System.out.println(clerkId);
        System.out.println(clerkRepository);
        return clerkRepository.findByClerkId(clerkId);
    }

}
