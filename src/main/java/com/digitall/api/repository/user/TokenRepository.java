package com.digitall.api.repository.user;

import com.digitall.api.model.user.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    public Token findTokenByToken(String token);
}
