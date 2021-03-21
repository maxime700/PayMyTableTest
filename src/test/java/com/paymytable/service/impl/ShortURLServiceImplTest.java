package com.paymytable.service.impl;

import com.paymytable.boot.Application;
import com.paymytable.entity.ShortURL;
import com.paymytable.service.ShortURLService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.util.Assert;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest(classes = Application.class, properties = "spring.main.allow-bean-definition-overriding=true")
public class ShortURLServiceImplTest {

    @Autowired
    private ShortURLService shortURLService;

    @Test
    public void testList() {
        List<ShortURL> list = shortURLService.list(null);
        Assert.notNull(list, "List should not be null");
    }

    @Test
    public void testPagedList() {
        Page<ShortURL> list = shortURLService.pagedList(PageRequest.of(0, 2), null);
        assertEquals( 2, list.getContent().size(),"First page size should be 2");
        assertEquals( 3, list.getTotalElements(), "Total number of elements should be 3");

        list = shortURLService.pagedList(PageRequest.of(1, 2), null);
        assertEquals( 1, list.getContent().size(), "Seconde page size should be 1");
        assertEquals( 3, list.getTotalElements(),"Total number of elements should be 3");
    }

    @Test
    public void testInsertWithEmptyURL_shouldThrowException() {
        TransactionSystemException exception = assertThrows(TransactionSystemException.class,
                () -> shortURLService.insertOrUpdate(new ShortURL()));

        assertEquals(ConstraintViolationException.class, exception.getOriginalException().getCause().getClass());
        ConstraintViolationException constraintViolationException = (ConstraintViolationException)exception.getOriginalException().getCause();
        assertEquals(1, constraintViolationException.getConstraintViolations().size());
        constraintViolationException.getConstraintViolations().stream().forEach(constraintViolation -> assertEquals("URL is mandatory",constraintViolation.getMessage()));

    }


}
