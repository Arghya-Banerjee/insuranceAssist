package com.example.insuranceAssist.controller;

import com.example.insuranceAssist.dto.DocumentResponseDTO;
import com.example.insuranceAssist.exception.ClaimNotFoundException;
import com.example.insuranceAssist.service.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/private/document")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/upload/{claimId}")
    public ResponseEntity<?> uploadDocument(@PathVariable UUID claimId, @RequestParam("file")MultipartFile file) throws IOException, ClaimNotFoundException {
        UUID docId = documentService.uploadDocument(file, claimId);
        return new ResponseEntity<>(docId, HttpStatus.OK);
    }

    @GetMapping("/download/{claimId}")
    public ResponseEntity<?> getDocuments(@PathVariable UUID claimId) throws ClaimNotFoundException {
        List<DocumentResponseDTO> response = documentService.getDocuments(claimId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
