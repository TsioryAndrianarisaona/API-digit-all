package com.digitall.api.service.document;

import com.digitall.api.model.document.Document;
import com.digitall.api.model.document.Folder;

import java.util.List;
import java.util.Map;

public interface DocumentService {
    public List<Document> findByNiveau(int niveau)throws Exception;
    public List<Document> findByFolder(String folder) throws Exception;
    public boolean saveDocument(Map<String,Object> requestBody) throws Exception;
}
