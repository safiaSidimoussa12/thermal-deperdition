# 🏗️ ThermoCalc — Calcul de Déperditions Thermiques

> Application web Java/Spring Boot pour calculer la déperdition thermique d'un bâtiment et déterminer sa classe énergétique DPE (A → G).

---

## 📋 Table des matières

- [Présentation](#-présentation)
- [Technologies](#-technologies)
- [Structure du projet](#-structure-du-projet)
- [Diagramme de classes](#-diagramme-de-classes)
- [Formules de calcul](#-formules-de-calcul)
- [Classification DPE](#-classification-dpe)
- [Installation & Lancement](#-installation--lancement)
- [Utilisation](#-utilisation)
- [Exemple numérique](#-exemple-numérique)

---

## 📌 Présentation

**ThermoCalc** est une application web académique réalisée dans le cadre du TP SIA M1-IL.  
Elle permet à un utilisateur de :

- Créer un bâtiment avec ses caractéristiques (surface, zone climatique, source d'énergie…)
- Ajouter ses éléments constructifs : **murs**, **ouvrants**, **planchers**, **toitures**
- Calculer automatiquement la **déperdition thermique totale**
- Obtenir la **consommation énergétique** en kWh/m²/an
- Visualiser la **classe DPE** (A à G) avec l'échelle colorée

---

## 🛠️ Technologies

| Technologie | Version | Rôle |
|---|---|---|
| Java | 17+ | Langage principal |
| Spring Boot | 3.4.5 | Framework web |
| Spring MVC | — | Contrôleurs HTTP |
| Thymeleaf | — | Templates HTML dynamiques |
| Maven | 3.x | Gestion des dépendances |
| HTML / CSS | — | Interface utilisateur |
| Git / GitHub | — | Versioning |

---

## 📁 Structure du projet

```
thermal-app/
├── pom.xml
└── src/
    └── main/
        ├── java/com/thermal/
        │   ├── ThermalApplication.java          # Point d'entrée Spring Boot
        │   ├── model/
        │   │   ├── Batiment.java                # Classe principale
        │   │   ├── Mur.java                     # Élément mur
        │   │   ├── Ouvrant.java                 # Fenêtres / portes
        │   │   ├── Plancher.java                # Plancher
        │   │   ├── Toiture.java                 # Toiture
        │   │   ├── ZoneClimatique.java           # Zone H1/H2/H3/H4
        │   │   └── SourceEnergie.java            # Source d'énergie
        │   ├── service/
        │   │   └── DeperditionService.java       # Coefficients K + calculs
        │   └── controller/
        │       └── BatimentController.java       # Routes HTTP
        └── resources/
            ├── application.properties
            └── templates/
                ├── index.html                   # Accueil
                ├── batiment-form.html           # Formulaire bâtiment
                ├── elements.html                # Ajout des éléments
                └── resultat.html                # Résultats + DPE
```

---



## 📐 Formules de calcul

### Déperdition par élément (W/K)

```
Mur      : Dep = longueur × hauteur × K_mur
Ouvrant  : Dep = surface × K_ouvrant
Plancher : Dep = surface × K_plancher
Toiture  : Dep = surface × K_toiture
```

### Déperdition totale

```
Dep_totale = Σ Dep_murs + Σ Dep_ouvrants + Σ Dep_planchers + Σ Dep_toitures
```

> Résultat en **W/K** (Watts par Kelvin)

### Consommation énergétique (kWh/m²/an)

```
Consommation = (Dep_totale × ΔT × 1800) / (1000 × surface)
```

| Paramètre | Valeur | Description |
|---|---|---|
| `ΔT` | dynamique | T_intérieur (20°C) − T_extérieur |
| `1800` | h/an | Heures de chauffe annuelles (RT2012) |
| `1000` | — | Conversion W → kW |

### ΔT selon la zone climatique

| Zone | T_extérieur | ΔT |
|---|---|---|
| H1 — Grand Nord / Montagne | −2°C | **22°C** |
| H2 — Centre / Bretagne | 5°C | **15°C** |
| H3 — Méditerranée / Sud | 8°C | **12°C** |
| H4 — DOM-TOM | 18°C | **2°C** |

---

## 🏷️ Classification DPE

| Classe | Consommation (kWh/m²/an) | Description |
|---|---|---|
| 🟢 **A** | ≤ 70 | Bâtiment à énergie positive |
| 🟢 **B** | 71 – 110 | Très bonne performance |
| 🟡 **C** | 111 – 180 | Performance moyenne |
| 🟠 **D** | 181 – 250 | Consommation standard |
| 🔴 **E** | 251 – 330 | Logement énergivore |
| 🔴 **F** | 331 – 420 | Passoire thermique |
| ⚫ **G** | > 420 | Passoire thermique extrême |

---

## 🚀 Installation & Lancement

### Prérequis

- Java 17 ou supérieur
- Maven 3.x
- Git

### Cloner le projet

```bash
git clone https://github.com/VOTRE_USERNAME/thermal-app.git
cd thermal-app
```

### Lancer l'application

```bash
mvn spring-boot:run
```

### Accéder à l'application

Ouvrir le navigateur sur :

```
http://localhost:8080
```

### Changer le port (optionnel)

Dans `src/main/resources/application.properties` :

```properties
server.port=8081
```

---

## 📖 Utilisation

L'application suit un flux en **3 étapes** :

### Étape 1 — Créer le bâtiment

Renseigner :
- Code, adresse, type de bâtiment
- Surface (m²), volume (m³), année de construction
- Nombre de niveaux, nombre d'occupants
- Zone climatique (H1 à H4)
- Source d'énergie

### Étape 2 — Ajouter les éléments

Pour chaque élément, choisir le **type** dans la liste déroulante (le coefficient K est automatique) :

| Élément | Paramètres |
|---|---|
| Mur | Longueur, hauteur, type, état |
| Ouvrant | Surface, type, état |
| Plancher | Surface, type, état |
| Toiture | Surface, type, état |

### Étape 3 — Calculer & consulter les résultats

- Déperdition totale **(W/K)**
- Consommation énergétique **(kWh/m²/an)**
- Classe énergétique **A → G**
- Échelle DPE colorée avec description de chaque classe

---

## 🔢 Exemple numérique

**Bâtiment test — Zone H2 — Surface 100 m²**

| Élément | Type | Dimensions | k | Déperdition |
|---|---|---|---|---|
| Mur 1 | Sans isolation | 8m × 3m | 2.5 | 60.0 W/K |
| Mur 2 | Sans isolation | 6m × 3m | 2.5 | 45.0 W/K |
| Ouvrant | Simple vitrage | 3 m² | 5.8 | 17.4 W/K |
| Plancher | Béton non isolé | 100 m² | 2.0 | 200.0 W/K |
| Toiture | Inclinée tuiles | 100 m² | 2.5 | 250.0 W/K |
| **TOTAL** | | | | **572.4 W/K** |

**Calcul de la consommation :**

```
Consommation = (572.4 × 15 × 1800) / (1000 × 100)
             = 15 454 800 / 100 000
             = 154.5 kWh/m²/an
             → Classe C ✅
```

---

## 👥 Équipe

Projet réalisé dans le cadre du **TP SIA — M1 Informatique**  
Département d'informatique — 2026

---

## 📄 Licence

Projet académique — Usage éducatif uniquement.
