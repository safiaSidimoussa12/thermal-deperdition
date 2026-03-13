package com.thermal.model;

import java.io.Serializable;

public class Plancher implements Serializable {
    private int numeroPlancher;
    private String etatPlancher;
    private double surfacePlancher;
    private double kPlancher;

    public Plancher() {}

    public double calculerDeperditionPlancher() {
        return surfacePlancher * kPlancher;
    }

    // Getters & Setters
    public int getNumeroPlancher() { return numeroPlancher; }
    public void setNumeroPlancher(int numeroPlancher) { this.numeroPlancher = numeroPlancher; }
    public String getEtatPlancher() { return etatPlancher; }
    public void setEtatPlancher(String etatPlancher) { this.etatPlancher = etatPlancher; }
    public double getSurfacePlancher() { return surfacePlancher; }
    public void setSurfacePlancher(double surfacePlancher) { this.surfacePlancher = surfacePlancher; }
    public double getkPlancher() { return kPlancher; }
    public void setkPlancher(double kPlancher) { this.kPlancher = kPlancher; }
}
