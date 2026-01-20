# Subscription API

API REST para gerenciamento de **assinaturas**, desenvolvida com **Spring Boot**, aplicando regras de negócio baseadas no status da assinatura.


## 📌 Funcionalidades

- Criar assinatura
- Listar assinaturas
- Buscar assinatura por ID
- Cancelar assinatura
- Filtrar assinaturas por status

---

## 📊 Status da Assinatura

- **ACTIVE** — Assinatura ativa
- **CANCELED** — Assinatura cancelada
- **EXPIRED** — Assinatura expirada

---

## 📦 Tipos de Plano

- **BASIC**
- **PREMIUM**
- **ENTERPRISE**

---

## 🧠 Regras de Negócio

- A criação de uma assinatura sempre inicia com status **ACTIVE**
- Apenas assinaturas com status **ACTIVE** podem ser canceladas
- Assinaturas **CANCELED** não podem ser reativadas
- Assinaturas **EXPIRED** não podem voltar para **ACTIVE**
- Ao cancelar uma assinatura, o **autoRenew** é automaticamente definido como `false`
- A listagem de assinaturas pode ser filtrada por status via query parameter

---

## 🌐 Endpoints Principais

| Método | Endpoint                    | Descrição                       |
|------|-----------------------------|---------------------------------|
| POST | `/subscription`             | Criar uma nova assinatura       |
| GET  | `/subscription`             | Listar assinaturas              |
| GET  | `/subscription/{id}`        | Buscar assinatura por ID        |
| PUT  | `/subscription/{id}/cancel` | Cancelar uma assinatura         |

---

## 🔍 Filtro por Status

GET /subscription?status=ACTIVE
GET /subscription?status=CANCELED
GET /subscription?status=EXPIRED

---

## 📝 Observações

Este projeto foi desenvolvido com foco em:

- Boas práticas REST
- Uso de **Enums** para controle de status e plano
- Regras de negócio implementadas na camada de Service
- Separação clara de responsabilidades (Controller, Service, Repository)
- Validação de dados com **Bean Validation**

---

## 🚀 Tecnologias

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation
- H2 Database
- Maven

---

