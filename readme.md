# PayMyTable test

This projet is based on Maven et spring boot 2.4.4. 
Java 8 is used in order to deploy on app engine standard.


## Frameworks and librairies

* `maven`
* `springboot`
* `hibernate`
* `lombok`
* `H2`
* `Junit`
* `Mockito`


## Architecture

Cette application est basé sur l'architecture trois tiers.

Le rôle de présentations est assuré par les controlleurs présents dans le package com.paymytable.controller.

Le rôle de traitement est assuré par les service présents dans le package com.paymytable.service.
Pour chaque service il existe une interface et son implémentation.  

Afin de limiter la complexité du projet toute les sources sont dans le meme war. 
Il serait judicieux de découper ce projet pour créer un projet api et un autre core afin de séparer les responsabilités des différents projets et supprimer les dépendences de couche. 
Dans ce cas le projet core (jar) deviendrait une dépendance du projet api (war). Un découpage plus fin peut encore être envisager pour que les entity soit aussi extraitent dans un projet (jar). 

The database is an h2 database for simplicity reason. 



## Exposed services 

### GET /short_url
Permet de récupérer la liste des ShortUrl existant de façon paginé ou non et trié ou non.

Dans le cas du mode non paginé une liste de shortUrl est retourné. 

Dans le cas du mode paginé un object de type <a href="https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Page.html">
Page</a> est retourné.

Ex d'url pour une pagination : 
```xpath
    /short_url?page=0 (default page size = 20)
    /short_url?page=0&size=2
```

Ex d'url pour une pagination triée:
```xpath
    /short_url?page=0&size=2&sort=url,asc
```

### POST /short_url 
Permet de créer et persister une short URL

### GET /short_url/:short_url_id 
Permet de suivre une short URL

### PUT /short_url/:short_url_id 
permettant de modifier une short URL

### DELETE /short_url/:short_url_id 
permettant de supprimer une short URL

```kotlin
fun example(): CharSequence? {
  try {
    return HttpRequests.request("https://example.com")
      .readChars()
  }
  catch (e: HttpRequests.HttpStatusException) {
    return null
  }
}
```

## Customize User-Agent

By default header `User-Agent` is not set,
* use `productNameAsUserAgent()` to set to current product name and version (e.g. `IntelliJ IDEA/2018.2`).
* or use `userAgent(String)` to set to arbitrary value.
