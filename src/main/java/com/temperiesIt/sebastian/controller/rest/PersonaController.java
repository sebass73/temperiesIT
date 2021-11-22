package com.temperiesIt.sebastian.controller.rest;

import com.temperiesIt.sebastian.model.PersonaModel;
import com.temperiesIt.sebastian.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPersona(@RequestBody PersonaModel persona) {
        personaService.createPersona(persona);
    }

    @GetMapping("/readAll")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<PersonaModel> getPersonas(){
        return personaService.getPersonas();
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updatePersona(@RequestBody PersonaModel persona) {
        personaService.updatePersona(persona);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePersona(@PathVariable Long id){
        personaService.deletePersona(id);
    }
}
