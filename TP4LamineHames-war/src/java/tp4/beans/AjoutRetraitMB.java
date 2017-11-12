/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4.beans;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import tp4.CompteBancaire;
import tp4.session.GestionnaireDeCompteBancaire;

/**
 *
 * @author MAROUANE
 */
@Named(value = "ajoutRetraitMB")
@ViewScoped
public class AjoutRetraitMB implements Serializable {

    @EJB
    private GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire;
    private long idCompte;

    private CompteBancaire compte;

    private String typeOperation;
    private int montant;

    /**
     * Creates a new instance of AjoutRetraitMB
     */
    public AjoutRetraitMB() {
    }

    public void chargeClient() {
        if (compte == null) {
            compte = gestionnaireDeCompteBancaire.findById(idCompte);
        }
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(long idCompte) {
        this.idCompte = idCompte;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public String operation() {
        if (typeOperation.equals("depot")) {
            compte.deposer(montant);
        } else {
            compte.retirer(montant);
        }
        gestionnaireDeCompteBancaire.update(compte);

        return "listeComptes?faces-redirect=true";
    }
}
