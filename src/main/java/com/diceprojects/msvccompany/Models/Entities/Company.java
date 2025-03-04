package com.diceprojects.msvccompany.Models.Entities;

import com.diceprojects.msvccompany.Models.Enums.TipoCompany;
import com.diceprojects.msvccompany.Models.Enums.TipoIdentificacion;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table("company")
public class Company extends AuditableEntity{

    @Id
    private String id;
    private String nombreFantasia;
    private TipoCompany tipo;
    private TipoIdentificacion identificacionFiscal;
    private String nroFiscal;
    private String razonSocial;
    private String descripcion;
    private String variableLibre;

}
