# Temperies IT

Este es el desafío técnico dado por Temperies IT.

Requerimientos:

- Java 11+
- Maven 3.6.3

### Manual de usuario

### Configuracion BBDD

Para persistir los elementos en la bbdd

- spring.jpa.hibernate.ddl-auto=update

Para limpiar los datos de la bbdd con la ejecución de la aplicación

- spring.jpa.hibernate.ddl-auto=create

### Endpoints

**POST** `localhost:8080/create` método para crear nuevas Personas.

Ejemplo de request:

``` JSON
{
    "nombre": "Sebastian",
    "tipoDocumento": "dni",
    "numeroDocumento": "38497992",
    "edad": "27",
    "email": "semartinez.6226@gmail.com",
    "pais": "Argentina"
}
```

**GET** `localhost:8080/readAll` método para obtener todas las Personas.

Ejemplo de response:

``` JSON
[
    {
        "id": 1,
        "nombre": "Sebastian",
        "tipoDocumento": "dni",
        "numeroDocumento": "38497992",
        "edad": 27,
        "email": "semartinez.6226@gmail.com",
        "pais": "Argentina"
    }
]
```

**POST** `localhost:8080/update` método para actualizar Personas. Cabe destacar que hay que agregar el **id** de la
Persona en el bodyRequest.

Ejemplo de request:

``` JSON
{
    "id": 1,
    "nombre": "Sebastian",
    "tipoDocumento": "dni",
    "numeroDocumento": "38497992",
    "edad": "27",
    "email": "semartinez.6226@gmail.com",
    "pais": "Argentina"
}
```

**DELETE** `localhost:8080/delete/{id}` método para eliminar Personas.

Ejemplo de endpoint:

```
localhost:8080/delete/3
```

**GET** `localhost:8080/stats` método que devuelve cifras totalizadoras de cantidad de porcentaje de personas por país.

Ejemplo de response:

``` JSON
[
    {
        "country": "Uruguay",
        "average": "0.25"
    },
    {
        "country": "Brasil",
        "average": "0.25"
    },
    {
        "country": "Argentina",
        "average": "0.5"
    }
]
```

**POST** `localhost:8080/personas/{id1}/{relacion}/{id2}` método para armar relaciones entre personas. Los ids deben ser
distintos y la relacion debe ser una soportada.

- Relaciones: "herman@, prim@, ti@".

Ejemplo de endpoint:

```
localhost:8080/personas/2/hermano/4
```

**GET** `localhost:8080/relaciones/2/4` método que retorna la relación que existe entre ambas personas según su id.

Ejemplo de response:

``` 
Daniel es hermano de Pedro.
```