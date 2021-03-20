package com.paymytable.service.impl;

import com.paymytable.dao.ShortURLRepository;
import com.paymytable.entity.ShortURL;
import com.paymytable.service.ShortURLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;


@Service
public class ShortURLServiceImpl implements ShortURLService {
    @Autowired
    private ShortURLRepository shortRepository;

    /**
     *
     * List all @{@link ShortURL} sorted if @{@link Sort} parameter is provided
     * @param sort the sort configuration to apply
     * @return @{@link List<@ShortURL>} List of short url
     */
    @Override
    public List<ShortURL> list(Sort sort) {
        return shortRepository.findAll();
    }

    /**
     *
     * List paginated @{@link ShortURL} sorted if @{@link Sort} parameter is provided
     * @param page the page configuration to apply
     * @param sort the sort configuration to apply
     * @return @{@link List<@ShortURL>} List of short url
     */
    @Override
    public Page<ShortURL> pagedList(Pageable page, Sort sort) {
        return shortRepository.findAll(page);
    }


    /**
     * Retrieve a @{@link ShortURL}
     * @param id the id of the @{@link ShortURL} to load
     * @return the @{@link ShortURL} identify by id or null if not exist
     */
    @Override
    public ShortURL findById(@NotNull Long id) {
        return shortRepository.findById(id).orElse(null);
    }

    @Override
    public ShortURL insertOrUpdate(ShortURL shortURL) {
        return shortRepository.save(shortURL);
    }

    /**
     * Delete a @{@link ShortURL}
     * @param id the id of the @{@link ShortURL} to delete
     */
    @Override
    public void deleteById(long id) {
        shortRepository.deleteById(id);
    }
}
