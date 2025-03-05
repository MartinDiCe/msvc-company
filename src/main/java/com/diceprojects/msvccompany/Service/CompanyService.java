package com.diceprojects.msvccompany.Service;

import com.diceprojects.msvccompany.Models.Entities.Company;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for handling business logic related to Company.
 */
public interface CompanyService {
    Mono<Company> findByName(String name);
    Mono<Company> createCompany(Company company);
    Mono<Company> getCompanyById(Long id);
    Flux<Company> getAllCompanies();
    Mono<Void> deleteCompany(Long id);
    Mono<Company> updateCompany(Long id, Company company);
}
