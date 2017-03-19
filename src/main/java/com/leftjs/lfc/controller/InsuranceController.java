package com.leftjs.lfc.controller;

import com.leftjs.lfc.auth.annotation.Authorization;
import com.leftjs.lfc.model.InsuranceList;
import com.leftjs.lfc.model.ResErrorMessage;
import com.leftjs.lfc.model.domain.Admin;
import com.leftjs.lfc.model.domain.Insurance;
import com.leftjs.lfc.model.domain.Clerk;
import com.leftjs.lfc.repository.ClerkRepository;
import com.leftjs.lfc.repository.insurance.InsuranceRepository;
import com.leftjs.lfc.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by jason on 2017/3/13.
 */
@RestController
@RequestMapping("/api/insurances")
public class InsuranceController {


    @Autowired
    private ClerkRepository clerkRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Authorization
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> createInsurance(@RequestBody Insurance insurance, HttpServletRequest request) {

        if (request.getAttribute("currentUser").getClass() == Admin.class) {
            return new ResponseEntity<>(new ResErrorMessage(HttpStatus.BAD_REQUEST.value(), "管理员不能插入保单"), HttpStatus.BAD_REQUEST);
        }
        Clerk clerk = (Clerk) request.getAttribute("currentUser");
        Insurance insuranceInDB = insuranceRepository.save(insurance);
        insuranceInDB.getInfo().setClerk(clerkRepository.findOne(clerk.getId()));
        insuranceInDB.getInfo().setInforceTime(new Date(System.currentTimeMillis()));
        insuranceInDB.getInfo().setBaodanNo("baodan" + insuranceInDB.getInfo().getId()); // 设置保单号
        insuranceInDB = insuranceRepository.save(insuranceInDB);
        return new ResponseEntity<>(insuranceInDB, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public InsuranceList listAllInsurance(Pageable pageable) {
        Page<Insurance> page = insuranceRepository.findAll(pageable);
        return new InsuranceList(page.getNumber(), page.getTotalPages(), page.getSize(), page.getTotalElements(), page.getContent());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteInsurance(@PathVariable Long id) {
        insuranceRepository.delete(id);
        return  ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public Insurance updateInsurance(@PathVariable Long id, @RequestBody Insurance insurance) {
        insurance.setId(id);
        return insuranceRepository.save(insurance);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public Insurance getInsuranceById(@PathVariable Long id) {
        return insuranceRepository.findOne(id);
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public InsuranceList queryInsurances(
            @PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC)
            Pageable pageable,
            @RequestParam(value = "clerk", defaultValue = "") String clerkId,
            @RequestParam(value = "holder", defaultValue = "") String holderName) {
        System.out.println("params =>>>>>>>>>>>>>>>>>>>>");
        System.out.println(clerkId);
        System.out.println(holderName);
        Page<Insurance> page = null;
        if (!Utils.isNullOrEmpty(clerkId)) {
            page = insuranceRepository.findByClerkId(clerkId, pageable);
        } else if (!Utils.isNullOrEmpty(holderName)) {
            page = insuranceRepository.findByHolderName(holderName, pageable);
        } else {
            return null;
        }


        return new InsuranceList(page.getNumber(), page.getTotalPages(), page.getSize(), page.getTotalElements(), page.getContent());
    }

}
