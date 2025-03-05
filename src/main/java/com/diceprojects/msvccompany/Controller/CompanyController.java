package com.diceprojects.msvccompany.Controller;

import com.diceprojects.msvccompany.Models.Entities.Company;
import com.diceprojects.msvccompany.Service.CompanyService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST Controller for managing Company API endpoints.
 */
@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public Mono<Company> createCompany(@RequestBody Company company) {
        return companyService.createCompany(company);
    }

    @GetMapping("/{id}")
    public Mono<Company> getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

    @GetMapping
    public Flux<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCompany(@PathVariable Long id) {
        return companyService.deleteCompany(id);
    }

    @PutMapping("/{id}")
    public Mono<Company> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        return companyService.updateCompany(id, company);
    }
}
