/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4.beans;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import tp4.CompteBancaire;
import tp4.session.GestionnaireDeCompteBancaire;

/**
 *
 * @author MAROUANE
 */
@Named(value = "nouveauCompteMB")
@RequestScoped
public class NouveauCompteMB {

    @EJB
    private GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire;
    private String nom;
    private int solde;

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public int getSolde() {
        return solde;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
    /**
     * Creates a new instance of NouveauCompteMB
     */
    public NouveauCompteMB() {
    }
    public String creerCompte(){
        if(nom.trim().isEmpty()){
            String msg = "Le nom ne doit pas etre vide";
            FacesMessage facesMsg
                    = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
            FacesContext.getCurrentInstance().addMessage("nouveauCompte:nom", facesMsg);
            return null;
        }
        CompteBancaire compteBancaire = new CompteBancaire(nom, solde);
        gestionnaireDeCompteBancaire.creerCompte(compteBancaire);
        return "listeComptes?faces-redirect=true";
    }
}
