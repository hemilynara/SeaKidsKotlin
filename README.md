# SeaKids

- Educação para a Conservação Marinha: Navegando em Direção a um Futuro Sustentável

Este projeto visa implementar uma plataforma educacional voltada para crianças, abordando de forma lúdica e acessível os conceitos de conservação marinha, poluição e a importância da biodiversidade marinha. Disponível em formato de aplicativo, a plataforma oferece material didático intuitivo e interativo. 

# Contribuidores

- MACIRANDER - RM551416 - 2TDSPF
- CARLOS - RM97528 - 2TDSPX
- HEMILY - RM550889 - 2TDSPX
- KAIQUE - RM551165 - 2TDSPX
- VINICIUS - RM98839 - 2TDSPX

# Documentação da API

## Endpoints Disponíveis

### Consulta de Endereço por CEP

- **Descrição:** Este endpoint é utilizado para buscar informações de endereço com base no CEP fornecido no momento do cadastro do usuário na plataforma.
  
- **Método:** GET
  
- **URL:** [https://viacep.com.br/ws/{cep}/json/](https://viacep.com.br/)

- **Exemplo de Requisição:** 
```bash
curl -X GET https://viacep.com.br/ws/01001000/json/
```

Exemplo de Resposta:
```json
{
  "cep": "01001-000",
  "logradouro": "Praça da Sé",
  "complemento": "lado ímpar",
  "bairro": "Sé",
  "localidade": "São Paulo",
  "uf": "SP",
  "ibge": "3550308",
  "gia": "1004",
  "ddd": "11",
  "siafi": "7107"
}
```
# Tratamento de Erros nas Requisições
Exemplo: Tratamento de Erros na classe  BuscarCepActivity

Na classe BuscarCepActivity fornecida, o tratamento de erros para requisições de rede é realizado principalmente em dois pontos:

- Quando a resposta HTTP não é bem-sucedida:

- Quando ocorre uma falha durante a chamada de rede

# Explicação do Código

- Tratando Resposta HTTP Não Bem-Sucedida:

No método onResponse, o código verifica se a resposta foi bem-sucedida. Se não for, ele define o TextView tvResultado para exibir uma mensagem de erro para o usuário. Isso garante que os usuários sejam informados sobre problemas relacionados ao código de resposta HTTP.

```kotlin
override fun onResponse(call: Call<Endereco>, response: Response<Endereco>) {
    if (response.isSuccessful) {
        val endereco = response.body()
        endereco?.let {
            tvResultado.text = """
                CEP: ${it.cep}
                Logradouro: ${it.logradouro}
                Complemento: ${it.complemento}
                Bairro: ${it.bairro}
                Localidade: ${it.localidade}
                UF: ${it.uf}
            """.trimIndent()
        } ?: run {
            tvResultado.text = "CEP não encontrado."
        }
    } else {
        tvResultado.text = "Erro na resposta: ${response.code()}"
    }
}
```

- Tratando Falha na Chamada de Rede:

No método onFailure, o código registra o erro e define o TextView tvResultado para exibir uma mensagem de erro para o usuário. Isso garante que qualquer falha na chamada de rede (por exemplo, problemas de conexão) seja comunicada ao usuário.

```koltin
override fun onFailure(call: Call<Endereco>, t: Throwable) {
    Log.e("BuscarCepActivity", "Erro na chamada", t)
    tvResultado.text = "Erro na chamada: ${t.message}"
}
```

# Pontos de Tratamento de Erro Evidenciados
- Resposta HTTP Não Bem-Sucedida:

```tvResultado.text = "Erro na resposta: ${response.code()}"``` dentro do método onResponse.

- Falha na Chamada de Rede:
  
```tvResultado.text = "Erro na chamada: ${t.message}"``` dentro do método onFailure.



