package com.temperiesIt.sebastian.repository;

import com.temperiesIt.sebastian.model.PersonaModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends CrudRepository<PersonaModel, Long> {
    List<PersonaModel> findByTipoDocumentoAndNumeroDocumentoAndPais(String tipoDocumento, String numeroDocumento, String pais);
}
