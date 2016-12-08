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
public class Cavalier extends AbstractPiece {

    public Cavalier(Couleur couleur, Coord coord) {
        super(couleur, coord);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
        if (Coord.coordonnees_valides(xFinal, yFinal)) {

            int z = Math.abs(xFinal - this.getX()) + Math.abs(yFinal - this.getY());
            if (z != 3) {
                return false;
            }

            if (Math.abs(xFinal - this.getX()) > 2 || Math.abs(yFinal - this.getY()) > 2) {
                return false;
            }
            return true;

        }

        return false;
    }

    @Override
    public String toString() {
        return "Ca";
    }

    @Override
    public String getName() {
        return "Cavalier";

    }

}
