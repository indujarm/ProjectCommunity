package com.infosys.module3.service;

import com.infosys.module3.exception.Module3Exception;
import com.infosys.module3.info.NoticeDto;
import com.infosys.module3.info.SocietyDto;
import com.infosys.module3.microService.SocietyManagementInterface;
import com.infosys.module3.model.Notice;
import com.infosys.module3.repository.NoticeRepository;
import com.infosys.module3.response.NoticeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticeServiceImplementation implements NoticeService{
    @Autowired
    NoticeRepository noticeRepository;
    @Autowired
    SocietyManagementInterface societyManagementInterface;
    @Override
    public NoticeResponse addNoticeInfo(String jwt, NoticeDto noticeDto) throws Module3Exception {
        SocietyDto society=societyManagementInterface.getAdminDetails(jwt);
        Notice newNotice=new Notice();
        newNotice.setHeading(noticeDto.getHeading());
        newNotice.setContent(noticeDto.getContent());
        newNotice.setDatePosted(noticeDto.getDatePosted());
        newNotice.setNoticeImage(noticeDto.getNoticeImage());
        newNotice.setSocietyId(society.getSocietyId());
        Notice savedNotice=noticeRepository.save(newNotice);
        if (savedNotice.getNoticeId()!=null){
            return new NoticeResponse(savedNotice,"Notice Created Successfully");
        }
        throw new Module3Exception("Unable To Create Notice");
    }

    @Override
    public List<Notice> getNoticeList() throws Module3Exception {
        return noticeRepository.findAll();
    }

    @Override
    public NoticeResponse editNotice(Long noticeId, NoticeDto noticeDto) throws Module3Exception {
        Optional<Notice> notice=noticeRepository.findById(noticeId);
        if (notice.isPresent()){
            Notice existingNotice=notice.get();
            if (!existingNotice.getHeading().equals(noticeDto.getContent())) {
                existingNotice.setHeading(noticeDto.getHeading());
            }
            if (!existingNotice.getContent().equals(noticeDto.getContent())) {
                existingNotice.setContent(noticeDto.getContent());
            }
            if (!existingNotice.getDatePosted().equals(noticeDto.getDatePosted())) {
                existingNotice.setDatePosted(noticeDto.getDatePosted());
            }
            if (!existingNotice.getNoticeImage().equals(noticeDto.getNoticeImage())) {
                existingNotice.setNoticeImage(noticeDto.getNoticeImage());
            }
            Notice updatedNotice = noticeRepository.save(existingNotice);
            if (updatedNotice.getNoticeId()!=null){
                return new NoticeResponse(updatedNotice,"Notice Updated Successfully");
            }
            throw new Module3Exception("Unable To Update Notice");
        }
        throw new Module3Exception("Unable To Update Notice");
    }

    @Override
    public NoticeResponse removeNotice(Long noticeId) throws Module3Exception {
        if (!noticeRepository.existsById(noticeId)) {
            throw new Module3Exception("Notice Not Found");
        }
        noticeRepository.deleteById(noticeId);
        return new NoticeResponse(null,"Notice Deleted Successfully");
    }

    @Override
    public Notice getNotice(Long noticeId) throws Module3Exception {
        Optional<Notice> notice=noticeRepository.findById(noticeId);
        if (notice.isPresent()){
            return notice.get();
        }
        throw new Module3Exception("Notice Not Found");
    }
}
