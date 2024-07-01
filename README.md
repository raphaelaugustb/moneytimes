
![Logo](https://github.com/raphaelaugustb/MoneyTimes/assets/66183690/75b543ae-69b7-4d3f-a632-c93117ae2f59)



![image](https://github.com/raphaelaugustb/MoneyTimes/assets/66183690/ec1a5f26-2203-4fce-8837-710f839beb32)


MoneyTimes is free to use app with simple UI to store all of yours bills and incomes on month you can also get the amount of your bills according to your incomes and other many functionality.


## Install

Clone project

```bash
  git clone https://github.com/raphaelaugustb/MoneyTimes/
```

cd Project directory

```bash
  cd MoneyTimes
```
Install dependencies
```bash
  mvn clean install package
```
Run docker container to start DB

```bash
  docker compose up
```
## Init front-end

Install Depedencies on front

```bash
  cd frontend && npm install
```

Inicie o servidor

```bash
  npm run start
```

## Init back-end

Install Depedencies on back end

```bash
  cd backend/money_times && mvn clean install package 
```

Run Back-End

```bash
  mvn spring-boot:run 
```

## Documents

![Documents](https://github.com/raphaelaugustb/MoneyTimes/blob/main/documents/documents.md)

