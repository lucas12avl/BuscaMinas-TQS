package model;

import java.util.Random;

public class GeneradorAleatorioDefault implements GeneradorAleatorio {
  private final Random random;

  public GeneradorAleatorioDefault(Random random) {
    this.random = random;
  }

  @Override
  public int obtenerAleatorio(int max) {
    return random.nextInt(max); // usaremos esta implementacion que nos da un valor aleatorio de verdad entre 0 y max-1
  }
}

