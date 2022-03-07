-- User 정보 테이블 생성
CREATE TABLE USERlist(
    USERID VARCHAR2(50) PRIMARY KEY,
    USERPW VARCHAR2(200) NOT NULL,
    NAME varchar2(50) not null,
    USERPHONENUMBER VARCHAR2(40) NOT NULL unique,
    USERBIRTH date NOT NULL,
    ENABLED NUMBER(1) DEFAULT 1,
    ROLENAME VARCHAR2(50) DEFAULT 'ROLE_USER' NOT NULL
);

-- schedule 테이블 생성
create table schedule(
    idx_num number primary key,
    userid varchar2(30) REFERENCES userlist(userid) ON DELETE cascade,
    title varchar2(100) not null,
    content varchar2(1000),
    startdate date not null,
    enddate date not null,
    importance number(1) not null,
    place varchar2(50)
);

CREATE SEQUENCE schedule_idx START WITH 1 INCREMENT BY 1;