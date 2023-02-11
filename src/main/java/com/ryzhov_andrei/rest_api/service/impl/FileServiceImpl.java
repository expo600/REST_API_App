package com.ryzhov_andrei.rest_api.service.impl;

import com.ryzhov_andrei.rest_api.model.Event;
import com.ryzhov_andrei.rest_api.model.User;
import com.ryzhov_andrei.rest_api.repository.FileRepository;
import com.ryzhov_andrei.rest_api.repository.impl.FileRepositoryImpl;
import com.ryzhov_andrei.rest_api.model.File;
import com.ryzhov_andrei.rest_api.service.EventService;
import com.ryzhov_andrei.rest_api.service.FileService;
import com.ryzhov_andrei.rest_api.service.UserService;

import java.io.FileInputStream;
import java.util.List;

public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final UserService userService;
    private final EventService eventService;

    public FileServiceImpl() {
        this.fileRepository = new FileRepositoryImpl();
        this.userService = new UserServiceImpl();
        this.eventService = new EventServiceImpl();
    }

    public FileServiceImpl(FileRepository fileRepository, UserService userService, EventService eventService) {
        this.fileRepository = fileRepository;
        this.userService = userService;
        this.eventService = eventService;
    }
    @Override
    public File getById(Integer id) {
        return fileRepository.getById(id);
    }

    @Override
    public List<File> getAll() {
        return fileRepository.getAll();
    }

    @Override
    public File create(File file) {
        return fileRepository.create(file);
    }

    @Override
    public File update(File file) {
        return fileRepository.update(file);
    }

    @Override
    public void deleteById(Integer id) {
        fileRepository.deleteById(id);
    }

    @Override
    public File uploadFile(FileInputStream is, Integer userId) {
        File file = new File();
        //TODO : get name from file
        //TODO: get path from properties
        //TODO: find user by userId
        file.setFileName("");
        file.setFilePath("");
        File savedFile = fileRepository.create(file);
        User user = userService.getById(userId);
        Event event = new Event();
        event.setUser(user);
        event.setFile(file);
        eventService.create(event);
        return savedFile;
    }
}
