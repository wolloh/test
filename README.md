# Обзор:
Сервис является REST api приложение для работы с крипто биржей.Реализованы минимальные требования кроме метода смены курса валют для администратора и часть доп заданий
### Сервис позволяет определить(минимальные требования)

* Зарегистрировать пользователя;
* Просмотр баланса кошелька;
* Пополнение кошелька;
* Вывод денег;
* Просмотр актуальных курсов валют;
* Обмен валют по установленному курсу;

* Посмотреть общую сумму на всех пользовательских счетах для указанной валюты;
* Посмотреть количество операций, которые были проведены за указанный период;


Сервис реализует дополнительные требования
* Сервис по запросу может возвращать данные в xml или json
* Формат может быть изменем добавлением header

  `accept:application/json или accept:application/xml к HTTP запросу`

* Подключена PostgreSQL для хранения данных.Также были созданы триггеры,последовательности и функции для нее,к примеру триггер для автоматического создания кошельков при регистрации пользователя

  ```create trigger wallets_create_trigger after insert on public.users for each row execute function create_wallets();```
 
 ```CREATE OR REPLACE FUNCTION public.create_wallets()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$
begin 
	insert into wallet values(nextval('my_serial1'),1,0,new.id);
	insert into wallet values(nextval('my_serial1'),2,0,new.id);
	insert into wallet values(nextval('my_serial1'),3,0,new.id);
	return new;
end;
$function$; 
```

* Или же триггер для автоматического добавления транзакций в таблицу транзакций после выполняемых операций

```create trigger transaction_create_trigger after update on public.wallet for each row execute function create_transaction();```

```
CREATE OR REPLACE FUNCTION public.create_transaction()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$
begin 
	insert into transaction(id,wallet_id,amount,transaction_type) values(nextval('my_serial3'),new.id,new.balance-old.balance,'update');
	return new;
end;
$function$
;

```

### Использованные технологии 
* Java 17
* Spring Boot
* Lombok
* Maven
* PostgreSQL

## Запуск 

Склонировать репозиторий и перейдя в корневую папку проекта  прописать команду:

`mvn spring-boot:run`

## Endpoints



### Возвращает сгенерировавшийся уникальный ключ 

` POST user/register`
В случае размещения на 8080 порте,запрос будет выглядеть :

**localhost:8080/user/register**

#### Параметры

` userName:String` - логин пользователя

` email:String` - эмэйл пользователя

#### Ответ

```
"username": "Rona",
"email": "Skyv",
"secretkey": "24d5e1eb17e4b6e524e550dc8e16682acf3d56499823ef44e28d92e83a2baf01",
"id": 19
```

#### Пример запроса 

![image](https://user-images.githubusercontent.com/67105296/222268030-2c9a7bae-e453-4e5e-b90a-64d899c9215b.png)

#### Пример ответа 

##### XML

![image](https://user-images.githubusercontent.com/67105296/222268374-9d745a90-1559-4a4d-a5e7-582408c827f9.png)


##### JSON

![image](https://user-images.githubusercontent.com/67105296/222268256-6df84d96-9363-4c6e-8337-f451399bd8e3.png)


Для выбора формата ответа достаточно добавить Header `Accept` или изменить его значение

* Для xml : `application/xml`
* Для json: `application/json`


### Возвращает кошельки пользователя 

` GET api/get_balance?key`

#### Параметры

` key:String ` - секретный ключ пользователя


#### Ответ

```
  
   BTC_wallet: 0,
    
   TON_wallet: 0,
  
   RUB_wallet: 75
    
```


### Пополняет кошелек

` POST api/deposit`

#### Параметры

` key:String ` - секретный ключ пользователя
`RUB_wallet:String` - тип кошелька,который нужно пополнить

#### Ответ

```
RUB_wallet: 75
```

### Вывод денег с биржи

` POST api/withdraw`

#### Параметры

` key:String ` - секретный ключ пользователя

`currency:String` - тип кошелька,который нужно пополнить

`count:BigDecimal` - количество денег для вывода

`credit_card:String` - карта для вывода

#### Ответ

```
RUB_wallet: 30
```


### Просмотр актуальных курсов валют 

` GET api/get_rate?key&currency`

#### Параметры

` key:String ` - секретный ключ пользователя

`currency:String` - тип валюты,относительно которой нужно посмотреть валюту



#### Ответ

```
 currencyType: RUB,
 rate: 1909464
 
 currencyType: BTC,
  rate: 1
        
 currencyType: TON,
  rate: 9275
```


### Обмен валют по установленному курсу 

` POST api/exchange`

#### Параметры

` key:String ` - секретный ключ пользователя

`currencyFrom:String` - валюта,из которой нужно перевести

`currencyTo:String` - валюта,в которую нужно перевести

`amount:BigDecimal` - количество единиц обмена

#### Ответ

```

    currencyFrom: RUB,
    currencyTo: TON,
    amountFrom: 10,
    amountTo: 0.05263157890

```


### Посмотреть общую сумму на всех пользовательских счетах для указанной валюты


` GET api/get_sum?key&currency`

#### Параметры

` key:String ` - секретный ключ администратора,который забит в системе

`currency:String` - тип валюты,сумму которой нужно собрать по всем кошелькам



#### Ответ

```

    type: RUB,
    amount: 199

```


### Посмотреть количество операций, которые были проведены за указанный период


` GET api/get_count_transactions?key&dateFrom&dateTo`

#### Параметры

` key:String ` - секретный ключ администратора,который забит в системе

`dateFrom:Date` - дата между которой будет происходить поиск операций

`dateFrom:Date` - дата между которой будет происходить поиск операций

#### Ответ

```

    transactionCount: 18


```

### SQL скрипты для создания бд


```

CREATE TABLE public.currency (
	id int4 NOT NULL,
	"type" text NOT NULL,
	CONSTRAINT currency_pk PRIMARY KEY (id)
);

```


```

CREATE TABLE public.currency_rate (
	id int4 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	currency_id int4 NOT NULL,
	rub_rate numeric NOT NULL,
	ton_rate numeric NOT NULL,
	btc_rate numeric NOT NULL,
	CONSTRAINT currency_rate_pk PRIMARY KEY (id)
);

ALTER TABLE public.currency_rate ADD CONSTRAINT currency_rate_currency_id_fkey FOREIGN KEY (currency_id) REFERENCES public.currency(id);

```



```

CREATE TABLE public."transaction" (
	id int4 NOT NULL,
	wallet_id int4 NOT NULL,
	amount numeric NOT NULL,
	"date" date NOT NULL DEFAULT now(),
	transaction_type text NOT NULL,
	CONSTRAINT transaction_pk PRIMARY KEY (id)
);

```




```

CREATE TABLE public.users (
	id int4 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	username text NOT NULL,
	email text NOT NULL,
	secretkey text NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (id)
);


create trigger wallets_create_trigger after
insert
    on
    public.users for each row execute function create_wallets();
    
```


```

CREATE TABLE public.wallet (
	id int4 NOT NULL,
	wallet_type_id int4 NULL,
	balance numeric NULL,
	user_id int4 NULL,
	CONSTRAINT wallet_pk PRIMARY KEY (id)
);



create trigger transaction_create_trigger after
update
    on
    public.wallet for each row execute function create_transaction();



ALTER TABLE public.wallet ADD CONSTRAINT wallet_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);
ALTER TABLE public.wallet ADD CONSTRAINT wallet_wallet_type_fkey FOREIGN KEY (wallet_type_id) REFERENCES public.wallet_type(id);
    
```



```

CREATE TABLE public.wallet_type (
	id int4 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	"type" text NOT NULL,
	CONSTRAINT wallet_type_pk PRIMARY KEY (id)
);

```

### Заполнение начальными  данными 


```

INSERT into public.wallet_type 
("type")
VALUES('BTC_wallet');
INSERT INTO public.wallet_type
("type")
VALUES('TON_wallet');
INSERT INTO public.wallet_type
("type")
VALUES('RUB_wallet');

insert into currency values(1,'RUB');
insert into currency values(2,'TON');
insert into currency values(3,'BTC');


insert into currency_rate values(1,1,1,0.00526315789,0.00000055854);
insert into currency_rate values(2,2,190.49,1,0.000108);
insert into currency_rate values(3,3,1909464,9275,1);

```
