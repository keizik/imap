How to read email with spring integration

In this article I will demonstrate a simple way to read your mail. 

First let's setup spring boot application, including integration and mail starters as well as spring-integration-mail

  compile('org.springframework.boot:spring-boot-starter-integration')
  compile('org.springframework.boot:spring-boot-starter-mail')
  compile('org.springframework.integration:spring-integration-mail:5.0.7.RELEASE')

Then we need a mailbox to connect to. I will use Microsoft outlook as an example.
