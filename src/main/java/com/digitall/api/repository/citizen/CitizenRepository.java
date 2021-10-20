package com.digitall.api.repository.citizen;

import com.digitall.api.model.citizen.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen,Long> {
    public Citizen findByQrCode(String qrCode);
}
