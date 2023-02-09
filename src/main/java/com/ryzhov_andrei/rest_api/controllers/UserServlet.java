package com.ryzhov_andrei.rest_api.controllers;

import com.ryzhov_andrei.rest_api.model.User;
import com.ryzhov_andrei.rest_api.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private final UserServiceImpl userServiceImpl = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = userServiceImpl.getAll();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(userList.toString());
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setUserName(request.getParameter("user_name"));
        userServiceImpl.create(user);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Save user ...");
        out.flush();
        response.sendRedirect("/users");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setId(Integer.valueOf(request.getParameter("id")));
        user.setUserName(request.getParameter("user_name"));
        userServiceImpl.update(user);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Update user ...");
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        userServiceImpl.deleteById(id);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Delete user ...");
        out.flush();
        response.sendRedirect("/users");
    }
}
