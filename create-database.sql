-- AtlasGym OS - Database Setup
-- Run this script in your MySQL/phpMyAdmin environment

CREATE DATABASE IF NOT EXISTS atlasgym_core 
  CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS 'atlas_user'@'localhost' IDENTIFIED BY 'atlas_pass_2026';
GRANT ALL PRIVILEGES ON atlasgym_core.* TO 'atlas_user'@'localhost';
FLUSH PRIVILEGES;

USE atlasgym_core;

-- The tables will be automatically created by Spring Boot (ddl-auto=update)
-- on the first run.
