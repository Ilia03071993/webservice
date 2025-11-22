package com.selivanov.exception;

public class NoValidAgeException extends RuntimeException {
  public NoValidAgeException(String message) {
    super(message);
  }
}
