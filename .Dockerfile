FROM student-management-app AS StudentManagementApp

LABEL com.duyhelloworld="URL of my web app"
LABEL version="1.0"
LABEL package="[controller, dao, exceptions, logging, model, repos, testing]"
LABEL MAINTAINER-NAME=duyhelloworld
LABEL MAINTAINER-MAIL="codedaovoiduy@gmail.com"

ENV mysqlUsername="duyaiti"
ENV mysqlPass="12345678"

COPY . ./

RUN sudo apt-get update -y
CMD [ "java", "-version"]