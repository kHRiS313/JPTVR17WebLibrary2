package util;


import entity.Roles;
import entity.User;
import entity.UserRoles;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import session.RolesFacade;
import session.UserRolesFacade;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Melnikov
 */
public class RoleManager {
    private RolesFacade rolesFacade;
    private UserRolesFacade userRolesFacade;

    public RoleManager() {
        Context context;
        try {
            context = new InitialContext();
            this.rolesFacade = (RolesFacade) context.lookup("java:module/RolesFacade");
            this.userRolesFacade = (UserRolesFacade) context.lookup("java:module/UserRolesFacade");
        } catch (NamingException ex) {
            Logger.getLogger(RoleManager.class.getName()).log(Level.SEVERE, "Не удалось найти бин", ex);
        }
    }
    
    public boolean isRoleUser(String roleName, User user){
        roleName = roleName.toUpperCase();
        Roles role = rolesFacade.getRoleByName(roleName);
        List<Roles> listRoles = userRolesFacade.findRolesByUser(user);
        if(null == role || null == listRoles) return false;
        return listRoles.contains(role);
    }
}
