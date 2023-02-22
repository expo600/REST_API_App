package com.ryzhov_andrei.rest_api.controllers;

import com.google.gson.Gson;
import com.ryzhov_andrei.rest_api.model.File;
import com.ryzhov_andrei.rest_api.repository.FileRepository;
import com.ryzhov_andrei.rest_api.repository.impl.FileRepositoryImpl;
import com.ryzhov_andrei.rest_api.service.EventService;
import com.ryzhov_andrei.rest_api.service.FileService;
import com.ryzhov_andrei.rest_api.service.UserService;
import com.ryzhov_andrei.rest_api.service.impl.EventServiceImpl;
import com.ryzhov_andrei.rest_api.service.impl.FileServiceImpl;
import com.ryzhov_andrei.rest_api.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig
@WebServlet("/api/v1/files")
public class FileRestControllerV1 extends HttpServlet {
    private  final FileRepository fileRepository = new FileRepositoryImpl();
    private final EventService eventService = new EventServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final FileService fileService = new FileServiceImpl(userService,eventService,fileRepository);
    private final Gson GSON = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = GSON.fromJson(request.getReader(), File.class);
        Integer id = file.getId();
        if (id == 0) {
            List<File> fileList = fileService.getAll();
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(fileList);
            out.flush();
        } else {
            File f = fileService.getById(id);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(f);
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream inputStream = request.getInputStream();
        Integer userId  = Integer.valueOf(request.getHeader("user_id"));
        File file = fileService.uploadFile(inputStream,userId);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(GSON.toJson(file));
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = GSON.fromJson(request.getReader(), File.class);
        fileService.update(file);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Update file ...");
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = GSON.fromJson(request.getReader(), File.class);
        fileService.deleteById(file.getId());
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Delete file ...");
        out.flush();
    }
}
