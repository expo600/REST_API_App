package com.ryzhov_andrei.rest_api.controllers;

import com.google.gson.Gson;
import com.ryzhov_andrei.rest_api.model.User;
import com.ryzhov_andrei.rest_api.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

@WebServlet("/users")
public class UserRestControllerV1 extends HttpServlet {

    private final UserServiceImpl userServiceImpl = new UserServiceImpl();
    private final Gson GSON = new Gson();

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
        User user = GSON.fromJson((Reader) request, User.class);
        userServiceImpl.create(user);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Save user ...");
        out.flush();
        response.sendRedirect("/users");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = GSON.fromJson((Reader) request, User.class);
        userServiceImpl.update(user);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Update user ...");
        out.flush();
        response.sendRedirect("/users");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Integer id = Integer.valueOf(request.getParameter("id"));
        User user = GSON.fromJson((Reader) request, User.class);
        userServiceImpl.deleteById(user.getId());
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Delete user ...");
        out.flush();
        response.sendRedirect("/users");
    }
}
