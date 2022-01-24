package org.magadiflo.webapp.jsf3.controllers;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Named
@SessionScoped
public class LenguajeController implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Locale locale;
    private String lenguaje;
    private Map<String, String> lenguajesSoportados;

    @PostConstruct
    public void init() {
        this.locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        this.lenguajesSoportados = new HashMap<>();
        this.lenguajesSoportados.put("Inglés", "en");
        this.lenguajesSoportados.put("Español", "es");
    }

    public void seleccionar(ValueChangeEvent event) {
        String nuevo = event.getNewValue().toString();
        this.lenguajesSoportados.values().forEach(v -> {
            if(v.equals(nuevo)){
                this.locale = new Locale(nuevo);
                FacesContext.getCurrentInstance().getViewRoot().setLocale(this.locale);
            }
        });
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Map<String, String> getLenguajesSoportados() {
        return lenguajesSoportados;
    }

    public void setLenguajesSoportados(Map<String, String> lenguajesSoportados) {
        this.lenguajesSoportados = lenguajesSoportados;
    }
}
