package com.infosys.ComplaintAndRequestService.repository;

import com.infosys.ComplaintAndRequestService.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {
}
