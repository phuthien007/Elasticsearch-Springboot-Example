package com.example.esdemo.entity;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@org.springframework.data.elasticsearch.annotations.Document(indexName = "document")
@Setting(settingPath = "static/es-setting.json")
@Mapping(mappingPath = "static/mapping/mappings.json")
public class Document {

    private String id;
    private String title;
    private String content;
}
