package com.temperiesIt.sebastian.repository;

import com.temperiesIt.sebastian.model.PersonaModel;
import com.temperiesIt.sebastian.vo.StatsVo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends CrudRepository<PersonaModel, Long> {
    List<PersonaModel> findByTipoDocumentoAndNumeroDocumentoAndPais(String tipoDocumento, String numeroDocumento, String pais);

    @Query(name = "buscar_por_pais", nativeQuery = true)
    List<StatsVo> findByPais();
}
