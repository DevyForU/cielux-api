package com.cielux.api.service.event;

import com.cielux.api.Model.Execptation.EntityNotFoundException;
import com.cielux.api.repository.FolderRepository;
import com.cielux.api.repository.model.File;
import com.cielux.api.repository.model.Folder;
import com.cielux.api.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FolderService {
    private static final String uploadDir = System.getenv("uploadDir");
    @Autowired
    private FolderRepository folderRepository;

    public Folder createFolder(String folderName, User user) {
        Folder folder = new Folder(folderName, uploadDir + folderName + "/", user);
        return folderRepository.save(folder);
    }

    public void deleteFolder(Long folderId) {
        folderRepository.deleteById(folderId);
    }

    public List<Folder> getAllFoldersByUserId(Long userId) {
        return folderRepository.findAllByUserId(userId);
    }
    public void addFileToFolder(Long folderId, File file) {
        Folder folder = folderRepository.findById(folderId).orElseThrow(() -> new RuntimeException("Dossier non trouvé"));
        folder.getFiles().add(file);

        folderRepository.save(folder);
    }
}
