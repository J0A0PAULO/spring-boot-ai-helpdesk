package br.com.helpDesk.exceptions;

public class NotFound extends RuntimeException {
  public NotFound(String message) {
    super(message);
  }
}
