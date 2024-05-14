import javax.swing.*;

import entities.MainGame;
import utils.screenSize;

public class App {
    public static void main(String[] args) throws Exception {
       //definir tamanho da tela
        screenSize screenSize = new screenSize();

        JFrame frame = new JFrame("Lixo Zero");

        frame.setSize(screenSize.boardWidth, screenSize.boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainGame main = new MainGame();
        frame.add(main);
        frame.pack();
        main.requestFocus();
        frame.setVisible(true);
    }
    
}
