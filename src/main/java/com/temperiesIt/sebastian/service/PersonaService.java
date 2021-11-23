package com.temperiesIt.sebastian.service;

import com.temperiesIt.sebastian.domain.Stat;
import com.temperiesIt.sebastian.model.PersonaModel;
import com.temperiesIt.sebastian.model.RelacionModel;
import com.temperiesIt.sebastian.repository.PersonaRepository;
import com.temperiesIt.sebastian.repository.RelacionRepository;
import com.temperiesIt.sebastian.vo.StatsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonaService {

    private static final String[] RELACIONES = new String[]{"hermano", "hermana", "tio", "tia", "primo", "prima"};

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private RelacionRepository relacionRepository;

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

    public void setRelacion(Long id1, String relacion, Long id2) {
        Optional<PersonaModel> persona1 = personaRepository.findById(id1);
        Optional<PersonaModel> persona2 = personaRepository.findById(id2);
        if (!id1.equals(id2) && esRelacionValida(relacion, persona1, persona2)) {
            RelacionModel relacionToPersist = new RelacionModel(persona1.get().getId(), persona2.get().getId(), relacion);
            relacionRepository.save(relacionToPersist);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean esRelacionValida(String relacion, Optional<PersonaModel> persona1, Optional<PersonaModel> persona2) {
        return persona1.isPresent() && persona2.isPresent() && Arrays.asList(RELACIONES).contains(relacion);
    }

    public String getRelacion(Long id1, Long id2) {
        Optional<PersonaModel> persona1 = personaRepository.findById(id1);
        Optional<PersonaModel> persona2 = personaRepository.findById(id2);
        if (!id1.equals(id2) && persona1.isPresent() && persona2.isPresent()) {
            return personaRepository.findById(id1).get().getNombre() + " es " + relacionRepository.findById1AndId2(id1, id2).get(0).getRelacion() + " de " + personaRepository.findById(id2).get().getNombre() + ".";
        } else {
            throw new IllegalArgumentException();
        }
    }
}
