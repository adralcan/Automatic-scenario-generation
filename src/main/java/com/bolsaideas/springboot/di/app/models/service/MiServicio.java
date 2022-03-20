package com.bolsaideas.springboot.di.app.models.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Primary // Es para marcar que es el IServicio por defecto
//@Component("miServicioSimple")
public class MiServicio implements IServicio {

    @Override
    public String operacion() {
        return "ejecutando algún proceso importante simple...";
    }
}
