package com.saquib.ilma.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.pinecone.PineconeVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class AiConfig {
	
	

	@Bean
    public ChatClient chatClient(ChatModel chatModel) {
        return ChatClient.builder(chatModel)
            // Add any custom advisors if needed
            // .withAdvisor()
            .build();
    }
	
	@Bean
    public VectorStore pineconeVectorStore(EmbeddingModel embeddingModel,
                                           @Value("${spring.ai.vectorstore.pinecone.api-key}") String apiKey,
                                           @Value("${spring.ai.vectorstore.pinecone.index-name}") String indexName) {
        return PineconeVectorStore.builder(embeddingModel)
                .apiKey(apiKey)
                .indexName(indexName)
                .build();
    }
	
}
