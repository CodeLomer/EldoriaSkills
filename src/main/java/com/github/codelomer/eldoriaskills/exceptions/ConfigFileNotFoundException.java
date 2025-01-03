package com.github.codelomer.eldoriaskills.exceptions;

public class ConfigFileNotFoundException extends RuntimeException {
        public ConfigFileNotFoundException(String message) {
            super(message);
        }

        public ConfigFileNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
    }