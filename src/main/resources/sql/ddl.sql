CREATE DATABASE IF NOT EXISTS HealthTrackDatabase;

USE HealthTrackDatabase;

CREATE TABLE IF NOT EXISTS users_credentials (
    id VARCHAR(36) PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

