package com.ryzhov_andrei.rest_api.controllers;

import com.google.gson.Gson;
import com.ryzhov_andrei.rest_api.model.File;
import com.ryzhov_andrei.rest_api.service.impl.FileServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

@WebServlet("/files")
public class FileRestControllerV1 extends HttpServlet {

    private final FileServiceImpl fileServiceImpl = new FileServiceImpl();
    private final Gson GSON = new Gson();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<File> fileList = fileServiceImpl.getAll();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(fileList.toString());
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = GSON.fromJson((Reader) request, File.class);
        fileServiceImpl.create(file);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Save file ...");
        out.flush();
        response.sendRedirect("/files");

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = GSON.fromJson((Reader) request, File.class);
        fileServiceImpl.update(file);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Update file ...");
        out.flush();
        response.sendRedirect("/files");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Integer id = Integer.valueOf(request.getParameter("id"));
        File file = GSON.fromJson((Reader) request, File.class);
        fileServiceImpl.deleteById(file.getId());
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Delete file ...");
        out.flush();
        response.sendRedirect("/files");
    }
}
