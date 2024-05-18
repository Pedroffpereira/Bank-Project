package com.example.Bank.Mappers;

/**
 * Interface for mapping between entity and DTO objects.
 *
 * @param <E> The type of the entity.
 * @param <R> The type of the DTO (response).
 * 
 * @author Pedro Pereira
 * @version 1.0
 */
public interface DTOMapper<E, R> {
    /**
     * Converts a DTO (response) object to an entity object.
     *
     * @param response The DTO object to be converted.
     * @return The corresponding entity object.
     */
    public E toEntatiy(R response);

    /**
     * Converts an entity object to a DTO (response) object.
     *
     * @param entity The entity object to be converted.
     * @return The corresponding DTO (response) object.
     */
    public R toDTO(E entity);
}
