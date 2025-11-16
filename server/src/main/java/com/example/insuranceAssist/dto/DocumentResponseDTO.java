package com.example.insuranceAssist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class DocumentResponseDTO {

    private UUID id;
    private String name;
    private String type;
    private byte[] data;

}
