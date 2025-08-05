package com.example;

import com.example.service.JiraService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JiraMcpServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(JiraMcpServerApplication.class, args);
    }
    @Bean
    public ToolCallbackProvider weatherTools(JiraService jiraService) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(jiraService)
                .build();
    }
}
