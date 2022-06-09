package com.example.gestiondesclimats;

public class Climat {
    private int Id;
    private String NomVille;
    private String Pays;
    private int Temperature;
    private int PourcentageNuages;

    public Climat() {
    }

    public Climat(int id, String nomVille, String pays, int temperature, int pourcentageNuages) {
        Id = id;
        NomVille = nomVille;
        Pays = pays;
        Temperature = temperature;
        PourcentageNuages = pourcentageNuages;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNomVille() {
        return NomVille;
    }

    public void setNomVille(String nomVille) {
        NomVille = nomVille;
    }

    public String getPays() {
        return Pays;
    }

    public void setPays(String pays) {
        Pays = pays;
    }

    public int getTemperature() {
        return Temperature;
    }

    public void setTemperature(int temperature) {
        Temperature = temperature;
    }

    public int getPourcentageNuages() {
        return PourcentageNuages;
    }

    public void setPourcentageNuages(int pourcentageNuages) {
        PourcentageNuages = pourcentageNuages;
    }
}
