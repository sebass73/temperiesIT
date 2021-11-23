package com.temperiesIt.sebastian.controller.rest;

import com.temperiesIt.sebastian.domain.Stat;
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

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPersona(@RequestBody PersonaModel persona) {
        personaService.createPersona(persona);
    }

    @GetMapping("/readAll")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<PersonaModel> getPersonas() {
        return personaService.getPersonas();
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updatePersona(@RequestBody PersonaModel persona) {
        personaService.updatePersona(persona);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePersona(@PathVariable Long id) {
        personaService.deletePersona(id);
    }

    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Stat> getStats() {
        return personaService.getStats();
    }

    @PostMapping("/personas/{id1}/{relacion}/{id2}")
    @ResponseStatus(HttpStatus.CREATED)
    public void setRelacion(@PathVariable Long id1, @PathVariable String relacion, @PathVariable Long id2) { personaService.setRelacion(id1, relacion, id2); }

    @GetMapping("/relaciones/{id1}/{id2}")
    public @ResponseBody
    String getRelacion(@PathVariable Long id1, @PathVariable Long id2) {
        return personaService.getRelacion(id1, id2);
    }

}
