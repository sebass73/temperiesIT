package com.temperiesIt.sebastian.service;

import com.temperiesIt.sebastian.domain.Stat;
import com.temperiesIt.sebastian.model.PersonaModel;
import com.temperiesIt.sebastian.repository.PersonaRepository;
import com.temperiesIt.sebastian.vo.StatsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<PersonaModel> getPersonas() {
        return (List<PersonaModel>) personaRepository.findAll();
    }

    public void updatePersona(PersonaModel persona) {
        if (persona.getId() != null && personaRepository.findById(persona.getId()).isPresent()) {
            personaRepository.save(persona);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void deletePersona(Long id) {
        personaRepository.deleteById(id);
    }

    public List<Stat> getStats() {
        List<StatsVo> nacionalidades = personaRepository.findByPais();
        int totalPersonas = getCantidadPersonas(nacionalidades);
        return nacionalidades.stream().map(nacionalidad -> new Stat(nacionalidad.getPais(), getAverage(nacionalidad, totalPersonas))).collect(Collectors.toList());
    }

    private String getAverage(StatsVo nacionalidad, int totalPersonas) {
        return Float.toString((float) nacionalidad.getCantidad() / totalPersonas);
    }

    private int getCantidadPersonas(List<StatsVo> nacionalidades) {
        return nacionalidades.stream().mapToInt(StatsVo::getCantidad).sum();
    }

}
