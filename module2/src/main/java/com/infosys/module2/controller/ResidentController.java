package com.infosys.module2.controller;

import com.infosys.module2.exception.Module2Exception;
import com.infosys.module2.info.ResidentDto;
import com.infosys.module2.info.ResidentProfileDto;
import com.infosys.module2.model.Resident;
import com.infosys.module2.service.FlatService;
import com.infosys.module2.service.ResidentService;
import com.infosys.module2.service.SocietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ResidentController {
    @Autowired
    ResidentService residentService;
    @Autowired
    FlatService flatService;
    @Autowired
    SocietyService societyService;
    @PostMapping("/residentRegister")
    public String residentRegistration(@RequestHeader("Authorization") String jwt, @RequestBody ResidentDto residentDto) throws Module2Exception {
        System.out.println("Received JWT: " + jwt);
        System.out.println("Admin Details DTO: " + residentDto);
        return residentService.residentRegistration(residentDto,jwt);
    }
    @GetMapping("/getResidentList")
    public List<Resident> getResidentList(@RequestHeader("Authorization") String jwt){
        return residentService.getResidentList();
    }

    @GetMapping("/getResidentDetails")
    public ResidentProfileDto getResidentDetails(@RequestHeader("Authorization") String jwt){
        return residentService.getResidentDetails(jwt);
    }

    @GetMapping("/getResident")
    public Resident getResident(@RequestHeader("Authorization") String jwt){
        return residentService.getResident(jwt);
    }
}
