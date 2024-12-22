package com.infosys.ComplaintAndRequestService.controller;

import com.infosys.ComplaintAndRequestService.dto.VendorDto;
import com.infosys.ComplaintAndRequestService.model.Vendor;
import com.infosys.ComplaintAndRequestService.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class VendorController {
    @Autowired
    VendorService vendorService;
    @PostMapping("/addVendor")
    public Vendor addVendor(@RequestHeader("Authorization") String jwt, @RequestBody VendorDto vendorDto){
        return vendorService.addVendor(jwt,vendorDto);
    }
    @GetMapping("/getAllVendors")
    public List<Vendor> getAllVendors(@RequestHeader("Authorization") String jwt){
        return vendorService.getAllVendors();
    }
}
