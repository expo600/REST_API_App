package com.ryzhov_andrei.rest_api.service.impl;

import com.ryzhov_andrei.rest_api.model.File;
import com.ryzhov_andrei.rest_api.repository.FileRepository;
import com.ryzhov_andrei.rest_api.repository.impl.FileRepositoryImpl;
import com.ryzhov_andrei.rest_api.service.FileService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class FileServiceImplTest extends TestCase {

    private FileRepository fileRepository = Mockito.mock(FileRepositoryImpl.class);
    private FileService serviceUnderTest = new FileServiceImpl(fileRepository);

    private List<File> getFiles() {
        return List.of(
                new File(1, "name_1", "path_1"),
                new File(2, "name_2", "path_2"),
                new File(3, "name_3", "path_3"),
                new File(4, "name_4", "path_4")
        );
    }

    private File getFile() {
        return new File(1, "name", "file/path");
    }

    @Test
    public void testGetById() {
        doReturn(getFile()).when(fileRepository).getById(anyInt());
        File file = serviceUnderTest.getById(1);
        assertEquals(file, getFile());
    }
    @Test
    public void testGetAll() {
        doReturn(getFiles()).when(fileRepository).getAll();
        List<File> files = serviceUnderTest.getAll();
        assertEquals(files.size(),getFiles().size());
    }
    @Test
    public void testCreate() {
        doReturn(getFile()).when(fileRepository).create(getFile());
        File file = serviceUnderTest.create(getFile());
        assertEquals(file, fileRepository.create(getFile()));
    }
    @Test
    public void testUpdate() {
        doReturn(getFile()).when(fileRepository).update(getFile());
        File file = serviceUnderTest.update(getFile());
        assertEquals(file, fileRepository.update(getFile()));
    }
    @Test
    public void testDeleteById() {
        fileRepository.deleteById(anyInt());
        verify(fileRepository).deleteById(anyInt());
    }
}