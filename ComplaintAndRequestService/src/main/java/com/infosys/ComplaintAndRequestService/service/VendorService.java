package com.infosys.ComplaintAndRequestService.service;

import com.infosys.ComplaintAndRequestService.dto.VendorDto;
import com.infosys.ComplaintAndRequestService.model.Vendor;

import java.util.List;

public interface VendorService {
    public Vendor addVendor(String jwt,VendorDto vendorDto);
    public List<Vendor> getAllVendors();
}
