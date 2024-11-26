package model;

public class GeneradorAleatorioMock implements GeneradorAleatorio {
  private final int[] valores; // especificamos los valores que irá dando (para el tablero sera [fila1][columna1][fila2][coluna2]... asi ponemos las minas en el tablero de forma controlada
  private int indice = 0; //para saber cual es el siguinte valor de la lista que debemos dar

  public GeneradorAleatorioMock(int[] valores) {
    this.valores = valores.clone(); //copiamos el array enetro
  }

  @Override
  public int obtenerAleatorio(int max) { //aqui el max no hace falta por que solo vamos sacando numeros de un array premontado pero lo necesitamos en el default para saber hasta que numero podemos sacar

    assert indice < valores.length : "no hay más valores 'aleatorios' en el mock";

    return  valores[indice++];
  }
}
