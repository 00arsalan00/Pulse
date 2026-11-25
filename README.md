# Project Overview:

Pulse is being developed to address the need for centralized monitoring of industrial equipment.

The platform is designed around three main concepts:

Device Monitoring: Monitor device connectivity, health, operational status, and sensor readings.

Alert Management: Detect abnormal conditions and maintain an alert lifecycle.

Operational Insights: Analyze device performance, maintenance activities, and operational data.

# Technology Stack:

Java 21: Primary programming language

Spring Boot: Backend application framework

Spring Data JPA: Database persistence

Hibernate: ORM

PostgreSQL: Relational database

Maven: Build and dependency management

Lombok: Boilerplate code reduction

Jakarta Validation: Data validation

# Database Design

The initial database follows an OLTP (Online Transaction Processing) model.

Its purpose is to support day-to-day operations such as organization registration, factory management, device registration, telemetry storage, alert handling, and maintenance tracking.

# Database Entities

Entity:                  Responsibility

Organizations:           Stores organization information

Factories:               Stores factory details belonging to organizations

Users:                   Stores user accounts and organization associations

Devices:                 Stores registered industrial devices

Telemetry:               Stores sensor readings from devices

Device Events:           Records important device events

Device Logs:             Stores device-generated logs

Alert Rules:             Defines conditions for generating alerts

Alerts:                  Stores detected abnormal conditions

Alert Actions:           Tracks actions performed on alerts

Maintenance Records:     Stores device maintenance history

# Database Relationships

One organization can have multiple factories.

One organization can have multiple users.

One factory can contain multiple devices.

One device can generate multiple telemetry records, events, logs, alerts, and maintenance records.

One organization can define multiple alert rules.

One alert can have multiple alert actions.

Users can be assigned to alerts and perform maintenance activities.

Primary keys: UUID

ORM mapping: Jakarta Persistence (JPA) with Hibernate.

# Project Setup

Prerequisites

Install the following:
JDK 21, PostgreSQL, IntelliJ IDEA (recommended), Git

1. Clone the repository

git clone <YOUR_GITHUB_REPOSITORY_URL>
cd app

2. Create the PostgreSQL database

Open PostgreSQL or pgAdmin and execute:

CREATE DATABASE industrialpulse;

3. Configure database connection

Create or update:

src/main/resources/application.yml

server:
port: 8085

spring:
datasource:
url: jdbc:postgresql://localhost:5432/industrialpulse
username: postgres
password: ${DB_PASSWORD}
driver-class-name: org.postgresql.Driver

jpa:
hibernate:
ddl-auto: update
show-sql: true
open-in-view: false
properties:
hibernate:
format_sql: true

Set your PostgreSQL password as an environment variable.

Windows PowerShell:

$env:DB_PASSWORD="your_postgres_password"

Do not commit database passwords or other secrets to GitHub.

4. Build the project

Using the Maven Wrapper:

.\mvnw clean compile

5. Run the application

.\mvnw spring-boot:run

Alternatively, run AppApplication.java from IntelliJ IDEA.

The application runs at: http://localhost:8085

# Database Verification

After starting the application:

Open PostgreSQL using pgAdmin or DBeaver.

Connect to the local PostgreSQL server.

Open the industrialpulse database.

Navigate to Schemas → public → Tables.

Verify that the expected tables have been created.

Inspect primary keys, foreign keys, UUID column types, and relationships.

The database schema is managed by Hibernate during early development using:

spring:
jpa:
hibernate:
ddl-auto: update

Note: ddl-auto: update is intended for early local development. Database migrations will be introduced as the project matures.

