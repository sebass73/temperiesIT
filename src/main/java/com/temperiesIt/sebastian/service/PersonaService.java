package com.temperiesIt.sebastian.service;

import com.temperiesIt.sebastian.model.PersonaModel;
import com.temperiesIt.sebastian.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    public void createPersona(PersonaModel persona) {
        if (esUnico(persona) && tieneDatoContacto(persona) && mayorEdad(persona)) {
            personaRepository.save(persona);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean mayorEdad(PersonaModel persona) {
        return persona.getEdad() > 17;
    }

    private boolean tieneDatoContacto(PersonaModel persona) {
        return persona.getEmail() != null;
    }

    private boolean esUnico(PersonaModel persona) {
        List<PersonaModel> personaList = personaRepository.findByTipoDocumentoAndNumeroDocumentoAndPais(persona.getTipoDocumento(), persona.getNumeroDocumento(), persona.getPais());
        return personaList.size() <= 0;
    }
}
