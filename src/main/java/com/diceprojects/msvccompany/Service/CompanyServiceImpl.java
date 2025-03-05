package com.diceprojects.msvccompany.Service;

import com.diceprojects.msvccompany.Models.Entities.Company;
import com.diceprojects.msvccompany.Repository.CompanyRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Implementation of CompanyService handling business logic.
 */
@Data
@Service
public abstract  class CompanyServiceImpl implements CompanyRepository {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Mono<Company> findByName(String name) {
        return companyRepository.findByName(name);
    }

    @Override
    public Mono<Company> createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Mono<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public Flux<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Mono<Void> deleteCompany(Long id) {
        return companyRepository.deleteById(id);
    }

    @Override
    public Mono<Company> updateCompany(Long id, Company company) {
        return companyRepository.findById(id)
                .flatMap(existingCompany -> {
                    existingCompany.setName(company.getName());
                    existingCompany.setActive(company.isActive());
                    return companyRepository.save(existingCompany);
                });
    }
}
