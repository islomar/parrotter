package com.islomar.parrotter.actions.utils;

public enum CommandType {
  POST (" -> "),
  FOLLOWS(" follows "),
  WALL (" wall");

  private final String symbol;

  private CommandType(String symbol) {
    this.symbol = symbol;
  }

  public String symbol() {
    return symbol;
  }
}
