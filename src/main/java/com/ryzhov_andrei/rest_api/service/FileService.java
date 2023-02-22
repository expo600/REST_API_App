package com.ryzhov_andrei.rest_api.service;

import com.ryzhov_andrei.rest_api.model.File;

import java.io.InputStream;

public interface FileService extends GenericService<File, Integer> {

    File uploadFile(InputStream inputStream, Integer userId);
}
