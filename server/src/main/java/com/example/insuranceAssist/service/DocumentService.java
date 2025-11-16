package com.example.insuranceAssist.service;

import com.example.insuranceAssist.dto.DocumentResponseDTO;
import com.example.insuranceAssist.entity.ClaimMaster;
import com.example.insuranceAssist.entity.DocumentMaster;
import com.example.insuranceAssist.exception.ClaimNotFoundException;
import com.example.insuranceAssist.repository.AuthorizationRepository;
import com.example.insuranceAssist.repository.DocumentRepository;
import com.example.insuranceAssist.utils.DocumentUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final AuthorizationRepository authorizationRepository;

    public DocumentService(DocumentRepository documentRepository, AuthorizationRepository authorizationRepository) {
        this.documentRepository = documentRepository;
        this.authorizationRepository = authorizationRepository;
    }

    public UUID uploadDocument(MultipartFile file, UUID claimId) throws IOException, ClaimNotFoundException {

        byte[] fileBytes = file.getBytes();

        ClaimMaster claim = authorizationRepository.findById(claimId)
                .orElseThrow(() -> new ClaimNotFoundException("Claim not found with id: " + claimId));

        byte[] dataToStore;
        if(file.getContentType().equalsIgnoreCase("application/pdf")){
            dataToStore = fileBytes;
        }
        else{
            dataToStore = DocumentUtils.compressImage(fileBytes);
        }

        DocumentMaster doc = new DocumentMaster(
                 file.getName(),
                 file.getContentType(),
                 dataToStore,
                 claim,
                 LocalDateTime.now()
        );

        DocumentMaster document = documentRepository.save(doc);
        return document.getId();

    }

    public List<DocumentResponseDTO> getDocuments(UUID claimId) throws ClaimNotFoundException {

        ClaimMaster claim = authorizationRepository.findById(claimId)
                .orElseThrow(() -> new ClaimNotFoundException("Claim not found with id: " + claimId));

        List<DocumentMaster> docs = documentRepository.findByClaimId(claim);

        List<DocumentResponseDTO> response = new ArrayList<>();
        for(DocumentMaster doc: docs) {
            DocumentResponseDTO docDTO = new DocumentResponseDTO(
                    doc.getId(),
                    doc.getName(),
                    doc.getType(),
                    doc.getData()
            );
            response.add(docDTO);
        }
        return response;

    }
}
