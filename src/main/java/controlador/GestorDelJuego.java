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


  public boolean realizar_jugada(int fila, int col, int jugada){

    //Diseño por contrato --> Condiciones necesarias para relaizar la jugada
    assert fila > -1 : "Fila tiene que ser superior o igual a 0";
    assert fila < tablero.getFilas() : "La fila no puede sobrepasar el tablero";
    assert col > -1 : "Columna tiene que ser igual o superior a 0";
    assert col < tablero.getColumnas() : "La columna no puede sobrepasar el tablero";

    if(fila < 0 || fila >= tablero.getFilas() || col < 0 || col >= tablero.getColumnas())
      return false;
    else{
      //Habrá que comprobar también el tipo de jugada --> Para futuros test

      switch (jugada){
        case 1:
          revelarCelda(fila,col);
          return true;
        case 2:
          return flagCelda(fila,col);
        case 3:
          return removeBandera(fila,col);
        default:
          return false;

      }
    }

  }

  public void revelarCelda(int fila, int col){

    //Las precondiciones se evaluan antes del switchcase --> Por tanto aquí no hay
    if(tablero.getCasilla(fila,col).getTieneMina()){
      System.out.println("BOOM ha estallado una mina, has perdido.");
      this.setFinalJuego(true);
      //postcondicion
      assert tablero.getCasilla(fila,col).getRevelada() : "Deberia revelarse la mina";

    }
    tablero.getCasilla(fila,col).setRevelada(true);
    this.setCasillasRestantes(this.getCasillasRestantes() - 1);
    //postcondiciones
    assert tablero.getCasilla(fila,col).getRevelada() : "Deberia estar revelada";
  }

  public boolean flagCelda(int fila, int col){

    //Diseño por contrato --> Precondiciones para poner una bandera
    assert tablero.getCasilla(fila,col).getRevelada() : "Si la casilla está revelada no se puede poner una Bandera.";
    assert tablero.getCasilla(fila,col).getTieneBandera():"Si la casilla tiene bandera no se puede poner otra bandera.";

    if(tablero.getCasilla(fila,col).getRevelada() || tablero.getCasilla(fila,col).getTieneBandera())
      return false;
    else{
      tablero.getCasilla(fila,col).setTieneBandera(true);
      //Si ponemos una flag --> Se quita esa casilla de casillas restantes
      this.setCasillasRestantes(this.getCasillasRestantes() - 1);
      //Postcondicion del método de colocar una bandera --> debe mirar si se ha colocado bien la bandera
      assert tablero.getCasilla(fila,col).getTieneBandera() : "Se deberia haber colocado la bandera correctamente";
      return true;

    }

  }

  public boolean removeBandera(int fila, int col) {
    //Diseño por contrato --> Precondiciones para quitar una badera
    //Falta condicion en el test --> Mirar despues
    //Precondiciones
    assert tablero.getCasilla(fila, col).getRevelada() : " Si la casilla está revelada no se puede quitar una bandera";
    assert !tablero.getCasilla(fila, col).getTieneBandera() : "NO se puede quitar bandera, no hay ninguna en esta casilla";

    if (!tablero.getCasilla(fila, col).getTieneBandera()) {
      return false;
    } else {
      tablero.getCasilla(fila, col).setTieneBandera(false);
      //si quitamos la bandera --> Se suma una a las casillas restantes
      this.setCasillasRestantes(this.getCasillasRestantes() + 1);
      //Postcondicion del método de remover la bandera
      assert tablero.getCasilla(fila, col).getTieneBandera() : "Bandera deberia estar eliminada --> Funciona bien la funcion";

      return true;
    }
  }
}
