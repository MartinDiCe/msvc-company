package com.diceprojects.msvccompany.Models.Entities;

import com.diceprojects.msvccompany.Models.Enums.TipoCompany;
import com.diceprojects.msvccompany.Models.Enums.TipoIdentificacion;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Representa una compañía dentro del sistema.
 * <p>
 * Esta entidad extiende {@link AuditableEntity} para heredar la auditoría (creación y modificación).
 * Se utiliza en un entorno 100% reactivo y cumple con los principios SOLID para facilitar
 * la mantenibilidad y escalabilidad del código.
 * </p>
 * <p>
 * <strong>Responsabilidad Única (SRP):</strong> La clase se encarga únicamente de representar
 * la información de una compañía.
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table("company")
public class Company extends AuditableEntity {

    /**
     * Nombre único de la compañía que actúa como identificador primario.
     */
    @Id
    private String name;

    /**
     * Identificador secundario de la compañía.
     */
    private String id;

    /**
     * Nombre de fantasía utilizado para la identidad comercial de la compañía.
     */
    private String nombreFantasia;

    /**
     * Tipo de la compañía, definido por el enumerado {@link TipoCompany}.
     */
    private TipoCompany tipo;

    /**
     * Tipo de identificación fiscal, definido por el enumerado {@link TipoIdentificacion}.
     */
    private TipoIdentificacion identificacionFiscal;

    /**
     * Número fiscal asignado a la compañía.
     */
    private String nroFiscal;

    /**
     * Razón social de la compañía.
     */
    private String razonSocial;

    /**
     * Descripción detallada de la compañía.
     */
    private String descripcion;

    /**
     * Campo destinado para variables adicionales o información libre.
     */
    private String variableLibre;
}
