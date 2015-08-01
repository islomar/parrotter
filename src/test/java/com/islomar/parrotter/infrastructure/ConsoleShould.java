package com.islomar.parrotter.infrastructure;

import com.google.common.base.Charsets;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Test
public class ConsoleShould {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private static final String MESSAGE = "Hello world";

  @BeforeMethod
  public void setUpMethod() {
    System.setOut(new PrintStream(outContent));
  }

  public void write_to_system_out_println() {

    Scanner scanner = new Scanner(System.in, Charsets.UTF_8.name());
    Console console = new Console(scanner);

    console.printMessage(MESSAGE);

    assertThat(outContent.toString(), is(MESSAGE + "\n"));
  }
}
