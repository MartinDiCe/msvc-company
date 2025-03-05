package com.diceprojects.msvccompany.Repository;

import com.diceprojects.msvccompany.Models.Entities.Company;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactive Repository for Company entity.
 * Provides basic CRUD operations and custom queries.
 */
@Repository
public interface CompanyRepository extends ReactiveCrudRepository<Company, Long> {

    Mono<Company> findByName(String name);

    Flux<Company> findByActive(boolean active);

    Mono<Company> createCompany(Company company);

    Mono<Company> getCompanyById(Long id);

    Flux<Company> getAllCompanies();

    Mono<Void> deleteCompany(Long id);

    Mono<Company> updateCompany(Long id, Company company);
}

