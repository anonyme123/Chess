/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.*;

/**
 *
 * @author hugo.talbot
 */
public class Echiquier implements BoardGames {

    protected Jeu jeublanc;
    protected Jeu jeunoir;
    protected Jeu jeucourant;
    private String message;
    private boolean isMoveOK;

    public static void main(java.lang.String[] args) {
        Echiquier echiquier = new Echiquier();
        System.out.println(echiquier);
    }

    public Echiquier() {
        this.jeublanc = new Jeu(Couleur.BLANC);
        this.jeunoir = new Jeu(Couleur.NOIR);
        this.jeucourant = jeublanc;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public List<PieceIHMs> getPiecesIHM() {
        //créer une liste de piècesHIM et le remplir avec les listes de pièces de jeublanc et jeunoir
        List<PieceIHMs> listeIHM = new LinkedList<PieceIHMs>();
        listeIHM.addAll(jeublanc.getPiecesIHM());
        listeIHM.addAll(jeunoir.getPiecesIHM());
        return listeIHM;
    }

    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
        /*
        s'il n'existe pas de piece du jeu courant aux coordonnées initiales --> false,
        si les coordonnées finales ne sont pas valides ou égales aux initiales --> false,
        si position finale ne correspond pas à algo de déplacement piece --> false,
        s'il existe une piéce intermédiaire sur la trajectoire --> false (sauf cavalier),
        s'il existe une piéce positionnées aux coordonnées finales :
            si elle est de la méme couleur --> false ou tentative roque du roi,
            sinon : prendre la piéce intermédiaire (vigilance pour le cas du pion) et déplacer la piéce -->true,
        sinon déplacer la piéce -->true
         */

        boolean isCatchOk = false;
        if (jeublanc.getPieceColor(xFinal, yFinal) != null || jeunoir.getPieceColor(xFinal, yFinal) != null) {
            isCatchOk = true;
        }
        if (jeucourant.isMoveOk(xInit, yInit, xFinal, yFinal, isCatchOk, jeucourant.castlingPossible)) {
            message = "OK";
            this.isMoveOK = true;
            return true;
        } else {
            message = "Non OK";
        }
        this.isMoveOK = false;
        return false;

    }

    public void switchJoueur() {
        if (this.jeucourant.getCouleur() == Couleur.BLANC) {
            this.jeucourant = jeunoir;
        } else {
            this.jeucourant = jeublanc;
        }
    }

    public String toString() {
        return jeublanc + "\n" + jeunoir;
    }

    @Override
    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
        if (this.isMoveOK) {
            boolean piecefinal = false;
            boolean pieceIntermediaire = false;
            //piece intermédiaire
            //on vérifie s'il n'y a pas de pièce sur le déplacement
            int dh, dv, sensH, sensV, i, j;
            //on cherche le déplcement en Horizontal et en Vertical
            dh = Math.abs(xFinal - xInit);
            dv = Math.abs(yFinal - yInit);
            //on cherche le sens de déplacement en Horizontal et en Vertical
            if (xFinal == xInit) {
                sensH = 0;
            } else {
                sensH = (xFinal - xInit) / dh;
            }
            if (yFinal == yInit) {
                sensV = 0;
            } else {
                sensV = (yFinal - yInit) / dv;
            }
            //on fait le parcours de la pièce pour voir si une pièce est rencontrée
            i = sensH;
            j = sensV;
            System.out.println("test DH= " + dh + "  dv = " + dv);

            System.out.println("test i = " + i + "  j = " + j);

            while (Math.abs(i) < dh || Math.abs(j) < dv) {

                if (jeublanc.isPieceHere(xInit + i, yInit + j) == true || jeunoir.isPieceHere(xInit + i, yInit + j) == true) {
                    return false;

                }
                i += sensH;
                j += sensV;

            }
            if (jeucourant.move(xInit, yInit, xFinal, yFinal)) {
                if (jeucourant.getCouleur() == Couleur.BLANC) {
                    jeunoir.capture(xFinal, yFinal);
                } else {
                    jeublanc.capture(xFinal, yFinal);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isEnd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Couleur getColorCurrentPlayer() {
        return jeucourant.getCouleur();
    }

    @Override
    public Couleur getPieceColor(int x, int y) {
        return jeucourant.getPieceColor(x, y);
    }

}
