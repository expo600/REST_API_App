package com.ryzhov_andrei.rest_api.service;

import com.ryzhov_andrei.rest_api.model.File;

import java.io.FileInputStream;

public interface FileService extends GenericService<File, Integer> {
    File uploadFile(FileInputStream is, Integer userId);
}
