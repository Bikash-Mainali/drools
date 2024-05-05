package com.bm.drools.droolsproject.controller;
import com.bm.drools.droolsproject.domain.ApplicantRequest;
import com.bm.drools.droolsproject.domain.Rate;
import com.bm.drools.droolsproject.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PROJECT IntelliJ IDEA
 * @AUTHOR Bikash Mainali
 * @DATE 5/5/24
 */

@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/loan-application")
    public ResponseEntity<Rate> getRate
            (@RequestBody ApplicantRequest applicantRequest){
        Rate rate = applicationService.getRate(applicantRequest);
        return new ResponseEntity<>(rate, HttpStatus.OK);
    }
}
