package com.example.esdemo.service.mapper;


import lombok.Data;
import org.springframework.stereotype.Service;

@Service
public class DocumentMapper {
    public  com.example.esdemo.entity.Document toEntity(com.example.esdemo.service.dto.DocumentDTO dto) {
        if (dto == null) {
            return null;
        } else {
            com.example.esdemo.entity.Document document = new com.example.esdemo.entity.Document();
            document.setId(dto.getId());
            document.setTitle(dto.getTitle());
            document.setContent(dto.getContent());
            return document;
        }
    }

    public  com.example.esdemo.service.dto.DocumentDTO toDto(com.example.esdemo.entity.Document entity) {
        if (entity == null) {
            return null;
        } else {
            com.example.esdemo.service.dto.DocumentDTO documentDTO = new com.example.esdemo.service.dto.DocumentDTO();
            documentDTO.setId(entity.getId());
            documentDTO.setTitle(entity.getTitle());
            documentDTO.setContent(entity.getContent());
            return documentDTO;
        }
    }
}
