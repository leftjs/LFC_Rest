package com.leftjs.lfc.controller;

import com.leftjs.lfc.model.ClerkList;
import com.leftjs.lfc.model.domain.Clerk;
import com.leftjs.lfc.repository.ClerkRepository;
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
@RequestMapping("/api/clerks")
public class ClerkController {

    @Autowired
    private ClerkRepository clerkRepository;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ClerkList listClerk(Pageable pageable) {
        Page<Clerk> clerks =  clerkRepository.findAll(pageable);
        return new ClerkList(clerks.getNumber(), clerks.getTotalPages(), clerks.getSize(), clerks.getTotalElements(), clerks.getContent());
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Clerk getClerk(@PathVariable Long id) {
        return clerkRepository.findOne(id);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteClerk(@PathVariable Long id) {
        clerkRepository.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Clerk> createClerk(@RequestBody Clerk clerk) {
        return new ResponseEntity<>(clerkRepository.save(clerk), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Clerk updateClerk(@PathVariable Long id, @RequestBody Clerk clerk) {
        clerk.setId(id);
        return clerkRepository.save(clerk);
    }

}
