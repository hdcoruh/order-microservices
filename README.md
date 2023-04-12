
# Project Title

Order Microservices.
  


## API Reference

Refer to postman collection for internal services and services exposed externally via the api gateway. Authentication is done in the gateway, so for requests made to the gateway a token must be obtained from oauth server(keycloak).


## Run Locally

Clone the project

```bash
  git clone https://link-to-project
```

Go to the project directory

```bash
  cd my-project
```

Run databases, keycloak, broker and zipkin

```bash
  docker-compose -f ./docker-compose.yml up
```

Start the servers


