package com.digitall.api.service.document.impl;

import com.digitall.api.model.document.Document;
import com.digitall.api.model.document.FolderDocument;
import com.digitall.api.repository.document.DocumentRepository;
import com.digitall.api.repository.document.FolderDocumentRepository;
import com.digitall.api.service.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private FolderDocumentRepository folderDocumentRepository;
    @Override
    public List<Document> findByNiveau(int niveau) {
        return this.documentRepository.findByNiveau(niveau);
    }

    @Override
    public List<Document> findByFolder(String folder) {
        return this.documentRepository.findByFolder(Integer.valueOf(folder));
    }

    @Transactional
    @Override
    public boolean saveDocument(Map<String, Object> requestBody) throws Exception {
        try {
            System.out.println(requestBody);
            String folder=requestBody.get("folder").toString();
            List<Integer> docs=(List<Integer>) requestBody.get("document");

            for(Integer doc :docs){
                FolderDocument folderDocument=new FolderDocument();
                folderDocument.setDocument(Integer.valueOf(doc.toString()));
                folderDocument.setFolder(Integer.valueOf(folder));
                this.folderDocumentRepository.save(folderDocument);
            }
            return true;
        }
        catch (Exception e){
            throw e;
        }
    }
}
