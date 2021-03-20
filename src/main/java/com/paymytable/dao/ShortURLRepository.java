package com.paymytable.dao;

import com.paymytable.entity.ShortURL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to manage @{@link ShortURL}
 */
@Repository
public interface ShortURLRepository extends JpaRepository<ShortURL, Long> {
}
