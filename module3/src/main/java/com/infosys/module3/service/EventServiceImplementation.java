package com.infosys.module3.service;

import com.infosys.module3.exception.Module3Exception;
import com.infosys.module3.info.EventDto;
import com.infosys.module3.info.SocietyDto;
import com.infosys.module3.microService.SocietyManagementInterface;
import com.infosys.module3.model.Event;
import com.infosys.module3.repository.EventRepository;
import com.infosys.module3.response.EventResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImplementation implements EventService{

    @Autowired
    EventRepository eventRepository;

    @Autowired
    SocietyManagementInterface societyManagementInterface;
    @Override
    public EventResponse addEventInfo(String jwt, EventDto eventDto) throws Module3Exception {
        SocietyDto society=societyManagementInterface.getAdminDetails(jwt);
        Event newEvent=new Event();
        newEvent.setEventName(eventDto.getEventName());
        newEvent.setEventDate(eventDto.getEventDate());
        newEvent.setEventDetails(eventDto.getEventDetails());
        newEvent.setEventImage(eventDto.getEventImage());
        newEvent.setSocietyId(society.getSocietyId());
        Event savedEvent=eventRepository.save(newEvent);
        if (savedEvent.getEventId()!=null){
            return new EventResponse(savedEvent,"Event Created Successfully");
        }
        throw new Module3Exception("Unable To Create Event");
    }

    @Override
    public List<Event> getEventList() {
        return eventRepository.findAll();
    }

    @Override
    public EventResponse editEvent(Long eventId, EventDto eventDto) throws Module3Exception {
        Optional<Event> event=eventRepository.findById(eventId);
        if (event.isPresent()){
            Event existingEvent=event.get();

            if (!existingEvent.getEventName().equals(eventDto.getEventName())) {
                existingEvent.setEventName(eventDto.getEventName());
            }
            if (!existingEvent.getEventDate().equals(eventDto.getEventDate())) {
                existingEvent.setEventDate(eventDto.getEventDate());
            }
            if (!existingEvent.getEventDetails().equals(eventDto.getEventDetails())) {
                existingEvent.setEventDetails(eventDto.getEventDetails());
            }
            if (!existingEvent.getEventImage().equals(eventDto.getEventImage())) {
                existingEvent.setEventImage(eventDto.getEventImage());
            }
            Event updatedEvent = eventRepository.save(existingEvent);
            if (updatedEvent.getEventId()!=null){
                return new EventResponse(updatedEvent,"Event Updated Successfully");
            }
            throw new Module3Exception("Unable To Update Event");
        }
        throw new Module3Exception("Unable To Update Event");
    }

    @Override
    public EventResponse removeEvent(Long eventId) throws Module3Exception{
        if (!eventRepository.existsById(eventId)) {
            throw new Module3Exception("Event Not Found");
        }
        eventRepository.deleteById(eventId);
        return new EventResponse(null,"Event Deleted Successfully");
    }

    @Override
    public Event getEvent(Long eventId) throws Module3Exception {
        Optional<Event> event=eventRepository.findById(eventId);
        if (event.isPresent()){
            return event.get();
        }
        throw new Module3Exception("Event Not Found");
    }
}
