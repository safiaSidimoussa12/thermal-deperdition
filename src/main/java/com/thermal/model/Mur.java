package com.thermal.model;

import java.io.Serializable;

public class Mur implements Serializable {
    private int numeroMur;
    private double longueurMur;
    private double hauteurMur;
    private String etatMur;
    private double kMur;

    public Mur() {}

    public double calculerDeperditionMur() {
        double surfaceMur = longueurMur * hauteurMur;
        return surfaceMur * kMur;
    }

    public double getSurfaceMur() {
        return longueurMur * hauteurMur;
    }

    // Getters & Setters
    public int getNumeroMur() { return numeroMur; }
    public void setNumeroMur(int numeroMur) { this.numeroMur = numeroMur; }
    public double getLongueurMur() { return longueurMur; }
    public void setLongueurMur(double longueurMur) { this.longueurMur = longueurMur; }
    public double getHauteurMur() { return hauteurMur; }
    public void setHauteurMur(double hauteurMur) { this.hauteurMur = hauteurMur; }
    public String getEtatMur() { return etatMur; }
    public void setEtatMur(String etatMur) { this.etatMur = etatMur; }
    public double getkMur() { return kMur; }
    public void setkMur(double kMur) { this.kMur = kMur; }
}
