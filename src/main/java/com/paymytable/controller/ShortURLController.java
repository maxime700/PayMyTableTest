package com.paymytable.controller;

import com.paymytable.entity.ShortURL;
import com.paymytable.service.ShortURLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

/**
 * Controller exposing @{@link ShortURL} operations
 */
@RestController
@RequestMapping(path = "/short_url")
public class ShortURLController {

    @Autowired
    ShortURLService shortURLService;

    /**
     * Entry point to use to retrieve the complete list of existing @{@link ShortURL}.
     *
     * @return 404 http status code if no @{@link ShortURL} are retrieved
     * 200 http status code if some @{@link ShortURL} are retrieved, content is a @{@link List<@ShortURL>}
     */
    @RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ShortURL>> getAll() {
        List<ShortURL> result = shortURLService.list(null);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * Entry point to use to retrieve a paginated list of @{@link ShortURL}.
     *
     * @param pageable the pagination information to use as @{@link Pageable}. Mandatory
     * @param sort     the sort information to use as @{@link Sort}
     * @return 404 http status code if no @{@link ShortURL} are retrieved
     * 200 http status code if some @{@link ShortURL} are retrieved, content is a @{@link Page}
     */
    @RequestMapping(path = "", params = {"page"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ShortURL>> getAll(Pageable pageable, Sort sort) {
        Page<ShortURL> result = shortURLService.pagedList(pageable, sort);
        if (!result.hasContent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * Entry point to use to retrieve a single a @{@link ShortURL} identified by id parameter.
     *
     * @param id the id of the @{@link ShortURL} to retrive
     * @return 200 http status code if the @{@link ShortURL} exists
     * 404 http status code if the @{@link ShortURL} does not exist
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShortURL> get(@NotNull @PathVariable Long id) {
        ShortURL result = shortURLService.findById(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * Entry point to use to delete a @{@link ShortURL}
     *
     * @param id the id of the @{@link ShortURL} to delete
     * @return 204 http status code
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShortURL> delete(@NotNull @PathVariable Long id) {

        shortURLService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Entry point to use to update a @{@link ShortURL}
     *
     * @param id       the id of the @{@link ShortURL} to update
     * @param shortURL the @{@link ShortURL} to update
     * @return 204 http status code
     * 400 http status code if id path variable and id in body are not equals
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShortURL> update(@NotNull @PathVariable Long id, @NotNull @RequestBody ShortURL shortURL) {

        if (id != shortURL.getId()) {
            return ResponseEntity.badRequest().build();
        }
        ShortURL result = shortURLService.findById(shortURL.getId());
        if (result == null) {
            return ResponseEntity.notFound().build();
        }

        //update the ShortURL
        shortURLService.insertOrUpdate(shortURL);

        //return code 204 because we do not return status code
        return ResponseEntity.noContent().build();

    }

    /**
     * Entry point to use to create a @{@link ShortURL}
     *
     * @param shortURL the @{@link ShortURL} to create
     * @return 201 http status code
     */
    @RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShortURL> create(@NotNull @RequestBody ShortURL shortURL) {
        //id overriding to prevent update of an existing ShortURL
        shortURL.setId(null);

        //add or update the ShortURL
        shortURLService.insertOrUpdate(shortURL);
        //return code 201 created with location header configured with the uri to call to access to the resource
        URI uri = MvcUriComponentsBuilder
                .fromMethodName(ShortURLController.class, "get", shortURL.getId())
                .buildAndExpand(shortURL.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

}
