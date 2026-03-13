package com.thermal.model;

import java.io.Serializable;

public class Toiture implements Serializable {
    private int numeroToiture;
    private String typeToiture;
    private String etatToiture;
    private double surfaceToit;
    private double kToiture;

    public Toiture() {}

    public double calculerDeperditionToiture() {
        return surfaceToit * kToiture;
    }

    // Getters & Setters
    public int getNumeroToiture() { return numeroToiture; }
    public void setNumeroToiture(int numeroToiture) { this.numeroToiture = numeroToiture; }
    public String getTypeToiture() { return typeToiture; }
    public void setTypeToiture(String typeToiture) { this.typeToiture = typeToiture; }
    public String getEtatToiture() { return etatToiture; }
    public void setEtatToiture(String etatToiture) { this.etatToiture = etatToiture; }
    public double getSurfaceToit() { return surfaceToit; }
    public void setSurfaceToit(double surfaceToit) { this.surfaceToit = surfaceToit; }
    public double getkToiture() { return kToiture; }
    public void setkToiture(double kToiture) { this.kToiture = kToiture; }
}
