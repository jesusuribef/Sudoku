import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazSudoku extends JFrame {
    private JButton[][] botones;
    private Sudoku sudoku;

    public InterfazSudoku() {
        sudoku = new Sudoku();
        inicializarUI();
        actualizarTablero();
    }

    private void inicializarUI() {
        setTitle("Sudoku");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(9, 9));

        botones = new JButton[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                botones[i][j] = new JButton("");
                final int x = i;
                final int y = j;
                botones[i][j].setFont(new Font("Arial", Font.BOLD, 20)); // Cambiar la fuente y tamaño
                botones[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String numeroStr = JOptionPane.showInputDialog("Ingrese un número (1-9):");
                        try {
                            int numero = Integer.parseInt(numeroStr);
                            if (numero >= 1 && numero <= 9 && sudoku.esMovimientoValido(x, y, numero)) {
                                sudoku.tablero[x][y] = numero;
                                actualizarTablero();
                            } else {
                                JOptionPane.showMessageDialog(null, "Movimiento no válido");
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Entrada no válida");
                        }
                    }
                });
                add(botones[i][j]);
            }
        }
    }

    private void actualizarTablero() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int valor = sudoku.tablero[i][j];
                botones[i][j].setText(valor == 0 ? "" : String.valueOf(valor));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                InterfazSudoku interfaz = new InterfazSudoku();
                interfaz.setVisible(true);
            }
        });
    }
}

