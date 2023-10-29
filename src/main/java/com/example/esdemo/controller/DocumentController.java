package com.example.esdemo.controller;

import com.example.esdemo.entity.Document;
import com.example.esdemo.service.ESDocumentService;
import com.example.esdemo.service.dto.DocumentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/document")
public record DocumentController(ESDocumentService esDocumentService) {

    // save document to elastic search
    @PostMapping(
            consumes = {"multipart/form-data"}
    )
    public ResponseEntity<String> saveDocument(
            @ModelAttribute DocumentDTO document
    ) {
        esDocumentService.saveDocument(document);
        return ResponseEntity.ok("Document saved successfully");
    }

    @PostMapping(
            value = "/createDocument",
            consumes = {"multipart/form-data"}
    )
    public ResponseEntity<String> saveIndexDocument(
            @ModelAttribute DocumentDTO document
    ) {
        esDocumentService.createDocument(document, "demo");
        return ResponseEntity.ok("Document saved successfully");
    }

    @PostMapping(
            value = "/updateDocument/{id}",
            consumes = {"multipart/form-data"}
    )
    public ResponseEntity<String> updateIndexDocument(
            @ModelAttribute DocumentDTO document,
            @PathVariable String id
    ) {
        document.setId(id);
        esDocumentService.createDocument(document, "demo");
        return ResponseEntity.ok("Document saved successfully");
    }

    // get all documents from elastic search
    @GetMapping
    public ResponseEntity<Iterable<Document>> getAllDocuments() {
        return ResponseEntity.ok(esDocumentService.getAllDocuments());
    }

    @PostMapping("{indexName}")
    public ResponseEntity<String> createIndex(@PathVariable String indexName) {
        esDocumentService.createIndex(indexName);
        return ResponseEntity.ok("Index created successfully");
    }

}
