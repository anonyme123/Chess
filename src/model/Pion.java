/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Florian
 */
public class Pion extends AbstractPiece {

    public Pion(Couleur couleur, Coord coord) {
        super(couleur, coord);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
        if (Coord.coordonnees_valides(xFinal, yFinal)) {
            //System.out.println(this.getX()!=xFinal);

            if (isCatchOk == true) {
                System.out.println("test11");
            }
            if (this.getCouleur() == Couleur.BLANC) {

                //Cas ou nous ne pouvons pas capturer de pion
                if (!isCatchOk) {
                    if (this.getX() == xFinal) {
                        if (this.getY() == 6) { //si on est à la position de début de jeu
                            if (yFinal == this.getY() - 1 || yFinal == this.getY() - 2) {
                                return true;
                            }
                        } else if (yFinal == this.getY() - 1) {
                            return true;
                        }
                    }
                } // OK 
                //Cas de isCatchOK=true
                else if (yFinal == this.getY() - 1 && Math.abs(this.getX() - xFinal) == 1) {
                    return true;
                }

            } else //pion NOIR
            {
                if (!isCatchOk) {
                    if (this.getX() == xFinal) {
                        if (this.getY() == 1) { //si on est à la position de début de jeu
                            if (yFinal == this.getY() + 1 || yFinal == this.getY() + 2) {
                                return true;
                            }
                        } else if (yFinal == this.getY() + 1) {
                            return true;
                        }
                    }
                } else if (yFinal == this.getY() + 1 && Math.abs(this.getX() - xFinal) == 1) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "Pi";
    }

    @Override
    public String getName() {

        return "Pion";
    }

}
