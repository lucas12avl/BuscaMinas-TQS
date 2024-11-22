package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableroTest {

  /*PROVES DE CAIXA NEGRA*/

    @Test
  void iniciarClaseTableroTest(){


    Tablero tablero = new Tablero(2,2,0); //valor interior frontera
    assertEquals(2, tablero.getFilas());
    assertEquals(2, tablero.getColumnas());

    Tablero tablero2 = new Tablero(1,1,0); //valor frontera
    assertEquals(1, tablero2.getFilas());
    assertEquals(1, tablero2.getColumnas());

    //verifica que cuando damos los parametros de un tablero no valido, el programa tire un error con el assert del DbyC del constructor
    AssertionError thrown = assertThrows(AssertionError.class, () -> new Tablero(0,1,0)); //valor exterior frontera
    assertEquals(thrown.getMessage(), "El número de filas debe ser mayor que 0");

    AssertionError thrown2 = assertThrows(AssertionError.class, () -> new Tablero(1,0,0)); //valor exterior frontera
    assertEquals(thrown2.getMessage(), "El número de columnas debe ser mayor que 0");

    AssertionError thrown3 = assertThrows(AssertionError.class, () -> new Tablero(1,1,-1)); //valor exterior frontera
    assertEquals(thrown3.getMessage(), "El número de minas debe ser 0 o mayor que 0");

    AssertionError thrown4 = assertThrows(AssertionError.class, () -> new Tablero(2,2,8)); //valor exterior frontera
    assertEquals(thrown4.getMessage(), "No se peuden poner mas minas que casillas tenga el tablero");


  }

  @Test
  void celdaValidaTest () {
    Tablero tablero = new Tablero(2,2,0);
    assertTrue(tablero.celdaValida(1, 1)); //limite superior
    assertTrue(tablero.celdaValida(0, 0)); // limite inferior

    assertTrue(tablero.celdaValida(0, 1)); // valor interior a la frontera
    assertTrue(tablero.celdaValida(1, 0)); // valor interior a la frontera

    assertFalse(tablero.celdaValida(1, -1)); //valor exterior a la frontera
    assertFalse(tablero.celdaValida(1, 5)); //valor exterior a la frontera

    Tablero tablero2 = new Tablero(1,1,0);
    assertTrue(tablero2.celdaValida(0, 0)); //limite inferior
    assertFalse(tablero2.celdaValida(-1, 0)); //valor exterior a la frontera
    assertFalse(tablero2.celdaValida(0, 5)); //valor exterior a la frontera


  }

  @Test
  void ponerMinasTest(){

    Tablero tablero = new Tablero(1,1,0); // valor limite

    int minasPuestas = 0;
    for(int i=0; i < tablero.getFilas(); i++){
      for(int j=0; j < tablero.getColumnas(); j++){
        if(tablero.matriz[i][j].getTieneMina()){
          minasPuestas++;
        }

      }
    }
    assertEquals(0, minasPuestas);

    Tablero tablero2 = new Tablero(1,1,1); // valor interior a la frontera
    int minasPuestas2 = 0;
    for(int i=0; i < tablero2.getFilas(); i++){
      for(int j=0; j < tablero2.getColumnas(); j++){
        if(tablero2.matriz[i][j].getTieneMina()){
          minasPuestas2++;
        }

      }
    }
    assertEquals(1, minasPuestas2);

    //valores externos a la frontera
    AssertionError thrown3 = assertThrows(AssertionError.class, () -> new Tablero(2,2,-2)); //valor exterior frontera
    assertEquals(thrown3.getMessage(), "El número de minas debe ser 0 o mayor que 0");

    AssertionError thrown4 = assertThrows(AssertionError.class, () -> new Tablero(2,2,8)); //valor exterior frontera
    assertEquals(thrown4.getMessage(), "No se peuden poner mas minas que casillas tenga el tablero");



  }


}