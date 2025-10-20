# AIS Decoder

A Spring Boot REST API service for decoding AIS (Automatic Identification System) NMEA messages.

## Overview

This service provides a simple HTTP API to decode AIS messages from NMEA format into structured JSON data. It uses the [aismessages](https://github.com/tbsalling/aismessages) library to parse and decode AIS messages.

AIS (Automatic Identification System) is an automatic tracking system used on ships and by vessel traffic services (VTS) for identifying and locating vessels by electronically exchanging data with other nearby ships, AIS base stations, and satellites.

## Features

- REST API endpoint for decoding AIS NMEA messages
- Support for multi-line NMEA messages (message fragments)
- Returns decoded AIS messages as JSON
- Built with Spring Boot for easy deployment
- Docker support for containerized deployment

## Prerequisites

- Java 8 or higher
- Gradle (included via wrapper)

## Building the Project

Build the project using the Gradle wrapper:

```bash
./gradlew build
```

This will compile the code, run tests, and create a JAR file in `build/libs/`.

## Running the Service

### Using Gradle

```bash
./gradlew bootRun
```

### Using the JAR file

```bash
java -jar build/libs/aisdecoder-0.0.1-SNAPSHOT.jar
```

### Using Docker

Build the Docker image:

```bash
./gradlew build
docker build -t aisdecoder .
```

Run the container:

```bash
docker run -p 8080:8080 aisdecoder
```

The service will start on port 8080 by default.

## API Usage

### Decode AIS Messages

**Endpoint:** `POST /decode`

**Content-Type:** `application/json`

**Request Body:** JSON array of NMEA message strings

**Example:**

```bash
curl -X POST http://localhost:8080/decode \
  -H "Content-Type: application/json" \
  -d '["!AIVDM,1,1,,A,13HOI:0P0000VOHLCnHQKwvL05Ip,0*23"]'
```

**Response:** JSON array of decoded AIS message objects

The structure of the response depends on the type of AIS message being decoded. Each message type has different fields according to the AIS specification.

### Example with Multi-Fragment Message

Some AIS messages are split across multiple NMEA sentences:

```bash
curl -X POST http://localhost:8080/decode \
  -H "Content-Type: application/json" \
  -d '[
    "!AIVDM,2,1,3,B,55?MbV02>H97ac<H4F220<h6@E:22222222220t1@H:36ho0Ht50000000000,0*09",
    "!AIVDM,2,2,3,B,000000000000000,2*27"
  ]'
```

## Technology Stack

- **Spring Boot 2.0.5** - Application framework
- **aismessages 2.2.3** - AIS message parsing library
- **Java 8** - Programming language
- **Gradle** - Build tool

## Project Structure

```
aisdecoder/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── dk/tbsalling/ais/decoder/aisdecoder/
│   │   │       ├── AisdecoderApplication.java    # Spring Boot main application
│   │   │       ├── AisdecoderController.java     # REST controller
│   │   │       └── AisdecoderService.java        # AIS decoding service
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── dk/tbsalling/ais/decoder/aisdecoder/
│               └── AisdecoderApplicationTests.java
├── build.gradle                                    # Gradle build configuration
├── Dockerfile                                      # Docker container configuration
└── README.md                                       # This file
```

## License

This project is maintained by Thomas Borg Salling (tbsalling@tbsalling.dk).

## Related Projects

- [aismessages](https://github.com/tbsalling/aismessages) - The underlying AIS message parsing library

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.
