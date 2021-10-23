package com.digitall.api.service.document.impl;

import com.digitall.api.model.document.Folder;
import com.digitall.api.repository.document.FolderRepository;
import com.digitall.api.service.document.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderServiceImpl implements FolderService {
    @Autowired
    private FolderRepository folderRepository;
    @Override
    public List<Folder> findFolderByCitizen(String citizen) throws Exception{
        try {
            return this.folderRepository.findByCitizen(Integer.valueOf(citizen));
        }
        catch (Exception e){
            throw e;
        }
    }
}
