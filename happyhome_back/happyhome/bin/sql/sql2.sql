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
INSERT INTO `interest` VALUES ('admin','1130510200','서울특별시 강북구 번동');
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
INSERT INTO `notice` VALUES (1,'공지1','내용1',1,0),(2,'공지2','내용2',0,0),(3,'공지3','내용3',0,0),(4,'공지4','내용4',0,0),(5,'공지5','내용5',0,0),(6,'공지6','내용6',0,0),(7,'공지7','내용7',0,0),(8,'공지8','내용8',0,0),(9,'공지9','내용9',0,0),(10,'공지10','내용10',0,0),(11,'공지11','내용11',0,0),(12,'공지12','내용12',0,0),(13,'공지13','내용13',0,0),(14,'공지14','내용14',0,0),(15,'공지15','내용15',0,0),(16,'공지16','내용16',0,0);
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
INSERT INTO `qna` VALUES (1,'qna1','홍길동','qna내용1',1, 0),(2,'qna제목2','홍길동','qna내용2',0,0),(3,'qna제목3','홍길동','qna내용3',0,0),(4,'qna제목4','홍길동','qna내용4',0,0),(5,'qna제목5','홍길동','qna내용5',0,0),(6,'qna제목6','홍길동','qna내용6',0,0),(7,'qna제목7','홍길동','qna내용7',0,0),(8,'qna제목8','홍길동','qna내용8',0,0),(9,'qna제목9','홍길동','qna내용9',0,0),(10,'qna제목10','홍길동','qna내용10',0,0),(11,'qna제목11','홍길동','qna내용11',0,0),(12,'qna제목12','홍길동','qna내용12',0,0),(13,'qna제목13','홍길동','qna내용13',0,0),(14,'qna제목14','홍길동','qna내용14',0,0),(15,'qna제목15','홍길동','qna내용15',0,0),(16,'qna제목16','홍길동','qna내용16',0,0);
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
INSERT INTO `freeboard` VALUES (1,3,'free제목1', '고길동','free내용1',1,0),(2,3,'free제목2', '고길동','free내용2',0,0),(3,3,'free제목3', '고길동','free내용3',0,0),(4,2,'free제목4', '고길동','free내용4',0,0),(5,3,'free제목5', '고길동','free내용5',0,0),(6,2,'free제목6', '고길동','free내용6',0,0),(7,1,'free제목7', '고길동','free내용7',0,0),(8,4,'free제목8', '고길동','free내용8',0,0),(9,1,'free제목9', '고길동','free내용9',0,0),(10,3,'free제목10', '고길동','free내용10',0,0),(11,3,'free제목11', '고길동','free내용11',0,0),(12,2,'free제목12', '고길동','free내용12',0,0),(13,2,'free제목13', '고길동','free내용13',0,0),(14,1,'free제목14', '고길동','free내용14',0,0),(15,2,'free제목15', '고길동','free내용15',0,0),(16,1,'free제목16', '고길동','free내용16',0,0);
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
