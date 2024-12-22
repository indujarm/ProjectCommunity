package com.infosys.ComplaintAndRequestService.service;

import com.infosys.ComplaintAndRequestService.dto.RequestDto;
import com.infosys.ComplaintAndRequestService.exception.RequestException;

public interface RequestService {
    public String sendRequest(RequestDto requestDto) throws RequestException;
}
