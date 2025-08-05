# Twitter [![Java Version](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen.svg)](https://spring.io/projects/spring-boot)

Practical implementation of a Twitter-like application.

Swagger: http://localhost:8080/swagger-ui/index.html

## System Design

A Twitter-style platform built with clean architecture, domain-driven design (DDD), and event-driven architecture. Users can create tweets, follow others, and browse timelines. 

**Features:**

* Tweets: Create tweets and view your own profile timeline.
* Search: Find tweets by keyword.
* Follows & Timelines: Follow users and see their tweets in your home timeline.

**Pluggable Data Layer:**

The clean architecture design keeps business logic independent of infrastructure so databases and search engines can be swapped and compared, enabling side-by-side evaluation of the following different stores:
* PostgreSQL
* MongoDB
* Elasticsearch

This lets you compare search capabilities (e.g., PostgreSQL full-text, MongoDB text indexes, Elasticsearch inverted index) without changing core domain code.

**Event-Driven Architecture:**

Timelines are generated via an event-driven flow: when a user posts a new tweet, an event is emitted; a handler consumes the event and fan-outs the tweet into the timelines of all the author’s followers. This decouples write paths from read models and keeps timelines fast.

## Technologies

- Gradle
- Java 17
- Spring Boot
- PostgreSQL
- Elasticsearch
- MongoDB
- Docker Compose

## Authors & Copyrights

Shaimaa Sabry

[![GitHub](https://img.icons8.com/ios-glyphs/30/000000/github.png)](https://github.com/ShaimaaSabry)
[![LinkedIn](https://img.icons8.com/ios-filled/30/0A66C2/linkedin.png)](https://www.linkedin.com/in/shaimaa-sabry-161b71a0/)