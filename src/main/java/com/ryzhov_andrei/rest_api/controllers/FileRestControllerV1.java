package com.ryzhov_andrei.rest_api.controllers;

import com.google.gson.Gson;
import com.ryzhov_andrei.rest_api.model.File;
import com.ryzhov_andrei.rest_api.service.FileService;
import com.ryzhov_andrei.rest_api.service.impl.FileServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/v1/files")
public class FileRestControllerV1 extends HttpServlet {
    private final FileService fileService = new FileServiceImpl();
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
        File file = GSON.fromJson(request.getReader(), File.class);
        fileService.create(file);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Save file ...");
        out.flush();
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
//      Integer id = Integer.parseInt(request.getParameter("id"));
        File file = GSON.fromJson(request.getReader(), File.class);
        fileService.deleteById(file.getId());
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Delete file ...");
        out.flush();
    }
}
