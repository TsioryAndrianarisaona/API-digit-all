package com.digitall.api.repository.document;

import com.digitall.api.model.document.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {
    public List<Document> findByNiveau(int niveau);

    @Query("SELECT d from Document d left join FolderDocument fd on d.id = fd.document WHERE fd.folder = ?1")
    public List<Document> findByFolder(int folder);
}
