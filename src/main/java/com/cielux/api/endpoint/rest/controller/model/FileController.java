package com.cielux.api.endpoint.rest.controller.model;

import com.cielux.api.repository.model.File;
import com.cielux.api.service.event.FileService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FileController {
    private FileService fileService;

    @PostMapping("/upload")
    public File uploadFile(@RequestParam("file") MultipartFile file,
                           @RequestParam("name") String name,
                           @RequestParam("type") String type,
                           @RequestParam("size") BigInteger size,
                           @RequestParam("creation_date") Timestamp creation_date) throws IOException {
        return fileService.uploadFileDetails(file, name, type, size, creation_date);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws MalformedURLException {
        Resource resource = fileService.downloadFile(fileName);
        return ResponseEntity.ok()
                .body(resource);
    }

    @GetMapping("/all")
    public List<File> getAllFiles() {
        return fileService.getAllFiles();
    }

    @GetMapping("/{fileName}")
    public File getFileByName(@PathVariable String fileName) {
        return fileService.getFileByName(fileName);
    }
}
