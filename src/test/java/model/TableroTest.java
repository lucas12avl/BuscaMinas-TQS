package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableroTest {

  /*PROVES DE CAIXA NEGRA*/
  // SIN CAMBIOS.
    @Test
  void iniciarClaseTableroTest(){

    // con este primer test conseguimos un statment y path coverage del metodo inicializarMatrizTablero()
    int[] posMinas = {0,0}; //da igual, porque no se van a poner minas en este test
    Tablero tablero = new Tablero(2,2,0, new GeneradorAleatorioMock(posMinas)); //valor interior frontera (válido)
    assertEquals(2, tablero.getFilas());
    assertEquals(2, tablero.getColumnas());

    Tablero tablero2 = new Tablero(1,1,0, new GeneradorAleatorioMock(posMinas)); //valor frontera (válido)
    assertEquals(1, tablero2.getFilas());
    assertEquals(1, tablero2.getColumnas());

    //verifica que cuando damos los parametros de un tablero no valido, el programa tire un error con el assert del DbyC del constructor
    AssertionError thrown = assertThrows(AssertionError.class, () -> new Tablero(0,1,0, new GeneradorAleatorioMock(posMinas))); //valor exterior frontera (inválido)
    assertEquals(thrown.getMessage(), "El número de filas debe ser mayor que 0");

    AssertionError thrown2 = assertThrows(AssertionError.class, () -> new Tablero(1,0,0, new GeneradorAleatorioMock(posMinas))); //valor exterior frontera (inválido)
    assertEquals(thrown2.getMessage(), "El número de columnas debe ser mayor que 0");

    AssertionError thrown3 = assertThrows(AssertionError.class, () -> new Tablero(1,1,-1, new GeneradorAleatorioMock(posMinas))); //valor exterior frontera (inválido)
    assertEquals(thrown3.getMessage(), "El número de minas debe ser 0 o mayor que 0");

    AssertionError thrown4 = assertThrows(AssertionError.class, () -> new Tablero(2,2,8,new GeneradorAleatorioMock(posMinas))); //valor exterior frontera (inválido)
    assertEquals(thrown4.getMessage(), "No se peuden poner mas minas que casillas tenga el tablero");


  }



  @Test
  void celdaValidaTest () {
    int[] posMinas = {0,0}; //da igual, porque no se van a poner minas en este test
    Tablero tablero = new Tablero(2,2,0, new GeneradorAleatorioMock(posMinas));
    assertTrue(tablero.celdaValida(1, 1)); //limite superior (válido)
    assertTrue(tablero.celdaValida(0, 0)); // limite inferior (válido)

    assertTrue(tablero.celdaValida(0, 1)); // valor interior a la frontera (válido)
    assertTrue(tablero.celdaValida(1, 0)); // valor interior a la frontera (válido)

    assertFalse(tablero.celdaValida(1, -1)); //valor exterior a la frontera (inválido)
    assertFalse(tablero.celdaValida(1, 5)); //valor exterior a la frontera (inválido)

    Tablero tablero2 = new Tablero(1,1,0, new GeneradorAleatorioMock(posMinas));
    assertTrue(tablero2.celdaValida(0, 0)); //limite inferior (válido)
    assertFalse(tablero2.celdaValida(-1, 0)); //valor exterior a la frontera (inválido)  //con esta linea consguimos true/false
    assertFalse(tablero2.celdaValida(0, 5)); //valor exterior a la frontera (inválido)


  }
  @Test
  void conditionCoverageCeldaValidaTest(){
      //condition coverage
    int[] posMinas = {0,0}; //da igual, porque no se van a poner minas en este test
    Tablero tablero = new Tablero(2,2,0, new GeneradorAleatorioMock(posMinas));
    assertFalse(tablero.celdaValida(5, 5)); // true false true false = false
    assertFalse(tablero.celdaValida(-5, -5));// false true false true  = false
  }
  @Test
  void decisionCoverageCeldaValidaTest(){
    int[] posMinas = {0,0}; //da igual, porque no se van a poner minas en este test
    Tablero tablero = new Tablero(2,2,0, new GeneradorAleatorioMock(posMinas));
    assertTrue(tablero.celdaValida(1, 1)); // evalua la condicion como true
    assertFalse(tablero.celdaValida(-1, -1)); // evalua la condicion como false


  }

  @Test
  void ponerMinasTest(){

    int[] posMinas = {0,0}; //da igual, porque no se van a poner minas en este test
    Tablero tablero = new Tablero(1,1,0,new GeneradorAleatorioMock(posMinas)); // valor limite (válido)

    int minasPuestas = 0;
    for(int i=0; i < tablero.getFilas(); i++){
      for(int j=0; j < tablero.getColumnas(); j++){
        if(tablero.matriz[i][j].getTieneMina()){
          minasPuestas++;
        }

      }
    }

    assertEquals(0, minasPuestas);

    int[] posMinas2 = {0,0,0,1,1,0,1,1};
    Tablero tablero2 = new Tablero(2,2,4, new GeneradorAleatorioMock(posMinas2)); // valor interior a la frontera (válido)

    int minasPuestas2 = 0;
    for(int i=0; i < tablero2.getFilas(); i++){
      for(int j=0; j < tablero2.getColumnas(); j++){
        if(tablero2.matriz[i][j].getTieneMina()){
          minasPuestas2++;
        }

      }
    }
    assertEquals(4, minasPuestas2);

    //valores externos a la frontera
    AssertionError thrown3 = assertThrows(AssertionError.class, () -> new Tablero(2,2,-2, new GeneradorAleatorioMock(posMinas))); //valor exterior frontera (inválido)
    assertEquals(thrown3.getMessage(), "El número de minas debe ser 0 o mayor que 0");

    AssertionError thrown4 = assertThrows(AssertionError.class, () -> new Tablero(2,2,8, new GeneradorAleatorioMock(posMinas))); //valor exterior frontera (inválido)
    assertEquals(thrown4.getMessage(), "No se peuden poner mas minas que casillas tenga el tablero");



  }
  @Test
  void loopTestingPonerMinas(){
    //valores que toma el total minas loop testin --> totalMinas

    int[] posMinas = {0,0}; //da igual, porque no se van a poner minas en este test
    Tablero tablero = new Tablero(3,3,0,new GeneradorAleatorioMock(posMinas)); // 0 --> evitamos el loop
    int minasPuestas = 0;
    for(int i=0; i < tablero.getFilas(); i++){
      for(int j=0; j < tablero.getColumnas(); j++){
        if(tablero.matriz[i][j].getTieneMina()){
          minasPuestas++;
        }

      }
    }

    assertEquals(0, minasPuestas);

    int[] posMinas2 = {0,0}; //da igual, porque no se van a poner minas en este test
    Tablero tablero2 = new Tablero(3,3,1,new GeneradorAleatorioMock(posMinas2)); // 1 --> hacemos una pasada por el loop
    int minasPuestas2 = 0;
    for(int i=0; i < tablero2.getFilas(); i++){
      for(int j=0; j < tablero2.getColumnas(); j++){
        if(tablero2.matriz[i][j].getTieneMina()){
          minasPuestas2++;
        }

      }
    }

    assertEquals(1, minasPuestas2);


    int[] posMinas3 = {0,0,0,1};
    Tablero tablero3 = new Tablero(3,3,2,new GeneradorAleatorioMock(posMinas3)); // 2 --> hacemos 2 pasadas por el loop
    int minasPuestas3 = 0;
    for(int i=0; i < tablero3.getFilas(); i++){
      for(int j=0; j < tablero3.getColumnas(); j++){
        if(tablero3.matriz[i][j].getTieneMina()){
          minasPuestas3++;
        }

      }
    }

    assertEquals(2, minasPuestas3);


    int[] posMinas4 = {0,0,0,1,0,2,1,0};
    Tablero tablero4 = new Tablero(3,3,4,new GeneradorAleatorioMock(posMinas4)); // 4 --> hacemos 4 pasadas por el loop
    int minasPuestas4 = 0;
    for(int i=0; i < tablero4.getFilas(); i++){
      for(int j=0; j < tablero4.getColumnas(); j++){
        if(tablero4.matriz[i][j].getTieneMina()){
          minasPuestas4++;
        }

      }
    }
    assertEquals(4, minasPuestas4);


    int[] posMinas5 = {0,0,0,1,0,2,1,0,1,1,1,2,2,0,2,1};
    Tablero tablero5 = new Tablero(3,3,8,new GeneradorAleatorioMock(posMinas5)); // 8 --> hacemos 8 pasadads hasta llegar al limite interior
    int minasPuestas5 = 0;
    for(int i=0; i < tablero5.getFilas(); i++){
      for(int j=0; j < tablero5.getColumnas(); j++){
        if(tablero5.matriz[i][j].getTieneMina()){
          minasPuestas5++;
        }

      }
    }

    assertEquals(8, minasPuestas5);

    int[] posMinas6 = {0,0,0,1,0,2,1,0,1,1,1,2,2,0,2,1,2,2};
    Tablero tablero6 = new Tablero(3,3,9,new GeneradorAleatorioMock(posMinas6)); // hacemos el maximo numero de pasadas hasta llegar a la frontera
    int minasPuestas6 = 0;
    for(int i=0; i < tablero6.getFilas(); i++){
      for(int j=0; j < tablero6.getColumnas(); j++){
        if(tablero6.matriz[i][j].getTieneMina()){
          minasPuestas6++;
        }

      }
    }

    assertEquals(9, minasPuestas6);

  }


  @Test
  void calcularMinasAdyacentesTest(){ // consigue statment coverage i path coverage ya que pasa por todas las sentencias, y ademas recorre todos los caminos

    int[] posMinas = {0, 0, 2, 2, 2, 1};
    Tablero tablero = new Tablero(3,3,3, new GeneradorAleatorioMock(posMinas));

    //limite superior izquierda (válido)
    assertEquals(1, tablero.getCasilla(0, 1).getMinasAdyacentes());
    assertEquals(2, tablero.getCasilla(1, 0).getMinasAdyacentes());

    //limite inferior derecha (válido)
    assertEquals(2, tablero.getCasilla(1, 2).getMinasAdyacentes());
    assertEquals(0, tablero.getCasilla(2, 1).getMinasAdyacentes()); // no se le deberia asignar minas adyacentes ya que es una mina

    //test values con 0 y 1  minas adyacentes (válido)
    assertEquals(0, tablero.getCasilla(0, 2).getMinasAdyacentes());
    assertEquals(1, tablero.getCasilla(2, 0).getMinasAdyacentes());

    //test value con 2 minas adyacentes (válido)
    assertEquals(3, tablero.getCasilla(1, 1).getMinasAdyacentes());

    assertFalse(tablero.celdaValida(-1, -1)); // valor exterior de la frontera (arriba izq) (inválido)
    assertFalse(tablero.celdaValida(3, 3));  // valor exterior de la frontera (abajo drch) (inválido)
  }


}