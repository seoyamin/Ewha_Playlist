# 음악 정보 시스템 이플리 Ewha_Playlist
2022-1 데이터베이스(01) 6조 캐쥬얼 사용자들: 2017008 김민서, 2071008 김서연, 2071020 김현민, 2076232 안채연, 2076429 최한비

## 1. 프로젝트 개요
### (1) 프로젝트의 목적
- Youtube 등의 웹, 앱 플랫폼이 활성화되면서 음악 서비스 이용자들의 비용 및 시간 투자가 지속적으로 상승하고 있다. 하지만 일부 플랫폼에서는 음악 제작 환경 및 저작권자, 실연자에 대한 정확한 정보 제공에 대해 여전히 미흡한 모습을 보이고 있다. 또한 연령제한이 있는 곡이 미성년자 유저에게 쉽게 노출되는 등 제대로 된 필터링이 이루어지지 않고 있다.
- 이에 정확한 음원 정보를 바탕으로 사용자의 취향이나 상황에 맞는 플레이리스트를 추천해주는 음원 정보 DBMS 시스템 "이플리 - Ewha Playlist" 프로젝트를 진행하였다.
### (2) 프로젝트의 기능
- 전체 음악 목록: DB에 존재하는 전체 음악 목록 및 장르별 음악 목록 조회
- TOP 10: 좋아요 수를 기준으로 정렬한 상위 10곡 조회
- 나만의 플레이리스트: DB에 존재하는 음악 목록 중 자신이 좋아하는 곡을 플레이리스트에 삽입/삭제/조회. 미성년자 유저의 경우 연령 제한이 있는 곡은 플레이리스트에 삽입/삭제가 불가능하며 전체 음악 목록 조회에서 해당 곡들은 노출되지 않음
- 추천 플레이리스트: 사용자가 놓인 환경에 맞는 계절별, 상황별, 나이대별 추천 플레이리스트 조회
- 음악 검색: 곡명과 가수명을 이용해 원하는 곡의 정보를 조회
- 음악 추천: 사용자가 선호 장르로 설정한 장르 및 마이 플레이리스트에 담은 곡의 장르를 바탕으로 음악 추천(추천 음악 목록 조회). 미성년자 유저의 경우 연령 제한이 있는 곡은 노출하지 않음
- 음원 관리: 관리자만 접근 가능한 메뉴로 기존 곡의 정보를 수정/삭제하거나 새로운 곡을 삽입
- 마이페이지: 회원가입을 통한 유저 정보 삽입 및 선호 장르 수정

## 2. 응용 프로그램 설치 및 사용 방법
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
