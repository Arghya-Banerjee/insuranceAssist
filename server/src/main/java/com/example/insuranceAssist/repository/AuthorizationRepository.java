package com.example.insuranceAssist.repository;

import com.example.insuranceAssist.entity.ClaimMaster;
import com.example.insuranceAssist.entity.UserMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorizationRepository extends JpaRepository<ClaimMaster, UUID> {
    List<ClaimMaster> findAllByAgent(UserMaster agent);

    List<ClaimMaster> findAllByClient(UserMaster client);

    Page<ClaimMaster> findAllByAgent(UserMaster agent, Pageable pageable);

    Page<ClaimMaster> findAllByClient(UserMaster client, Pageable pageable);
}
