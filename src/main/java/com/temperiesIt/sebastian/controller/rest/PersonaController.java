package com.temperiesIt.sebastian.controller.rest;

import com.temperiesIt.sebastian.model.PersonaModel;
import com.temperiesIt.sebastian.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public void crearPersona(@RequestBody PersonaModel persona) {
        personaService.createPersona(persona);
    }
}
