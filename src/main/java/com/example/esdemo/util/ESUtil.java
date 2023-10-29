package com.example.esdemo.util;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import com.example.esdemo.entity.Document;
import com.example.esdemo.service.dto.DocumentDTO;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class ESUtil {

    private final ElasticsearchClient elasticsearchClient;

    private final String dataDir = "src/main/resources/static";

    public ESUtil(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    // check index exist
    // if not exist, create index
    // if exist, do nothing
    public void checkIndexExist(String indexName) throws IOException {
        if (!indexExist(indexName)) {
            createIndex(indexName);
        }

    }

    // check index exist
    private boolean indexExist(String indexName) throws IOException {
        return elasticsearchClient.indices().exists(b -> b.index(indexName)).value();
    }

    private void createIndex(String indexName) throws IOException {
        InputStream input = this.getClass()
                .getResourceAsStream("/static/es-setting.json");

        CreateIndexRequest req = CreateIndexRequest.of(b -> b
                .index(indexName)
                .withJson(input)
        );

        elasticsearchClient.indices().create(req);
    }

    public IndexResponse createDocument(Document documentDTO, String indexName) throws IOException {
        IndexResponse indexResponse = elasticsearchClient.index(b -> b
                .index(indexName)
                .id(documentDTO.getId().toString())
                .document(documentDTO)
        );

        return indexResponse;
    }

}
