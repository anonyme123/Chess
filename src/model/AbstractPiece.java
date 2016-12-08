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
public abstract class AbstractPiece implements Pieces {
    
    
    public static void main (String[] args){
        Pieces maTour = new Tour(Couleur.NOIR, new Coord(0, 0));
        System.out.println(maTour.getX()+" "+maTour.getY());
        
        System.out.println(maTour.isMoveOk(3, 0, true, true));
        //maTour.move(3,0);
        System.out.println(maTour.getX()+" "+maTour.getY());
        System.out.println(maTour.isMoveOk(3, 3, true, true));
        maTour.move(3,3);
        System.out.println(maTour.getX()+" "+maTour.getY());
        
    }

    private Coord coord;
    private Couleur couleur;

    public AbstractPiece(Couleur couleur, Coord coord) {
        this.couleur = couleur;
        this.coord = coord;
    }

    public int getX() {
        return this.coord.x;
    }

    public int getY() {
        return this.coord.y;
    }

    public Couleur getCouleur() {
        return this.couleur;
    }

    public abstract String toString();

    public boolean move(int x, int y) {
        this.coord.x = x;
        this.coord.y = y;
        return true;
    }

    public boolean capture() {
        this.coord.x = -1;
        this.coord.y = -1;
        return true;

    }

    public abstract boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible);
    
  

}
