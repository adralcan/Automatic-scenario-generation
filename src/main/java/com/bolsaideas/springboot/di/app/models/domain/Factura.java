package com.bolsaideas.springboot.di.app.models.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.Serializable;
import java.util.List;

@Component
@RequestScope // Singleton que se guarda en el contexto Servlet
//@SessionScope // El alcance del objeto dura desde que se inicia la sesion hasta que se cierre el navegador.
// O cuando haya un timeout o se invalida la sesion
// Cuando estamos con un objeto que queramos guardar en la sesion http hay que extender de Serializable
public class Factura implements Serializable {

    private static final long serialVersionUID = 5087147239832448561L;

    @Value("${factura.descripcion}")
    private String descripcion;
    @Autowired
    private Cliente cliente;
    @Autowired
    @Qualifier("itemsFacturaOficina")
    private List<ItemFactura> items;

    @PostConstruct //Despues de la construccion de objetos y de la inyeccion de dependencias
    public void inicializar() {
        cliente.setApellido(cliente.getApellido().concat(" Delgado"));
        descripcion = descripcion.concat(" del cliente: "+ cliente.getNombre() + " " + cliente.getApellido());
    }

    @PreDestroy // Esto no se ejecuta si esta en SessionScope
    public void destruir() {
        System.out.println("Factura destruida: ".concat(descripcion));
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemFactura> getItems() {
        return items;
    }

    public void setItems(List<ItemFactura> items) {
        this.items = items;
    }
}
