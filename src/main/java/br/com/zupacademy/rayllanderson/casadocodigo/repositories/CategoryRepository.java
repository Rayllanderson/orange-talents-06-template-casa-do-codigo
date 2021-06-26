package br.com.zupacademy.rayllanderson.casadocodigo.repositories;

import br.com.zupacademy.rayllanderson.casadocodigo.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);
}
