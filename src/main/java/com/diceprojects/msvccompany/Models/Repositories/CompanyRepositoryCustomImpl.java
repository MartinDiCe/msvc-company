package com.diceprojects.msvccompany.Models.Repositories;

import com.diceprojects.msvccompany.Models.Entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Implementación de las operaciones personalizadas definidas en {@link ICompanyRepositoryCustom}
 * para la entidad {@link Company}.
 * <p>
 * Esta clase se encarga únicamente de la interacción con la base de datos, sin contener
 * lógica de negocio, y utiliza consultas SQL personalizadas donde se requiere.
 * </p>
 */
@Repository
public class CompanyRepositoryCustomImpl implements ICompanyRepositoryCustom {

    private final R2dbcEntityTemplate template;

    /**
     * Constructor de la implementación.
     *
     * @param template plantilla de R2DBC para operaciones reactivas en la base de datos.
     */
    @Autowired
    public CompanyRepositoryCustomImpl(R2dbcEntityTemplate template) {
        this.template = template;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Void> delete(String companyId) {
        return template.getDatabaseClient()
                .sql("UPDATE company SET deleted = true WHERE id = $1")
                .bind("$1", companyId)
                .fetch()
                .rowsUpdated()
                .then();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Void> restore(String companyId) {

        return template.getDatabaseClient()
                .sql("UPDATE company SET deleted = false WHERE id = $1")
                .bind("$1", companyId)
                .fetch()
                .rowsUpdated()
                .then();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Company> activate(String companyId, boolean activate) {
        return template.getDatabaseClient()
                .sql("UPDATE company SET active = :active WHERE id = :id RETURNING *")
                .bind("active", activate)
                .bind("id", companyId)
                .map((row, metadata) -> template.getConverter().read(Company.class, row))
                .one();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Company> create(Company company) {
        return template.insert(Company.class)
                .using(company);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Company> update(Company company) {
        return template.update(company);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Company> findByName(String name) {
        return template.getDatabaseClient()
                .sql("SELECT * FROM company WHERE UPPER(name) = UPPER($1)")
                .bind("$1", name)
                .map((row, metadata) -> template.getConverter().read(Company.class, row))
                .one();
    }

}