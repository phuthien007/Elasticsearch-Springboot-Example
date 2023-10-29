package com.example.esdemo.service.dto;

import lombok.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {

        private MultipartFile file;
        private String id;
        private String title;
        private String content;
}
