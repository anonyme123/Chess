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
public class Roi extends AbstractPiece {

    private boolean hasMoove;
    public Roi(Couleur couleur, Coord coord) {
        super(couleur, coord);
        hasMoove=false;
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
        if (Coord.coordonnees_valides(xFinal, yFinal)) {

            if ((Math.abs(xFinal - this.getX())<=1) && (Math.abs(yFinal - this.getY())<=1)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "Ro";

    }

    @Override
    public String getName() {
        return "Roi";

    }

    public boolean isHasMoove() {
        return hasMoove;
    }
    

}
