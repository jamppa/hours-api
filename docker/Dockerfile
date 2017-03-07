FROM clojure
MAINTAINER Jani Arvonen <jani.arvonen@gmail.com>

# Set the timezone.
RUN echo "Europe/Helsinki" > /etc/timezone
RUN dpkg-reconfigure -f noninteractive tzdata

RUN adduser hours --home /home/hours --gecos "" --disabled-password
USER hours
WORKDIR /app
