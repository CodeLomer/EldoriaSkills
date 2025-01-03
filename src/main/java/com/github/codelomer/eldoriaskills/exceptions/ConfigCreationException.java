package com.github.codelomer.eldoriaskills.exceptions;

public class ConfigCreationException extends RuntimeException {
        public ConfigCreationException(String message) {
            super(message);
        }

        public ConfigCreationException(String message, Throwable cause) {
            super(message, cause);
        }
    }