INSERT INTO PRO_MASTER (PROJECTID, PROJECTNAME, STARTDATE, ENDDATE, REGDATE, PROJECTEXPLAIN, REGISTER)
VALUES ('10', 'test', SYSDATE, SYSDATE,SYSDATE, 'test', 'admin@admin.com');

INSERT INTO "FINAL"."PRO_BOARDMASTER" (PROJECTID, BOARDNAME, BOARDTYPE, FILEYN, REPLYYN, FILEDOWNLOADYN, REGISTER, REGDATE)
VALUES ('10', '공지', '공지', 'y', 'y', 'y', 'admin@admin.com', SYSDATE);

INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '서울', '강남권', '개포, 대치, 도곡, 논현');
INSERT INTO JUSOMASTER VALUES(jusoMaster_seq.nextval, '서울', '강북권', '성북, 도봉, 노원, 중계');