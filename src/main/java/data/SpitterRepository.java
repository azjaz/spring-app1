package data;

import entity.Spitter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpitterRepository extends CrudRepository<Spitter, Long> {
//    List<Spitter> findAll();
    Spitter findByUsername(String username);
}
