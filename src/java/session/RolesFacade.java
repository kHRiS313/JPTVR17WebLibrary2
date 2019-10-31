/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Roles;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Melnikov
 */
@Stateless
public class RolesFacade extends AbstractFacade<Roles> {

    @PersistenceContext(unitName = "JPTVR17WebLibraryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolesFacade() {
        super(Roles.class);
    }

    public Roles getRoleByName(String roleName) {
        try {
            return (Roles) em.createQuery("SELECT r FROM Roles r WHERE r.role = :roleName")
                    .setParameter("roleName", roleName)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
}
