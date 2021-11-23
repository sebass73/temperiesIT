package com.temperiesIt.sebastian.repository;

import com.temperiesIt.sebastian.model.RelacionModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelacionRepository extends CrudRepository<RelacionModel, Long> {
    List<RelacionModel> findById1AndId2(Long id1, Long id2);
}
