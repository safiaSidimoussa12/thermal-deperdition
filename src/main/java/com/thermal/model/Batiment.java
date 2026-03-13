package com.thermal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Batiment implements Serializable {
    private String codeBatiment;
    private String adresseBatiment;
    private String coordonneesGeographiques;
    private String typeBatiment;
    private double surface;
    private double volume;
    private int anneeConstruction;
    private int nombreNiveaux;
    private int nombreOccupants;

    private ZoneClimatique zoneClimatique;
    private SourceEnergie sourceEnergie;

    private List<Mur> murs = new ArrayList<>();
    private List<Ouvrant> ouvrants = new ArrayList<>();
    private List<Plancher> planchers = new ArrayList<>();
    private List<Toiture> toitures = new ArrayList<>();

    public Batiment() {
    }

    public double calculerDeperdition() {
        double total = 0;
        for (Mur m : murs)
            total += m.calculerDeperditionMur();
        for (Ouvrant o : ouvrants)
            total += o.calculerDeperditionOuvrant();
        for (Plancher p : planchers)
            total += p.calculerDeperditionPlancher();
        for (Toiture t : toitures)
            total += t.calculerDeperditionToiture();
        return total;
    }

    /**
     * Consommation energetique annuelle en kWh/m2/an.
     *
     * Formule : (deperditionTotale x deltaT x heuresChauffage) / (1000 x surface)
     * - deperditionTotale : somme U*S de tous les elements (W/K)
     * - deltaT = 15 C : ecart interieur(20C) / exterieur(5C)
     * - heuresChauffage : 1800 h/an (standard RT2012)
     * - / 1000 : W -> kW
     * - / surface : ramene au m2
     *
     * Resultat en kWh/m2/an, comparable au bareme DPE A a G.
     */
    public double calculerConsommation() {
        if (surface == 0)
            return 0;
        final double deltaT = 15.0;
        final double heuresChauffage = 1800.0;
        return (calculerDeperdition() * deltaT * heuresChauffage) / (1000.0 * surface);
    }

    public String classerEnergie() {
        double conso = calculerConsommation();
        if (conso <= 70)
            return "A";
        if (conso <= 110)
            return "B";
        if (conso <= 180)
            return "C";
        if (conso <= 250)
            return "D";
        if (conso <= 330)
            return "E";
        if (conso <= 420)
            return "F";
        return "G";
    }

    // Getters & Setters
    public String getCodeBatiment() {
        return codeBatiment;
    }

    public void setCodeBatiment(String v) {
        this.codeBatiment = v;
    }

    public String getAdresseBatiment() {
        return adresseBatiment;
    }

    public void setAdresseBatiment(String v) {
        this.adresseBatiment = v;
    }

    public String getCoordonneesGeographiques() {
        return coordonneesGeographiques;
    }

    public void setCoordonneesGeographiques(String v) {
        this.coordonneesGeographiques = v;
    }

    public String getTypeBatiment() {
        return typeBatiment;
    }

    public void setTypeBatiment(String v) {
        this.typeBatiment = v;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double v) {
        this.surface = v;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double v) {
        this.volume = v;
    }

    public int getAnneeConstruction() {
        return anneeConstruction;
    }

    public void setAnneeConstruction(int v) {
        this.anneeConstruction = v;
    }

    public int getNombreNiveaux() {
        return nombreNiveaux;
    }

    public void setNombreNiveaux(int v) {
        this.nombreNiveaux = v;
    }

    public int getNombreOccupants() {
        return nombreOccupants;
    }

    public void setNombreOccupants(int v) {
        this.nombreOccupants = v;
    }

    public ZoneClimatique getZoneClimatique() {
        return zoneClimatique;
    }

    public void setZoneClimatique(ZoneClimatique v) {
        this.zoneClimatique = v;
    }

    public SourceEnergie getSourceEnergie() {
        return sourceEnergie;
    }

    public void setSourceEnergie(SourceEnergie v) {
        this.sourceEnergie = v;
    }

    public List<Mur> getMurs() {
        return murs;
    }

    public void setMurs(List<Mur> v) {
        this.murs = v;
    }

    public List<Ouvrant> getOuvrants() {
        return ouvrants;
    }

    public void setOuvrants(List<Ouvrant> v) {
        this.ouvrants = v;
    }

    public List<Plancher> getPlanchers() {
        return planchers;
    }

    public void setPlanchers(List<Plancher> v) {
        this.planchers = v;
    }

    public List<Toiture> getToitures() {
        return toitures;
    }

    public void setToitures(List<Toiture> v) {
        this.toitures = v;
    }
}