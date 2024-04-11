package fr.hetic;

public class HelloWorld {
  public static void main(String[] args) {
    helloName("Pierre");
  }

  public static void helloName(String name) {
    System.out.printf("Hello '%s' !", name);
  }
}
