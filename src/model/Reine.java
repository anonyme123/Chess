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
public class Reine extends AbstractPiece {

    public Reine(Couleur couleur, Coord coord) {
        super(couleur, coord);
    }

   
     @Override
    public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
        if(Coord.coordonnees_valides(xFinal, yFinal)) {
           
                if(Math.abs(xFinal-this.getX())==Math.abs(yFinal-this.getY())) {
                    return true;
                }
                if(this.getX()==xFinal || this.getY()==yFinal) {
                    return true;
                }
            
        }
        
        return false;
    }

    @Override
    public String toString() {
        return "Re";
    }

    @Override
    public String getName() {
        
        return "Reine";
    }
    
    
}
