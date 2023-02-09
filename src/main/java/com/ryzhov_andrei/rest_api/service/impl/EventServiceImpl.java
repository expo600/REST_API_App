package com.ryzhov_andrei.rest_api.service.impl;

import com.ryzhov_andrei.rest_api.repository.EventRepository;
import com.ryzhov_andrei.rest_api.repository.impl.EventRepositoryImpl;
import com.ryzhov_andrei.rest_api.model.Event;
import com.ryzhov_andrei.rest_api.service.EventService;

import java.util.List;

public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventServiceImpl() {
        this.eventRepository = new EventRepositoryImpl();
    }

    @Override
    public Event getById(Integer id) {
        return eventRepository.getById(id);
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.getAll();
    }

    @Override
    public Event create(Event event) {
        return eventRepository.create(event);
    }

    @Override
    public Event update(Event event) {
        return eventRepository.update(event);
    }

    @Override
    public void deleteById(Integer id) {
        eventRepository.deleteById(id);
    }
}
