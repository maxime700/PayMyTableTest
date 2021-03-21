package com.paymytable.service;

import com.paymytable.entity.ShortURL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Interface exposing available operations to manage @{@link ShortURL}
 */
public interface ShortURLService {
    /**
     *
     * List all @{@link ShortURL} sorted if @{@link Sort} parameter is provided
     * @param sort the sort configuration to apply
     * @return @{@link List<@ShortURL>} List of short url
     */
    List<ShortURL> list(Sort sort);

    /**
     *
     * List paginated @{@link ShortURL} sorted if @{@link Sort} parameter is provided
     * @param page the page configuration to apply
     * @param sort the sort configuration to apply
     * @return @{@link List<@ShortURL>} List of short url
     */
    Page<ShortURL> pagedList(Pageable page, Sort sort);

    /**
     * Retrieve a @{@link ShortURL}
     * @param id the id of the @{@link ShortURL} to load
     * @return the @{@link ShortURL} identify by id or null if not exist
     */
    ShortURL findById(@NotNull Long id);

    /**
     * Insert or update a @{@link ShortURL}. If id field is null is empty, a creation is tried
     * otherwise an update
     * @param shortURL the i@{@link ShortURL} to add or tu update
     * @return the @{@link ShortURL} identify by id or null if not exist
     */
    ShortURL insertOrUpdate(ShortURL shortURL);

    /**
     * Delete a @{@link ShortURL}
     * @param id the id of the @{@link ShortURL} to delete
     */
    void deleteById(long id);
}
