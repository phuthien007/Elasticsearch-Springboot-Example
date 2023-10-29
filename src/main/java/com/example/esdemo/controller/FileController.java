package com.example.esdemo.controller;


import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RestController
public class FileController {
    @Autowired
    private Tesseract tesseract;

    @PostMapping(value = "/api/pdf/extractText", consumes = "multipart/form-data")
    public @ResponseBody ResponseEntity<String>
    extractTextFromPDFFile(@RequestParam("file") MultipartFile file) {
        try {
            // Load file into PDFBox class
            PDDocument document =  PDDocument.load(file.getBytes());
            PDFTextStripper stripper = new PDFTextStripper();
            String strippedText = stripper.getText(document);

            // Check text exists into the file
            if (strippedText.trim().isEmpty()){
                strippedText = extractTextFromScannedDocument(document);
            }

            JSONObject obj = new JSONObject();
            obj.put("fileName", file.getOriginalFilename());
            obj.put("text", strippedText.toString());

            return new ResponseEntity<String>(obj.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private String extractTextFromScannedDocument(PDDocument document) throws IOException, TesseractException {

        // Extract images from file
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        StringBuilder out = new StringBuilder();

        for (int page = 0; page < document.getNumberOfPages(); page++)
        {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

            // Create a temp image file
            File temp = File.createTempFile("tempfile_" + page, ".png");
            ImageIO.write(bim, "png", temp);

            // TODO: Apply here OCR logic
            String result = tesseract.doOCR(temp);
            out.append(result);
            // Delete temp file
            temp.delete();

        }

        return out.toString();

    }

}
