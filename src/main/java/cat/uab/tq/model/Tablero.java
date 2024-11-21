package main.java.cat.uab.tq.model;

/*
es el propio tablero del juego, contiene todas las casillas  y sabe cuantas fiilas i columnas hay para poder hacer busquedas
dentro del propio talero para luego actualizar casillas, tambien guarda la info de cuantas minas hay.

OBS: estaria guay ponerle al usuario cuantas minas hay para que se acuerde y no ponga banderas de mas

*/

public class Tablero {

  private final int filas;
  private final int columnas;
  private final int totalMinas;
  private final Casilla matriz[][]; // representa el tablero del juego donde estan todas las casillas que puden tener o no minas

  public Tablero(int filas, int columnas, int totalMinas){
    this.filas = filas;
    this.columnas = columnas;
    this.totalMinas = totalMinas;
    this.matriz = new Casilla [filas][columnas];

    iniciarTablero(); //pone las Casllas dentro de cada celda de la matriz
    ponerMinas(); //ponemos las minas de forma aleatoria
  }



  private void iniciarTablero() {
    // doble bucle que recorra la matriz haciendo el "new Casilla" en cada posicion para inicliazar las casillas
  }
  private void ponerMinas() {
    // selecciona sitios aleatorios dodne poner las minas (pone a true el esMina de la clase mina)
    // donde ponga la mina a las de al lado hay que incrementra el contador de minasAdjacentes

    //OBS hay que tener cuidado de que si se pone la mina en un limite, que no se ponga a buscar como mina adjacente fuera de la matriz que hace de tablero
  }


  //getters
  public Casilla getCasilla(int fila, int columna){
    return matriz[fila][columna];

  }
  public int getFilas(){
    return this.filas;
  }

  public int getColumnas(){
    return this.columnas;
  }

  public int getTotalMinas(){
    return this.totalMinas;
  }

}