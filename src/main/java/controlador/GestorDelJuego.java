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

  private final Tablero tablero;
  private final Interfaz interfaz;
  private boolean finalJuego;
  private boolean hasGanado;
  private int casillasRestantes;

  public GestorDelJuego(int filas, int columnas, int minas) {


    this.tablero = new Tablero(filas, columnas, minas, new GeneradorAleatorioDefault(new Random()));
    this.interfaz = new Interfaz();
    this.casillasRestantes = filas + columnas;
    this.hasGanado = false;
    this.finalJuego = false;


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

  public void setFinalJuego(boolean finalJuego) {
    this.finalJuego = finalJuego;
  }

  public void setHasGanado(boolean hasGanado) {
    this.hasGanado = hasGanado;
  }

  public void setCasillasRestantes(int casillasRestantes) {
    this.casillasRestantes = casillasRestantes;
  }


  public boolean realizar_jugada(int fila, int col, int accion) {
    if (fila >= 3 || fila < 0 || col >= 3 || col < 0)
      return false;
    else {
      switch (accion) {
        case 1:
          revelarCelda(fila, col);
          return true;
        case 2:
          return flagCelda(fila, col);
        case 3:
          return removeBandera(fila, col);
        default:
          return false;
      }
    }

  }

  public void revelarCelda(int fila, int col) {
    if(tablero.getCasilla(fila,col).getTieneMina()){
      System.out.println("BOOM ha estallado una mina, has perdido.");
      this.setFinalJuego(true);
    }
    tablero.getCasilla(fila, col).setRevelada(true);
    this.setCasillasRestantes(this.getCasillasRestantes() - 1);
    assert tablero.getCasilla(fila, col).getRevelada() : "Deberia estar revelada";
  }

  public boolean flagCelda(int fila, int col) {

    //Miramos que no esté reveladala casilla ni que tenga bandera
    if(tablero.getCasilla(fila,col).getRevelada() || tablero.getCasilla(fila,col).getTieneBandera())
      return false;
    else {
      tablero.getCasilla(fila, col).setTieneBandera(true);
      //Si ponemos una flag --> Se quita esa casilla de casillas restantes
      this.setCasillasRestantes(this.getCasillasRestantes() - 1);
      return true;
    }
  }

  public boolean removeBandera(int fila, int col) {

    //Si la casilla no tiene bandera -->
    if (!tablero.getCasilla(fila, col).getTieneBandera() || tablero.getCasilla(fila,col).getRevelada()){
      return false;
    }
    else{
      tablero.getCasilla(fila,col).setTieneBandera(false);
      //si quitamos la bandera --> Se suma una a las casillas restantes
      this.setCasillasRestantes(this.getCasillasRestantes()+1);

      return true;
    }
  }
}
