/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ticherhaz.bean;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Hazman
 */
@Named(value = "managedBean")
@Dependent
public class ManagedBean {

    /**
     * Creates a new instance of ManagedBean
     */
    public ManagedBean() {
    }
    
}
