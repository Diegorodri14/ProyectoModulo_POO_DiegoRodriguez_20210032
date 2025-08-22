package DiegoRodriguez_20210032.DiegoRodriguez_20210032.Models.DTO.Libros;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class LibrosDTO {

    private Long Id;

    @NotNull(message = "El id del autor no debe de ser nulo")
    private Long Autor_Id;

    @NotNull(message = "El titulo del libro no puede ser nulo")
    private String Titulo;

    @Size(max = 12,min = 12, message = "El codigo del libro tiene que ser de 12 dijitos")
    @NotNull(message = "El ISBN del libro no debe de ser nulo")
    private Number ISBN;

    @Past(message = "La fecha de publicacion debe de ser en pasado")
    private Date Año_Publicacion;

    @NotNull(message = "El genero del libor no tiene que ser nulo")
    private String Genero;


}
