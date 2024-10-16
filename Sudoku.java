public class Sudoku {
    public int[][] tablero; // Hacer el campo público
    private final int TAMANO = 9;

    public Sudoku() {
        tablero = new int[TAMANO][TAMANO];
        generarTablero();
    }

    // Resto de la clase...


    private void generarTablero() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                tablero[i][j] = 0; // Inicializa con ceros.
            }
        }
    }

    public void imprimirTablero() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean esMovimientoValido(int fila, int columna, int numero) {
        for (int i = 0; i < TAMANO; i++) {
            if (tablero[fila][i] == numero || tablero[i][columna] == numero) {
                return false;
            }
        }

        int subCuadroFila = fila - fila % 3;
        int subCuadroColumna = columna - columna % 3;
        for (int i = subCuadroFila; i < subCuadroFila + 3; i++) {
            for (int j = subCuadroColumna; j < subCuadroColumna + 3; j++) {
                if (tablero[i][j] == numero) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean resolverSudoku() {
        for (int fila = 0; fila < TAMANO; fila++) {
            for (int columna = 0; columna < TAMANO; columna++) {
                if (tablero[fila][columna] == 0) {
                    for (int numero = 1; numero <= 9; numero++) {
                        if (esMovimientoValido(fila, columna, numero)) {
                            tablero[fila][columna] = numero;
                            if (resolverSudoku()) {
                                return true;
                            } else {
                                tablero[fila][columna] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
