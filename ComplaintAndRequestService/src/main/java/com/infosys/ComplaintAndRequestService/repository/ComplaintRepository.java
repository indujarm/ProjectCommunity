package com.infosys.ComplaintAndRequestService.repository;

import com.infosys.ComplaintAndRequestService.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,Long> {
}
