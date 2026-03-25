# javax-validation Demo

A Spring Boot application demonstrating comprehensive Java Bean Validation (JSR 380 / Hibernate Validator) with custom validators, validation groups, and group sequences.

## Features

- **Custom Constraint Validators**: Domain-specific validation rules for email, nicknames, names, colors, and musician types
- **Cross-Field Validation**: Method-level constraint that validates multiple parameters together
- **Validation Groups**: Ordered group sequences for conditional, sequential validation
- **Global Exception Handling**: Structured error responses with field path and HTTP status code mapping
- **OpenAPI/Swagger Integration**: Code generation from OpenAPI spec

## Tech Stack

- Java 8
- Spring Boot 2.4.1
- Hibernate Validator (JSR 380)
- OpenAPI Generator (spring)
- Lombok
- H2 (in-memory database)

## Getting Started

### Prerequisites

- Java 8+
- Maven 3.6+

### Build

```bash
mvn clean install
```

### Run

```bash
mvn spring-boot:run
```

The application starts on `http://localhost:8080`.

## REST Endpoints

### `GET /musicians`

Validates query parameters using method-level validation with a group sequence.

| Parameter  | Constraint              | Group       |
|------------|-------------------------|-------------|
| `nickName` | Must contain "creative" | FirstGroup  |
| `email`    | Valid email format      | SecondGroup |
| `type`     | Valid musician type     | FourthGroup |

Additionally, `nickName` and `email` must start with the same letter (case-insensitive).

**Example:**
```
GET /musicians?nickName=alice_creative&email=alice@example.com&type=ESTRADA
```

### `POST /musicians`

Validates request body using `MusicianGroupSequence` (groups 1–7 in order).

**Request Body:**
```json
{
  "nickName": "alice_creative",
  "email": "alice@example.com",
  "musicianType": "POP",
  "costumes": []
}
```

### `GET /generated-musicians`

Generated endpoint from OpenAPI spec. Same validation patterns.

### `POST /generated-musicians`

Generated endpoint from OpenAPI spec with bean validation.

## Validation Error Response

Validation errors return a list of `Error` objects:

```json
[
  {
    "message": "invalid@email is invalid email",
    "field": "postMusician.musician.email",
    "date": "2024-01-01T12:00:00Z"
  }
]
```

HTTP status codes are mapped via the `StatusCode` attribute on each constraint:
- `BAD_REQUEST` (400) — default
- `UNPROCESSABLE_ENTITY` (422)

## Custom Validators

| Annotation             | Validates                                       |
|------------------------|-------------------------------------------------|
| `@ValidEmail`          | Email format (RFC-compatible regex)             |
| `@ValidNickName`       | Must contain the word "creative" (any case)     |
| `@ValidName`           | Alphabetic characters only                      |
| `@ValidColor`          | Configurable enum values (default: RED, YELLOW) |
| `@ValidMusicianType`   | Configurable enum values (default: POP, ROCK)   |
| `@NickNameAndEmailStartWithSameLetter` | Cross-parameter: first letters match (case-insensitive) |

## Validation Groups & Sequences

**MusicianGroupSequence**: FirstGroup → SecondGroup → ThirdGroup → FourthGroup → FifthGroup → SixthGroup → SeventhGroup

**MusicianMethodLevelGroupSequence**: FirstGroup → SecondGroup → ThirdGroup → FourthGroup

Validation stops at the first failing group, preventing cascading errors.

## Running Tests

```bash
mvn test
```
