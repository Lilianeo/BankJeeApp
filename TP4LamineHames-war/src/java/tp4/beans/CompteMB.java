/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4.beans;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import tp4.CompteBancaire;
import tp4.session.GestionnaireDeCompteBancaire;

/**
 *
 * @author MAROUANE
 */
@Named(value = "compteMB")
@Dependent
public class CompteMB {

    private long idCompte;

    public long getIdCompte() {
        return idCompte;
    }
    private CompteBancaire compteBancaire;

    @EJB
    private GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire;

    /**
     * Creates a new instance of CompteMB
     */
    public CompteMB() {

    }

    public List<CompteBancaire> getComptes() {
        //gestionnaireDeCompteBancaire.creerComptesTest();
        return gestionnaireDeCompteBancaire.getAllComptes();
    }

    public String showDetails(int id) {
        this.idCompte = id;
        return "detailCompte?faces-redirect=true&amp;includeViewParams=true";
    }
}
