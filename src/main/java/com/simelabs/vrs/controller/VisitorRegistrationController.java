package com.simelabs.vrs.controller;

import com.simelabs.vrs.impl.VisitsServiceImpl;
import com.simelabs.vrs.model.VisitsModel;
import com.simelabs.vrs.request.VisitsRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class VisitorRegistrationController {

    @Autowired
    VisitsServiceImpl visitsService;

//    @PostMapping(value = ApiEndPoints.SAVE_VISITS, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<BaseResponse<VisitsModel>> saveVisits(@Valid @RequestBody VisitsRequest visitsRequest) {
//        BaseResponse<VisitsModel> baseResponse;
//    try{
//        visitsService.
//
//    }
//    }
//    catch(){
//
//        }
}
