package api.chocolates.rest.domain;

import org.springframework.data.repository.CrudRepository;

public interface ProductosRepository extends CrudRepository<Producto, Long> { // tipo producto e ID tipo Long

}
