package com.example;

/**
 * This is a class.
 */
public class Greeter {

  /**
   * This is a constructor.
   */
  public Greeter() {

  }
  /*
   *@param name of the person
   *@return gret the person
   */
  public final String greet(final String someone) {
    return String.format("Hello, %s!", someone);
  }
}
