/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controler.ChessGameControlers;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import model.Coord;
import model.Couleur;
import model.PieceIHMs;
import model.Pieces;
import model.observable.ChessGame;
import tools.ChessImageProvider;
import tools.ChessPieceImage;

/**
 *
 * @author hugo.talbot
 */
public class ChessGameGUI extends JFrame implements Observer, MouseListener, MouseMotionListener {

    String jeu_echec;
    ChessGameControlers chessGameControler;
    Dimension dim;
    Coord coordDep;
    Coord coordArr;

    private JLayeredPane layeredPane;
    private JPanel chessBoard;
    private JLabel chessPiece;
    private int xAdjustment;
    private int yAdjustment;

    public ChessGameGUI(String jeu_déchec, ChessGameControlers chessGameControler, Dimension dim) {
        super();

        this.chessGameControler = chessGameControler;
        this.jeu_echec = jeu_déchec;
        this.dim = dim;

        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(dim);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(this.dim);
        chessBoard.setBounds(0, 0, this.dim.width, this.dim.height);

    }

    @Override
    public void update(Observable o, Object arg) {
        chessBoard.removeAll();
        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            square.setPreferredSize(new Dimension(10, 10));
            square.setSize(10, 10);
            chessBoard.add(square);
            int row = (i / 8) % 2;
            if (row == 0) {
                square.setBackground(i % 2 == 0 ? Color.black : Color.white);
            } else {
                square.setBackground(i % 2 == 0 ? Color.white : Color.black);
            }
        }
        List<PieceIHMs> list = (List<PieceIHMs>) arg;
        ListIterator<PieceIHMs> itr = list.listIterator();
        while (itr.hasNext()) {
            PieceIHMs p = itr.next();
            JLabel piece = new JLabel(new ImageIcon(ChessImageProvider.getImageFile(p.getNamePiece(), p.getCouleur())));
            JPanel panel = (JPanel) this.chessBoard.getComponent(p.getX() + p.getY() * 8);
            panel.add(piece);
        }
        chessBoard.validate();
        chessBoard.repaint();
    }

    public void mousePressed(MouseEvent e) {
        chessPiece = null;

        this.coordDep = new Coord(e.getX() * 8 / dim.width, e.getY() * 8 / dim.height);
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        if (chessGameControler.isPlayerOK(this.coordDep)) {
            if (c instanceof JPanel) {
                return;
            }

            Point parentLocation = c.getParent().getLocation();
            xAdjustment = parentLocation.x - e.getX();
            yAdjustment = parentLocation.y - e.getY();
            chessPiece = (JLabel) c;
            chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
            chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
            layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
        }
    }

    //Move the chess piece around
    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) {
            return;
        }
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
        setVisible(true);
    }

    //Drop the chess piece back onto the chess board
    public void mouseReleased(MouseEvent e) {
        if (chessPiece == null) {
            return;
        }
        this.coordArr = new Coord(e.getX() * 8 / dim.width, e.getY() * 8 / dim.height);
        chessGameControler.move(coordDep, coordArr);
        chessPiece.setVisible(false);
//        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
//        if (c instanceof JLabel) {
//            Container parent = c.getParent();
//            parent.remove(0);
//            parent.add(chessPiece);
//        } else {
//            Container parent = (Container) c;
//            parent.add(chessPiece);
//        }
//
//        chessPiece.setVisible(true);
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

}
