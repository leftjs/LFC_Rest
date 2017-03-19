package com.leftjs.lfc.controller;

import com.leftjs.lfc.model.AdminList;
import com.leftjs.lfc.model.domain.Admin;
import com.leftjs.lfc.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jason on 2017/3/11.
 */
@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;


    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public AdminList listAdmin(Pageable pageable) {
        Page<Admin> admins =  adminRepository.findAll(pageable);
        return new AdminList(admins.getNumber(), admins.getTotalPages(), admins.getSize(), admins.getTotalElements(), admins.getContent());
    }


    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Admin getAdmin(@PathVariable Long id) {
        return adminRepository.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAdmin(@PathVariable Long id) {
        adminRepository.delete(id);
        return ResponseEntity.noContent().build();

    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        return new ResponseEntity<>(adminRepository.save(admin), HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Admin updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        admin.setId(id);
        return adminRepository.save(admin);
    }

}
