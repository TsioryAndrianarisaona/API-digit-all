package com.digitall.api.repository.user;

import com.digitall.api.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public List<User> findByLogin(String login);
}
