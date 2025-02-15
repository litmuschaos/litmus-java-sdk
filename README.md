# Litmus Java SDK

[![Slack Channel](https://img.shields.io/badge/Slack-Join-purple)](https://slack.litmuschaos.io)
[![GitHub issues](https://img.shields.io/github/issues/litmuschaos/litmus-go)](https://github.com/litmuschaos/litmus-go/issues)
[![Twitter Follow](https://img.shields.io/twitter/follow/litmuschaos?style=social)](https://twitter.com/LitmusChaos)
[![YouTube Channel](https://img.shields.io/badge/YouTube-Subscribe-red)](https://www.youtube.com/channel/UCa57PMqmz_j0wnteRa9nCaw)
<br><br>

## **Introduction**

The Litmus Java SDK makes it easy to communicate with Litmus’ internal servers.

## Requirements

This library requires Java 17 or later.

Litmus version 3.13.0 or later.

## Installation

### Gradle example

```groovy
implementation 'io.litmuschaos:litmus-sdk:<VERSION>'
```

### Maven example

```xml
<dependency>
    <groupId>io.litmuschaos</groupId>
    <artifactId>litmus-sdk</artifactId>
    <version>VERSION</version>
</dependency>
```

## LitmusClient

LitmusClient contains simple, easy-to-use interface for making requests to litmus internal servers.

```java
import io.litmuschaos.LitmusClient;
import io.litmuschaos.request.UserCreateRequest;
import io.litmuschaos.response.UserResponse;

String host = "http://localhost:3000"; // LitmusChaos frontend url
String token = "eyJhbGciOiJIUzUxMiIsInR..." // api token

LitmusClient litmusClient = new LitmusClient(host, token);

UserCreateRequest request = UserCreateRequest.builder()
         .username("litmus")
         .password("passwd")
         .role("user")
         .email("test@litmus.com")
         .build();

UserResponse response = litmusClient.createUser(request);
```

You can customize httpClient configuration by injecting `LitmusConfig` class.

```java
LitmusConfig config = new LitmusConfig();

config.setHttpClientReadTimeoutMillis(1000L);
config.setHttpClientWriteTimeoutMillis(1000L);
config.setHttpClientConnectTimeoutMillis(1000L);
        
LitmusClient litmusClient = new LitmusClient("host", "token", config);

// if you don't need to set custom properties, just pass host and token. 
// Default configurations are applied.
LitmusClient litmusClient = new LitmusClient("host", "token");
```

## How to use ProjectionRoot

You need to understand `projectionRoot` for using Litmus Java SDK well. When you access to litmus graphQL backend server by SDK, you can filter response field by projectionRoot.

```java
GetEnvironmentGraphQLQuery query = new GetEnvironmentGraphQLQuery.Builder()
      .projectID("567ccf04-7195-4311-a215-0803fe5e93f6")
      .environmentID("test-environments")
      .build();
        
GetEnvironmentProjectionRoot projectionRoot = new GetEnvironmentProjectionRoot<>()
      .projectID()
      .createdBy().userID().username().root()
      .name()
      .updatedBy().userID().username().root();

Environment response = litmusClient.getEnvironment(query, projectionRoot);
``` 
litmusClient only return fields that selected by projectionRoot.
```
{
  projectID="567ccf04-7195-4311-a215-0803fe5e93f6",
  name="test",
  createdBy={
    userID="567ccf04-7195-4311",
    username="test-user",
    email=null       // not selected
  }
  description=null,   // not selected
  tags=null,          // not selected
  ...
}
```

ProjectionRoot is tree data structure, so you can explore object graph by `parent()` and `root()` method.

- parent() : move to upper position
- root() : move to root position

```
{
  "name": "test",
  "faultName": "test fault",
  "executedByExperment": {
    "experimentID": "test-id",
    "experimentName": "test-experiment",
    "updatedBy": {
      "username": "test-user", // If you call parent() here, you can access to experimentName field
      "email": "test@litmus.com" // If you call root() here, you can access to faultName field
    }
  }
}
```

## **Sample code**

This project contains the following [sample code](https://github.com/litmuschaos/litmus-java-sdk/blob/master/src/test/java/io/litmuschaos/LitmusClientTest.java)

## **Contributing**

We'd love to have you contribute! Please refer to [CONTRIBUTING](./CONTRIBUTING.md) for details.

## License

Here is a copy of the License: [License](./LICENSE)