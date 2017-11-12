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
@Named(value = "transfertMB")
@RequestScoped
public class TransfertMB {

    @EJB
    private GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire;
    private long sourceId;
    private long destinationId;
    private int montantTransfert;

    /**
     * Creates a new instance of TransfertMB
     */
    public TransfertMB() {
    }

    public long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(long destinationId) {
        this.destinationId = destinationId;
    }

    public int getMontant() {
        return montantTransfert;
    }

    public void setMontant(int montant) {
        this.montantTransfert = montant;
    }

    public long getSourceId() {
        return sourceId;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
    }

    public String transferer() {
        boolean ok = true;
        CompteBancaire compteSource
                = gestionnaireDeCompteBancaire.findById(sourceId);
        if (compteSource == null) {
            String msg = "Compte source n'existe pas";
            FacesMessage facesMsg
                    = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
            FacesContext.getCurrentInstance().addMessage("transfert:source", facesMsg);
            ok = false;
        }
        CompteBancaire compteDestination
                = gestionnaireDeCompteBancaire.findById((long) destinationId);
        if (compteDestination == null) {
            String msg = "Compte destination n'existe pas";
            FacesMessage facesMsg
                    = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
            FacesContext.getCurrentInstance().addMessage("transfert:destination", facesMsg);
            ok = false;
        }
        if (compteSource != null) {
            double soldeCompteSource = compteSource.getSolde();
            if (soldeCompteSource < montantTransfert) {
                String msg = "Pas assez d'argent sur le compte de "
                        + compteSource.getNom();
                FacesMessage facesMsg
                        = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
                FacesContext.getCurrentInstance().addMessage("transfert:montant", facesMsg);
                ok = false;
            }
        }
        if (ok) {
            gestionnaireDeCompteBancaire.transferer(compteSource, compteDestination,
                    montantTransfert);
        }
        return null;
    }
}
