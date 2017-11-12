/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4.session;

import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.primefaces.model.SortOrder;
import tp4.CompteBancaire;
import tp4.OperationBancaire;

/**
 *
 * @author MAROUANE
 */
@Stateless
@LocalBean
public class GestionnaireDeCompteBancaire {

    @PersistenceContext
    private EntityManager em;

    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }

    public List getAllComptes() {
        return em.createNamedQuery("CompteBancaire.findAll").getResultList();
    }

    public void creerComptesTest() {
        creerCompte(new CompteBancaire("John Lennon", 150000));
        creerCompte(new CompteBancaire("Paul McCartney", 950000));
        creerCompte(new CompteBancaire("Ringo Starr", 20000));
        creerCompte(new CompteBancaire("Georges Harrisson", 100000));
    }

    public void deleteCompte(CompteBancaire c) {
        em.remove(em.merge(c));
    }

    public CompteBancaire findById(long id) {
        return (CompteBancaire) em.find(CompteBancaire.class, id);
    }

    public List<OperationBancaire> findOperationsDuCompte(long idCompte) {
        Query query = em.createNamedQuery("CompteBancaire.findOperationsByIdCompte");
        query.setParameter("idCompte", idCompte);
        return (List<OperationBancaire>) query.getResultList();
    }

    public CompteBancaire update(CompteBancaire c) {
        return em.merge(c);
    }

    public void transferer(CompteBancaire source, CompteBancaire destination,
            int montant) {
        int val = source.retirer(montant);
        if (val == 0) {
            return;
        }
        destination.deposer(montant);
        update(source);
        update(destination);
    }

    public List<CompteBancaire> getResultList(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        return (List<CompteBancaire>) em.createNamedQuery("CompteBancaire.findAll")
                .setFirstResult(first)
                .setMaxResults(pageSize)
                .getResultList();

    }

    public int count(String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        return ((Long) em.createNamedQuery("CompteBancaire.count")
                .getSingleResult()).intValue();

    }
}
