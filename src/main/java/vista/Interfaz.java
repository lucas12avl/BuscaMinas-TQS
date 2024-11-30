package vista;
/*
  la encargada de representar el estado actual del tablero, se llama automaticamente cuando el tabero de juego esta listo,
  a partir de ahi la llamaremos cada vez que el usuario haga una accion (poner una bandera o revelar una celda)
  le pasan la matriz i mira casilla a casilla el estado de forma que coloca el icono que corresponda

OBS: hacerle saber al jugador que ha perdido o ha ganado cuando ocurra

*/

public class Interfaz {



  /*

  representacion:
  * --> es una mina
  f --> cuando el usario pone una bandera
  . , [ ], _  --> ideas de si todavia no ha puesto nada el usuario
  0 - 8 --> casilla revelada con el numero de minas adyacentes

  **ideas de diseño**
    op1
     .  .  .  .  .
     .  .  3  3  .
     .  .  f  .  .
     .  .  .  1  .
     .  .  .  .  .

    op2
    [ ] [ ] [ ] [ ] [ ]
    [ ] [ ] [3] [3] [ ]
    [ ] [ ] [f] [ ] [ ]
    [ ] [ ] [ ] [1] [ ]
    [ ] [ ] [ ] [ ] [ ]

 op3
    _  _  _  _  _
    _  _ [3][3] _
    _  _ [f] _  _
    _  _  _ [1] _
    _  _  _  _  _

  */

  public void mostrarMensaje(String mensaje){
    System.out.println(mensaje);
  }
}
