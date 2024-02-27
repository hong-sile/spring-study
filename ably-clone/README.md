# ably clone coding

## 요구사항

- [x] 홈 화면 API(Item 목록 조회)
    - [x] API 명세 정의
    - [x] rest docs 테스트 작성
    - [x] Item 목록 조회 로직 구현
        - [x] 페이지 네이션(후순위)
        - [x] no offset 페이지네이션(후후순위)
- [x] 상세 페이지 화면 API
    - [x] API 명세 정의
    - [x] rest docs 테스트 작성
    - [x] Item 단건 조회 로직 구현
- [x] 엔티티 설계
    - [x] Item
    - [x] Seller
- [x] restdocs 환경 설정
- [x] 테스트 격리 수행
- [ ] 초기 데이터 삽입

## API 명세

### 홈 화면 API(Item 목록 조회)

#### Request

GET /items

#### Response

```json
{
  "success": true,
  "msg": "아이템 목록 조회 성공",
  "data": [
    {
      "id": 1,
      "discountPercentage": "29%",
      "sellerName": "clug_1",
      "itemName": "나시 티셔츠",
      "imageUrl": "이미지",
      "discountedPrice": 23500
    }
  ]
}
```

### 상세 페이지 화면 API

#### Request

GET /items/{itemId}

#### Response

```json
{
  "success": true,
  "msg": "아이템 상세 조회 성공",
  "data": {
    "imageUrls": [
      "image"
    ],
    "name": "나시 티셔츠",
    "originPrice": 29900,
    "discountedPrice": 21229,
    "sellerName": "Clug_1",
    "sellerProfileImageUrl": "클러그 프로필 이미지 url"
  }
}
```

## 설명

### 테스트

- 자동화된 테스트를 구축하기 위하여 다음과 같은 환경의 테스트 코드를 작성

#### 문서화 테스트

- 문서화 테스트는 Rest Docs, MockMvc를 이용하여 작성
- 문서화 테스트로 실제 프로덕션 코드의 end point, request, response 들을 검증할 수 있고, 해당 테스트 코드로 문서화된 API 명세 페이지 자동생성
- 생성된 문서는 테스트를 통과해야만 작성되므로, 명세와 백엔드의 코드가 일치하지 않을 일은 없음

[API 명세 문서](src/main/resources/static/docs/index.html)

#### 인수 테스트

#### 통합 테스트

### 설계

### DefaultResponseFormat

#### No Offest 기반 페이징