package com.leftjs.lfc.controller;

import com.leftjs.lfc.auth.manager.TokenManager;
import com.leftjs.lfc.model.ResErrorMessage;
import com.leftjs.lfc.model.domain.Admin;
import com.leftjs.lfc.model.domain.Clerk;
import com.leftjs.lfc.repository.AdminRepository;
import com.leftjs.lfc.repository.ClerkRepository;
import com.leftjs.lfc.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jason on 2017/3/12.
 */
@RestController
@RequestMapping("/api/token")
public class TokenController {



    @Autowired
    private ClerkRepository clerkRepository;

    @Autowired
    private AdminRepository adminRepository;

    @RequestMapping(value = "/clerk", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> signInWithClerk(@RequestBody Clerk clerk) {
        Clerk clerkInDatabase = clerkRepository.findByClerkIdAndPassword(clerk.getClerkId(), clerk.getPassword());
        if (Utils.isNullOrEmpty(clerkInDatabase)) {
            return new ResponseEntity<>(new ResErrorMessage(HttpStatus.BAD_REQUEST.value(), "业务员的用户名或密码错误"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(TokenManager.createToken("clerk:" + clerkInDatabase.getId()), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> signInWithAdmin(@RequestBody Admin admin) {
        Admin adminInDatabase = adminRepository.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
        if (Utils.isNullOrEmpty(adminInDatabase)) {
            return new ResponseEntity<>(new ResErrorMessage(HttpStatus.BAD_REQUEST.value(), "管理员的用户名或密码错误"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(TokenManager.createToken("admin:" + adminInDatabase.getId()), HttpStatus.OK);
    }
}
