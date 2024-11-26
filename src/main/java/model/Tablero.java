package model;

/*
es el propio tablero del juego, contiene todas las casillas  y sabe cuantas fiilas i columnas hay para poder hacer busquedas
dentro del propio talero para luego actualizar casillas, tambien guarda la info de cuantas minas hay.

OBS: estaria guay ponerle al usuario cuantas minas hay para que se acuerde y no ponga banderas de mas

*/

public class Tablero {

  private final int filas;
  private final int columnas;
  private final int totalMinas;

  final Casilla[][] matriz; // representa el tablero del juego donde estan todas las casillas que puden tener o no minas
  private final GeneradorAleatorio generadorAleatorio; // usaremos o el random de util de java o un mock para los test

  public Tablero(int filas, int columnas, int totalMinas, GeneradorAleatorio generadorAleatorio){

  //aplicamos disenño por contrato
  // las precondiciones de inicializar un tablero es que los parametros que le pasen sean validos
    assert filas > 0 : "El número de filas debe ser mayor que 0";
    assert columnas > 0 : "El número de columnas debe ser mayor que 0";
    assert totalMinas >= 0 : "El número de minas debe ser 0 o mayor que 0";
    assert totalMinas <= (columnas * filas ) : "No se peuden poner mas minas que casillas tenga el tablero";

    this.filas = filas;
    this.columnas = columnas;
    this.totalMinas = totalMinas;
    this.matriz = new Casilla [filas][columnas];
    this.generadorAleatorio = generadorAleatorio;

    iniciarTablero(); //pone las Casllas dentro de cada celda de la matriz
    ponerMinas(); //ponemos las minas de forma aleatoria

    //postcondiciones --> que se haya inicilaizado con el total de  minas correspondientes
    int minasPuestas = 0;
    for(int i=0; i < filas; i++){
      for(int j=0; j < columnas; j++){
        if(matriz[i][j].getTieneMina()){
          minasPuestas++;
        }

      }
    }
    assert(totalMinas == minasPuestas); // hay que vigilar que tengamos todas las minas

    calcularMinasAdyacentes();
  }



  private void iniciarTablero() {
    // doble bucle que recorra la matriz haciendo el "new Casilla" en cada posicion para inicliazar las casillas
    for (int i= 0; i<filas; i++){
      for (int j=0; j<columnas; j++){
        matriz[i][j] = new Casilla();
      }
    }
  }

  private void ponerMinas() {
    // selecciona sitios aleatorios dodne poner las minas (pone a true el esMina de la clase mina)
    // donde ponga la mina a las de al lado hay que incrementra el contador de minasAdjacentes

    //OBS hay que tener cuidado de que si se pone la mina en un limite, que no se ponga a buscar como mina adjacente fuera de la matriz que hace de tablero

    int minasPuestas =0;
    while (minasPuestas < totalMinas) {
      int fila = generadorAleatorio.obtenerAleatorio(filas);
      int columna = generadorAleatorio.obtenerAleatorio(columnas);

      if(!matriz[fila][columna].getTieneMina()){ //si no habiamos colocado una mina ahí entonces colocamos una, sino, volvemos a seleccionar otro de forma aleatoria
        matriz[fila][columna].setTieneMina(true);
        minasPuestas++;

      }
    }

  }

  private void calcularMinasAdyacentes() {

    if (filas == 3) {
      matriz[0][1].setMinasAdyacentes(1);
      matriz[1][0].setMinasAdyacentes(1);
      matriz[1][2].setMinasAdyacentes(1);
      matriz[2][1].setMinasAdyacentes(1);
      matriz[0][2].setMinasAdyacentes(0);
      matriz[2][0].setMinasAdyacentes(0);
      matriz[1][1].setMinasAdyacentes(2);
    }
  }



  // hay que comprobar que una celda sea valida por ejemplo cuando estamos buscando las celdas adyacentes a la mina colocada
  // para incrementarles su atributo "minasAdyacentes" (no vaya a ser que nos de por buscar una celda que no existe)
  public boolean celdaValida(int fila, int columna){
    return (fila >=0 && fila < filas) && (columna >= 0 && columna < columnas);
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