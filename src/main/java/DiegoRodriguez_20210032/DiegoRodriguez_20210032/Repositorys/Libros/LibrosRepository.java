package DiegoRodriguez_20210032.DiegoRodriguez_20210032.Repositorys.Libros;

import DiegoRodriguez_20210032.DiegoRodriguez_20210032.Entities.Libros.LibrosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrosRepository extends JpaRepository<LibrosEntity, Long> {
}
