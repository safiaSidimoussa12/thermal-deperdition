package com.thermal.model;

import java.io.Serializable;

public class ZoneClimatique implements Serializable {
    private String nomZoneClimatique;

    public ZoneClimatique() {}
    public ZoneClimatique(String nomZoneClimatique) {
        this.nomZoneClimatique = nomZoneClimatique;
    }

    public String getNomZoneClimatique() { return nomZoneClimatique; }
    public void setNomZoneClimatique(String nomZoneClimatique) { this.nomZoneClimatique = nomZoneClimatique; }
}
