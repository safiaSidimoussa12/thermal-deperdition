package com.thermal.service;

import com.thermal.model.Batiment;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DeperditionService {

    // Coefficients K pour les murs (W/m²/K)
    public static final Map<String, Double> K_MURS = new LinkedHashMap<>();
    // Coefficients K pour les ouvrants
    public static final Map<String, Double> K_OUVRANTS = new LinkedHashMap<>();
    // Coefficients K pour les planchers
    public static final Map<String, Double> K_PLANCHERS = new LinkedHashMap<>();
    // Coefficients K pour les toitures
    public static final Map<String, Double> K_TOITURES = new LinkedHashMap<>();

    static {
        // Murs
        K_MURS.put("Mur sans isolation", 2.5);
        K_MURS.put("Mur double paroi", 1.8);
        K_MURS.put("Mur double paroi avec isolation", 0.6);
        K_MURS.put("Mur à ossature bois isolé", 0.4);
        K_MURS.put("Mur en béton isolé", 0.5);

        // Ouvrants
        K_OUVRANTS.put("Fenêtre simple vitrage", 5.8);
        K_OUVRANTS.put("Fenêtre double vitrage", 2.8);
        K_OUVRANTS.put("Fenêtre double vitrage argon", 1.6);
        K_OUVRANTS.put("Fenêtre triple vitrage", 0.8);
        K_OUVRANTS.put("Porte pleine bois", 3.0);
        K_OUVRANTS.put("Porte isolée", 1.5);

        // Planchers
        K_PLANCHERS.put("Plancher béton non isolé", 2.0);
        K_PLANCHERS.put("Plancher béton isolé", 0.5);
        K_PLANCHERS.put("Plancher bois", 1.2);
        K_PLANCHERS.put("Plancher parquet", 1.0);
        K_PLANCHERS.put("Plancher carrelage isolé", 0.6);

        // Toitures
        K_TOITURES.put("Toiture plate non isolée", 3.5);
        K_TOITURES.put("Toiture plate isolée", 0.4);
        K_TOITURES.put("Toiture inclinée tuiles", 2.5);
        K_TOITURES.put("Toiture inclinée isolée", 0.3);
        K_TOITURES.put("Toiture terrasse isolée", 0.5);
    }

    public double calculerDeperditionTotale(Batiment batiment) {
        return batiment.calculerDeperdition();
    }

    public double calculerConsommation(Batiment batiment) {
        return batiment.calculerConsommation();
    }

    public String classerEnergie(Batiment batiment) {
        return batiment.classerEnergie();
    }

    public String getCouleurClasse(String classe) {
        return switch (classe) {
            case "A" -> "#2e7d32";
            case "B" -> "#558b2f";
            case "C" -> "#f9a825";
            case "D" -> "#ef6c00";
            case "E" -> "#e53935";
            case "F" -> "#b71c1c";
            case "G" -> "#4a0000";
            default  -> "#666666";
        };
    }
}
