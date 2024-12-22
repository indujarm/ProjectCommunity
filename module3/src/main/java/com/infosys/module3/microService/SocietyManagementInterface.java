package com.infosys.module3.microService;

import com.infosys.module3.info.FlatDto;
import com.infosys.module3.info.SocietyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="module2")
@Component
public interface SocietyManagementInterface {
    @GetMapping("/api/get-admin")
    public SocietyDto getAdminDetails(@RequestHeader("Authorization") String jwt);
    @GetMapping("/api/flatByFlatNo")
    public FlatDto getFlatByFlatNo(@RequestHeader("Authorization") String jwt,@RequestParam("flatNo") String flatNo);
}
