package com.ryzhov_andrei.rest_api.controllers;

import com.google.gson.Gson;
import com.ryzhov_andrei.rest_api.model.Event;
import com.ryzhov_andrei.rest_api.model.User;
import com.ryzhov_andrei.rest_api.service.EventService;
import com.ryzhov_andrei.rest_api.service.impl.EventServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

@WebServlet("/api/v1/events")
public class EventRestControllerV1 extends HttpServlet {
    private final EventService eventService = new EventServiceImpl();
    private final Gson GSON = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Event event = GSON.fromJson(request.getReader(), Event.class);
        Integer id = event.getId();
        if (id == 0) {
            List<Event> eventList = eventService.getAll();
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(eventList);
            out.flush();
        } else {
            Event e = eventService.getById(id);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(e);
            out.flush();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Event event = GSON.fromJson(request.getReader(), Event.class);
        eventService.update(event);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Update event ...");
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Event event = GSON.fromJson(request.getReader(), Event.class);
        eventService.deleteById(event.getId());
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Delete event ...");
        out.flush();
    }
}
