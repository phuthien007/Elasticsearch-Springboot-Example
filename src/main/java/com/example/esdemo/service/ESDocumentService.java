package com.example.esdemo.service;

import co.elastic.clients.elasticsearch._types.Result;
import com.example.esdemo.entity.Document;
import com.example.esdemo.repository.es.ESDocumentRepository;
import com.example.esdemo.service.dto.DocumentDTO;
import com.example.esdemo.service.mapper.DocumentMapper;
import com.example.esdemo.util.ESUtil;
import org.springframework.stereotype.Service;

@Service
public record ESDocumentService(ESDocumentRepository esDocumentRepository, DocumentMapper documentMapper, ESUtil esUtil,
                                FilestorageService filestorageService) {

    // save document to elastic search
    public void saveDocument(DocumentDTO document) {

        // read content file in document and set to content
        try {
            String fileName = filestorageService.saveFile(document.getFile());
            System.out.println("data: " + filestorageService.getContentFile(fileName));
            document.setContent(filestorageService.getContentFile(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        esDocumentRepository.save(documentMapper.toEntity(document));
    }

    // get all documents from elastic search
    public Iterable<Document> getAllDocuments() {
        return esDocumentRepository.findAll();
    }

    //    create index
    public void createIndex(String indexName) {
        try {
            esUtil.checkIndexExist(indexName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Result createDocument(DocumentDTO documentDTO, String indexName) {
        try {
            return esUtil.createDocument(documentMapper.toEntity(documentDTO), indexName).result();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
