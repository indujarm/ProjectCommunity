package com.infosys.ComplaintAndRequestService.repository;

import com.infosys.ComplaintAndRequestService.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor,Long> {
}
