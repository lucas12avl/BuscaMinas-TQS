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
  private boolean hasPerdido;
  private boolean hasGanado;

  public GestorDelJuego (int filas, int columnas, int minas) {


    this.tablero = new Tablero(filas, columnas, minas, new GeneradorAleatorioDefault(new Random()));
    this.interfaz = new Interfaz();

    this.hasGanado = false;
    this.hasPerdido = false;


  }
  //hacen falta getters o setters?

  private void procesarMovJugador(){
    //encargaado de hacer la accion del jugador, podemos recibir un sring del tipo "1 1 flag", "2 2 revelar"
    //en caso de que al revelar sea una mina


  }




}
