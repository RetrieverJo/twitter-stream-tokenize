####참고사항
이 프로젝트는 IntelliJ 기반의 Maven Project 를 토대로 작성되었습니다.

IntelliJ 를 사용하지 않는 경우에는 Eclipse 에 M2E Plugin 을 설치하여 Maven Project 로 Import 해 주시기 바랍니다.


<br>
<br>

###Pre-requirements
* JDK 1.7 이상
* IntelliJ IDEA(권장) 혹은 Eclipse
* Apache Maven
* Git
* [트위터 개발자 사이트](https://dev.twitter.com)에서 받은 Access Tokens


###Source Code
이 프로젝트의 소스코드는 [Github Repository](https://github.com/RetrieverJo/twitter-stream-tokenize)에 업로드 되어있습니다.
<br>
<br>

###Guide

####0. Twitter에서 API Key 받기
[트위터 개발자 사이트](https://dev.twitter.com)에 로그인하여 [Twitter Apps](https://apps.twitter.com)페이지에서 어플리케이션을 생성하고,<br>
그 어플리케이션에 해당하는 Access Tokens를 확인합니다.

####1. Source code download
[Github Repository](https://github.com/RetrieverJo/twitter-stream-tokenize)에서 `git clone`을 이용하여 소스코드를 다운로드 합니다.
####2. Project import
Java IDE(IntelliJ IDEA or Eclipse)에서 Maven 기반의 프로젝트로 Import 합니다.
####3. Configuration
`twitter4j.properties`에서 API Key 를 설정합니다.<br>
`fluentd.properties`에 수집한 결과를 저장할 원격지 로그 서버를 설정합니다.
####4. Run
`TweetSampleStream` 클래스를 수행하여 트위터 데이터를 수집하고, 명사를 추출하여 원격 서버로 전송합니다.