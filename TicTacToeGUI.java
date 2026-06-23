import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeGUI extends JFrame implements ActionListener {

    JButton[] buttons = new JButton[9];
    char currentPlayer = 'X';

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create buttons
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 50));
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        // If already clicked → ignore
        if (!clicked.getText().equals("")) return;

        clicked.setText(String.valueOf(currentPlayer));

        if (checkWin()) {
            JOptionPane.showMessageDialog(this, "🎉 Player " + currentPlayer + " Wins!");
            resetGame();
            return;
        }

        if (isDraw()) {
            JOptionPane.showMessageDialog(this, "It's a Draw!");
            resetGame();
            return;
        }

        // Switch player
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    boolean checkWin() {
        String[][] winPatterns = {
            {buttons[0].getText(), buttons[1].getText(), buttons[2].getText()},
            {buttons[3].getText(), buttons[4].getText(), buttons[5].getText()},
            {buttons[6].getText(), buttons[7].getText(), buttons[8].getText()},
            {buttons[0].getText(), buttons[3].getText(), buttons[6].getText()},
            {buttons[1].getText(), buttons[4].getText(), buttons[7].getText()},
            {buttons[2].getText(), buttons[5].getText(), buttons[8].getText()},
            {buttons[0].getText(), buttons[4].getText(), buttons[8].getText()},
            {buttons[2].getText(), buttons[4].getText(), buttons[6].getText()}
        };

        for (String[] pattern : winPatterns) {
            if (!pattern[0].equals("") &&
                pattern[0].equals(pattern[1]) &&
                pattern[1].equals(pattern[2])) {
                return true;
            }
        }
        return false;
    }

    boolean isDraw() {
        for (JButton btn : buttons) {
            if (btn.getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    void resetGame() {
        for (JButton btn : buttons) {
            btn.setText("");
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}