# wanted-pre-onboarding-backend

Java & Spring Boot
JPA & MySql

### Entity

- RecruitmentAds
  - company_id
  - job_position
  - reward_amount
  - company_name
  - technologies_used
  - job_description
  - country
  - region
- Member
  - user_id
- ApplicationRecord
  - Member
  - RecruitmentAds


### 요구사항
#### 1. 채용공고 등록
POST - /recruitment/s
##### HTTP request
```json
{
    "companyId": "ai",
    "jobPosition": "AI",
    "rewardAmount": 1000000,
    "jobDescription": "우리는 해낸다",
    "technologiesUsed" : "python",
    "companyName": "네이버",
    "country": "한국",
    "region":"서울"
}
```
##### Response body
```json
{
    "issuccess": true,
    "code": "200",
    "message": "요청에 성공하였습니다."
}
```

#### 2. 채용공고 수정
PATCH - /recruitment/{id}/u
##### HTTP request
```json
{
    "companyId": "ai",
    "jobPosition": "AI",
    "rewardAmount": 1000000,
    "jobDescription": "우리는 해낸다",
    "technologiesUsed" : "python",
    "companyName": "네이버",
    "country": "한국",
    "region":"서울"
}
```
##### Response body
```json
{
    "issuccess": true,
    "code": "200",
    "message": "요청에 성공하였습니다."
}
```

#### 3. 채용공고 삭제
DELETE - /recruitment/{id}/d
##### Path parameters
| Name | Description |
| ------- | ------- |
| id | 채용공고 번호 |

#### 4-1. 채용공고 목록 가져오기
GET - /recruitment/all
##### Response body
```json
{
    "issuccess": true,
    "code": "200",
    "message": "요청에 성공하였습니다.",
    "result": [
        {
            "id": 1,
            "companyId": "naver11",
            "jobPosition": "backend",
            "rewardAmount": 1000000,
            "companyName": "naver",
            "technologiesUsed": "spring"
        },
        {
            "id": 2,
            "companyId": "naver",
            "jobPosition": "AI",
            "rewardAmount": 1000000,
            "companyName": "네이버",
            "technologiesUsed": "python"
        },
        {
            "id": 4,
            "companyId": "naver",
            "jobPosition": "forentend",
            "rewardAmount": 1000000,
            "companyName": "네이버",
            "technologiesUsed": "javascript"
        }
    ]
}
```

#### 4-2. 채용공고 검색
GET - /recruitment/search?keyword=
##### Query parameters
| Parameter | Description |
| ------- | ------- |
| keyword | 검색할 키워드 |

##### Response body
```json
{
    "issuccess": true,
    "code": "200",
    "message": "요청에 성공하였습니다.",
    "result": [
        {
            "id": 1,
            "companyId": "naver11",
            "jobPosition": "backend",
            "rewardAmount": 1000000,
            "companyName": "naver",
            "technologiesUsed": "spring"
        }
    ]
}
```

#### 5. 채용 상세 페이지 가져오기
GET - /recruitment/{id}/detail
##### Path parameters
| Name | Description |
| ------- | ------- |
| id | 채용공고 번호 |

##### Response body
```json
{
    "issuccess": true,
    "code": "200",
    "message": "요청에 성공하였습니다.",
    "result": {
        "companyId": "naver",
        "jobPosition": "AI",
        "rewardAmount": 1000000,
        "companyName": "네이버",
        "technologiesUsed": "python",
        "jobDescription": "우리는 해낸다",
        "country": "한국",
        "region": "서울",
        "otherRecruitments": [
            {
                "id": 4,
                "companyId": "naver",
                "jobPosition": "forentend",
                "rewardAmount": 1000000,
                "companyName": "네이버",
                "technologiesUsed": "javascript",
                "jobDescription": "우리는 해낸다",
                "country": "한국",
                "region": "서울"
            }
        ]
    }
}
```

#### 6. 사용자 채용공고 지원
POST - /applicants/{recruitmentId}
##### Path parameters
| Name | Description |
| ------- | ------- |
| recruitmentId | 채용공고 번호 |
##### HTTP request
```json
{
    "userId":"aossuper7"
}
```
##### Response body
```json
{
    "issuccess": true,
    "code": "200",
    "message": "요청에 성공하였습니다."
}
```


#### Error Code
| code | 설명 및 메시지 | HTTP 상태코드 | Status 설명 |
| ------- | ------- | ------- | ------- |
| REC400 | 채용공고를 찾을 수 없습니다. | 404 | NotFound |
| MBR400 | 유저를 찾을 수 없습니다. | 404 | NotFound |
| APP400 | 사용자당 한번만 지원 할 수 있습니다. | 409 | Conflict |
