package com.cielux.api.endpoint.rest.controller.model;

import com.cielux.api.repository.model.File;
import com.cielux.api.repository.model.Folder;
import com.cielux.api.repository.model.User;
import com.cielux.api.service.event.FolderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/folders")
@AllArgsConstructor
public class FolderController {
    private final FolderService folderService;

    @PostMapping("/create")

    public Folder createFolder(@RequestParam String folderName, @RequestParam Long userId) {
        return folderService.createFolder(folderName, userId);
    }

    @DeleteMapping("/delete/{folderId}")
    public void deleteFolder(@PathVariable Long folderId) {
        folderService.deleteFolder(folderId);
    }

    @GetMapping("/getAll/{userId}")
    public List<Folder> getAllFoldersByUserId(@PathVariable Long userId) {
        return folderService.getAllFoldersByUserId(userId);
    }

    @PostMapping("/addFile/{folderId}")
    public void addFileToFolder(@PathVariable Long folderId, @RequestBody File file) {
        folderService.addFileToFolder(folderId, file);
    }

}
