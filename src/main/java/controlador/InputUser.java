package controlador;
import java.util.InputMismatchException; //Hacer saltar la excepción de input
import java.util.Scanner;

public class InputUser implements InputProvider {
  private final Scanner scanner;

  public InputUser(Scanner scan) {
    this.scanner = scan;
  }

  @Override
  public int getInt(){

  }
}