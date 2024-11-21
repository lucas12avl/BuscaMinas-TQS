package controlador;

import model.Tablero;
import vista.Interfaz;

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
  private boolean hasPerdido;
  private boolean hasGanado;

  public GestorDelJuego (int filas, int columnas, int minas) {
    this.tablero = new Tablero(filas, columnas, minas);
    this.interfaz = new Interfaz();

    this.hasGanado = false;
    this.hasPerdido = false;


  }
  //hacen falta getters o setters?


}
