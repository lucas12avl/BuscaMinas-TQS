package controlador;

import model.GeneradorAleatorioDefault;
import model.Tablero;


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

  public GestorDelJuego (int filas, int columnas, int minas) {


    this.tablero = new Tablero(filas, columnas, minas, new GeneradorAleatorioDefault(new Random()));
    this.interfaz = new Interfaz();
    this.casillasRestantes = filas + columnas;
    this.hasGanado = false;
    this.finalJuego = false;


  }

  //getters --> MAX 5
  public Tablero getTablero(){
    return this.tablero;
  }
  public boolean getFinal(){
    return this.finalJuego;
  }
  public boolean getHasGanado(){return this.hasGanado;}
  public int getCasillasRestantes(){return this.casillasRestantes;}

  //Setters --> MAX 5

  public void setFinalJuego(boolean finalJuego){
    this.finalJuego = finalJuego;
  }

  public void setHasGanado(boolean hasGanado){
    this.hasGanado = hasGanado;
  }
  public void setCasillasRestantes(int casillasRestantes){this.casillasRestantes = casillasRestantes;}


  public boolean realizar_jugada(int fila, int columna, String accion){
    if (fila >= 3 || fila < 0 || columna >= 3 || columna <0)
      return false;
    else{
      switch (accion){
        case "Reveal":
          return true;
        default:
          return true;
      }
    }

  }




}
