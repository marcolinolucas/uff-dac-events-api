
# REST API Endpoints Documentation

O objetivo do trabalho é criar um sistema para cadastrar anais de eventos científicos. Esses anais são agregados em volumes de artigos e cada artigo tem seus autores.

## Volume

Representa o agregado de artigos aceitos em um dado evento

| Atributo | Descrição |
| --- | --- |
| id | Chave única para identificação do volume |
| initials | Sigla do evento (Máximo 32 caracteres) |
| edition | Número da edição do evento |
| city | Cidade onde foi realizado o evento |
| startDate | Data de início do evento |
| portugueseDescription | Descrição em português |
| englishDescription | Descrição em inglês |

### GET /volumes - Lista todos os volumes

```json
[
  {
    "id": 1,
    "initials": "initials",
    "edition": 1,
    "city": "city",
    "startDate": "2021-08-10",
    "portugueseDescription": "portugueseDescription",
    "englishDescription": "englishDescription",
    "articles": [
      {
        "id": 2,
        "volumeOrder": 1,
        "language": "pt",
        "originalTitle": "Título 999",
        "englishTitle": "Title 999",
        "originalSummary": "Um sumário",
        "englishSummary": "One summary",
        "originalKeywords": "palavra,novo",
        "englishKeywords": "word,new",
        "numberOfPages": 10,
        "authors": [
          {
            "id": 3,
            "articleOrder": 1,
            "email": "email@email.com",
            "firstName": "Nome 1",
            "middleName": "Nome 2",
            "lastName": "Nome 3",
            "affiliation": "ABC",
            "englishAffiliation": "CBA",
            "country": "BR",
            "orcID": "1234-1234-1234-1234"
          }
        ]
      }
    ]
  }
]
```

### GET /volumes/:volumeId - Lista um volume

```json
{
	"id": 1,
	"initials": "initials",
	"edition": 1,
	"city": "city",
	"startDate": "2021-08-10",
	"portugueseDescription": "portugueseDescription",
	"englishDescription": "englishDescription",
	"articles": [
		{
			"id": 2,
			"volumeOrder": 1,
			"language": "pt",
			"originalTitle": "Título 999",
			"englishTitle": "Title 999",
			"originalSummary": "Um sumário",
			"englishSummary": "One summary",
			"originalKeywords": "palavra,novo",
			"englishKeywords": "word,new",
			"numberOfPages": 10,
			"authors": [
				{
					"id": 3,
					"articleOrder": 1,
					"email": "email@email.com",
					"firstName": "Nome 1",
					"middleName": "Nome 2",
					"lastName": "Nome 3",
					"affiliation": "ABC",
					"englishAffiliation": "CBA",
					"country": "BR",
					"orcID": "1234-1234-1234-1234"
				}
			]
		}
	]
}
```

### POST /volumes - Cria um volume

| Body | Descrição |
| --- | --- |
| initials | Sigla do evento (Máximo 32 caracteres) |
| edition | Número da edição do evento |
| city | Cidade onde foi realizado o evento |
| startDate | Data de início do evento |
| portugueseDescription | Descrição em português |
| englishDescription | Descrição em inglês |

```json
{
	"id": 1,
	"initials": "initials",
	"edition": 1,
	"city": "city",
	"startDate": "2021-08-10",
	"portugueseDescription": "portugueseDescription",
	"englishDescription": "englishDescription",
	"articles": []
}
```

### PUT /volumes/:volumeId - Atualiza um volume

| Body | Descrição |
| --- | --- |
| initials | Sigla do evento (Máximo 32 caracteres) |
| edition | Número da edição do evento |
| city | Cidade onde foi realizado o evento |
| startDate | Data de início do evento |
| portugueseDescription | Descrição em português |
| englishDescription | Descrição em inglês |

```json
{
	"id": 1,
	"initials": "initials 2",
	"edition": 1,
	"city": "city",
	"startDate": "2021-08-10",
	"portugueseDescription": "portugueseDescription",
	"englishDescription": "englishDescription",
	"articles": []
}
```

### DELETE /volumes/:volumeId - Deleta um volume

```json
No Content
```

## Artigo

Representa os artigos dos volumes

| Atributo | Descrição |
| --- | --- |
| id | Chave única para identificação do volume |
| volumeOrder | Ordem do artigo no volume |
| originalTitle | Título original |
| englishTitle | Título em inglês |
| originalSummary | Resumo original |
| englishSummary | Resumo em inglês  |
| language | Idioma do artigo ("pt", "en" ou "es") |
| originalKeywords | Palavras-chaves originais (separadas por ";")  |
| englishKeywords | Palavras-chaves em inglês (separadas por ";") |
| numberOfPages | Número de páginas |

### GET /volumes/:volumeId/articles - Lista todos os artigos de um volume

```json
[
  {
    "id": 3,
    "volumeOrder": 1,
    "language": "pt",
    "originalTitle": "Título 999",
    "englishTitle": "Title 999",
    "originalSummary": "Um sumário",
    "englishSummary": "One summary",
    "originalKeywords": "palavra,novo",
    "englishKeywords": "word,new",
    "numberOfPages": 10,
    "authors": [
      {
        "id": 5,
        "articleOrder": 1,
        "email": "email@email.com",
        "firstName": "Nome 1",
        "middleName": "Nome 2",
        "lastName": "Nome 3",
        "affiliation": "ABC",
        "englishAffiliation": "CBA",
        "country": "BR",
        "orcID": "1234-1234-1234-1234"
      }
    ]
  }
]
```

### GET /volumes/:volumeId/articles/:articleId - Lista um artigo de um volume

```json
{
  "id": 3,
  "volumeOrder": 1,
  "language": "pt",
  "originalTitle": "Título 1",
  "englishTitle": "Title 1",
  "originalSummary": "Um sumário",
  "englishSummary": "One summary",
  "originalKeywords": "palavra,novo",
  "englishKeywords": "word,new",
  "numberOfPages": 10,
  "authors": []
}
```

### POST /volumes/:volumeId/articles - Cria um artigo de um volume

| Body | Descrição |
| --- | --- |
| volumeOrder | Ordem do artigo no volume |
| originalTitle | Título original |
| englishTitle | Título em inglês |
| originalSummary | Resumo original |
| englishSummary | Resumo em inglês  |
| language | Idioma do artigo ("pt", "en" ou "es") |
| originalKeywords | Palavras-chaves originais (separadas por ";")  |
| englishKeywords | Palavras-chaves em inglês (separadas por ";") |
| numberOfPages | Número de páginas |

```json
{
  "id": 4,
  "volumeOrder": 2,
  "language": "pt",
  "originalTitle": "Título 2",
  "englishTitle": "Title 2",
  "originalSummary": "Um sumário",
  "englishSummary": "One summary",
  "originalKeywords": "palavra,novo",
  "englishKeywords": "word,new",
  "numberOfPages": 10,
  "authors": null
}
```

### PUT /volumes/:volumeId/articles/:articleId - Atualiza um artigo de um volume

| Body | Descrição |
| --- | --- |
| volumeOrder | Ordem do artigo no volume |
| originalTitle | Título original |
| englishTitle | Título em inglês |
| originalSummary | Resumo original |
| englishSummary | Resumo em inglês  |
| language | Idioma do artigo ("pt", "en" ou "es") |
| originalKeywords | Palavras-chaves originais (separadas por ";")  |
| englishKeywords | Palavras-chaves em inglês (separadas por ";") |
| numberOfPages | Número de páginas |

```json
{
  "id": 3,
  "volumeOrder": 1,
  "language": "pt",
  "originalTitle": "Título 999",
  "englishTitle": "Title 999",
  "originalSummary": "Um sumário",
  "englishSummary": "One summary",
  "originalKeywords": "palavra,novo",
  "englishKeywords": "word,new",
  "numberOfPages": 10,
  "authors": null
}
```

### DELETE /volumes/:volumeId/articles/:articleId - Deleta um artigo de um volume

```json
No Content
```

## Autor

Representa os autores dos artigos dos volumes

| Atributo | Descrição |
| --- | --- |
| id | Chave única para identificação do volume |
| articleOrder | Ordem do artigo no volume |
| email | E-mail do autor |
| firstName | Primeiro nome do autor |
| middleName | Nome do meio do autor |
| lastName | Sobrenome do autor  |
| affiliation | Afiliação do autor |
| englishAffiliation | Afiliação do autor em inglês  |
| country | País do autor ("BR", "PT", "US", "FR", "UK", ou "ES") |
| orcID | Regitro OrcID ("XXXX-XXXX-XXXX-XXXX") |

### GET /articles/:articleId/authors - Lista todos os autores de um artigo

```json
[
  {
    "id": 5,
    "articleOrder": 1,
    "email": "email@email.com",
    "firstName": "Nome 1",
    "middleName": "Nome 2",
    "lastName": "Nome 3",
    "affiliation": "ABC",
    "englishAffiliation": "CBA",
    "country": "BR",
    "orcID": "1234-1234-1234-1234"
  }
]
```

### GET /articles/:articleId/authors/:authorId - Lista um autor de um artigo

```json
{
  "id": 5,
  "articleOrder": 1,
  "email": "email@email.com",
  "firstName": "Nome 1",
  "middleName": "Nome 2",
  "lastName": "Nome 3",
  "affiliation": "ABC",
  "englishAffiliation": "CBA",
  "country": "BR",
  "orcID": "1234-1234-1234-1234"
}
```

### POST /articles/:articleId/authors - Cria um autor para um artigo

| Body | Descrição |
| --- | --- |
| articleOrder | Ordem do artigo no volume |
| email | E-mail do autor |
| firstName | Primeiro nome do autor |
| middleName | Nome do meio do autor |
| lastName | Sobrenome do autor  |
| affiliation | Afiliação do autor |
| englishAffiliation | Afiliação do autor em inglês  |
| country | País do autor ("BR", "PT", "US", "FR", "UK", ou "ES") |
| orcID | Regitro OrcID ("XXXX-XXXX-XXXX-XXXX") |

```json
{
  "id": 6,
  "articleOrder": 1,
  "email": "email@email.com",
  "firstName": "Nome 2",
  "middleName": "Nome 3",
  "lastName": "Nome 4",
  "affiliation": "ABC",
  "englishAffiliation": "CBA",
  "country": "BR",
  "orcID": "1234-1234-1234-1234"
}
```

### PUT /articles/:articleId/authors/:authorId - Atualiza um autor de um artigo

| Body | Descrição |
| --- | --- |
| articleOrder | Ordem do artigo no volume |
| email | E-mail do autor |
| firstName | Primeiro nome do autor |
| middleName | Nome do meio do autor |
| lastName | Sobrenome do autor  |
| affiliation | Afiliação do autor |
| englishAffiliation | Afiliação do autor em inglês  |
| country | País do autor ("BR", "PT", "US", "FR", "UK", ou "ES") |
| orcID | Regitro OrcID ("XXXX-XXXX-XXXX-XXXX") |

```json
{
  "id": 6,
  "articleOrder": 2,
  "email": "email222@email.com",
  "firstName": "Nome 1",
  "middleName": "Nome 2",
  "lastName": "Nome 3",
  "affiliation": "ABC",
  "englishAffiliation": "CBA",
  "country": "BR",
  "orcID": "1234-1234-1234-1234"
}
```

### DELETE /articles/:articleId/authors/:authorId - Deleta um autor de um artigo

```json
No Content
```
