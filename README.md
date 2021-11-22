# Temperies IT
Este es el desafío técnico dado por Temperies IT.

### Manual de usuario

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

**POST** `localhost:8080/update` método para actualizar Personas. Cabe destacar que hay que agregar el **id** de la Persona en el bodyRequest.

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

### Configuracion BBDD 
Para persistir los elementos en la bbdd
 - spring.jpa.hibernate.ddl-auto=update

Para limpiar los datos de la bbdd con la ejecución de la aplicación
 - spring.jpa.hibernate.ddl-auto=create