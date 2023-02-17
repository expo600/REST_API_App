package com.ryzhov_andrei.rest_api.controllers;

import com.google.gson.Gson;
import com.ryzhov_andrei.rest_api.model.User;
import com.ryzhov_andrei.rest_api.service.UserService;
import com.ryzhov_andrei.rest_api.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/v1/users")
public class UserRestControllerV1 extends HttpServlet {

    private final UserService userService = new UserServiceImpl();
    private final Gson GSON = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      Integer id = Integer.parseInt(request.getParameter("id"));
        User user = GSON.fromJson(request.getReader(), User.class);
        final Integer id = user.getId();
        if (id == 0) {
            List<User> userList = userService.getAll();
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(userList);
            out.flush();
        } else {
            User u = userService.getById(id);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(u);
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = GSON.fromJson(request.getReader(), User.class);
        userService.create(user);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Save user ...");
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = GSON.fromJson(request.getReader(), User.class);
        userService.update(user);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Update user ...");
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Integer id = Integer.parseInt(request.getParameter("id"));
        User user = GSON.fromJson(request.getReader(), User.class);
        userService.deleteById(user.getId());
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Delete user ...");
        out.flush();
    }
}
