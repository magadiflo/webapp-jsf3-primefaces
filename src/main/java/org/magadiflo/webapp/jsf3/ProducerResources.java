package org.magadiflo.webapp.jsf3;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.util.Locale;
import java.util.ResourceBundle;

@ApplicationScoped
public class ProducerResources {

    @Produces
    @RequestScoped
    public FacesContext beanFacesContext() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        return facesContext;
    }

    @Produces
    @Named("msg")
    public ResourceBundle beanBundle() {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        return ResourceBundle.getBundle("messages", locale);
    }

}
