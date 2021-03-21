package geekbrains.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import geekbrains.Entity.Product;

import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> , JpaSpecificationExecutor<Product> {
    @Query("select s from Product s where s.id = ?1")
    Optional<Product> findById(int id);
}