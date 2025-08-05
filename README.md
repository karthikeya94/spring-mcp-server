````markdown name=README.md
# spring-mcp-server

A Java-based Spring Boot application for interacting with Jira APIs, providing essential server-side capabilities for Jira automation and management.

## Overview

This project is built using Java and Spring Boot. Its primary purpose is to facilitate integration with Jira via REST APIs, enabling users to automate tasks such as fetching issues assigned to a user, retrieving comments, and adding comments to Jira issues.

## Features

- **Jira Issue Management**: 
  - Fetch issues assigned to a specific user, sorted by priority and update time.
  - Retrieve all comments for a given Jira issue.
  - Add comments to Jira issues.

- **Spring Boot Architecture**: 
  - Uses service classes and dependency injection for modular design.
  - Easily extendable for other Jira operations.

- **REST Client Integration**:
  - Interacts with Jira using basic authentication and REST endpoints.
  - Handles API calls to search, fetch, and update Jira issues.

## Getting Started

### Prerequisites

- Java 17 or above
- Maven or Gradle
- Access to a Jira instance with an API token

### Installation

1. **Clone the repository**

    ```bash
    git clone https://github.com/karthikeya94/spring-mcp-server.git
    cd spring-mcp-server
    ```

2. **Configure Jira Credentials**

   Set the following properties in your `application.properties` or environment:

   ```
   jira.base-url=<YOUR_JIRA_BASE_URL>
   jira.username=<YOUR_JIRA_USERNAME>
   jira.api-token=<YOUR_JIRA_API_TOKEN>
   ```

3. **Build and Run**

    ```bash
    ./mvnw spring-boot:run
    ```
    or
    ```bash
    ./gradlew bootRun
    ```

## Usage

The core functionality is provided through the `JiraService`:

- **Get issues assigned to the user**
- **Retrieve comments for a Jira issue**
- **Add a comment to a Jira issue**

Service methods can be called directly or exposed via additional REST controllers as needed.

Example methods in `JiraService`:

```java
public String getIssuesAssigned();
public String getCommentsForGivenIssue(String issueKey);
public String addCommentToGivenIssueKey(String issueKey, String comment);
```

## Extending

You can add more Jira automation features by expanding the `JiraService` or adding new services/controllers.

## License

This project is licensed under the [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## Author

- [karthikeya94](https://github.com/karthikeya94)

## Repository

- [spring-mcp-server on GitHub](https://github.com/karthikeya94/spring-mcp-server)
````
