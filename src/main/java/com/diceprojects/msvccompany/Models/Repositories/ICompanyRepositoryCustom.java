package com.diceprojects.msvccompany.Models.Repositories;

import com.diceprojects.msvccompany.Models.Entities.Company;
import reactor.core.publisher.Mono;

/**
 * Interfaz para operaciones personalizadas en el repositorio de la entidad {@link Company}.
 * <p>
 * Esta interfaz se encarga de definir las operaciones que requieren una lógica
 * de acceso a datos particular, como el borrado lógico, restauración, activación,
 * creación y actualización.
 * </p>
 */
public interface ICompanyRepositoryCustom {

    /**
     * Realiza el borrado lógico de una compañía.
     * <p>
     * Este método marca la compañía como eliminada sin removerla físicamente de la base de datos.
     * Se asume que la tabla cuenta con una columna (por ejemplo, {@code deleted}) para ello.
     * </p>
     *
     * @param companyId identificador único de la compañía.
     * @return un {@link Mono} que señala la finalización de la operación.
     */
    Mono<Void> delete(String companyId);

    /**
     * Restaura una compañía que fue eliminada lógicamente.
     *
     * @param companyId identificador único de la compañía.
     * @return un {@link Mono} que señala la finalización de la operación.
     */
    Mono<Void> restore(String companyId);

    /**
     * Activa o inactiva una compañía.
     *
     * @param companyId identificador único de la compañía.
     * @param activate  {@code true} para activar la compañía o {@code false} para inactivarla.
     * @return un {@link Mono} que emite la compañía actualizada.
     */
    Mono<Company> activate(String companyId, boolean activate);

    /**
     * Crea una nueva compañía en la base de datos.
     *
     * @param company entidad de la compañía a crear.
     * @return un {@link Mono} que emite la compañía creada.
     */
    Mono<Company> create(Company company);

    /**
     * Actualiza la información de una compañía existente.
     *
     * @param company entidad de la compañía con la información actualizada.
     * @return un {@link Mono} que emite la compañía actualizada.
     */
    Mono<Company> update(Company company);

    /**
     * Obtiene una compañía a partir de su nombre.
     *
     * @param name nombre de la compañía.
     * @return un {@link Mono} que emite la compañía encontrada o vacío si no existe.
     */
    Mono<Company> findByName(String name);
}