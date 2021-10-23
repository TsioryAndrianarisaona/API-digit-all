package com.digitall.api.repository.document;

import com.digitall.api.model.document.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder,Long> {
    public List<Folder> findByCitizen(int citizen);
}
