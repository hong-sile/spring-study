# ably clone coding

## 요구사항

- [ ] 홈 화면 API
    - [x] API 명세 정의
    - [ ] rest docs 테스트 작성
    - [ ] Item 목록 조회 로직 구현
        - [ ] 페이지 네이션(후순위)
        - [ ] no offset 페이지네이션(후후순위)
    - [ ] ItemCache(고민)
- [ ] 상세 페이지 화면 API
    - [x] API 명세 정의
    - [ ] rest docs 테스트 작성
    - [ ] Item 단건 조회 로직 구현
- [ ] 도커 컴포즈 구성
- [ ] 엔티티 설계
    - [ ] Item
    - [x] Seller
    - [ ] User(후순위)
- [x] restdocs 환경 설정

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
      "discountRate": "29%",
      "sellerName": "clug_1",
      "itemName": "나시 티셔츠",
      "imageUrl": "이미지",
      "price": 23500
    }
  ]
}
```

### 상세 페이지 화면 API

## 디비 테이블 구조

## 설명
