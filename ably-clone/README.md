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

- 인수 테스트는 Rest assured를 이용하여 작성
- 인수 테스트는 간략하게 설명하자면 실제로 외부에서 웹 요청을 쏘고, 서버가 실제로 응답을 반환하고 해당 응답을 검증하는 과정을 거침
- 현 코드에서는 아이템 목록 조회, 아이템 단건 조회를 인수 조건으로 설정하고 테스트를 작성
- ATDD로 코드를 작성함으로써 안정적인 코드 작성

#### 통합 테스트

- 통합 테스트는 기본적인 AssertJ만 이용하여 작성
- 통합 테스트는 실제로 스프링 컨텍스트를 띄우지만, 웹 요청까진 받지 않음. 현 코드에선 service 계층에서 호출되는 메서드를 통합테스트로 검사
- 인수 테스트보단 조금 더 가볍고, 작은 단위를 테스트

#### 테스트 환경 구성

1. 테스트 컨텍스트 캐싱
    - 단순한 도메인 테스트나 문서화 테스트의 경우 부하가 크지 않지만, 스프링을 실제로 띄우는 인수테스트나 통합테스트는 테스트 속도에 영향을 줄 수 있음
    - support 패키지에 정의되어있는 AcceptanceTest, DocTest, ServiceTest라는 abstract class 를 정의하고 이를 상속받게 함으로써
      테스트 환경을 한정 짓고 캐싱을 통한 테스트 속도 상승
2. 테스트 픽스쳐
    - 반복적으로 사용되는 픽스쳐들에 대해서는 fixture 패키지에 모아두고, 이를 이용하여 테스트 메서드 작성
    - static 메서드로 생성하게 함으로써 테스트마다 사용되는 픽스쳐들은 다른 객체이므로 테스트간 격리 보장
3. 테스트 디비 격리
    - 테스트 컨텍스트 캐싱을 하기 때문에, 각각의 테스트가 같은 디비를 공유하게 됨.
    - 테스트가 다른 테스트에 영향을 끼치면 안되므로, 테스트가 수행되고 나면 디비를 비워줘야 함
    - config 패키지에서 DatabaseCleaner와 DatabaseClearExtension을 구현하고 이를 support 패키지에 있는 abstract class에
      적용
    - 테스트 메서드가 실행되기전 모든 데이터를 초기화하는 과정이 자동화됨으로써 테스트간 디비 격리 수행

### 설계

#### DefaultResponseFormat

- response를 다음과 같은 형식으로 지켜달라는 요구사항이 존재

```json
{
  "suceess": true,
  "msg": "요청에 성공했습니다.",
  "data": {
    "key1": "blabla1",
    "key2": 150000,
    "key3": "blabla2"
  }
}
```

- data 부분을 제외하면 나머지는 모두 공유되는 부분이므로 이 또한 abstract class(DefaultResponseFormat)로 추상화
- 실제 response dto 들은 해당 class를 상속받고 inner 클래스에서 data 부분을 정의하도록 설계

#### H2 memory DB 사용

- database를 연결은 하였으나, 디비를 연결할 만큼 큰 규모의 프로젝트가 아니기 때문에 h2 메모리 디비 선택
- h2 메모리 디비를 선택했으나 Dialect는 Mysql로 설정함으로써, 추후 실제 디비를 연결할 때 Mysql을 써도 문제가 없도록 설정

#### No Offest 기반 페이징

- 실제 서비스의 경우 Item 목록 전체를 불러 오는 것은 굉장히 큰 과부하가 발생
- 그렇기 때문에 일부만 로딩하는 Pagination 구현
- 거기에 덧붙여, no offset 방식으로 구현하였는데, 이렇게 작성된 쿼리가 offset 보다 더 효율적인 쿼리로 동작
- 실제 에이블리 서비스의 경우 무한 스크롤 방식이기 때문에 no offset 방식이 효율적이어서 해당 방식으로 구현
- 아래는 실제 코드에 작성된 쿼리(jpql이기 때문에 기존 sql과는 문법이 살짝 다름)

```
select i
from Item i
join fetch Seller s on s.id = i.seller.id
where i.id > :lastItemId
ORDER BY i.id ASC
limit 10
```

## 개발 환경

- JDK 17
- Spring Boot 3.2.3
    - Spring data jpa
    - Spring web
    - Spring test
- lombok
- h2
- rest docs
- rest assured