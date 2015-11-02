/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amitinside.nb.rcp;

import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.modules.ModuleInstall;

public class Installer extends ModuleInstall {

    private static final long serialVersionUID = 1L;

    @Override
    public void restored() {
        // TODO
    }

    @Override
    public boolean closing() {
        NotifyDescriptor d = new NotifyDescriptor.Confirmation(
                "Do you really want to exit the application?",
                "Exit",
                NotifyDescriptor.YES_NO_OPTION);
        if (DialogDisplayer.getDefault().notify(d) == NotifyDescriptor.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }
}
