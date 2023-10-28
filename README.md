# NoteHub

A Spring Boot application for managing notes.

## Description

NoteHub is a web-based application that allows users to create, update, retrieve, and delete notes. It uses Spring Boot for the backend, JPA for ORM, and PostgreSQL as its database.

## Features

-   Create a new note with a title, body, and pin status.
-   Update existing notes.
-   Retrieve all notes or a single note by its ID.
-   Delete a note by its ID.

## Prerequisites

-   Java 8 or higher.
-   Docker and Docker Compose.
-   An IDE like IntelliJ IDEA or Eclipse.

## Setup

### Database Setup with Docker

1.  Navigate to the root directory of the project.
2.  Run the following command to start the PostgreSQL container:

bashCopy code

`docker-compose up` 

This will start a PostgreSQL instance on port `5439`. The default user is `postgres` and the password is also `postgres`.

### Running the Application

1.  Import the project into your preferred IDE.
2.  Build and run the application.
3.  The API endpoints will be available at `http://localhost:8080/api/v1/notehub`.

## Endpoints

-   `GET /api/v1/notehub`: Fetch all notes.
-   `GET /api/v1/notehub/{id}`: Fetch a note by its ID.
-   `POST /api/v1/notehub`: Create a new note.
-   `PUT /api/v1/notehub/{id}`: Update a note by its ID.
-   `DELETE /api/v1/notehub/{id}`: Delete a note by its ID.

## Contribution

If you'd like to contribute, please fork the repository and use a feature branch. Pull requests are warmly welcome.
