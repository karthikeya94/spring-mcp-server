package com.example.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Base64;
import java.util.Map;

@Service
public class JiraService {
    private final String baseUrl;
    private final String userName;
    private final String apiToken;

    private final RestClient restClient;

    public JiraService(@Value("${jira.base-url}") String baseUrl, @Value("${jira.username}") String userName, @Value("${jira.api-token}") String apiToken) {
        this.baseUrl = baseUrl;
        this.userName = userName;
        this.apiToken = apiToken;
        String auth = userName+":"+apiToken;
        String encoded = Base64.getEncoder().encodeToString(auth.getBytes());
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Authorization","Basic "+encoded)
                .defaultHeader("Accept", "application/json")
                .build();
    }
    
    @Tool(description = "get issues assigned to a user")
    public String getIssuesAssigned(){
        String jql = String.format("assignee=\"%s\" ORDER BY priority ASC, updated DESC",this.userName);
        String path = String.format("/rest/api/2/search?jql=%s&fields=key,summary,status,priority,assignee,created,updated,description,issueType&maxResults=50",jql);
        System.out.println(path);
        return restClient.get()
                .uri(path,"")
                .retrieve()
                .body(String.class);
    }

    @Tool(description = "get comments for jira id")
    public String getCommentsForGivenIssue(@ToolParam(description = "jira key ex: KAN-4") String issueKey){
        return restClient.get()
                .uri("/rest/api/2/issue/{key}/comment",issueKey)
                .retrieve()
                .body(String.class);
    }

    @Tool(description = "add comment to issue")
    public String addCommentToGivenIssueKey(@ToolParam(description = "issue key ex: KAN-4") String issueKey, @ToolParam(description = "comment text to add in jira") String comment){
        return restClient.post()
                .uri("/rest/api/2/issue/{key}/comment",issueKey)
                .body(Map.of("body",comment))
                .retrieve()
                .body(String.class);
    }
}
