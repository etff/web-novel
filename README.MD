# 웹소설 클론

## 개요

- 인기 웹소설 사이트를 바탕으로 웹소설 기능을 클론하는 프로젝트입니다.

## 실행방법

- 별도의 설치는 필요하지 않고 `./gradlew clean`, `./gradlew bootRun` 명령어를 통해 실행할 수 있습니다.
- 테스트 계정
  - id: test1@test.com
  - pw: "test"

## ERD

![erd](./assets/image/erd.png)

# 기능

- [x] 소설 등록/작성/삭제
- [x] 선호작 목록
- [x] 포인트 충전
- [X] 소설 편당 조회 및 결제
- [] 다양한 기준의 소설 목록 조회 기능(홈 노출/베스트/기타)

---

## API
`http://localhost:8080/swagger-ui/index.html`
## 사용자

### 회원가입

- URL: /api/v1/users/signup
- Method: POST
- Request Body

```
{
    "email": "test2@test.com",
    "name": "test2",
    "password": "test"
}
```

### 로그인

- URL: /api/v1/auth/login
- Method: POST
- Request Body

```
{
    "email": "test2@test.com",
    "password": "test"
}
```

### 사용자 선호도서 가져오기

- URL: /api/v1/users//{userId}/favorites?page={page}&size={size}
- Header: Authorization: Bearer {token}
- Method: GET

---

## 상품

### 상품 등록

- URL: /api/v1/products
- Method: POST
- Header: Authorization: Bearer {token}
- Request Body

```
{
    "name": "스페셜티켓",
    "price": 1000,
    "quantity": 10,
    "productType": "TICKET"
}
```

### 상품 상태변경

- URL: /api/v1/products/{productId}/status
- Method: POST
- Header: Authorization: Bearer {token}
- Request Body

```
{
    "ProductStatus": "SELLING',
    "productType": "TICKET"
}
```

---

## 주문

### 토큰 발급

- URL: /api/v1/orders/token
- Method: POST
- Header: Authorization: Bearer {token}
- Request Body

```
{
}
```

### 상품주문

- URL: /api/v1/orders
- Method: POST
- Header: Authorization: Bearer {token}
- Request Body

```
{
    "productId": 1,
    "userId": 1,
    "token": {주문토큰}
}
```

---

## 도서

### 도서 등록

- URL: /api/v1/books
- Method: POST
- Header: Authorization: Bearer {token}
- Request Body

```
{
    "title": "test",
    "categoryId": 1
    "authorId": 1
}
```

### 도서 읽기

- URL: /api/v1/books/{bookId}
- Method: GET
- Header: Authorization: Bearer {token}

### 도서 목록 가져오기

- URL: /api/v1/books?bookStatus={bookStatus}?page={page}&size={size}
- Method: GET
- Header: Authorization: Bearer {token}

### 도서 상태변경

- URL: /api/v1/books/{bookId}/status
- Method: PATCH
- Header: Authorization: Bearer {token}
- Request Body

```
{
    "BookStatus": "SELLING"
}
```

## 에피소드

### 에피소드 등록

- URL: /api/v1/books/{bookId}/episodes
- Method: POST
- Header: Authorization: Bearer {token}
- Request Body

```
{
    "title": "test",
    "content": "test",
    "ticketPrice": 1000,
    "page": 50
}
```

### 에피소드 읽기

- URL: /api/v1/books/{bookId}/episodes/{episodeId}
- Method: GET
- Header: Authorization: Bearer {token}
