# Transaction Authorization API 

# api-transaction-authorization-persistence

## Overview

This is a simple transaction authorization API built with Java 21, Spring Boot 3.3.2, and JPA with MySQL as the database. The primary function of this API is to authorize transactions based on the Merchant Category Code (MCC) of the transaction.

## Functionality

The API currently supports authorization for the following MCC categories:

* **FOOD**
* **MEAL**
* **CASH**

## API Endpoint

* **POST /api/v1/transactions/authorize**
    * This endpoint receives a transaction request containing details like account number, total amount, MCC, and merchant name.
    * It processes the request and returns a response indicating whether the transaction was authorized or not.

## Technologies Used

* **Java 21**
* **Spring Boot 3.3.2**
* **JPA (Java Persistence API)**
* **MySQL**

## Getting Started

1. **Prerequisites:**
   * Java 21 installed
   * MySQL server running
   * Maven (or your preferred build tool) installed

2. **Clone the repository:**

   ```bash
   git clone https://github.com/KleyvissonMatias/api-transaction-authorizathion-persistence.git

3. **Notes:**

   * Adjust the database username and password in application.yml for each local environment

