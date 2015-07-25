package com.islomar.parrotter.actions;


public interface Command {

  void execute(String inputCommandLine);

  boolean canExecuteCommandline(String inputCommandLine);
}
