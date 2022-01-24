package org.magadiflo.webapp.jsf3.converters;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.magadiflo.webapp.jsf3.entities.Categoria;
import org.magadiflo.webapp.jsf3.services.ProductoService;

import java.util.Optional;

@RequestScoped
@Named("categoriaConverter")
public class CategoriaConverter implements Converter<Categoria> {

    @Inject
    private ProductoService service;

    @Override
    public Categoria getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        if(id == null){
            return null;
        }
        Optional<Categoria> categoriaOptional = this.service.porIdCategoria(Long.valueOf(id));
        return categoriaOptional.orElse(null);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Categoria categoria) {
        if(categoria == null){
            return "0";
        }
        return categoria.getId().toString();
    }
}
