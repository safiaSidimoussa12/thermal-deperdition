package com.thermal.model;

import java.io.Serializable;

public class SourceEnergie implements Serializable {
    private String nomSource;
    private String etatSource;
    private String caracteristiques;

    public SourceEnergie() {}

    public String getNomSource() { return nomSource; }
    public void setNomSource(String nomSource) { this.nomSource = nomSource; }
    public String getEtatSource() { return etatSource; }
    public void setEtatSource(String etatSource) { this.etatSource = etatSource; }
    public String getCaracteristiques() { return caracteristiques; }
    public void setCaracteristiques(String caracteristiques) { this.caracteristiques = caracteristiques; }
}
