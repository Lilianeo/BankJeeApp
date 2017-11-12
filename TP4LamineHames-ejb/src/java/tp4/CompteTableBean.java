/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import tp4.session.GestionnaireDeCompteBancaire;

@ManagedBean
@ViewScoped
public class CompteTableBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EJB
    GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire;

    private LazyDataModel<CompteBancaire> model;

    @PostConstruct
    public void init() {
        this.model = new LazyDataModel<CompteBancaire>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<CompteBancaire> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List<CompteBancaire> result = gestionnaireDeCompteBancaire.getResultList(first, pageSize, sortField, sortOrder, filters);
                
                model.setRowCount(gestionnaireDeCompteBancaire.count(sortField, sortOrder, filters));
                return result;
            }

        };

    }

    public LazyDataModel<CompteBancaire> getModel() {
        return model;
    }

    public void setModel(LazyDataModel<CompteBancaire> model) {
        this.model = model;
    }

}
