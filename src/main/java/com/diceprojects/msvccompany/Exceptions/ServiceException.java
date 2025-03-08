package com.diceprojects.msvccompany.Exceptions;

/**
 * Excepción general para errores ocurridos en la capa de servicio de compañías.
 * <p>
 * Esta excepción se utiliza para encapsular cualquier error que se produzca en la lógica
 * del servicio, permitiendo un manejo centralizado de las excepciones y la propagación
 * de la causa original.
 * </p>
 *
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Crea una nueva instancia de CompanyServiceException sin mensaje.
     */
    public ServiceException() {
        super();
    }

    /**
     * Crea una nueva instancia de CompanyServiceException con el mensaje especificado.
     *
     * @param message El mensaje que describe el error.
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Crea una nueva instancia de CompanyServiceException con el mensaje y la causa especificados.
     *
     * @param message El mensaje que describe el error.
     * @param cause   La causa que originó la excepción.
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Crea una nueva instancia de CompanyServiceException con la causa especificada.
     *
     * @param cause La causa que originó la excepción.
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }
}
