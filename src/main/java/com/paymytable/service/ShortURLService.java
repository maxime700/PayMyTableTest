package com.paymytable.service;

import com.paymytable.entity.ShortURL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ShortURLService {
    List<ShortURL> list(Sort sort);

    Page<ShortURL> pagedList(Pageable page, Sort sort);

    ShortURL findById(@NotNull Long id);

    ShortURL insertOrUpdate(ShortURL shortURL);

    void deleteById(long id);
}
