package com.example.insuranceAssist.repository;

import com.example.insuranceAssist.entity.ClaimMaster;
import com.example.insuranceAssist.entity.DocumentMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentMaster, UUID> {

    List<DocumentMaster> findByClaimId(ClaimMaster claimId);
}
