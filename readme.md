
# PayMyTable test

This projet is based on Maven et spring boot 2.4.4.  
Java 8 is used in order to deploy on app engine standard.


## Frameworks and libraries

* `Maven` 
* `Spring Boot` 
* `Hibernate` ORM to access data base
* `Lombok` to generate at compilation needed getter/setter/constructors etc.  
* `H2` to store data
* `Junit` to test service layer and dao
* `Mockito`to test controller layer

Validation is done by Spring Boot

## Architecture

This application is based on three-tier architecture.

Presentation layer is done by controllers present in package com.paymytable.controller.

Application layer is done by services present in package com.paymytable.service.
Each service has an interface and an implementation for modularity and decoupling reason.  

Data access layer is done by repository present in package com.paymytable.dao.  
```html
┌──────────────────────────────┐       ┌──────────────────────────┐  
│ com.paymytable.controller.*  │       │ com.paymytable.service.* │  
│                              ├───────┤                          │  
│         Controllers          │       │          Services        │  
└──────────────────────────────┘       └──────────────┬───────────┘  
                                                      │  
                                                      │  
                                                      │  
        ┌─────────────┐                  ┌────────────┴─────────┐
        │             │                  │ com.paymytable.dao.* │
        │ H2 database ├──────────────────┤                      │
        │             │                  │     Repository       │
        └─────────────┘                  └──────────────────────┘
```

All sources are in the same project and war because of the small size of the application and also in order to limit complexity. 
If the project is doomed to grow it would be good idea to split it into multiple part. It would separate responsibilities and delete layer dependencies. 
Ex: 
* Api projet -> war
* Core projet -> jar would be a dependency for api
* Model projet -> jar would be a dependency for api and core 

The database is an h2 database for simplicity reason.


## Exposed services 

#### GET /short_url
To retrieve ShortUrl list, paginated or not. It can be sorted.  
In case case of paginated request, the result is of type <a href="https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Page.html">
Page</a>.  
In case of not paginated request, the result is of type List<ShortURL>. 

Example of request without pagination nor sorting :
```xpath
    /short_url
```

Example of request with pagination : 
```xpath
    /short_url?page=0 (default page size = 20)
    /short_url?page=0&size=2
```

Example of request with sorting :
```xpath
    /short_url?page=0&size=2&sort=url,asc
```

#### POST /short_url 
To create a ShortURL

#### GET /short_url/:short_url_id
To retrieve a ShortURL identified by {short_url_id}

#### PUT /short_url/:short_url_id 
To update a ShortURL identified by {short_url_id}

#### DELETE /short_url/:short_url_id
To delete a ShortURL identified by {short_url_id}


