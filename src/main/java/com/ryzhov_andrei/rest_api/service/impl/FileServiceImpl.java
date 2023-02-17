package com.ryzhov_andrei.rest_api.service.impl;

import com.ryzhov_andrei.rest_api.model.File;
import com.ryzhov_andrei.rest_api.repository.FileRepository;
import com.ryzhov_andrei.rest_api.repository.impl.FileRepositoryImpl;
import com.ryzhov_andrei.rest_api.service.FileService;

import java.util.List;

public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    public FileServiceImpl() {
        this.fileRepository = new FileRepositoryImpl();
    }

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
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
}
