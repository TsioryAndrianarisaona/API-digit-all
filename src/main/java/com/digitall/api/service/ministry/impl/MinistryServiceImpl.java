package com.digitall.api.service.ministry.impl;

import com.digitall.api.model.ministry.Ministry;
import com.digitall.api.model.user.User;
import com.digitall.api.repository.ministry.MinistryRepository;
import com.digitall.api.service.ministry.MinisitryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MinistryServiceImpl implements MinisitryService {
    @Autowired
    private MinistryRepository ministryRepository;
}
