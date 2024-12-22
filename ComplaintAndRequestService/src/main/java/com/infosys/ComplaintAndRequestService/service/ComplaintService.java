package com.infosys.ComplaintAndRequestService.service;

import com.infosys.ComplaintAndRequestService.dto.ComplaintDto;
import com.infosys.ComplaintAndRequestService.exception.ComplaintException;
import com.infosys.ComplaintAndRequestService.model.Complaint;

import java.util.List;

public interface ComplaintService {
    Complaint addComplaint(String jwt, ComplaintDto complaintDto) throws ComplaintException;
    List<Complaint> getAllComplaints();
    Complaint closeComplaint(Long complaintId);
}
