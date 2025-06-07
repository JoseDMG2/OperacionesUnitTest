package com.unitTestingP.Calculadora.controllers;

import com.unitTestingP.Calculadora.services.Operaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controllers {

    private Operaciones operaciones;

    @Autowired
    public Controllers(Operaciones operaciones) {
        this.operaciones = operaciones;
    }

    @GetMapping("/")
    public Map<String, String> home() {
        return new HashMap<>() {
            {put("menssaje", "hello unit tests");}
        };
    }

    @GetMapping("/factorial")
    public Map<String, String> factorial(@RequestParam int numero) {
        return new HashMap<>(){
            {put("mensaje", "resultado: " + operaciones.factorial(numero));}
        };
    }
}
