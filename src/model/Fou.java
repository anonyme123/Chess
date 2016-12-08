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
public class Fou extends AbstractPiece {

    public Fou(Couleur couleur, Coord coord) {
        super(couleur, coord);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
        if(Coord.coordonnees_valides(xFinal, yFinal)) {
            //System.out.println(this.getX()!=xFinal);
            
                if(Math.abs(xFinal-this.getX())==Math.abs(yFinal-this.getY())) {
                    return true;
                }
            
        }
        
        return false;
    }

    @Override
    public String toString() {
        return "Fo";
    }

    @Override
    public String getName() {
        return "Fou";
        
    }
    
    
}
