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
import org.springframework.util.Assert;

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
        Assertions.assertEquals( 2, list.getContent().size(),"First page size should be 2");
        Assertions.assertEquals( 3, list.getTotalElements(), "Total number of elements should be 3");

        list = shortURLService.pagedList(PageRequest.of(1, 2), null);
        Assertions.assertEquals( 1, list.getContent().size(), "Seconde page size should be 1");
        Assertions.assertEquals( 3, list.getTotalElements(),"Total number of elements should be 3");
    }


}
