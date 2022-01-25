package org.magadiflo.webapp.jsf3.controllers;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.magadiflo.webapp.jsf3.entities.Categoria;
import org.magadiflo.webapp.jsf3.entities.Producto;
import org.magadiflo.webapp.jsf3.services.ProductoService;

import java.util.List;
import java.util.ResourceBundle;

/**
 * @Model, es un stereotipo que agrupa @Named y @RequestScoped
 */
@Model
public class ProductoController {

    private Producto producto;
    private Long id;

    @Inject
    private ProductoService service;

    @Produces
    @Model
    public String titulo() {
        return this.bundle.getString("producto.texto.titulo");
    }

    @Inject
    private FacesContext facesContext;

    @Inject
    @Named("msg")
    private ResourceBundle bundle;

    private List<Producto> listado;

    private String textoBuscar;

    @PostConstruct
    public void init() {
        this.listado = this.service.listar();
    }

    @Produces
    @Model //request + named
    public Producto producto() {
        this.producto = new Producto();
        if(this.id != null && this.id > 0){
            this.service.porId(this.id).ifPresent(p -> {
                this.producto = p;
            });
        }
        return this.producto;
    }

    @Produces
    @Model
    public List<Categoria> categorias() {
        return this.service.listarCategorias();
    }

    public String guardar() {
        System.out.println(this.producto);
        if(producto.getId() != null && producto.getId() > 0){
            this.facesContext.addMessage(null, new FacesMessage(String.format(bundle.getString("producto.mensaje.editar"), producto.getNombre())));
        } else {
            this.facesContext.addMessage(null, new FacesMessage(String.format(bundle.getString("producto.mensaje.crear"), producto.getNombre())));
        }
        this.service.guardar(producto);
        this.listado = this.service.listar();
        return "index.xhtml";
    }

    public String editar(Long id) {
        this.id = id;
        return "form.xhtml";
    }

    public void eliminar(Producto producto) {
        this.service.eliminar(producto.getId());
        this.facesContext.addMessage(null, new FacesMessage(String.format(bundle.getString("producto.mensaje.eliminar"), producto.getNombre())));
        this.listado = this.service.listar();
    }

    public void buscar() {
        this.listado = this.service.buscarPorNombre(this.textoBuscar);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Producto> getListado() {
        return listado;
    }

    public void setListado(List<Producto> listado) {
        this.listado = listado;
    }

    public String getTextoBuscar() {
        return textoBuscar;
    }

    public void setTextoBuscar(String textoBuscar) {
        this.textoBuscar = textoBuscar;
    }
}
