package com.diceprojects.msvccompany.Models.Repositories;

import com.diceprojects.msvccompany.Models.Entities.Company;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interfaz reactiva para el repositorio de la entidad {@link Company}.
 * <p>
 * Esta interfaz se encarga únicamente de la interacción con la base de datos,
 * sin contener lógica de negocio. Se extiende de {@link ReactiveCrudRepository} para
 * operaciones CRUD básicas y de {@link ICompanyRepositoryCustom} para operaciones
 * personalizadas.
 * </p>
 */
public interface ICompanyRepository extends ReactiveCrudRepository<Company, String>, ICompanyRepositoryCustom {

    /**
     * Obtiene una compañía a partir de su identificador único.
     *
     * @param id identificador único de la compañía.
     * @return un {@link Mono} que emite la compañía encontrada o vacío si no existe.
     */
    @Override
    @NonNull
    Mono<Company> findById(@NonNull String id);

    /**
     * Obtiene todas las compañías.
     *
     * @return un {@link Flux} que emite todas las compañías almacenadas.
     */
    @Override
    @NonNull
    Flux<Company> findAll();
}
