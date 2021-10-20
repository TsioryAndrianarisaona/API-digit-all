package com.digitall.api.repository.declaration;

import com.digitall.api.model.declaration.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeclarationRepository extends JpaRepository<Declaration,Long> {
    public List<Declaration> findByCitizenAndDigitallUser(int citizen, int digitallUser);
    public List<Declaration> findByCitizen(int citizen);
}
