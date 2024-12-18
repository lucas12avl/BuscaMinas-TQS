package controlador;

import model.GeneradorAleatorioDefault;
import model.Tablero;
import java.util.Scanner;


import vista.Interfaz;

import java.util.Random;

public class GestorDelJuego {
  /*
    la clase que se encarga del flujo del juego,
     debe saber si el jugador ha perdido o ha ganado

     es el que sabe todos los inputs que puede dar el usuario y
     reaccionar a ellos modificando cosas de las casillas para luego
     volver a llmar a la vista/interfaz para que vuelva dibujar el
     nuevo estado del tablero


  */

  private Tablero tablero;
  private Interfaz interfaz;
  private boolean finalJuego;
  private boolean hasGanado;
  private Integer casillasRestantes;


  public GestorDelJuego(int filas, int columnas, int minas) {


    this.tablero = new Tablero(filas, columnas, minas, new GeneradorAleatorioDefault(new Random()));
    this.interfaz = new Interfaz();
    this.casillasRestantes = filas * columnas;
    this.hasGanado = false;
    this.finalJuego = false;


  }
  public GestorDelJuego(){
    this.interfaz = new Interfaz();
    this.casillasRestantes = null;
    this.hasGanado = false;
    this.finalJuego = false;
    this.tablero = null;

  }

  //getters --> MAX 5
  public Tablero getTablero() {
    return this.tablero;
  }

  public boolean getFinal() {
    return this.finalJuego;
  }

  public boolean getHasGanado() {
    return this.hasGanado;
  }

  public int getCasillasRestantes() {
    return this.casillasRestantes;
  }

  //Setters --> MAX 5
  public void setTablero(Tablero tablero) {
    this.tablero = tablero;
  }
  public void setFinalJuego(boolean finalJuego) {
    this.finalJuego = finalJuego;
  }
  public void setCasillasRestantes(int casillasRestantes) {
    this.casillasRestantes = casillasRestantes;
  }
  public void setInterfaz(Interfaz inter){
    this.interfaz = inter;
  }

  public void configurarJuego(){
    Scanner scanner = new Scanner(System.in);
    int filas = 0, columnas = 0, minas = 0;

    interfaz.mostrarMensaje("Bienvenido al Buscaminas :)");
    interfaz.mostrarMensaje("Vamos a configurar el tablero.");

    while (true) { // Bucle que pide configuración del tablero hasta que sea válida.
        try {
            interfaz.mostrarMensaje("Introduce el número de filas (1-7):");
            filas = Integer.parseInt(scanner.nextLine());

            interfaz.mostrarMensaje("Introduce el número de columnas (1-7):");
            columnas = Integer.parseInt(scanner.nextLine());

            interfaz.mostrarMensaje("Introduce el número de minas (1-" + (filas * columnas) + "):");
            minas = Integer.parseInt(scanner.nextLine());
            //Comprueba que los datos introducidos sean válidos
            if (filas > 0 && filas <= 7 && columnas > 0 && columnas <= 7
                    && minas > 0 && minas <= filas * columnas) {
                break;
            } else {
                interfaz.mostrarMensaje("Error: filas, columnas y minas deben estar entre 1 y 7.");
                interfaz.mostrarMensaje("Además, las minas no pueden superar filas x columnas.");
            }
        } catch (NumberFormatException e) { //Si se entran parametros no válidos
            interfaz.mostrarMensaje("Error: Introduzca solo números válidos.");
        }
    }

    // Inicializar el tablero con las dimensiones y minas proporcionadas.
    this.setTablero(new Tablero(filas, columnas, minas, new GeneradorAleatorioDefault(new Random())));
    this.setCasillasRestantes(filas*columnas);
    interfaz.mostrarMensaje("¡Tablero configurado exitosamente con " + filas + " filas, " + columnas + " columnas y " + minas + " minas!");
  }
  public void empezarJuego(){
    Scanner scanner = new Scanner(System.in);
    interfaz.mostrarMensaje("Introduce tu movimiento en el formato: fila columna acción (1=Revelar, 2=Poner bandera, 3=Quitar bandera)");
    //Bucle del juego --> No acaba hasta que se acabe le juego
    while (!finalJuego) {
      interfaz.mostrarTablero(tablero);

      String input = scanner.nextLine();
      String[] parts = input.split(" ");

      //Comprobar datos de entrada + su tratamiento
      if (parts.length == 3) {
        try {
          int fila = Integer.parseInt(parts[0]);
          int col = Integer.parseInt(parts[1]);
          int action = Integer.parseInt(parts[2]);

          if (realizar_jugada(fila, col, action)) {
            //Este if nos ayuda en caso de que estalle una mina al revelar --> Evita que salga el mensaje de victoria
            if (casillasRestantes <= tablero.getTotalMinas() && !finalJuego) {
              interfaz.mostrarMensaje("¡Felicidades, has ganado!");
              hasGanado = true;
              finalJuego = true;
              return;
            }
          }
        } catch (NumberFormatException e) {
          interfaz.mostrarMensaje("Error: Introduce números válidos.");
        }
      } else {
        interfaz.mostrarMensaje("Formato incorrecto. Usa: fila columna acción.");
      }
    }
  }



  public boolean realizar_jugada(int fila, int col, int jugada){
    if(fila < 0 || fila >= tablero.getFilas() || col < 0 || col >= tablero.getColumnas())
      return false;
    else{
      //SWITCH DE LAS JUAGADAS --> True si se ha podido realizar --> False si no.
      switch (jugada){
        case 1:
          return revelarCelda(fila,col);
        case 2:
          return flagCelda(fila,col);
        case 3:
          return removeBandera(fila,col);
        default:
          return false;

      }
    }
  }

  public boolean revelarCelda(int fila, int col){

    if(tablero.getCasilla(fila,col).getRevelada())
      return false;
    //CASO DE REVELAR UNA MINA
    if(tablero.getCasilla(fila,col).getTieneMina()){
      System.out.println("BOOM ha estallado una mina, has perdido.");
      this.setFinalJuego(true);
      return true;
    }
    tablero.getCasilla(fila,col).setRevelada(true);
    //Si una casilla se revela --> Una menos para la victoria
    this.setCasillasRestantes(this.getCasillasRestantes() - 1);
    return true;
  }

  public boolean flagCelda(int fila, int col){
    //No podemos poner flags si la casilla esta revelada o ya tiene una flag
    if(tablero.getCasilla(fila,col).getRevelada() || tablero.getCasilla(fila,col).getTieneBandera())
      return false;
    else{
      tablero.getCasilla(fila,col).setTieneBandera(true);
      //Si ponemos una flag --> Se quita esa casilla de casillas restantes si realmente tiene mina
      if(this.getTablero().getCasilla(fila,col).getTieneMina()){
        this.setCasillasRestantes(this.getCasillasRestantes() - 1);
      }
      return true;
    }
  }

  public boolean removeBandera(int fila, int col) {
    //Tiene que tener bandera para quitarla
    if (!tablero.getCasilla(fila, col).getTieneBandera()) {
      return false;
    } else {
      tablero.getCasilla(fila, col).setTieneBandera(false);
      //si quitamos la bandera y tenia mina --> Se suma una a las casillas restantes
      if(tablero.getCasilla(fila,col).getTieneMina())
        this.setCasillasRestantes(this.getCasillasRestantes() + 1);
      return true;
    }
  }
}
