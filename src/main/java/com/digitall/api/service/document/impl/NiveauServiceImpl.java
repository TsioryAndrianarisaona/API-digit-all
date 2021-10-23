package com.digitall.api.service.document.impl;

import com.digitall.api.model.document.Niveau;
import com.digitall.api.repository.document.NiveauRepository;
import com.digitall.api.service.document.NiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NiveauServiceImpl implements NiveauService {
    @Autowired
    private NiveauRepository niveauRepository;

    @Override
    public List<Niveau> getAllNiveau() throws Exception {
        return this.niveauRepository.findAll();
    }
}
