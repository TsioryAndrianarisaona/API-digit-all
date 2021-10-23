package com.digitall.api.service.document;

import com.digitall.api.model.document.Folder;

import java.util.List;

public interface FolderService {
    public List<Folder> findFolderByCitizen(String citizen) throws Exception;

}
