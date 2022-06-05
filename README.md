# 음악 정보 시스템 이플리 Ewha_Playlist
2022-1 데이터베이스(01) 6조 캐쥬얼 사용자들: 2017008 김민서, 2071008 김서연, 2071020 김현민, 2076232 안채연, 2076429 최한비

## 1. 프로젝트 개요

## 2. 응용 프로그램 설치 및 사용방법
### (1) JDBC Driver 설치 및 연결
- MySQL Connector/J 8.0.29 설치 (https://dev.mysql.com/downloads/connector/j/)
- Eclipse에서 프로젝트 선택 후 Project - Properties - Java Build Path - Libraries - Modulepath - Add External JARs... - mysql-connector-java-8.0.29.jar 선택
### (2) Database 구성
```
create database DB2022Team06 default character set utf8 collate utf8_unicode_ci;
create user DB2022Team06@localhost identified by 'DB2022Team06';
grant all privileges on DB2022Team06.* to DB2022Team06@localhost;
mysql -uDB2022Team06 -p DB2022Team06 < 로컬 저장 경로\create.sql
```
### (3) Java 프로젝트 실행
- open projects - DB2022Team06
- package DB2022Team06 선택 후 run
