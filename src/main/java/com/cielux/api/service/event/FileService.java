package com.cielux.api.service.event;

import com.cielux.api.repository.model.File;
import com.cielux.api.Model.Execptation.EntityNotFoundException;
import com.cielux.api.repository.FileRepository;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class FileService {
    private static final String uploadDir = System.getenv("uploadDir");
    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File uploadFileDetails(MultipartFile file, String name, String type, BigInteger size, Timestamp creation_date) throws IOException {

        Path filePath = Path.of(uploadDir + name);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        try {
            File fileDetails = new File();
            fileDetails.setName(name);
            fileDetails.setType(type);
            fileDetails.setSize(size);
            fileDetails.setCreation_date(creation_date);
            fileDetails.setModification_date(Timestamp.from(Instant.now()));

            fileDetails = fileRepository.save(fileDetails);


            return fileDetails;
        } catch (Exception e) {
            throw new RuntimeException("Error with uploading file: " + e.getMessage());
        }
    }

    public Resource downloadFile(String fileName) throws MalformedURLException {
        Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists()) {
            return resource;
        } else {
            throw new RuntimeException("File not found: " + fileName);
        }
    }

    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    public File getFileByName(String fileName) throws EntityNotFoundException {
        return fileRepository.findByNameAndUserId(fileName)
                .orElseThrow(() -> new EntityNotFoundException("File not found with name " + fileName));
    }


}
