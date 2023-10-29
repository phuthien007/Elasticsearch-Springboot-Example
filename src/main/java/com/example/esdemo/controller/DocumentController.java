package com.example.esdemo.controller;

import com.example.esdemo.entity.Document;
import com.example.esdemo.service.ESDocumentService;
import com.example.esdemo.service.OcrService;
import com.example.esdemo.service.dto.DocumentDTO;
import com.example.esdemo.service.dto.OcrResult;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/document")
public record DocumentController(ESDocumentService esDocumentService, OcrService ocrService) {

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

    @PostMapping(value = "/demo/ocr",
            consumes = {"multipart/form-data"})
    public ResponseEntity<OcrResult> upload(@RequestParam("file") MultipartFile file) throws IOException, TesseractException {
        return ResponseEntity.ok(ocrService.ocr(file));
    }


}
