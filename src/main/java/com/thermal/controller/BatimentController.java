package com.thermal.controller;

import com.thermal.model.*;
import com.thermal.service.DeperditionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BatimentController {

    @Autowired
    private DeperditionService deperditionService;

    // ─── Accueil ────────────────────────────────────────────────────────────
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // ─── Création bâtiment ──────────────────────────────────────────────────
    @GetMapping("/batiment/nouveau")
    public String formulaireBatiment(Model model) {
        model.addAttribute("batiment", new Batiment());
        return "batiment-form";
    }

    @PostMapping("/batiment/creer")
    public String creerBatiment(
            @RequestParam String codeBatiment,
            @RequestParam String adresseBatiment,
            @RequestParam String coordonneesGeographiques,
            @RequestParam String typeBatiment,
            @RequestParam double surface,
            @RequestParam double volume,
            @RequestParam int anneeConstruction,
            @RequestParam int nombreNiveaux,
            @RequestParam int nombreOccupants,
            @RequestParam String nomZone,
            @RequestParam String nomSource,
            HttpSession session) {

        Batiment b = new Batiment();
        b.setCodeBatiment(codeBatiment);
        b.setAdresseBatiment(adresseBatiment);
        b.setCoordonneesGeographiques(coordonneesGeographiques);
        b.setTypeBatiment(typeBatiment);
        b.setSurface(surface);
        b.setVolume(volume);
        b.setAnneeConstruction(anneeConstruction);
        b.setNombreNiveaux(nombreNiveaux);
        b.setNombreOccupants(nombreOccupants);

        ZoneClimatique zone = new ZoneClimatique(nomZone);
        b.setZoneClimatique(zone);

        SourceEnergie source = new SourceEnergie();
        source.setNomSource(nomSource);
        b.setSourceEnergie(source);

        session.setAttribute("batiment", b);
        return "redirect:/batiment/elements";
    }

    // ─── Gestion des éléments ───────────────────────────────────────────────
    @GetMapping("/batiment/elements")
    public String elements(Model model, HttpSession session) {
        Batiment b = getBatiment(session);
        model.addAttribute("batiment", b);
        model.addAttribute("kMurs", DeperditionService.K_MURS);
        model.addAttribute("kOuvrants", DeperditionService.K_OUVRANTS);
        model.addAttribute("kPlanchers", DeperditionService.K_PLANCHERS);
        model.addAttribute("kToitures", DeperditionService.K_TOITURES);
        return "elements";
    }

    // ─── Ajout Mur ──────────────────────────────────────────────────────────
    @PostMapping("/batiment/ajouterMur")
    public String ajouterMur(
            @RequestParam double longueurMur,
            @RequestParam double hauteurMur,
            @RequestParam String etatMur,
            @RequestParam String typeMur,
            HttpSession session) {

        Batiment b = getBatiment(session);
        Mur mur = new Mur();
        mur.setNumeroMur(b.getMurs().size() + 1);
        mur.setLongueurMur(longueurMur);
        mur.setHauteurMur(hauteurMur);
        mur.setEtatMur(etatMur);
        mur.setkMur(DeperditionService.K_MURS.getOrDefault(typeMur, 1.0));
        b.getMurs().add(mur);
        return "redirect:/batiment/elements";
    }

    // ─── Ajout Ouvrant ──────────────────────────────────────────────────────
    @PostMapping("/batiment/ajouterOuvrant")
    public String ajouterOuvrant(
            @RequestParam double surfaceOuvrant,
            @RequestParam String etatOuvrant,
            @RequestParam String typeOuvrant,
            HttpSession session) {

        Batiment b = getBatiment(session);
        Ouvrant o = new Ouvrant();
        o.setNumeroOuvrant(b.getOuvrants().size() + 1);
        o.setSurfaceOuvrant(surfaceOuvrant);
        o.setEtatOuvrant(etatOuvrant);
        o.setkOuvrant(DeperditionService.K_OUVRANTS.getOrDefault(typeOuvrant, 1.0));
        b.getOuvrants().add(o);
        return "redirect:/batiment/elements";
    }

    // ─── Ajout Plancher ─────────────────────────────────────────────────────
    @PostMapping("/batiment/ajouterPlancher")
    public String ajouterPlancher(
            @RequestParam double surfacePlancher,
            @RequestParam String etatPlancher,
            @RequestParam String typePlancher,
            HttpSession session) {

        Batiment b = getBatiment(session);
        Plancher p = new Plancher();
        p.setNumeroPlancher(b.getPlanchers().size() + 1);
        p.setSurfacePlancher(surfacePlancher);
        p.setEtatPlancher(etatPlancher);
        p.setkPlancher(DeperditionService.K_PLANCHERS.getOrDefault(typePlancher, 1.0));
        b.getPlanchers().add(p);
        return "redirect:/batiment/elements";
    }

    // ─── Ajout Toiture ──────────────────────────────────────────────────────
    @PostMapping("/batiment/ajouterToiture")
    public String ajouterToiture(
            @RequestParam double surfaceToit,
            @RequestParam String etatToiture,
            @RequestParam String typeToiture,
            HttpSession session) {

        Batiment b = getBatiment(session);
        Toiture t = new Toiture();
        t.setNumeroToiture(b.getToitures().size() + 1);
        t.setSurfaceToit(surfaceToit);
        t.setTypeToiture(typeToiture);
        t.setEtatToiture(etatToiture);
        t.setkToiture(DeperditionService.K_TOITURES.getOrDefault(typeToiture, 1.0));
        b.getToitures().add(t);
        return "redirect:/batiment/elements";
    }

    // ─── Calcul & Résultats ─────────────────────────────────────────────────
    @GetMapping("/batiment/calculer")
    public String calculer(Model model, HttpSession session) {
        Batiment b = getBatiment(session);
        double deperdition = deperditionService.calculerDeperditionTotale(b);
        double consommation = deperditionService.calculerConsommation(b);
        String classe = deperditionService.classerEnergie(b);
        String couleur = deperditionService.getCouleurClasse(classe);

        model.addAttribute("batiment", b);
        model.addAttribute("deperdition", String.format("%.2f", deperdition));
        model.addAttribute("consommation", String.format("%.2f", consommation));
        model.addAttribute("classe", classe);
        model.addAttribute("couleur", couleur);
        return "resultat";
    }

    // ─── Reset ──────────────────────────────────────────────────────────────
    @GetMapping("/reset")
    public String reset(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    private Batiment getBatiment(HttpSession session) {
        Batiment b = (Batiment) session.getAttribute("batiment");
        if (b == null) b = new Batiment();
        return b;
    }
}
