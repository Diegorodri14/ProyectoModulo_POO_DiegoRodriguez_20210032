package DiegoRodriguez_20210032.DiegoRodriguez_20210032.Entities.Libros;

import jakarta.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "LIBROS")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LibrosEntity {
    @Id
    @Column(name = "")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_libro")
    @SequenceGenerator(name = "sq_libro", sequenceName = "sq_libro", allocationSize = 1)
    private Long Id;

    @Column(name = "AUTOR_ID", nullable = false)
    private Long Autor_Id;

    @Column(name = "TITULO", nullable = false)
    private String Titulo;

    @Column(name = "ISBN", length = 12, unique = true)
    private Number ISBN;

    @Temporal(TemporalType.DATE)
    @Column(name = "AÑO_PUBLICACION")
    private Date Año_Publicacion;

    @Column(name = "GENERO", nullable = false)
    private String Genero;
}
