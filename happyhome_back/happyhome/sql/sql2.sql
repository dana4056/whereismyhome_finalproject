--
-- Table structure for table `interest`
--

DROP TABLE IF EXISTS `interest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interest` (
  `id` varchar(40) NOT NULL,
  `dongCode` varchar(10) NOT NULL,
  `area` varchar(50) NOT NULL,
  PRIMARY KEY (`id`,`dongCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interest`
--

LOCK TABLES `interest` WRITE;
/*!40000 ALTER TABLE `interest` DISABLE KEYS */;
INSERT INTO `interest` VALUES ('admin','1168010100','서울특별시 강남구 역삼동');
/*!40000 ALTER TABLE `interest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mymap`
--

DROP TABLE IF EXISTS `mymap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mymap` (
  `id` varchar(40) NOT NULL,
  `dongCode` varchar(10) NOT NULL,
  `mymapnum` int NOT NULL AUTO_INCREMENT, 
  `category_group_code` varchar(40) NOT NULL,
  `category_group_name` varchar(40) NOT NULL,
  `lati` varchar(30),
  `longi` varchar(30),
  
  `addr` varchar(30),
  `apartmentName` varchar(30),
  `aptCode` bigint,  
  `maxPrice` varchar(20),
  `minPrice` varchar(20),
  `recentPrice` varchar(20),
  
  `address_name` varchar(30),
  `phone` varchar(30),
  `place_name`  varchar(50),
  `place_url` varchar(200),
  `road_address_name` varchar(50),

   
  
  PRIMARY KEY (`mymapnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mymap`
--



--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(40) NOT NULL,
  `content` text NOT NULL,
  `view` int NOT NULL DEFAULT '0',
  `pin` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (1,'서비스 출시 안내','안녕하세요 해피하우스 서비스 관리자입니다....',52,1),
(2,'@@정책 변경 안내','@@정책을 변경하고자 안내드립니다.',38,1),
(3,'##정책 변경 안내','##정책을 변경하고자 안내드립니다.',29,0),
(4,'$$정책 변경 안내','$$정책을 변경하고자 안내드립니다.',5,0),
(5,'%%정책 변경 안내','%%정책을 변경하고자 안내드립니다.',23,0),
(6,'@@@ 버전 지원 종료 안내','M사의 @@@ 지원 종료 발표에 따라,....',9,1),
(7,'해피하우스 위치기반서비스 이용약관 개정 안내','항상 해피하우스 서비스를 이용해주시는 이용자 여러분께 진심으로 감사드리며 해피하우스 위치기반서비스 이용약관 개정에 대한 사전 안내 말씀 드립니다.',12,0),
(8,'허위광고 검증 관련 안내','해피하우스는 이용자분들 및 중개사분들의 허위광고로 인한 피해를 예방하기 위해, 전 지역을 대상으로 강력한 매물 검증을 진행하고자 합니다...',4,0),
(9,'서비스 장애 발생에 따른 일부 서비스 복구 안내','안녕하세요, 고객님. 먼저 이용에 불편을 드리게 되어 대단히 죄송합니다.',2,0),
(10,'ㅁㅁ정책 변경 안내','ㅁㅁ정책을 변경하고자 안내드립니다. 앞서 ㅁㅁ에 대해서 간략히 설명드리겠습니다.',5,0),
(11,'서비스 출시 안내','안녕하세요 해피하우스 서비스 관리자입니다....',12,1),
(12,'임직원 교육에 따른 상담 업무 일시 중지 안내','안녕하세요, 고객님 \r 항상 저희 해피홈을 믿고 이용해주시는 고객님께 깊이 감사드립니다.',13,0),
(13,'직방콜 서비스 중단 안내','안녕하세요, 고객님 ooo서비스가 잠정 중단될 예정입니다',5,1);
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;





--
-- Table structure for table `qna`
--

DROP TABLE IF EXISTS `comments`;
DROP TABLE IF EXISTS `qna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qna` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(40) NOT NULL,
  `writer` varchar(40) NOT NULL,
  `content` text NOT NULL,
  `view` int NOT NULL DEFAULT '0',
  `good` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qna`
--

LOCK TABLES `qna` WRITE;
/*!40000 ALTER TABLE `qna` DISABLE KEYS */;
INSERT INTO `qna` VALUES (1,'qna1','홍길동','qna내용1',1, 0),
(2,'qna제목2','홍길동','qna내용2',0,0),
(3,'qna제목3','홍길동','qna내용3',0,0),
(4,'qna제목4','홍길동','qna내용4',0,0),
(5,'qna제목5','홍길동','qna내용5',0,0),
(6,'qna제목6','홍길동','qna내용6',0,0),
(7,'qna제목7','홍길동','qna내용7',0,0),
(8,'qna제목8','홍길동','qna내용8',0,0),
(9,'qna제목9','홍길동','qna내용9',0,0),
(10,'qna제목10','홍길동','qna내용10',0,0),
(11,'qna제목11','홍길동','qna내용11',0,0),
(12,'qna제목12','홍길동','qna내용12',0,0),
(13,'qna제목13','홍길동','qna내용13',0,0),
(14,'qna제목14','홍길동','qna내용14',0,0),
(15,'qna제목15','홍길동','qna내용15',0,0),
(16,'qna제목16','홍길동','qna내용16',0,0);
/*!40000 ALTER TABLE `qna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `comment_no` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(16) NOT NULL,
  `comment` varchar(500) NOT NULL,
  `comment_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `id` int NOT NULL,
  PRIMARY KEY (`comment_no`),
  KEY `comment_id_idx` (`id`),
  CONSTRAINT `comment_id` FOREIGN KEY (`id`) REFERENCES `qna` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1, '김길동', '댓글댓글1', 0, 2);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `freeboard`
--

DROP TABLE IF EXISTS `comments2`;
DROP TABLE IF EXISTS `freeboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `freeboard` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` int NOT NULL,
  `title` varchar(40) NOT NULL DEFAULT '0',
  `writer` varchar(40) NOT NULL,
  `area` varchar(100) NOT NULL,
  `content` text NOT NULL,
  `view` int NOT NULL DEFAULT '0',
  `good` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `freeboard`
--

LOCK TABLES `freeboard` WRITE;
/*!40000 ALTER TABLE `freeboard` DISABLE KEYS */;
INSERT INTO `freeboard` VALUES (1,1,'마라탕 맛집 공유', 'dana','서울특별시 강남구 역삼동','역삼역 1번출구로 쭉 가면 나오는 마라탕집 JMT',1,0),
(2,2,'조기축구회 모집', '구로구 7번','서울특별시 구로구 신도림동','주말 아침 조기축구회 회원 모집합니다. 번호는...',22,10),
(3,1,'ㅁㅁ커피', '맛잘알','서울특별시 강남구 역삼동','ㅁㅁ커피 마카롱 진짜 맛있어요',13,7),
(4,4,'에어팟 프로 오른쪽 보신분', '짝짝이','서울특별시 강남구 역삼동','사례하겠습니다.....ㅜㅜㅜ',5,2),
(5,2,'DIY목공', '역삼동 야근쟁이','서울특별시 강남구 역삼동','간단한 목공 같이 하실 분',12,5),
(6,2,'케이크 만들기 원데이클래스', '거북목','서울특별시 강남구 역삼동','케이크 원데이 클래스 같이 등록하실 분 구합니다~',12,0),
(7,1,'ㅇㅇ백화점 맛집', 'dana','서울특별시 구로구 신도림동','백화점 지하 1층에 맛집 아시는 분',12,6),
(8,1,'양꼬치 무한리필', '신도림 불주먹','서울특별시 구로구 신도림동','양꼬치 무한리필인데 진짜 맛있어요',14,5),
(9,2,'팔찌만들기클래스', '워크맨','서울특별시 구로구 신도림동','이쁜 팔찌를 만듭시다 룰루',5,1),
(10,4,'목도리 주으신분', 'hoho','서울특별시 구로구 신도림동','도림천에서 마지막까지 있었는데 회색 목도리 보신분....',12,10),
(11,3,'복사기 빌려주실분', '역삼동 사고뭉ㅊㅣ','서울특별시 강남구 역삼동','회사 복사기를 고장냈는데 3시간만 빌려주실 분 계실까요...',20,15),
(12,3,'강아지를 찾아주세요', '멍멍아ㅏ','서울특별시 강남구 역삼동','강아지를 찾습니다.',52,20),
(13,2,'테니스 같이 치실 분', '구로구 주정뱅이','서울특별시 구로구 신도림동','테이스장에서 만나요',2,0),
(14,1,'포장마차', '구로구 주정뱅이','서울특별시 구로구 신도림동','포장마차 우동 JMT',2,0),
(15,3,'감나무 감따기', '감감무소식','서울특별시 구로구 신도림동','마당에 큰 감나무가 있는데, 주말에 같이 작업해주실분 구합니다.',12,10),
(16,4,'OO지갑 분실', '뚜벅이','서울특별시 강남구 역삼동','역삼역에서 OO브랜드 지갑 보신분..',20,15),
(17,2,'조깅합시다', '호롤롤로','서울특별시 구로구 신도림동','도림천 같이 뛰실분',12,5),
(18,1,'중국집 가장 빨리 오는 곳 아시는 분', '고길동','서울특별시 강남구 역삼동','중국집 가장 빨리 오는 곳 아시는 분있나요????',5,0);

/*!40000 ALTER TABLE `freeboard` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `comments2`
--

DROP TABLE IF EXISTS `comments2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments2` (
  `comment_no` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(16) NOT NULL,
  `comment` varchar(500) NOT NULL,
  `comment_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `id` int NOT NULL,
  PRIMARY KEY (`comment_no`),
  KEY `comment2_id_idx` (`id`),
  CONSTRAINT `comment2_id` FOREIGN KEY (`id`) REFERENCES `freeboard` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `comments2`
--

LOCK TABLES `comments2` WRITE;
/*!40000 ALTER TABLE `comments2` DISABLE KEYS */;
INSERT INTO `comments2` VALUES (1, '김길동', '자유게시판댓글1', 0, 2);
/*!40000 ALTER TABLE `comments2` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(40) NOT NULL,
  `password` varchar(1000) NOT NULL,
  `name` varchar(40) NOT NULL,
  `address` varchar(40) NOT NULL,
  `phone` varchar(40) NOT NULL,
  `email` varchar(100) NOT NULL,
  `salt` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','fea1e9613d4c623e52e481a465cc93f5fde1cf40abbc32ebba3d99004d0418dc1c6d8d26d6158e1a3b94192029119c262a5859518580727e90049f383ec4f031','admin','서울기 강남구 역삼동','01011111111','minjateo@naver.com','/jmHuxHQpdstak9kccHeIg=='),('daeun','4f222b72f728472e09b4b50fc84a1c08b264572d4e511bbddd528f041580cca0249efb8d25d52f5cb1b3808a50dd6222de595378286307af0f68a417aa6f684b','다은','서울시 구로구','1234','minjateo@naver.com','yAc7MNwhp5blxt7rAhzPRA=='),('ssafy','b1758ea5978e341c2edc4a9b36de96b07b90fdd2215438ba6eaeef9d11b1714b30789aff6ad53f8d50f761c9c0e70bcb39721a08ac200863d5d97da6a3276ab2','ssafy','서울시 강남구 역삼동','01022222222','minjateo@naver.com','x3WcmabYz+slalboPejtLw==');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `freeboardgood`
--


DROP TABLE IF EXISTS `freeboardgood`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `freeboardgood` (
  `id` varchar(40) NOT NULL,
  `freeboardid` int NOT NULL,
  PRIMARY KEY (`id`, `freeboardid`)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `freeboardgood`
--

LOCK TABLES `freeboardgood` WRITE;
/*!40000 ALTER TABLE `freeboardgood` DISABLE KEYS */;
INSERT INTO `freeboardgood` VALUES ('admin', 2), ('admin', 3),('admin', 4),('ssafy', 1),('ssafy', 2);
/*!40000 ALTER TABLE `freeboardgood` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `qnagood`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qnagood` (
  `id` varchar(40) NOT NULL,
  `qnaid` int NOT NULL,
  PRIMARY KEY (`id`, `qnaid`)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `freeboardgood`
--

LOCK TABLES `qnagood` WRITE;
/*!40000 ALTER TABLE `qnagood` DISABLE KEYS */;
INSERT INTO `qnagood` VALUES ('admin', 2), ('admin', 3),('admin', 4),('ssafy', 1),('ssafy', 2);
/*!40000 ALTER TABLE `qnagood` ENABLE KEYS */;
UNLOCK TABLES;
