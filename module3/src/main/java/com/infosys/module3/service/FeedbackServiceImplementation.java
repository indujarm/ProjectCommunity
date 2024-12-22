package com.infosys.module3.service;

import com.infosys.module3.exception.Module3Exception;
import com.infosys.module3.info.FeedbackDto;
import com.infosys.module3.model.Feedback;
import com.infosys.module3.repository.FeedbackRepository;
import com.infosys.module3.response.FeedbackResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImplementation implements FeedbackService{

    @Autowired
    FeedbackRepository feedbackRepository;
    @Override
    public FeedbackResponse submitFeedback(FeedbackDto feedbackDto, Long eventId) throws Module3Exception{
        Feedback newFeedback=new Feedback();
        newFeedback.setContent(feedbackDto.getContent());
        newFeedback.setEventId(eventId);
        Feedback savedFeedback=feedbackRepository.save(newFeedback);
        if (savedFeedback.getFeedbackId()!=null){
            return new FeedbackResponse(savedFeedback,"Feedback Sent Successfully");
        }
        throw new Module3Exception("Unable to Add Feedback");
    }

    @Override
    public List<Feedback> getFeedback(Long eventId) throws Module3Exception {
        return feedbackRepository.findByEventId(eventId);
    }
}
