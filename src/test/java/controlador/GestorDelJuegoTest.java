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

    assertTrue(gestor.realizar_jugada(0, 0, "Reveal"), "Casilla (0,0) debería revelarse");

    //valor (2,2) --> límite superior interior

    assertTrue(gestor.realizar_jugada(2, 2, "Reveal"), "Casilla (2,2) debería revelarse");

    //valor (1,1) --> valor intermedio

    assertTrue(gestor.realizar_jugada(1, 1, "Reveal"), "Casilla (1,1) debería revelarse");


    //valor (-1,-1) --> límite inferior exterior

    assertFalse(gestor.realizar_jugada(-1, -1, "Reveal"), "Casilla (-1,-1) no es válida");

    //valor (3,3) --> límite superior exterior

    assertFalse(gestor.realizar_jugada(3, 3, "Reveal"), "Casilla (3,3) no es válida");

  }




}