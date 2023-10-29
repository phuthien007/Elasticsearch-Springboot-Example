package com.example.esdemo.service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;

@ExtendWith(MockitoExtension.class)
public class TesseractService {

    @Test
    public void test() throws TesseractException {
        File image = new File("src/main/resources/Capture.PNG");
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("src/main/resources/static/tessdata");
        tesseract.setLanguage("vie");
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);
        String result = tesseract.doOCR(image);
        System.out.println(result);
    }


}
