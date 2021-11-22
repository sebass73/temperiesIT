package com.temperiesIt.sebastian.model;

import com.temperiesIt.sebastian.vo.StatsVo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "persona")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "buscar_por_pais",
                query =
                        "SELECT pais, count(pais) as cantidad " +
                                "FROM persona " +
                                "group by pais;",
                resultSetMapping = "stats_vo"
        ),
})
@SqlResultSetMapping(
        name = "stats_vo",
        classes = @ConstructorResult(
                targetClass = StatsVo.class,
                columns = {
                        @ColumnResult(name = "pais", type = String.class),
                        @ColumnResult(name = "cantidad", type = Integer.class),
                }
        )
)

public class PersonaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String nombre;
    private String tipoDocumento;
    private String numeroDocumento;
    private int edad;
    private String email;
    private String pais;
}
