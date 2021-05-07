FROM python:2

WORKDIR /

COPY requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt

COPY ./api-server ./api-server

CMD [ "python", "./api-server/server.py","8085" ]
