package com.infosys.module2.service;

import com.infosys.module2.exception.Module2Exception;
import com.infosys.module2.info.ResidentDto;
import com.infosys.module2.info.ResidentProfileDto;
import com.infosys.module2.model.Resident;

import java.util.List;

public interface ResidentService {
    public String residentRegistration(ResidentDto residentDto,String jwt) throws Module2Exception;
    public List<Resident> getResidentList();
    public Resident getResident(String jwt);
    public ResidentProfileDto getResidentDetails(String jwt);
}
