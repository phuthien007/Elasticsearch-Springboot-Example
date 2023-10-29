package com.example.esdemo.repository.es;

import com.example.esdemo.entity.Document;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ESDocumentRepository extends ElasticsearchRepository<Document, String> {
}
