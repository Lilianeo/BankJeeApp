/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author MAROUANE
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "CompteBancaire.findAll",
            query = "SELECT c FROM CompteBancaire c"),
    @NamedQuery(name = "CompteBancaire.count",
            query = "SELECT COUNT(c.id) FROM CompteBancaire c"),
    @NamedQuery(name = "CompteBancaire.findById",
            query = "SELECT c FROM CompteBancaire c WHERE c.id = :id"),
    @NamedQuery(name = "CompteBancaire.findOperationsByIdCompte",
            query = "SELECT o FROM CompteBancaire c join c.operations o WHERE c.id = :idCompte")
})
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<OperationBancaire> operations = new ArrayList<>();

    private String nom;
    private int solde;

    public CompteBancaire() {
        nom = "";
        solde = 0;
    }

    public CompteBancaire(String nom, int solde) {
        this.nom = nom;
        this.solde = solde;
        this.operations.add(new OperationBancaire("Création du compte", solde));
        System.out.println("Compte creé " + this.nom);
    }

    public void deposer(int montant) {
        solde += montant;
        operations.add(new OperationBancaire("dépôt", montant));
    }

    public int retirer(int montant) {
        if (montant < solde) {
            solde -= montant;
            operations.add(new OperationBancaire("retrait", montant));
            return montant;
        } else {
            operations.add(new OperationBancaire("retrait refusé", montant));
            return 0;
        }
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String getNom() {
        return nom;
    }

    public int getSolde() {
        return solde;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tp4.CompteBancaire[ id=" + id + " ]";
    }

}
