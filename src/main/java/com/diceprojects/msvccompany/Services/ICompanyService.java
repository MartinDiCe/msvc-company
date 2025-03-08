package com.diceprojects.msvccompany.Services;

import com.diceprojects.msvccompany.Models.Entities.Company;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interfaz para el servicio reactivo de la entidad {@link Company}.
 * <p>
 * Esta interfaz define las operaciones de negocio para manipular compañías de forma reactiva.
 * Se adhiere a los principios SOLID, en particular la Responsabilidad Única y la Inversión de Dependencias.
 * </p>
 */
public interface ICompanyService {

    /**
     * Obtiene una compañía por su identificador único.
     *
     * @param id identificador único de la compañía.
     * @return un {@link Mono} que emite la compañía encontrada o vacío si no existe.
     */
    Mono<Company> getById(String id);

    /**
     * Obtiene una compañía por su nombre.
     *
     * @param name nombre de la compañía.
     * @return un {@link Mono} que emite la compañía encontrada o vacío si no existe.
     */
    Mono<Company> getByName(String name);

    /**
     * Obtiene todas las compañías.
     *
     * @return un {@link Flux} que emite todas las compañías.
     */
    Flux<Company> getAll();

    /**
     * Crea una nueva compañía.
     *
     * @param company compañía a crear.
     * @return un {@link Mono} que emite la compañía creada.
     */
    Mono<Company> create(Company company);

    /**
     * Actualiza la información de una compañía existente.
     *
     * @param company compañía con la información actualizada.
     * @return un {@link Mono} que emite la compañía actualizada.
     */
    Mono<Company> update(Company company);

    /**
     * Realiza el borrado lógico de una compañía.
     *
     * @param id identificador de la compañía a eliminar.
     * @return un {@link Mono} que indica la finalización de la operación.
     */
    Mono<Void> delete(String id);

    /**
     * Restaura una compañía que fue eliminada lógicamente.
     *
     * @param id identificador de la compañía a restaurar.
     * @return un {@link Mono} que indica la finalización de la operación.
     */
    Mono<Void> restore(String id);

    /**
     * Activa o inactiva una compañía.
     *
     * @param id       identificador de la compañía.
     * @param activate {@code true} para activar o {@code false} para inactivar la compañía.
     * @return un {@link Mono} que emite la compañía actualizada.
     */
    Mono<Company> activate(String id, boolean activate);
}

