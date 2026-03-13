package com.thermal.model;

import java.io.Serializable;

public class Ouvrant implements Serializable {
    private int numeroOuvrant;
    private double surfaceOuvrant;
    private double kOuvrant;
    private String etatOuvrant;

    public Ouvrant() {}

    public double calculerDeperditionOuvrant() {
        return surfaceOuvrant * kOuvrant;
    }

    // Getters & Setters
    public int getNumeroOuvrant() { return numeroOuvrant; }
    public void setNumeroOuvrant(int numeroOuvrant) { this.numeroOuvrant = numeroOuvrant; }
    public double getSurfaceOuvrant() { return surfaceOuvrant; }
    public void setSurfaceOuvrant(double surfaceOuvrant) { this.surfaceOuvrant = surfaceOuvrant; }
    public double getkOuvrant() { return kOuvrant; }
    public void setkOuvrant(double kOuvrant) { this.kOuvrant = kOuvrant; }
    public String getEtatOuvrant() { return etatOuvrant; }
    public void setEtatOuvrant(String etatOuvrant) { this.etatOuvrant = etatOuvrant; }
}
