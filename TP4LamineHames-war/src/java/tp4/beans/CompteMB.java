/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4.beans;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ComponentSystemEvent;
import tp4.CompteBancaire;
import tp4.OperationBancaire;
import tp4.session.GestionnaireDeCompteBancaire;

/**
 *
 * @author MAROUANE
 */
@Named(value = "compteMB")
@RequestScoped
public class CompteMB {

    @EJB
    private GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire;
    private int idCompte;
    private CompteBancaire compteBancaire;
    private OperationBancaire operationBancaire;

    public OperationBancaire getOperationBancaire() {
        return operationBancaire;
    }

    public void setCompteBancaire(CompteBancaire compteBancaire) {
        this.compteBancaire = compteBancaire;
    }

    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public int getIdCompte() {
        return idCompte;
    }

    /**
     * Creates a new instance of CompteMB
     */
    public CompteMB() {
    }

    public List<OperationBancaire> getOperationsDuCompte() {
        return gestionnaireDeCompteBancaire.findOperationsDuCompte(idCompte);
    }

    public void chargeCompte(ComponentSystemEvent event) {
        if (compteBancaire == null) {
            compteBancaire = gestionnaireDeCompteBancaire.findById(idCompte);
        }
    }

    public List<CompteBancaire> getComptes() {
        return gestionnaireDeCompteBancaire.getAllComptes();
    }

    public String supprimerCompte(CompteBancaire compteBancaire) {
        gestionnaireDeCompteBancaire.deleteCompte(compteBancaire);
        return "listeComptes?faces-redirect=true";
    }

    public String showDetails(int id) {

        this.idCompte = id;
        return "detailCompte?faces-redirect=true&amp;includeViewParams=true";
    }

    public String creeComptesTest() {
        gestionnaireDeCompteBancaire.creerComptesTest();
        return "listeComptes?faces-redirect=true";
    }

}
