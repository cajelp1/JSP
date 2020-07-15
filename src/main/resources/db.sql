DROP TABLE MEMBER cascade constraints  PURGE ;
DROP TABLE ROLE CASCADE CONSTRAINTS PURGE;
DROP TABLE MENU CASCADE CONSTRAINTS PURGE;
DROP TABLE PRO_MASTER  CASCADE CONSTRAINTS  PURGE;
DROP TABLE PRO_TEAM CASCADE CONSTRAINTS PURGE;
DROP TABLE PRO_BOARDMASTER  CASCADE CONSTRAINTS PURGE;
DROP TABLE PRO_BOARD CASCADE CONSTRAINTS PURGE;
DROP TABLE REPLY CASCADE CONSTRAINTS PURGE;
DROP TABLE ATTACHFILE CASCADE CONSTRAINTS PURGE;
DROP TABLE PER_SCHEDULE CASCADE CONSTRAINTS PURGE;
DROP TABLE NOT_HISTORY CASCADE CONSTRAINTS PURGE;
DROP TABLE MEETINGROOMINFO CASCADE CONSTRAINTS PURGE;
DROP TABLE MEETINGROOMATTACHFILE CASCADE CONSTRAINTS PURGE;
DROP TABLE COMMONCODE CASCADE CONSTRAINTS PURGE;
DROP TABLE RESERVATION CASCADE CONSTRAINTS PURGE;
DROP TABLE OFF_SCHEDULE CASCADE CONSTRAINTS PURGE;
DROP TABLE SITE_BOARD CASCADE CONSTRAINTS PURGE;
DROP TABLE SITE_BOARDMASTER CASCADE CONSTRAINTS PURGE;
DROP TABLE JUSOMASTER CASCADE CONSTRAINTS PURGE;
DROP TABLE PROJECTNOTICEBOARD CASCADE CONSTRAINTS PURGE;
DROP TABLE PROJECTNOTICEATTACFILE CASCADE CONSTRAINTS PURGE;
DROP TABLE SETSENSOR CASCADE CONSTRAINTS PURGE;
DROP TABLE sensorinfo CASCADE CONSTRAINTS PURGE;
DROP TABLE PROJECTNOTICEATTACFILE CASCADE CONSTRAINTS PURGE;
DROP TABLE PLAN CASCADE CONSTRAINTS PURGE;
DROP TABLE BOARDSEQMASTER CASCADE CONSTRAINTS PURGE;
DROP TABLE MAILHISTORY CASCADE CONSTRAINTS PURGE;
drop sequence reservate_seq;
drop sequence meetingroominfo_seq;
drop sequence meetingroomattachfile_seq;
drop sequence not_history_seq;
drop sequence site_board_seq;
drop sequence attachfile_seq;
drop sequence reply_seq;
drop sequence pro_board_seq;
drop sequence project_seq;
drop sequence off_schedule_seq;
drop sequence per_schedule_seq;
drop sequence SENSORINFO_SEQ;
drop sequence jusomaster_seq;
drop sequence menu_seq;
drop sequence projectNoiceBoard_seq;
drop sequence plan_seq;
drop sequence admincommon_seq;
drop sequence LOGINLOG_SEQ;
drop sequence MAILHISOTRY_SEQ;
drop sequence SMSHISTORY_SEQ;
drop sequence pro_boardmaster_seq;

create sequence reservate_seq;
create sequence meetingroominfo_seq;
create sequence meetingroomattachfile_seq;
create sequence not_history_seq;
create sequence site_board_seq;
create sequence attachfile_seq;
create sequence reply_seq;
create sequence pro_board_seq;
create sequence project_seq;
create sequence off_schedule_seq;
create sequence per_schedule_seq;
create sequence SENSORINFO_SEQ;
create sequence jusomaster_seq;
create sequence menu_seq;
create sequence projectNoiceBoard_seq;
create sequence plan_seq;
create sequence admincommon_seq;
create sequence pro_boardmaster_seq;
-- 사용자
CREATE TABLE MEMBER (
                        email VARCHAR(100) NOT NULL,
                        password VARCHAR(15) NOT NULL,
                        phonenumber VARCHAR(30) NOT NULL,
                        name VARCHAR(50) NOT NULL,
                        nickname VARCHAR(50) NOT NULL,
                        postcode VARCHAR(10) NOT NULL,
                        address VARCHAR(150) NOT NULL,
                        detailaddress VARCHAR(150) NOT NULL,
                        role VARCHAR(20) NOT NULL,
                        deleteyn VARCHAR(1) NOT NULL,
                        regdate DATE NOT NULL
);

-- 사용자
ALTER TABLE MEMBER
    ADD CONSTRAINT PK_MEMBER -- 사용자 기본키
        PRIMARY KEY (
                     email -- 아이디
            );

-- 권한
CREATE TABLE ROLE (
                      code VARCHAR(20) NOT NULL,
                      name VARCHAR(50) NOT NULL,
                      regdate DATE NOT NULL
);

-- 권한
ALTER TABLE ROLE
    ADD CONSTRAINT PK_ROLE -- 권한 기본키
        PRIMARY KEY (
                     code -- 코드명
            );

-- 프로젝트관리
CREATE TABLE PRO_MASTER (
                            projectid NUMERIC NOT NULL,
                            projectname VARCHAR(300) NOT NULL,
                            projectexplain VARCHAR(300) NOT NULL,
                            startdate DATE NOT NULL,
                            enddate DATE NOT NULL,
                            deleteyn CHAR(1) default 'n',
                            register VARCHAR(100) NULL,
                            regdate DATE NOT NULL
);

-- 프로젝트관리
ALTER TABLE PRO_MASTER
    ADD CONSTRAINT PK_PRO_MASTER -- 프로젝트관리 기본키
        PRIMARY KEY (
                     projectid -- 프로젝트아이디
            );

-- 프로젝트멤버
CREATE TABLE PRO_TEAM (
                          projectid NUMERIC  NOT NULL,
                          memberid VARCHAR(100) NOT NULL,
                          job VARCHAR(20) NULL,
                          regdate DATE NOT NULL
);

-- 프로젝트멤버
ALTER TABLE PRO_TEAM
    ADD CONSTRAINT PK_PRO_TEAM -- 프로젝트멤버 기본키
        PRIMARY KEY (
                     projectid, -- 프로젝트아이디
                     memberid   -- 멤버아이디
            );

-- 프로젝트게시판관리
CREATE TABLE PRO_BOARDMASTER (
								 boardid NUMERIC NOT NULL,
                                 projectid NUMERIC NOT NULL,
                                 boardname VARCHAR(20) NOT NULL,
                                 boardtype VARCHAR(20) NOT NULL,
                                 fileyn CHAR(1) NOT NULL,
                                 replyyn CHAR(1) NOT NULL,
                                 filedownloadyn CHAR(1) NOT NULL,
                                 register VARCHAR(100) NOT NULL,
                                 regdate DATE NOT NULL
);

-- 프로젝트게시판관리
ALTER TABLE PRO_BOARDMASTER
    ADD CONSTRAINT PK_PRO_BOARDMASTER -- 프로젝트게시판관리 기본키
        PRIMARY KEY (
                     boardid -- 보드아이디
            );

-- PMS게시판
CREATE TABLE PRO_BOARD (
                           boardid NUMERIC NOT NULL,
                           no NUMERIC NOT NULL,
                           title VARCHAR(100) NOT NULL,
                           content CLOB NOT NULL,
                           writer VARCHAR(100) NOT NULL,
                           viewcnt NUMERIC NOT NULL,
                           regdate DATE NOT NULL,
                           updatedate DATE NOT NULL,
                           startdate DATE NULL,
                           enddate DATE NULL,
                           deleteyn CHAR(1) NOT NULL,
                           fileopenyn CHAR(1) NOT NULL
);

-- PMS게시판
ALTER TABLE PRO_BOARD
    ADD CONSTRAINT PK_PRO_BOARD -- PMS게시판 기본키
        PRIMARY KEY (
                     no -- 게시글번호
            );


-- 댓글
CREATE TABLE REPLY (
                       no NUMERIC NOT NULL,
                       pbno NUMERIC NULL,
                       sno NUMERIC NULL,
                       content VARCHAR(600) NOT NULL,
                       writer VARCHAR(100) NOT NULL,
                       regdate DATE NOT NULL,
                       deleteyn CHAR(1) NOT NULL,
                       updatedate DATE NOT NULL
);

-- 댓글
ALTER TABLE REPLY
    ADD CONSTRAINT PK_REPLY -- 댓글 기본키
        PRIMARY KEY (
                     no -- 관리번호
            );

-- 파일관리테이블
CREATE TABLE ATTACHFILE (
                            no NUMERIC NOT NULL,
                            attachdirectory VARCHAR(30) NOT NULL,
                            pno NUMERIC NULL,
                            sno NUMERIC NULL,
                            mailno NUMERIC NULL,
                            perschno NUMERIC NULL,
                            email VARCHAR(100) NULL,
                            storedfilename VARCHAR(100) NOT NULL,
                            originfilename VARCHAR(100) NOT NULL,
                            extension VARCHAR(5) NOT NULL,
                            filesize NUMERIC NOT NULL,
                            regdate DATE NOT NULL
);

-- 파일관리테이블
ALTER TABLE attachfile
    ADD CONSTRAINT PK_attachfile -- 파일관리테이블 기본키
        PRIMARY KEY (
                     no -- 관리번호
            );

-- 개인별일정
CREATE TABLE PER_SCHEDULE (
                              no NUMERIC NOT NULL,
                              projectid NUMERIC NULL,
                              title VARCHAR(150) NULL,
                              content CLOB NOT NULL,
                              color VARCHAR(30) NOT NULL,
                              startdate DATE NOT NULL,
                              enddate DATE NOT NULL,
                              charger VARCHAR(100) NULL,
                              memberid VARCHAR(100) NULL,
                              processpercent NUMERIC NULL,
                              approvalyn CHAR(1) NULL,
                              processstaues VARCHAR(10) NULL,
                              reason CLOB NULL,
                              updatedate DATE default sysdate,
                              regdate DATE NOT NULL
);

-- 개인별일정
ALTER TABLE PER_SCHEDULE
    ADD CONSTRAINT PK_PER_SCHEDULE -- 개인별일정 기본키
        PRIMARY KEY (
                     no -- 관리번호
            );



-- 쪽지이력관리
CREATE TABLE NOT_HISTORY (
                             no NUMERIC NOT NULL,
                             projectid NUMERIC NOT NULL,
                             type VARCHAR(100),
                             receiver VARCHAR(100) NOT NULL,
                             sender VARCHAR(100) NOT NULL,
                             title VARCHAR(100) NOT NULL,
                             content VARCHAR(1500) NOT NULL,
                             url VARCHAR(512),
                             regdate DATE NOT NULL,
                             readyn CHAR(1) default 'n'

);

-- 쪽지이력관리
ALTER TABLE NOT_HISTORY
    ADD CONSTRAINT PK_NOT_HISTORY -- 쪽지이력관리 기본키
        PRIMARY KEY (
                     no -- 관리번호
            );

-- 회의실정보
CREATE TABLE MEETINGROOMINFO (
                                 no NUMERIC NOT NULL,
                                 roomname VARCHAR(100) NOT NULL,
                                 capacity NUMERIC NOT NULL,			--수용인원
                                 priceperday NUMERIC NOT NULL,		--하루 이용비
                                 latitude  VARCHAR(50) NOT NULL,
                                 longitude VARCHAR(50) NOT NULL,
                                 address VARCHAR(200) NOT NULL, --전체 주소
                                 detailaddress VARCHAR(200) NOT NULL, --전체 주소
                                 deleteyn CHAR(1) NOT NULL,
                                 jusono NUMERIC NOT NULL,
                                 register VARCHAR(100) NOT NULL,
                                 regdate DATE NOT NULL

);

-- 회의실정보
ALTER TABLE MEETINGROOMINFO
    ADD CONSTRAINT PK_MEETINGROOMINFO -- 회의실정보 기본키
        PRIMARY KEY (
                     no -- 관리번호
            );

-- 아두이노센서이력
CREATE TABLE SETSENSOR (
                           roomno NUMERIC NOT NULL, -- 방 번호
                           Temperatured NUMERIC NOT NULL,	-- 온도
                           Humidity NUMERIC NOT NULL,	-- 습도
                           Distance NUMERIC NOT NULL,	-- 거리
                           Discomfort_index NUMERIC NOT NULL,	-- 불쾌지수
                           regdate DATE NOT NULL	-- 등록일
);

-- 아두이노센서이력
ALTER TABLE SETSENSOR
    ADD CONSTRAINT PK_SETSENSOR-- 아두이노센서이력 기본키
        PRIMARY KEY (
                     ROOMNO -- 관리번호
            );

-- 센서 위험범위 데이터
CREATE TABLE SENSORINFO (
                            NO NUMERIC NOT NULL,
                            TYPE NUMERIC NOT NULL,	-- 타입(화재, 문단속을 숫자로)
                            VAL NUMERIC NOT NULL,	-- 값
                            ROOMNO NUMERIC NOT NULL, -- 방 번호
                            reason VARCHAR(100) NOT NULL, -- 사유(타입의 사유)
                            readyn VARCHAR(20) NOT NULL,
                            checkdate DATE NOT NULL, -- 센서데이터 등록날짜
                            regdate DATE NOT NULL	-- 등록일
);

-- 센서 위험범위 데이터
ALTER TABLE SENSORINFO
    ADD CONSTRAINT PK_SENSORINFO -- 센서 위험범위 데이터 기본키
        PRIMARY KEY (
                     NO -- 관리번호
            );

-- 공통코드
CREATE TABLE COMMONCODE (
                            code VARCHAR(10) NOT NULL,
                            name VARCHAR(50) NOT NULL,
                            register VARCHAR(100) NOT NULL,
                            regdate DATE NOT NULL
);

-- 공통코드
ALTER TABLE COMMONCODE
    ADD CONSTRAINT PK_COMMONCODE -- 공통코드 기본키
        PRIMARY KEY (
                     code -- 공통코드
            );

-- 예약
CREATE TABLE RESERVATION (
                             no NUMERIC NOT NULL,
                             amount NUMERIC(10,3) NOT NULL,
                             meetingno NUMERIC NOT NULL,
                             startdate date,
                             enddate date,
                             processtatus varchar2(20),
                             projectid NUMERIC,
                             register VARCHAR(100) NOT NULL,
                             regdate DATE NOT NULL

);


-- 예약
ALTER TABLE RESERVATION
    ADD CONSTRAINT PK_RESERVATION -- 예약 기본키
        PRIMARY KEY (
                     no -- 관리번호
            );

-- 공식일정
CREATE TABLE OFF_SCHEDULE (
                              no NUMERIC NOT NULL,
                              projectid NUMERIC NOT NULL,
                              title VARCHAR(150) NOT NULL,
                              content CLOB NOT NULL,
                              startdate DATE NOT NULL,
                              enddate DATE NOT NULL,
                              color VARCHAR(30)  NOT NULL,
                              register VARCHAR(100) NULL,
                              regdate DATE NOT NULL
);

-- 공식일정
ALTER TABLE OFF_SCHEDULE
    ADD CONSTRAINT PK_OFF_SCHEDULE -- 공식일정 기본키
        PRIMARY KEY (
                     no -- 관리번호
            );

-- 사이트게시판
CREATE TABLE SITE_BOARD (
                            boardname VARCHAR(20) NULL,
                            sno NUMERIC NOT NULL,
                            title VARCHAR(100) NOT NULL,
                            content VARCHAR(1500) NOT NULL,
                            writer VARCHAR(100) NULL,
                            viewcnt NUMERIC NOT NULL,
                            regdate DATE NOT NULL,
                            updatedate DATE NOT NULL,
                            startdate DATE NULL,
                            enddate DATE NULL,
                            deleteyn CHAR(1) NOT NULL,
                            fileopenyn CHAR(1) NOT NULL
);

-- 사이트게시판
ALTER TABLE SITE_BOARD
    ADD CONSTRAINT PK_SITE_BOARD -- 사이트게시판 기본키
        PRIMARY KEY (
                     sno -- 게시글번호
            );

-- SITE게시판관리
CREATE TABLE SITE_BOARDMASTER (
                                  boardname VARCHAR(20) NOT NULL,
                                  boardtype VARCHAR(20) NOT NULL,
                                  fileyn CHAR(1) NOT NULL,
                                  replyyn CHAR(1) NOT NULL,
                                  filedownloadyn CHAR(1) NOT NULL,
                                  register VARCHAR(100) NULL,
                                  regdate DATE NOT NULL
);

-- SITE게시판관리
ALTER TABLE SITE_BOARDMASTER
    ADD CONSTRAINT PK_SITE_BOARDMASTER -- SITE게시판관리 기본키
        PRIMARY KEY (
                     boardname -- 게시판이름
            );

-- 주소마스터
CREATE TABLE jusomaster (
                            no NUMERIC NOT NULL,
                            city VARCHAR(20) NULL,
                            area VARCHAR(30) NULL,
                            detail VARCHAR(60) NULL
);

-- 주소마스터
ALTER TABLE jusomaster
    ADD CONSTRAINT PK_jusomaster -- 주소마스터 기본키
        PRIMARY KEY (
                     no -- 관리번호
            );

-- 회의실 첨부파일
CREATE TABLE meetingroomattachfile(
                                      no NUMERIC  NOT NULL,
                                      roomno NUMERIC NOT NULL,
                                      filename VARCHAR(100) NOT NULL,
                                      originfilename VARCHAR(100) NOT NULL,
                                      uploadpath VARCHAR(100) NOT NULL,
                                      PROFILEYN VARCHAR(1) NULL,
                                      FILESIZE NUMERIC NOT NULL
);

-- 회의실 첨부파일
ALTER TABLE meetingroomattachfile
    ADD CONSTRAINT PK_meetingroomattachfile -- 회의실 첨부파일 기본키
        PRIMARY KEY(
                    no -- 관리번호
            );

-- 메뉴
CREATE TABLE menu (
                      no NUMERIC  NOT NULL,
                      parenturl VARCHAR(100) NOT NULL,
                      childurl VARCHAR(100) NULL,
                      menucomment VARCHAR(100) NOT NULL,
                      depth NUMERIC NULL,
                      register VARCHAR(100) NULL,
                      regdate DATE NOT NULL
);

-- 메뉴
ALTER TABLE menu
    ADD CONSTRAINT PK_menu -- 메뉴 기본키
        PRIMARY KEY (
                     no -- 관리번호
            );

-- 센서세팅
ALTER TABLE SETSENSOR
    ADD CONSTRAINT FK_MET_ROO_INF_TO_SETSENSOR -- 회의실정보 -> 센서세팅
        FOREIGN KEY (
                     roomno -- 회의실정보번호
            )
            REFERENCES MEETINGROOMINFO ( -- 회의실정보
                                        no -- 관리번호
                );

-- 센서 위험범위 데이터
ALTER TABLE SENSORINFO
    ADD CONSTRAINT FK_MET_ROO_INF_TO_SENSORINFO -- 회의실정보 -> 센서 위험범위 데이터
        FOREIGN KEY (
                     roomno -- 회의실정보번호
            )
            REFERENCES MEETINGROOMINFO ( -- 회의실정보
                                        no -- 관리번호
                );

-- 사용자
ALTER TABLE MEMBER
    ADD CONSTRAINT FK_ROLE_TO_MEMBER -- 권한 -> 사용자
        FOREIGN KEY (
                     role -- 권한
            )
            REFERENCES ROLE ( -- 권한
                             code -- 코드명
                );

-- 프로젝트관리
ALTER TABLE PRO_MASTER
    ADD CONSTRAINT FK_MEMBER_TO_PRO_MASTER -- 사용자 -> 프로젝트관리
        FOREIGN KEY (
                     register -- 등록자
            )
            REFERENCES MEMBER ( -- 사용자
                               email -- 아이디
                );

-- 프로젝트멤버
ALTER TABLE PRO_TEAM
    ADD CONSTRAINT FK_MEMBER_TO_PRO_TEAM -- 사용자 -> 프로젝트멤버
        FOREIGN KEY (
                     memberid -- 멤버아이디
            )
            REFERENCES MEMBER ( -- 사용자
                               email -- 아이디
                );

-- 프로젝트멤버
ALTER TABLE PRO_TEAM
    ADD CONSTRAINT FK_PRO_MASTER_TO_PRO_TEAM -- 프로젝트관리 -> 프로젝트멤버
        FOREIGN KEY (
                     projectid -- 프로젝트아이디
            )
            REFERENCES PRO_MASTER ( -- 프로젝트관리
                                   projectid -- 프로젝트아이디
                );

-- 프로젝트멤버
ALTER TABLE PRO_TEAM
    ADD CONSTRAINT FK_COMMONCODE_TO_PRO_TEAM -- 권한 -> 프로젝트멤버
        FOREIGN KEY (
                     job -- 직책코드
            )
            REFERENCES COMMONCODE ( -- 권한
                                   code -- 코드명
                );

-- 프로젝트게시판관리
ALTER TABLE PRO_BOARDMASTER
    ADD CONSTRAINT FK_MEMBER_TO_PRO_BOARDMASTER -- 사용자 -> 프로젝트게시판관리
        FOREIGN KEY (
                     register -- 등록한사람
            )
            REFERENCES MEMBER ( -- 사용자
                               email -- 아이디
                );

-- 프로젝트게시판관리
ALTER TABLE PRO_BOARDMASTER
    ADD CONSTRAINT FK_PRO_MASTER_TO_PRO_BOA_MAS -- 프로젝트관리 -> 프로젝트게시판관리
        FOREIGN KEY (
                     projectid -- 프로젝트아이디
            )
            REFERENCES PRO_MASTER ( -- 프로젝트관리
                                   projectid -- 프로젝트아이디
                );

-- PMS게시판
ALTER TABLE PRO_BOARD
    ADD CONSTRAINT FK_PRO_BOA_MAS_TO_PRO_BOARD -- 프로젝트게시판관리 -> PMS게시판
        FOREIGN KEY (
                     boardid -- 보드아이디
            )
            REFERENCES PRO_BOARDMASTER ( -- 프로젝트게시판관리
                                        boardid -- 보드아이디
                );

-- PMS게시판
ALTER TABLE PRO_BOARD
    ADD CONSTRAINT FK_MEMBER_TO_PRO_BOARD -- 사용자 -> PMS게시판
        FOREIGN KEY (
                     writer -- 작성자
            )
            REFERENCES MEMBER ( -- 사용자
                               email -- 아이디
                );

-- 댓글
ALTER TABLE REPLY
    ADD CONSTRAINT FK_MEMBER_TO_REPLY -- 사용자 -> 댓글
        FOREIGN KEY (
                     writer -- 작성자
            )
            REFERENCES MEMBER ( -- 사용자
                               email -- 아이디
                );

-- 댓글
ALTER TABLE REPLY
    ADD CONSTRAINT FK_PRO_BOARD_TO_REPLY -- PMS게시판 -> 댓글
        FOREIGN KEY (
                     pbno -- 프로젝트게시판부모글번호
            )
            REFERENCES PRO_BOARD ( -- PMS게시판
                                  no -- 게시글번호
                );

-- 댓글
ALTER TABLE REPLY
    ADD CONSTRAINT FK_SITE_BOARD_TO_REPLY -- 사이트게시판 -> 댓글
        FOREIGN KEY (
                     sno -- 사이트게시판부모글번호
            )
            REFERENCES SITE_BOARD ( -- 사이트게시판
                                   sno -- 게시글번호
                );

-- 파일관리테이블
ALTER TABLE attachfile
    ADD CONSTRAINT FK_PRO_BOARD_TO_attachfile -- PMS게시판 -> 파일관리테이블
        FOREIGN KEY (
                     pno -- 프로젝트게시판게시글번호
            )
            REFERENCES PRO_BOARD ( -- PMS게시판
                                  no -- 게시글번호
                );


-- 파일관리테이블
ALTER TABLE attachfile
    ADD CONSTRAINT FK_PER_SCHEDULE_TO_attachfile -- 개인별일정 -> 파일관리테이블
        FOREIGN KEY (
                     perschno -- 개인별일정관리번호
            )
            REFERENCES PER_SCHEDULE ( -- 개인별일정
                                     no -- 관리번호
                );

-- 파일관리테이블
ALTER TABLE attachfile
    ADD CONSTRAINT FK_MEMBER_TO_attachfile -- 사용자 -> 파일관리테이블
        FOREIGN KEY (
                     email -- 회원아이디
            )
            REFERENCES MEMBER ( -- 사용자
                               email -- 아이디
                );

-- 파일관리테이블

ALTER TABLE attachfile
    ADD CONSTRAINT FK_SITE_BOARD_TO_attachfile -- 사이트게시판 -> 파일관리테이블
        FOREIGN KEY (
                     sno -- 사이트게시판게시글번호
            )
            REFERENCES SITE_BOARD ( -- 사이트게시판
                                   sno -- 게시글번호
                );

-- 개인별일정
ALTER TABLE PER_SCHEDULE
    ADD CONSTRAINT FK_PRO_TEAM_TO_PER_SCHEDULE -- 프로젝트멤버 -> 개인별일정
        FOREIGN KEY (
                     projectid, -- 프로젝트아이디
                     memberid   -- 담당자
            )
            REFERENCES PRO_TEAM ( -- 프로젝트멤버
                                 projectid, -- 프로젝트아이디
                                 memberid   -- 멤버아이디
                );

-- 개인별일정
ALTER TABLE PER_SCHEDULE
    ADD CONSTRAINT FK_COM_COD_TO_PER_SCHEDULE -- 공통코드 -> 개인별일정
        FOREIGN KEY (
                     processstaues -- 프로세스상태
            )
            REFERENCES COMMONCODE ( -- 공통코드
                                   code -- 공통코드
                );

-- 개인별일정
ALTER TABLE PER_SCHEDULE
    ADD CONSTRAINT FK_PRO_TEAM_TO_PER_SCHEDULE2 -- 프로젝트멤버 -> 개인별일정2
        FOREIGN KEY (
                     projectid, -- 프로젝트아이디
                     charger    -- 시킨사람
            )
            REFERENCES PRO_TEAM ( -- 프로젝트멤버
                                 projectid, -- 프로젝트아이디
                                 memberid   -- 멤버아이디
                );


-- 쪽지이력관리
ALTER TABLE NOT_HISTORY
    ADD CONSTRAINT FK_MEMBER_TO_NOT_HISTORY -- 사용자 -> 쪽지이력관리
        FOREIGN KEY (
                     sender -- 등록한사람
            )
            REFERENCES MEMBER ( -- 사용자
                               email -- 아이디
                );
ALTER TABLE NOT_HISTORY
    ADD CONSTRAINT FK_PRO_MASTER_TO_NOT_HISTORY -- 프로젝트 마스터 -> 쪽지이력관리
        FOREIGN KEY (
                     projectid -- 등록한사람
            )
            REFERENCES PRO_MASTER ( -- 사용자
                                   projectid -- 아이디
                );
ALTER TABLE NOT_HISTORY
    ADD CONSTRAINT FK_COMMONCODE_TO_NOT_HISTORY -- 공통코드 -> 쪽지이력관리
        FOREIGN KEY (
                     type -- 타입
            )
            REFERENCES COMMONCODE ( -- 사용자
                                   code -- 코드번호
                );

-- 회의실정보
ALTER TABLE MEETINGROOMINFO
    ADD CONSTRAINT FK_MEMBER_TO_MET_ROO_INFO -- 사용자 -> 회의실정보
        FOREIGN KEY (
                     register -- 등록한사람
            )
            REFERENCES MEMBER ( -- 사용자
                               email -- 아이디
                );

-- 회의실정보
ALTER TABLE MEETINGROOMINFO
    ADD CONSTRAINT FK_JUSO_TO_MET_ROO_INFO -- 사용자 -> 회의실정보
        FOREIGN KEY (
                     jusono -- 주소관리번호
            )
            REFERENCES JUSOMASTER ( -- 주소마스터
                                   no -- 관리번호
                );


-- 공통코드
ALTER TABLE COMMONCODE
    ADD CONSTRAINT FK_MEMBER_TO_COMMONCODE -- 사용자 -> 공통코드
        FOREIGN KEY (
                     register -- 등록한사람
            )
            REFERENCES MEMBER ( -- 사용자
                               email -- 아이디
                );



-- 예약
ALTER TABLE RESERVATION
    ADD CONSTRAINT FK_MET_ROO_INF_TO_RESERVATION -- 회의실정보 -> 예약
        FOREIGN KEY (
                     meetingno -- 회의실정보번호
            )
            REFERENCES MEETINGROOMINFO ( -- 회의실정보
                                        no -- 관리번호
                );

-- 공식일정
ALTER TABLE OFF_SCHEDULE
    ADD CONSTRAINT FK_PRO_MASTER_TO_OFF_SCHEDULE -- 프로젝트관리 -> 공식일정
        FOREIGN KEY (
                     projectid -- 프로젝트아이디
            )
            REFERENCES PRO_MASTER ( -- 프로젝트관리
                                   projectid -- 프로젝트아이디
                );

-- 공식일정
ALTER TABLE OFF_SCHEDULE
    ADD CONSTRAINT FK_PRO_TEAM_TO_OFF_SCHEDULE -- 프로젝트멤버 -> 공식일정
        FOREIGN KEY (
                     projectid, -- 프로젝트아이디
                     register   -- 등록자
            )
            REFERENCES PRO_TEAM ( -- 프로젝트멤버
                                 projectid, -- 프로젝트아이디
                                 memberid   -- 멤버아이디
                );

-- 사이트게시판
ALTER TABLE SITE_BOARD
    ADD CONSTRAINT FK_SITE_BOA_MAS_TO_SITE_BOARD -- SITE게시판관리 -> 사이트게시판
        FOREIGN KEY (
                     boardname -- 게시판이름
            )
            REFERENCES SITE_BOARDMASTER ( -- SITE게시판관리
                                         boardname -- 게시판이름
                );

-- 사이트게시판
ALTER TABLE SITE_BOARD
    ADD CONSTRAINT FK_MEMBER_TO_SITE_BOARD -- 사용자 -> 사이트게시판
        FOREIGN KEY (
                     writer -- 작성자
            )
            REFERENCES MEMBER ( -- 사용자
                               email -- 아이디
                );

-- SITE게시판관리
ALTER TABLE SITE_BOARDMASTER
    ADD CONSTRAINT FK_MEMBER_TO_SITE_BOA_MAS -- 사용자 -> SITE게시판관리
        FOREIGN KEY (
                     register -- 등록한사람
            )
            REFERENCES MEMBER ( -- 사용자
                               email -- 아이디
                );

-- 메뉴
ALTER TABLE menu
    ADD CONSTRAINT FK_MEMBER_TO_menu -- 사용자 -> 메뉴
        FOREIGN KEY (
                     register -- 등록자
            )
            REFERENCES MEMBER ( -- 사용자
                               email -- 아이디
                );

-- 회의실 첨부파일
ALTER TABLE meetingroomattachfile
    ADD CONSTRAINT FK_ROOMFILE_TO_ROOMINFO -- 회의실 정보 -> 회의실 파일첨부
        FOREIGN KEY (
                     roomno -- 회의실 번호
            )
            REFERENCES meetingroominfo ( -- 회의실 정보
                                        no -- 관리번호
                );


ALTER TABLE RESERVATION
    ADD CONSTRAINT FK_MEMBER_TO_RESERVATION -- 사용자 -> 쪽지이력관리
        FOREIGN KEY (
                     register -- 등록한사람
            )
            REFERENCES MEMBER ( -- 사용자
                               email -- 아이디
                );
ALTER TABLE RESERVATION
    ADD CONSTRAINT FK_RES_TO_PRO_M -- 사용자 -> 쪽지이력관리
        FOREIGN KEY (
                     projectid -- 등록한사람
            )
            REFERENCES PRO_MASTER ( -- 사용자
                                   projectid           -- 아이디
                );
ALTER TABLE RESERVATION
    ADD CONSTRAINT FK_RES_TO_COC -- 사용자 -> 쪽지이력관리
        FOREIGN KEY (
                     processtatus -- 등록한사람
            )
            REFERENCES COMMONCODE ( -- 사용자
                                   code          -- 아이디
                );

--INSERT INTO RESERVATION values(1,8000,1,sysdate,sysdate,10,'N','DolphinShark@naver.com',sysdate);
--INSERT INTO RESERVATION values(2,8000,1,sysdate,sysdate,9,'N','DolphinShark@naver.com',sysdate);
create view project as
select a.PROJECTID, a.MEMBERID, a.JOB, b.PROJECTNAME,b.PROJECTEXPLAIN,b.STARTDATE,b.ENDDATE,b.REGISTER,b.REGDATE from PRO_TEAM a ,PRO_MASTER b
where b.PROJECTID=a.PROJECTID;
CREATE VIEW RESERVATION_MYPAGE
AS SELECT r.register, r.no, r.amount, r.meetingno, r.startdate, r.enddate, r.processtatus, r.regdate, m.roomname, m.capacity, m.latitude, m.longitude, m.address, m.detailaddress, m.jusono, m.priceperday, a.filename, a.uploadpath
   FROM reservation r, meetingroominfo m , meetingroomattachfile a
   WHERE r.meetingno = m.no
     AND m.no = a.roomno;
--Pro_BoardMaster duumy data


create or replace view PROJECTBoardINFO as
select a.PROJECTID,
       a.BOARDNAME,
       a.BOARDTYPE,
       b.PROJECTNAME
from pro_boardmaster a ,PRO_MASTER b
where
        a.projectId=b.projectId;
CREATE TABLE PROJECTNOTICEBOARD (
                                    no NUMERIC NOT NULL,
                                    projectid numeric NULL,
                                    title VARCHAR2(200) NULL,
                                    content CLOB NULL,
                                    viewcount NUMERIC NULL,
                                    poststartdate DATE,
                                    postenddate DATE,
                                    deleteyn varchar2(1),
                                    writer VARCHAR(100) NULL,
                                    regdate DATE
);

-- 프로젝트공지사항
ALTER TABLE PROJECTNOTICEBOARD
    ADD CONSTRAINT PK_PROJECTNOTICEBOARD -- 프로젝트공지사항 기본키
        PRIMARY KEY (
                     no -- 글번호
            );

-- 공지사항 파일 테이블
CREATE TABLE PROJECTNOTICEATTACFILE (
                                        no NUMERIC NULL,
                                        attachdirectory VARCHAR(30) NOT NULL,
                                        storedfilename VARCHAR(100) NOT NULL,
                                        originfilename VARCHAR(100) NOT NULL,
                                        extension VARCHAR(5) NOT NULL,
                                        filesize NUMERIC NOT NULL,
                                        regdate DATE NOT NULL
);
ALTER TABLE PROJECTNOTICEBOARD
    ADD CONSTRAINT FK_PRO_MASTER_TO_PRO_NOT_BOA -- 프로젝트관리 -> 프로젝트공지사항
        FOREIGN KEY (
                     projectid -- 프로젝트아이디
            )
            REFERENCES PRO_MASTER ( -- 프로젝트관리
                                   projectid -- 프로젝트아이디
                );

-- 프로젝트공지사항
ALTER TABLE PROJECTNOTICEBOARD
    ADD CONSTRAINT FK_MEMBER_TO_PRO_NOT_BOA -- 사용자 -> 프로젝트공지사항
        FOREIGN KEY (
                     writer -- 작성자
            )
            REFERENCES MEMBER ( -- 사용자
                               email -- 아이디
                );

-- 공지사항 파일 테이블
ALTER TABLE PROJECTNOTICEATTACFILE
    ADD CONSTRAINT FK_PRO_NOT_BOA_TO_PRO_NOT_ATT -- 프로젝트공지사항 -> 공지사항 파일 테이블
        FOREIGN KEY (
                     no -- 글번호
            )
            REFERENCES PROJECTNOTICEBOARD ( -- 프로젝트공지사항
                                           no -- 글번호
                );


create or replace view PROTEAM_PERSCHUDULE_VIEW as
SELECT a.MEMBERID,
       a.PROCESSPERCENT,
       a.APPROVALYN,
       a.TITLE,
       a.CONTENT,
       a.CHARGER,
       a.STARTDATE,
       a.ENDDATE,
       a.no,
       b.PROJECTID, b.JOB, b.REGDATE
FROM PER_SCHEDULE a,
     PRO_TEAM b
WHERE b.PROJECTID = a.PROJECTID
  AND b.MEMBERID = a.MEMBERID
  AND a.CHARGER=b.MEMBERID;
COMMIT;

-- 일정 테이블
CREATE TABLE PLAN
(
    NO NUMBER NOT NULL PRIMARY KEY
    , PROJECTID numeric
    , MEMBERID VARCHAR2(100 BYTE)
    , TITLE VARCHAR2(100 BYTE) NOT NULL
    , CONTENT CLOB
    , STARTDATE DATE NOT NULL
    , ENDDATE DATE NOT NULL
    , COLOR VARCHAR2(20 BYTE) NOT NULL
    , CODE VARCHAR2(20 BYTE) NOT NULL
    , REGDATE DATE NOT NULL
    , UPDATEDATE DATE
    , ADDRESS VARCHAR2(100 BYTE)
    , DETAILADDRESS VARCHAR2(300 BYTE)
    , LATITUDE NUMBER
    , LONGITUDE NUMBER
)
;
ALTER TABLE PLAN
    ADD CONSTRAINT PLAN_FK1 FOREIGN KEY
        (
         MEMBERID
            )
        REFERENCES MEMBER
            (
             EMAIL
                )
            ENABLE;

ALTER TABLE PLAN
    ADD CONSTRAINT PLAN_FK2 FOREIGN KEY
        (
         CODE
            )
        REFERENCES COMMONCODE
            (
             CODE
                )
            ENABLE;

ALTER TABLE PLAN
    ADD CONSTRAINT PLAN_FK3 FOREIGN KEY
        (
         PROJECTID
            )
        REFERENCES PRO_MASTER
            (
             PROJECTID
                );
create table ADMINCOOMON
(
    NO NUMBER not null
        constraint ADMINCOOMON_PK
            primary key,
    ITEM VARCHAR2(1500),
    CONTENT CLOB
)
;
-- ROLE 테이블 데이터
INSERT INTO ROLE VALUES ('ROLE_USER', '일반사용자', SYSDATE);
INSERT INTO ROLE VALUES ('ROLE_ADMIN', '관리자', SYSDATE);
INSERT INTO ROLE VALUES ('ROLE_SLEEP', '휴면사용자', SYSDATE);
INSERT INTO ROLE VALUES ('ROLE_DISABLED', '탈퇴된사용자', SYSDATE);
-- 멤버 테이블 관리자 INSERT
INSERT INTO MEMBER (EMAIL, PASSWORD, PHONENUMBER, NAME, NICKNAME, POSTCODE, ADDRESS, DETAILADDRESS, ROLE, DELETEYN, REGDATE)
VALUES ('DolphinShark@naver.com', '1234', '01012345678', '상어가된돌고래', '돌고래상어', '548754', '내고향바다', '돌고래도좋고 상어도 좋아', 'ROLE_ADMIN', 'n', SYSDATE);
INSERT INTO MEMBER (EMAIL, PASSWORD, PHONENUMBER, NAME, NICKNAME, POSTCODE, ADDRESS, DETAILADDRESS, ROLE, DELETEYN, REGDATE)
VALUES ('SharkTroll@naver.com', '1234', '01012345678', '샤크트롤', '트롤상어입니당', '548754', '내고향바다', '트롤상어 모르줴?', 'ROLE_USER', 'n', SYSDATE);
-- 공통 코드

INSERT INTO commoncode VALUES ('1', '개인 일정', 'DolphinShark@naver.com', SYSDATE);
INSERT INTO commoncode VALUES ('2', '팀 일정', 'DolphinShark@naver.com', SYSDATE);
INSERT INTO commoncode VALUES ('100', '팀원', 'DolphinShark@naver.com', SYSDATE);
INSERT INTO commoncode VALUES ('110', '팀장', 'DolphinShark@naver.com', SYSDATE);
INSERT INTO commoncode VALUES ('120', ' 프로젝트 권한 양도', 'DolphinShark@naver.com', SYSDATE);
INSERT INTO commoncode VALUES ('401', '권한없음에러', 'DolphinShark@naver.com', SYSDATE);
INSERT INTO commoncode VALUES ('500', '서버에러', 'DolphinShark@naver.com', SYSDATE);
INSERT INTO commoncode VALUES ('700', '완료', 'DolphinShark@naver.com', SYSDATE);
INSERT INTO commoncode VALUES ('710', '진행', 'DolphinShark@naver.com', SYSDATE);
INSERT INTO commoncode VALUES ('720', '신규', 'DolphinShark@naver.com', SYSDATE);
INSERT INTO commoncode VALUES ('721', '재요청', 'DolphinShark@naver.com', SYSDATE);
INSERT INTO commoncode VALUES ('730', '결제완료', 'DolphinShark@naver.com', SYSDATE);
INSERT INTO commoncode VALUES ('740', '결제취소대기중', 'DolphinShark@naver.com', SYSDATE);
INSERT INTO commoncode VALUES ('750', '결제취소승인', 'DolphinShark@naver.com', SYSDATE);
INSERT INTO commoncode VALUES ('910', '쪽지알림', 'DolphinShark@naver.com', SYSDATE);
INSERT INTO commoncode VALUES ('911', '일정알림', 'DolphinShark@naver.com', SYSDATE);
INSERT INTO commoncode VALUES ('912', '프로젝트초대,탈퇴알림', 'DolphinShark@naver.com', SYSDATE);
INSERT INTO commoncode VALUES ('913', '공지사항알림', 'DolphinShark@naver.com', SYSDATE);
INSERT INTO commoncode VALUES ('914', '결제완료알림', 'DolphinShark@naver.com', SYSDATE);

INSERT INTO SITE_BOARDMASTER (BOARDNAME, BOARDTYPE, FILEYN, REPLYYN, FILEDOWNLOADYN, REGISTER, REGDATE) VALUES ('공지사항', '공지사항', 'y', 'n', 'y', 'DolphinShark@naver.com',sysdate);-- jusoMaster 필요한 데이터
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '서울', '강남권', '개포, 대치, 도곡, 논현');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '서울', '강북권', '성북, 도봉, 노원, 중계');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '서울', '강서권', '염창, 화곡, 방화, 구로, 금천');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '서울', '마포권', '신촌, 서대문, 은평');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '서울', '성동권', '성동, 광진, 중랑, 동대문');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '서울', '송파권', '잠실, 천호, 강동, 암사');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '서울', '양천권', '목동, 여의도, 당산');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '서울', '종로권', '종로, 혜화, 인사동');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '서울', '중구권', '중구, 서울역, 용산, 숙대입구');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '경기', '경기남부', '용인, 평택, 오산 등');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '경기', '경기동부', '하남, 구리, 광주, 남양주 등');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '경기', '경기북부', '포천, 양주, 의정부, 파주, 고양, 김포');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '경기', '경기서부', '안양, 광명, 부천, 과천, 시흥, 안산 등');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '경기', '성남권', '성남, 분당, 서현, 판교, 백현 등');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '경기', '수원권', '수원, 화성, 동탄');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '강원', '강릉권', '강릉, 동해, 삼척');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '강원', '속초권', '속초, 양양, 고성');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '강원', '춘천권', '춘천, 홍천, 원주, 횡성');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '강원', '평창권', '평창, 정선, 영월, 태백');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '강원', '기타', '철원, 화천, 양구, 인제 등');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '충청', '대전권', '대전, 유성, 세종 등');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '충청', '충남아산권', '남구, 동구, 남동구');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '충청', '충북권', '청주, 충주, 제천 등');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '충청', '태안권', '보령, 서산, 태안 등');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '인천', '남동권', '남구, 동구, 남동구');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '인천', '부평권', '부평, 산곡, 청천');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '인천', '서구권', '계양, 서구');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '인천', '송도권', '송도, 연수구');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '인천', '중구권', '중구, 송월, 영종도');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '인천', '기타', '강화, 옹진 등');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '전라', '광주권', '광주, 나주, 담양 등');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '전라', '여수권', '여수, 순천, 광양');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '전라', '전북권', '전주, 익산, 군산, 부안');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '전라', '해남권', '목포, 해남, 진도, 완도');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '전라', '기타', '남원, 김제, 구례, 무주 등');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '경상', '경남권', '진주, 김해, 남해, 거제 등');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '경상', '경북권', '김천, 안동, 문경 등');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '경상', '경주권', '포항, 울산, 경주 등');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '경상', '대구권', '대구, 수성, 달서, 구미, 성주 등');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '경상', '창원권', '창원, 마산, 진해 등');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '경상', '기타', '군위, 의성, 성주, 울릉 등');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '부산', '부산역권', '중구, 동구, 남포동 등');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '부산', '서면권', '서면, 부산진구, 연제구 등');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '부산', '해운대권', '해운대구, 광안리, 센텀시티 등');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '부산', '기타', '사하, 동래, 영도, 사상, 기장 등');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '제주', '제주 전체', '제주, 일도, 서귀포, 중문 등');

-- meetingRoomInfo 관련 데이터  --잠정삭제. 프로필 파일 첨부가 필수이기에 관리자로 로그인해서 추가 바람.
--INSERT INTO meetingRoomInfo(no, latitude, longitude, address, detailAddress, regdate, jusoNo, capacity, roomName, register, deleteyn)
--					VALUES (meetingRoomInfo_seq.nextval, 100, 100,'대덕인재개발원', '205호', sysdate, 1, 4, '대덕인재개발원 205호', 'DolphinShark@naver.com', 'n');
--INSERT INTO meetingRoomInfo(no, latitude, longitude, address, detailAddress, regdate, jusoNo, capacity, roomName, register, deleteyn)
--					VALUES (meetingRoomInfo_seq.nextval, 100, 100, '대덕인재개발원', '203호', sysdate, 2, 8, '대덕인재개발원 203호', 'DolphinShark@naver.com', 'n');

-- MEETINGROOMATTACHFILE comment
COMMENT ON COLUMN MEETINGROOMATTACHFILE.ROOMNO IS '사무실번호';
COMMENT ON COLUMN MEETINGROOMATTACHFILE.FILENAME IS '저장파일명';
COMMENT ON COLUMN MEETINGROOMATTACHFILE.ORIGINFILENAME IS '원본파일명';
COMMENT ON COLUMN MEETINGROOMATTACHFILE.UPLOADPATH IS '서버저장경로';
--pro_master
INSERT INTO pro_master (projectId, projectName, projectExplain, startDate, endDate, regDate, register)
VALUES (project_seq.nextval, 'Test1', 'Test1설명', SYSDATE, SYSDATE, SYSDATE, 'DolphinShark@naver.com');
INSERT INTO pro_master (projectId, projectName, projectExplain, startDate, endDate, regDate, register)
VALUES (project_seq.nextval, 'Test2', 'Test2설명', SYSDATE, SYSDATE, SYSDATE, 'DolphinShark@naver.com');
INSERT INTO pro_master (projectId, projectName, projectExplain, startDate, endDate, regDate, register)
VALUES (project_seq.nextval, 'Test3', 'Test3설명', SYSDATE, SYSDATE, SYSDATE, 'DolphinShark@naver.com');
INSERT INTO pro_master (projectId, projectName, projectExplain, startDate, endDate, regDate, register)
VALUES (project_seq.nextval, 'Test4', 'Test4설명', SYSDATE, SYSDATE, SYSDATE, 'DolphinShark@naver.com');
INSERT INTO pro_master (projectId, projectName, projectExplain, startDate, endDate, regDate, register)
VALUES (project_seq.nextval, 'Test5', 'Test5설명', SYSDATE, SYSDATE, SYSDATE, 'DolphinShark@naver.com');
-- pro_team
INSERT INTO pro_team VALUES (1, 'DolphinShark@naver.com', '110', SYSDATE);
INSERT INTO pro_team VALUES (1, 'SharkTroll@naver.com', '100', SYSDATE);
INSERT INTO pro_team VALUES (2, 'DolphinShark@naver.com', '110', SYSDATE);
-- PRO_BOARDMASTER
INSERT INTO PRO_BOARDMASTER (BOARDID, PROJECTID, BOARDNAME, BOARDTYPE, FILEYN, REPLYYN, FILEDOWNLOADYN, REGISTER, REGDATE) VALUES (pro_boardmaster_seq.NEXTVAL, 1, 'pms자료실', '자료실', 'y', 'y', 'y', 'DolphinShark@naver.com', SYSDATE);
-- off_schedule
INSERT INTO off_schedule (no, projectid, title, content, startdate, enddate, color, register, regdate)
VALUES (OFF_SCHEDULE_SEQ.nextval, '1', '테스트제목1', '테스트일정1', TO_DATE('2020-06-12 10:48:51', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2020-06-27 10:48:56', 'YYYY-MM-DD HH24:MI:SS'), '#FF0000', 'DolphinShark@naver.com', SYSDATE);
INSERT INTO off_schedule (no, projectid, title, content, startdate, enddate, color, register, regdate)
VALUES (OFF_SCHEDULE_SEQ.nextval, '1', '테스트제목2',  '테스트일정2', TO_DATE('2020-06-14 10:48:51', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2020-06-15 10:48:56', 'YYYY-MM-DD HH24:MI:SS'), '#00FF00', 'DolphinShark@naver.com', SYSDATE);
INSERT INTO off_schedule (no, projectid, title, content, startdate, enddate, color, register, regdate)
VALUES (OFF_SCHEDULE_SEQ.nextval, '1', '테스트제목3',  '테스트일정3', TO_DATE('2020-06-16 10:48:51', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2020-06-16 10:48:56', 'YYYY-MM-DD HH24:MI:SS'), '#0000FF', 'DolphinShark@naver.com', SYSDATE);
INSERT INTO off_schedule (no, projectid, title, content, startdate, enddate, color, register, regdate)
VALUES (OFF_SCHEDULE_SEQ.nextval, '2', '테스트제목4',  '테스트일정4', TO_DATE('2020-06-11 10:48:51', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2020-06-12 10:48:56', 'YYYY-MM-DD HH24:MI:SS'), '#00FFFF', 'DolphinShark@naver.com', SYSDATE);
INSERT INTO meetingRoomInfo(no, latitude, longitude, address, detailAddress, regdate, jusoNo, capacity, roomName, register, deleteyn,priceperday)
VALUES (meetingRoomInfo_seq.nextval, 100, 100,'대덕인재개발원', '205호', sysdate, 42, 4, '대덕인재개발원 205호', 'DolphinShark@naver.com', 'n',8000);
INSERT INTO meetingRoomInfo(no, latitude, longitude, address, detailAddress, regdate, jusoNo, capacity, roomName, register, deleteyn,priceperday)
VALUES (meetingRoomInfo_seq.nextval, 100, 100, '대덕인재개발원', '203호', sysdate, 43, 8, '대덕인재개발원 203호', 'DolphinShark@naver.com', 'n',8000);
INSERT INTO MEMBER (EMAIL, PASSWORD, PHONENUMBER, NAME, NICKNAME, POSTCODE, ADDRESS, DETAILADDRESS, ROLE, DELETEYN, REGDATE) VALUES ('admin', '1234', '01021254151', 'adim', 'admin', '1234', 'admin', 'admin', 'ROLE_ADMIN', 'n', sysdate);
INSERT INTO MEMBER (EMAIL, PASSWORD, PHONENUMBER, NAME, NICKNAME, POSTCODE, ADDRESS, DETAILADDRESS, ROLE, DELETEYN, REGDATE) VALUES ('user', '1234', '01021254151', 'user', 'user', '1234', 'user', 'user', 'ROLE_USER', 'n', sysdate);
INSERT INTO MEMBER (EMAIL, PASSWORD, PHONENUMBER, NAME, NICKNAME, POSTCODE, ADDRESS, DETAILADDRESS, ROLE, DELETEYN, REGDATE) VALUES ('user1', '1234', '01021254151', 'user', 'user', '1234', 'user', 'user', 'ROLE_USER', 'n', sysdate);
INSERT INTO MEMBER (EMAIL, PASSWORD, PHONENUMBER, NAME, NICKNAME, POSTCODE, ADDRESS, DETAILADDRESS, ROLE, DELETEYN, REGDATE) VALUES ('user2', '1234', '01021254151', 'user', 'user', '1234', 'user', 'user', 'ROLE_USER', 'n', sysdate);
INSERT INTO MEMBER (EMAIL, PASSWORD, PHONENUMBER, NAME, NICKNAME, POSTCODE, ADDRESS, DETAILADDRESS, ROLE, DELETEYN, REGDATE) VALUES ('user3', '1234', '01021254151', 'user', 'user', '1234', 'user', 'user', 'ROLE_USER', 'n', sysdate);
INSERT INTO MEMBER (EMAIL, PASSWORD, PHONENUMBER, NAME, NICKNAME, POSTCODE, ADDRESS, DETAILADDRESS, ROLE, DELETEYN, REGDATE) VALUES ('user4', '1234', '01021254151', 'user', 'user', '1234', 'user', 'user', 'ROLE_USER', 'n', sysdate);
INSERT INTO PRO_MASTER (PROJECTID, PROJECTNAME, PROJECTEXPLAIN, STARTDATE, ENDDATE, DELETEYN, REGISTER, REGDATE) VALUES ('1000', 'Test100', 'Test1000설명', TO_DATE('2020-06-22 11:22:42', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2020-10-22 11:22:46', 'YYYY-MM-DD HH24:MI:SS'), 'n', 'admin', sysdate);
INSERT INTO PRO_BOARDMASTER (BOARDID, PROJECTID, BOARDNAME, BOARDTYPE, FILEYN, REPLYYN, FILEDOWNLOADYN, REGISTER, REGDATE) VALUES (pro_boardmaster_seq.NEXTVAL, '1000', '공지사항', '공지', 'Y', 'N', 'Y', 'admin', sysdate);
INSERT INTO PRO_TEAM (PROJECTID, MEMBERID, JOB, REGDATE) VALUES ('1000', 'admin', '110', sysdate);
INSERT INTO PRO_TEAM (PROJECTID, MEMBERID, JOB, REGDATE) VALUES ('1000', 'user1', '100', sysdate);
INSERT INTO PRO_TEAM (PROJECTID, MEMBERID, JOB, REGDATE) VALUES ('1000', 'user2', '100', sysdate);
INSERT INTO PRO_TEAM (PROJECTID, MEMBERID, JOB, REGDATE) VALUES ('1000', 'user3', '100', sysdate);
COMMENT ON COLUMN PLAN.NO IS '번호';
COMMENT ON COLUMN PLAN.PROJECTID IS '프로젝트아이디';
COMMENT ON COLUMN PLAN.MEMBERID IS '멤버아이디';
COMMENT ON COLUMN PLAN.TITLE IS '일정 제목';
COMMENT ON COLUMN PLAN.CONTENT IS '내용';
COMMENT ON COLUMN PLAN.STARTDATE IS '시작날짜';
COMMENT ON COLUMN PLAN.ENDDATE IS '끝날짜';
COMMENT ON COLUMN PLAN.COLOR IS '달력표시색깔';
COMMENT ON COLUMN PLAN.CODE IS '타입';
COMMENT ON COLUMN PLAN.REGDATE IS '등록일자';
COMMENT ON COLUMN PLAN.UPDATEDATE IS '수정일자';
COMMENT ON COLUMN PLAN.ADDRESS IS '주소';
COMMENT ON COLUMN PLAN.DETAILADDRESS IS '상세주소';
COMMENT ON COLUMN PLAN.LATITUDE IS '위도';
COMMENT ON COLUMN PLAN.LONGITUDE IS '경도';

-- 운영DB에 사무실 관련 파일이 없어서 추가합니다
-- c:\attach\meetingroom\upload 에 파일 이름 1.png, 2.png를 올려주세요
-- 파일은 src/main/webapp/resources/site/img/ 안에 있습니다
insert into meetingroomAttachFile
values (MEETINGROOMATTACHFILE_SEQ.nextval, 1, '1.png', '1.png', 'c:\attach\meetingroom\upload', 'y', 2000);
insert into meetingroomAttachFile
values (MEETINGROOMATTACHFILE_SEQ.nextval, 2, '2.png', '2.png', 'c:\attach\meetingroom\upload', 'y', 2000);

-- 운영DB에 멤버 관련 파일이 없어서 추가합니다
-- c:\attach\memberProfile\upload 에 파일 이름 member1.jpg를 올려주세요
-- 파일은 src/main/webapp/resources/site/img/ 안에 있습니다
insert into attachFile
values (ATTACHFILE_SEQ.nextval, 'c:\attach\memberProfile\upload','','','','',
        'DolphinShark@naver.com','member1.jpg', 'member1.jpg','jpg',0,sysdate
       );

-- SetSensor 테이블 데이터 추가
INSERT (ROOMNO, TEMPERATURED, HUMIDITY, DISTANCE, DISCOMFORT_INDEX, REGDATE)
VALUES (1, 30, 50, 5, 0, sysdate)

commit;
