package com.example.esdemo.service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TesseractService {

    @Test
    public void test() throws TesseractException {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("src/main/resources/static/tessdata");
        tesseract.setLanguage("vie");
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);
        try {

            FileInputStream fileInputStream = new FileInputStream("src/main/resources/static/ngoaingu.doc");

            XWPFDocument document = new XWPFDocument(fileInputStream);
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);
            StringBuilder text = new StringBuilder();
            text.append(extractor.getText());
            // Get the Tesseract instance
//            ITesseract tesseract = new Tesseract();
//            tesseract.setDatapath("path/to/tessdata"); // Set the path to the tessdata folder containing language data

            // Iterate through pictures in the document and extract text from images
            List<XWPFPictureData> pictures = document.getAllPictures();
            int imageIndex = 1;
            for (XWPFPictureData pictureData : pictures) {
                byte[] bytes = pictureData.getData();
                String extension = pictureData.suggestFileExtension();
                String imageName = "image" + imageIndex + "." + extension;

                try (FileOutputStream fos = new FileOutputStream(imageName)) {
                    fos.write(bytes);
                    String imageText = tesseract.doOCR(new File(imageName));
                    text.append("Image Text:\n");
                    text.append(imageText);
                    text.append("\n");
                } catch (TesseractException e) {
                    e.printStackTrace();
                }

                imageIndex++;
            }

            fileInputStream.close();
            System.out.println("result: " + text.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
