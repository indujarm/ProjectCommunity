package com.infosys.ComplaintAndRequestService.feign;

import com.infosys.ComplaintAndRequestService.dto.ResidentDto;
import com.infosys.ComplaintAndRequestService.dto.SocietyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "societymanagementservice")
public interface SocietyManagementInterface {
    @GetMapping("/api/getResidentByJWT")
    ResidentDto getResidentByJWT(@RequestHeader("Authorization") String jwt);
    @GetMapping("/api/get-admin")
    SocietyDto getAdminDetails(@RequestHeader("Authorization") String jwt);
}


