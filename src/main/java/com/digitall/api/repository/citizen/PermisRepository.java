package com.digitall.api.repository.citizen;

import com.digitall.api.model.citizen.Permis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermisRepository extends JpaRepository<Permis,Long> {
    public List<Permis> findByCitizen(int citizen);
}
