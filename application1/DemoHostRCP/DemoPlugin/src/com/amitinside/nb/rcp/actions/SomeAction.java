/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amitinside.nb.rcp.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Edit",
        id = "com.amitinside.nb.rcp.actions.SomeAction")
@ActionRegistration(
        iconBase = "com/amitinside/nb/rcp/actions/Burn-icon.png",
        displayName = "#CTL_SomeAction")
@ActionReference(path = "Menu/File", position = 1300)
@Messages("CTL_SomeAction=SomeAction")
public final class SomeAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
    }
}
