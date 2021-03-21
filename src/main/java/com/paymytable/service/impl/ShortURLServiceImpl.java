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


/**
 * Implementation for ShortURLService
 */
@Service
public class ShortURLServiceImpl implements ShortURLService {
    @Autowired
    private ShortURLRepository shortRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShortURL> list(Sort sort) {
        return shortRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ShortURL> pagedList(Pageable page, Sort sort) {
        return shortRepository.findAll(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShortURL findById(@NotNull Long id) {
        return shortRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShortURL insertOrUpdate(ShortURL shortURL) {
        return shortRepository.save(shortURL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(long id) {
        shortRepository.deleteById(id);
    }
}
