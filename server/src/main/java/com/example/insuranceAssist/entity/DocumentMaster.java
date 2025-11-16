package com.example.insuranceAssist.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "document_master")
@Data
@RequiredArgsConstructor
public class DocumentMaster {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "data", columnDefinition = "LONGBLOB")
    private byte[] data;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "claim_id", nullable = false)
    private ClaimMaster claimId;

    public DocumentMaster(String name, String type, byte[] data, ClaimMaster claimId, LocalDateTime uploadedAt) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.claimId = claimId;
        this.uploadedAt = uploadedAt;
    }
}
