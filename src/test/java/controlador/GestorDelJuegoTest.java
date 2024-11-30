package controlador;
import model.Casilla;
import model.Tablero;
import org.junit.jupiter.api.Test;
import java.util.Scanner; //Escaner para entrada de user

import java.util.InputMismatchException; //Hacer saltar la excepción de input

import org.mockito.Mockito;
import vista.Interfaz;

import javax.swing.text.View;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;


import static org.junit.jupiter.api.Assertions.*;

class GestorDelJuegoTest {

  //PROVES CAIXA NEGRA
  // ######### TESTS RELACIONADOS CON LOS MOVIMIENTOS DEL JUGADOR #########
  @Test
  void revelarMovimientoTest(){

    //CASO SIN MINAS --> Comprobar que el juego funciona bien
    //ver que pasa en valores límite(interiores y exteriores)
    GestorDelJuego gestor = new GestorDelJuego(3, 3, 0);
    Tablero tablero = gestor.getTablero();

    //valor (0,0) --> Limite inferior interior

    assertTrue(gestor.realizar_jugada(0, 0, 1), "Casilla (0,0) debería revelarse");

    //valor (2,2) --> límite superior interior

    assertTrue(gestor.realizar_jugada(2, 2, 1), "Casilla (2,2) debería revelarse");

    //valor (1,1) --> valor intermedio

    assertTrue(gestor.realizar_jugada(1, 1, 1), "Casilla (1,1) debería revelarse");


    //valor (-1,-1) --> límite inferior exterior

    assertFalse(gestor.realizar_jugada(-1, -1, 1), "Casilla (-1,-1) no es válida");

    //valor (3,3) --> límite superior exterior

    assertFalse(gestor.realizar_jugada(3, 3, 1), "Casilla (3,3) no es válida");

    //Revelar una casilla ya revelada
    assertFalse(gestor.realizar_jugada(1, 1,1), "Casilla ya está revelada");

  }

  //MOVIMIENTO --> FLAGGING y Rflagging (2 y 3)
  @Test
  void flagMovimientoTest() { //comprueba los movimientos de rflag y Flagging
    GestorDelJuego gestor = new GestorDelJuego(3, 3, 0);
    Tablero tablero = gestor.getTablero();
    //Poner una flag
    assertTrue( gestor.realizar_jugada(0, 0, 2), "Casilla (0,0) debería estar con una flag");


    //flag en una casilla no válida
    assertFalse(gestor.realizar_jugada(-1, -1, 2), "Casilla (-1,-1) no es válida");


    //poner una flag en una casilla ya revelada
    gestor.realizar_jugada(1, 1, 1);
    assertFalse( gestor.realizar_jugada(1, 1, 2), "No deberia tener bandera, ya estaba revelada");


    //PONER BANDERA EN UNA CASILLA CON BANDERA
    assertFalse(gestor.realizar_jugada(0, 0, 2), "Casilla ya con bandera, no se debe eliminar la bandera");

  }

  @Test
  void quitarBanderaTest(){
    GestorDelJuego gestor = new GestorDelJuego(3, 3, 0);
    Tablero tablero = gestor.getTablero();

    // CASO DONDE SE QUITA LA BANDERA DE UNA CASILLA CON BANDERA
    gestor.realizar_jugada(0,0,2);
    assertTrue(gestor.realizar_jugada(0, 0, 3), "No deberia tener bandera, ya que se ha quitado");


    //CASO DONDE SE QUITA UNA BANDERA DONDE NO LA HAY
    assertFalse(gestor.realizar_jugada(2, 2, 3), "No deberia tener bandera");

    //CASO QUITAR FLAG EN CASILLA REVELADA (1,1) está revelada de antes
    assertFalse(gestor.realizar_jugada(1, 1, 3), "No deberia quitar la bandera de una celda ya revelada");

    //Quitar bandera de posicion no válida
    assertFalse(gestor.realizar_jugada(-1, -1, 3), "No deberia tener bandera, ya que se ha quitado");
  }

  @Test
  void movimientoNoValidoTest(){
    GestorDelJuego gestor = new GestorDelJuego(3, 3, 0);
    Tablero tablero = gestor.getTablero();


    //jugada no válida límite superior
    assertFalse( gestor.realizar_jugada(0, 0, 4), "Jugada 4 no existe");

    //jugada no válida límite inferior
    assertFalse( gestor.realizar_jugada(0, 0, 0), "Jugada 0 no existe");

  }

  @Test
  void detectarMinaTest() {
    GestorDelJuego gestor = new GestorDelJuego(1, 1, 1);
    Tablero tablero = gestor.getTablero();

    gestor.realizar_jugada(0, 0, 1);
    assertTrue(gestor.getFinal(), "Si se descubre una mina el juego se acaba");

  }
  //######## TESTS RELACIONADOS CON EL FLUJO DEL JUEGO #######################
  @Test
  //deberia mostrar por pantalla un mensaje para introducir la jugada + repetir hasta fin del juego
  //deberiamos hacer el statment coverage y loop testing

  void configuracionJuegotest(){
    //MOCK DE ENTRADA
    String mockInput = "5\n5\n3\n"; //ENTRADA SIMULADA
    System.setIn(new ByteArrayInputStream(mockInput.getBytes()));



    GestorDelJuego gestor = new GestorDelJuego(1, 1, 1);


    gestor.configurarJuego();

    // Coger tablero para coprobaciones
    Tablero tablero = gestor.getTablero();

    // Comprobaciones
    assertNotNull(tablero, "El tablero no debería ser null después de configurar el juego.");
    assertEquals(5, tablero.getFilas(), "El número de filas debería ser 5.");
    assertEquals(5, tablero.getColumnas(), "El número de columnas debería ser 5.");
    assertEquals(3, tablero.getTotalMinas(), "El número de minas debería ser 3.");

  }






}