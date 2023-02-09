package com.ryzhov_andrei.rest_api.controllers;

import com.ryzhov_andrei.rest_api.model.Event;
import com.ryzhov_andrei.rest_api.model.File;
import com.ryzhov_andrei.rest_api.service.impl.EventServiceImpl;
import com.ryzhov_andrei.rest_api.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet("/events")
public class EventServlet extends HttpServlet {

    private  final EventServiceImpl eventServiceImpl = new EventServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Event> eventList = eventServiceImpl.getAll();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(eventList.toString());
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setId(Integer.valueOf(request.getParameter("id")));

        File file = new File();
        file.setId(Integer.valueOf(request.getParameter("id")));

        Event event = new Event();
        event.setUser(user);
        event.setFile(file);
        eventServiceImpl.create(event);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Save event ...");
        out.flush();
        response.sendRedirect("/events");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setId(Integer.valueOf(request.getParameter("id")));
        user.setUserName(request.getParameter("user_name"));

        File file = new File();
        file.setId(Integer.valueOf(request.getParameter("id")));
        file.setFileName(request.getParameter("file_name"));
        file.setFilePath(request.getParameter("file_path"));

        Event event = new Event();
        event.setId(Integer.valueOf(request.getParameter("id")));
        event.setUser(user);
        event.setFile(file);
        eventServiceImpl.update(event);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Update event ...");
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        eventServiceImpl.deleteById(id);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Delete event ...");
        out.flush();
        response.sendRedirect("/events");
    }
}
