package model;
/*
  las casillas son las que saben si son una mina o no,todas tendran un numero de minas adyacentes, pero si son mina,
   este numero nunca se representa en la interfaz. Tambien guardan la info de si el usario les ha pueto una bandera o no
  y tambien si están reveladas
  */
public class Casilla {
  private boolean tieneMina;
  private boolean revelada;
  private boolean tieneBandera;
  private int minasAdjacentes;

  //constructor
  public Casilla(){
    this.tieneMina = false;
    this.revelada = false;
    this.tieneBandera = false;
    this.minasAdjacentes = 0;
  }


//GETTERS
  public boolean getRevelada() {
    return revelada;
  }
  public boolean getTieneMina() {
    return tieneMina;
  }
  public boolean getTieneBandera() {
    return tieneBandera;
  }
  public int getMinasAdjacentes() {
    return minasAdjacentes;
  }


  //SETTERS
  public void setRevelada(boolean revelada) {
    this.revelada = revelada;
  }

  public void setTieneMina(boolean tieneMina) {
    this.tieneMina = tieneMina;
  }

  public void setTieneBandera(boolean tieneBandera) {
    this.tieneBandera = tieneBandera;
  }

  public void setMinasAdjacentes(int minasAdjacentes) {
    this.minasAdjacentes = minasAdjacentes;
  }
}
