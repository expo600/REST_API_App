package com.ryzhov_andrei.rest_api.controllers;

import com.ryzhov_andrei.rest_api.model.File;
import com.ryzhov_andrei.rest_api.service.impl.FileServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet("/files")
public class FileServlet extends HttpServlet {

    private final FileServiceImpl fileServiceImpl = new FileServiceImpl();


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
        File file = new File();
        file.setFileName(request.getParameter("file_name"));
        file.setFilePath(request.getParameter("file_path"));
        fileServiceImpl.create(file);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Save file ...");
        out.flush();
        response.sendRedirect("/files");

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = new File();
        file.setId(Integer.valueOf(request.getParameter("id")));
        file.setFileName(request.getParameter("file_name"));
        file.setFilePath(request.getParameter("file_path"));
        fileServiceImpl.update(file);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Update file ...");
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        fileServiceImpl.deleteById(id);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("Delete file ...");
        out.flush();
        response.sendRedirect("/files");
    }
}
