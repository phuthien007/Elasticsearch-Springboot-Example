package com.example.esdemo.word;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;

import java.io.FileInputStream;
import java.util.List;

public class TextReader {
//    public static void main(String[] args) {
//        try {
//            FileInputStream fis = new FileInputStream("F:\\Project\\Learning\\Java\\elasticsearch\\ES-demo\\src\\main\\resources\\static\\ngoaingu.doc");
//            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
//            XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
//            System.out.println("body: " +extractor.getText());
//
//            XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(xdoc);
//
//            XWPFHeader header = policy.getDefaultHeader();
//            if (header != null) {
//                System.out.println("h: "+ header.getText());
//            }
//
//            XWPFFooter footer = policy.getDefaultFooter();
//            if (footer != null) {
//                System.out.println("f: "+ footer.getText());
//            }
//
//
//
//        } catch(Exception ex) {
//            ex.printStackTrace();
//        }
//    }

}
