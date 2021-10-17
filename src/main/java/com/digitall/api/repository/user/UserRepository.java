package com.digitall.api.repository.user;

import com.digitall.api.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    public List<User> findByLogin(String login);
}
