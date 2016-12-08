/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import tools.ChessPiecesFactory;

/**
 *
 * @author hugo.talbot
 */
public class Jeu implements Game {

    private Couleur couleur;
    private List<Pieces> pieces;
    boolean castlingPossible;

    public Jeu(Couleur couleur) {
        this.pieces = ChessPiecesFactory.newPieces(couleur);
        this.castlingPossible = false;
        this.couleur = couleur;
    }

    public static void main(String[] args) {
        Jeu jeu = new Jeu(Couleur.BLANC);
        System.out.println(jeu);
    }

    @Override
    public String toString() {
        return "Jeu{" + "pieces=" + pieces + '}';
    }

    private Pieces findPiece(int x, int y) { // a changer 
        ListIterator<Pieces> itr = pieces.listIterator();
        while (itr.hasNext()) {
            Pieces p = itr.next();
            if (p.getX() == x && p.getY() == y) {
                return p;
            }
        }
        /*
        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).getX() == x && pieces.get(i).getY() == y) {
                return pieces.get(i);
            }
        }*/
        return null;
    }

    public boolean setCastlingPossible() {
        return castlingPossible;

    }

    public boolean getCastlingPossible() {
        return castlingPossible;
    }

    @Override
    public boolean isPieceHere(int x, int y) { //a changer 
        if (this.findPiece(x, y) != null) {
            return true;
        } else {
            return false;
        }
    }

    public Couleur getCouleur() {
        return this.couleur;
    }

    public List<Pieces> getPieces() {
        return pieces;
    }

    @Override
    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
        Pieces p = findPiece(xInit, yInit);
        Pieces p2;
        p2 = findPiece(xFinal, yFinal);

        if ((p = findPiece(xInit, yInit)) != null && p2 == null) {// si on bouge sur une case différente de nos pieces  
                    return p.isMoveOk(xFinal, yFinal, isCatchOk, isCastlingPossible);
        }
        
        
        /*else if ((p = findPiece(xInit, yInit)).getName() != null && p2 != null) {// cas exceptionnel ou on peut faire le rock // test a ajouter
            if (p.getName() == "Tour" && p2.getName() == "Roi") {
                    return p.isMoveOk(xFinal, yFinal, isCatchOk, isCastlingPossible);
            } else if (p.getName() == "Roi" && p2.getName() == "Tour") {
                    return p.isMoveOk(xFinal, yFinal, isCatchOk, isCastlingPossible);
            }
        }*/
        return false;
    }

    @Override
    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {//comme au dessus
        return findPiece(xInit, yInit).move(xFinal, yFinal);
    }

    @Override
    public boolean capture(int xCatch, int yCatch) {
        Pieces p = findPiece(xCatch, yCatch);
        if (p != null) {
            pieces.remove(p);
            return true;
        }
        return false;
    }

    public List<PieceIHMs> getPiecesIHM() {
        PieceIHMs newPieceIHM = null;
        List<PieceIHMs> list = new LinkedList<PieceIHMs>();

        for (Pieces piece : pieces) {
            //si la pièce est toujours en jeu 
            if (piece.getX() != -1) {
                newPieceIHM = new PieceIHM(piece);
                list.add(newPieceIHM);
            }
        }
        return list;
    }

    public Couleur getPieceColor(int x, int y) {
        Pieces p = findPiece(x, y);
        if (p != null) {
            return p.getCouleur();
        } else {
            return null;
        }

    }

}
