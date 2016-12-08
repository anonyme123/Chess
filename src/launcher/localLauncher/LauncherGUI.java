package launcher.localLauncher;

import java.awt.Dimension;
import java.util.Observer;

import javax.swing.JFrame;

import controler.ChessGameControlers;
import controler.controlerLocal.ChessGameControler;
import model.observable.ChessGame;
import vue.ChessGameGUI;

/**
 * @author francoise.perrin Lance l'exécution d'un jeu d'échec en mode
 * graphique. La vue (ChessGameGUI) observe le modèle (ChessGame) les échanges
 * passent par le contrôleur (ChessGameControlers)
 *
 */
public class LauncherGUI extends JFrame {

    /**
     * @param args
     */
    public static void main(String[] args) {

        ChessGame chessGame;
        ChessGameControlers chessGameControler;
        JFrame frame;
        Dimension dim;

        dim = new Dimension(600, 600);

        chessGame = new ChessGame();
        chessGameControler = new ChessGameControler(chessGame);

        frame = new ChessGameGUI("Jeu d'échec", chessGameControler, dim);
        chessGame.addObserver((Observer) frame);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setPreferredSize(dim);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }
}
