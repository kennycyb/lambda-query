# Lambda Query

A fluent, type-safe query builder library for Java that provides an expressive DSL for building database queries using lambda expressions and criteria-based filtering.

## Project Structure

This project consists of two main modules:

### lambda-query

Core query builder framework providing:

- Abstract query builder implementation
- Fluent API interfaces for constructing queries
- Criteria collection and evaluation system
- Support for SELECT, UPDATE, and DELETE operations

### lambda-query-jpa

JPA-specific implementation providing:

- JPA-based query builders for common database operations
- Criteria implementations for various predicates (equals, greater than, less than, between, like, etc.)
- DAO abstraction layer for simplified data access
- Full integration with JPA entity management

## Features

- **Type-safe queries**: Leverages Java's type system for compile-time safety
- **Fluent API**: Chainable method calls for readable query construction
- **Criteria-based filtering**: Composable criteria for complex WHERE clauses
- **Support for multiple operations**: SELECT, UPDATE, DELETE, ORDER BY, LIMIT
- **Lambda expressions**: Functional programming style query building
- **JPA integration**: Built-in support for Java Persistence API

## Building

This is a Maven project. Build both modules using:

```bash
mvn clean install
```

To build a specific module:

```bash
cd lambda-query
mvn clean install

cd ../lambda-query-jpa
mvn clean install
```

## Usage

### Basic Query Building

The library provides a fluent interface for constructing queries:

```java
// Select queries
Select select = new Select()
    .from(User.class)
    .where(new IsEquals<>("name", "John"))
    .orderBy("id")
    .limit(10);

// Update queries
Update update = new Update()
    .table(User.class)
    .set("status", "active")
    .where(new IsEquals<>("id", 1));

// Delete queries
Delete delete = new Delete()
    .from(User.class)
    .where(new GreaterThan<>("age", 65));
```

### Using Criteria

Build complex filtering conditions using the criteria system:

```java
where(
    new And(
        new IsEquals<>("status", "active"),
        new GreaterThan<>("age", 18)
    )
);
```

## Testing

Run the test suite:

```bash
mvn test
```

Tests cover:

- Query builder functionality
- WHERE clause construction
- SELECT, UPDATE, DELETE operations
- Criteria evaluation
- DAO operations

## Module Dependencies

- **lambda-query-jpa** depends on **lambda-query**
- Both modules require Java 8+
- JPA support requires a JPA implementation (e.g., Hibernate)

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
