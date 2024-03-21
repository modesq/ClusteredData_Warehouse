# FX Deal Management System Documentation

## Overview

The FX Deal Management System is a software application developed by Mahmoud for Bloomberg, aimed at facilitating the
analysis of foreign exchange deals (FX deals). The system allows users to input FX deal details, validate them, and
persist them into a database for further analysis.

## Deployment

1. Run the bash script using `bash build_docker.sh`.
2. Run the command `docker-compose up`.

## Components

The system consists of the following main components:

1. **Model**: Contains the FXDeal class representing the structure of an FX deal, including its unique ID, currency
   codes, timestamp, and amount.

2. **DAO (Data Access Object)**: Provides an interface for accessing and manipulating FX deals in the database. Includes
   methods for inserting, selecting, updating, and deleting FX deals.

3. **Service**: Implements business logic and acts as an intermediary between the controller and DAO. Validates FX deal
   data before interacting with the database.

4. **Datasource Configuration**: Configures the datasource for connecting to the database, specifically for PostgreSQL.

5. **Tests**: Contains unit tests for ensuring the proper functionality of the service layer methods.

## Workflow

1. **Input**: Users input FX deal details including a unique ID, currency codes, timestamp, and deal amount.

2. **Validation**: The system validates the input data, ensuring that all required fields are present and in the correct
   format. Additionally, it checks that the deal amount is a positive value.

3. **Persistence**: Validated FX deal data is persisted into the configured database, ensuring that duplicate deals are
   not imported.

4. **Retrieval**: Users can retrieve FX deals from the database based on their unique IDs or query for all FX deals
   stored.

5. **Update and Delete**: Users can update existing FX deals with new data or delete them from the database.

## Technologies Used

- **Java**: The primary programming language used for developing the application.
- **Spring Framework**: Utilized for dependency injection, transaction management, and overall application structure.
- **PostgreSQL**: The chosen database management system for storing FX deal data.
- **Docker**: Employed specifically for containerizing the PostgreSQL database, facilitating easy deployment and
  management of the database instance.
- **JUnit and Mockito**: Used for writing unit tests to ensure the reliability of the application.

## Deployment

The application can be deployed using Docker Compose, which sets up the necessary environment including the database and
the application itself.

## Error Handling

Proper error and exception handling mechanisms are implemented throughout the application to provide meaningful error
messages and prevent system crashes.

## Logging

The system utilizes logging to record important events and errors, aiding in troubleshooting and monitoring.

## Unit Testing

Comprehensive unit tests are written for each service method to verify their correctness and ensure sufficient code
coverage.

## Author

Mahmoud Qunbor.

---