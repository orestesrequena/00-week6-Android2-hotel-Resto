package com.example.guide.models.hotel;

import java.io.Serializable;
import java.util.Arrays;

public class Fields  implements Serializable {
    public  String commune;
    public  String typologie;
    public String categorie;
    public String capacite_personne;
    public String nom;
    public String adresse;
    public double[] coord_geo_adresse;

    @Override
    public String toString() {
        return "Fields{" +
                "commune='" + commune + '\'' +
                ", typologie='" + typologie + '\'' +
                ", categorie='" + categorie + '\'' +
                ", capacite_personne=" + capacite_personne +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", coord_geo_adresse=" + Arrays.toString(coord_geo_adresse) +
                '}';
    }
}
