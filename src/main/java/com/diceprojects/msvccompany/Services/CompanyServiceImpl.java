package com.diceprojects.msvccompany.Services;

import com.diceprojects.msvccompany.Exceptions.NotFoundException;
import com.diceprojects.msvccompany.Exceptions.ServiceException;
import com.diceprojects.msvccompany.Models.Entities.Company;
import com.diceprojects.msvccompany.Models.Repositories.ICompanyRepository;
import com.diceprojects.msvccompany.Models.Repositories.ICompanyRepositoryCustom;
import com.diceprojects.msvccompany.Logging.AppLogger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Implementación del servicio reactivo para la entidad {@link Company}.
 * <p>
 * Esta clase implementa {@link ICompanyService} y se encarga de coordinar las operaciones
 * de negocio utilizando un enfoque 100% reactivo, delegando en el repositorio para la
 * interacción con la base de datos. Además, utiliza {@link AppLogger} para registrar
 * eventos y errores, permitiendo manejar el logging de forma centralizada según el entorno.
 * Se cumple el principio de Inversión de Dependencias al inyectar tanto el repositorio como el logger.
 * </p>
 */
@Service
public class CompanyServiceImpl implements ICompanyService {

    private final ICompanyRepository repository;
    private final ICompanyRepositoryCustom customRepository;
    private final AppLogger logger;

    /**
     * Constructor de la implementación del servicio.
     *
     * @param repository       repositorio reactivo para {@link Company}
     * @param customRepository implementación personalizada del repositorio
     * @param logger           instancia de {@link AppLogger} para logging
     */
    public CompanyServiceImpl(ICompanyRepository repository,
                              @Qualifier("companyRepositoryCustomImpl") ICompanyRepositoryCustom customRepository,
                              AppLogger logger) {
        this.repository = repository;
        this.customRepository = customRepository;
        this.logger = logger;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Company> getById(String id) {
        if (id == null || id.isBlank()) {
            logger.error("El identificador de la compañía es nulo o vacío", null);
            return Mono.error(new IllegalArgumentException("El identificador de la compañía no puede ser nulo ni vacío. Id recibido: " + id));
        }

        logger.debug("Iniciando búsqueda de la compañía con id: {}", id);

        return repository.findById(id)
                .switchIfEmpty(Mono.defer(() -> {
                    logger.warn("No se encontró la compañía con id: {}", id);
                    return Mono.error(new NotFoundException("No se encontró la compañía con id: " + id));
                }))
                .doOnSuccess(company -> logger.info("Compañía encontrada: {}", company))
                .doOnError(e -> logger.error("Error al obtener la compañía con id: {}", e, id))
                .onErrorMap(e -> new ServiceException("Error al obtener la compañía con id: " + id, e));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Company> getByName(String name) {
        logger.debug("Iniciando búsqueda de la compañía con nombre: {}", name);
        return customRepository.findByName(name)
                .doOnSuccess(company -> {
                    if (company != null) {
                        logger.info("Compañía encontrada: {}", company);
                    } else {
                        logger.warn("No se encontró la compañía con nombre: {}", name);
                    }
                })
                .doOnError(e -> logger.error("Error al obtener la compañía con nombre: {}", e, name));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Flux<Company> getAll() {
        logger.debug("Recuperando todas las compañías");
        return repository.findAll()
                .doOnComplete(() -> logger.info("Se han recuperado todas las compañías"))
                .doOnError(e -> logger.error("Error al recuperar todas las compañías", e));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Company> create(Company company) {
        logger.debug("Creando nueva compañía: {}", company);
        return customRepository.create(company)
                .doOnSuccess(created -> logger.info("Compañía creada exitosamente: {}", created))
                .doOnError(e -> logger.error("Error al crear la compañía: {}", e, company));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Company> update(Company company) {
        logger.debug("Actualizando compañía: {}", company);
        return customRepository.update(company)
                .doOnSuccess(updated -> logger.info("Compañía actualizada exitosamente: {}", updated))
                .doOnError(e -> logger.error("Error al actualizar la compañía: {}", e, company));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Void> delete(String id) {
        logger.debug("Eliminando (lógicamente) la compañía con id: {}", id);
        return customRepository.delete(id)
                .doOnSuccess(v -> logger.info("Compañía eliminada (lógicamente) con id: {}", id))
                .doOnError(e -> logger.error("Error al eliminar la compañía con id: {}", e, id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Void> restore(String id) {
        logger.debug("Restaurando la compañía con id: {}", id);
        return customRepository.restore(id)
                .doOnSuccess(v -> logger.info("Compañía restaurada con id: {}", id))
                .doOnError(e -> logger.error("Error al restaurar la compañía con id: {}", e, id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Company> activate(String id, boolean activate) {
        logger.debug("Cambiando estado de activación de la compañía con id: {} a {}", id, activate);
        return customRepository.activate(id, activate)
                .doOnSuccess(company -> logger.info("Estado de la compañía actualizado: {}", company))
                .doOnError(e -> logger.error("Error al cambiar el estado de activación de la compañía con id: {}", e, id));
    }
}
