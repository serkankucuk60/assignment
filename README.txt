******** Assignment Readme *********

- Tek repo içerisinde 4 farklı proje bulunuyor.
	- auth (User Management)
	- todo (ToDo Management)
	- group (Group Management)
	- gateway (API to use todo and group)

- Her projede docker-compose.yaml dosyası mevcuttur.

** auth **

- auth-gateway bir SpringBoot uygulamasıdır ve localhost:8000 adresinde ayağa kalkar.
- auth-management ise authentication, authorization ve user management ile sorumlu. 
	Kullanıcıların, rollerin CRUD işlerini de yapar.
- auth-management PostgreSql kullanır ve kurulumu için docker-compose dosyası mevcuttur.
- PgAdmin de docker-compose ile ayağa kaldırılıyor. "localhost:8088/login" adresinden giriş yapılabilir.
- PgAdmin kullanıcı giriş bilgileri: 
		* skucuk@nurd.com
		* a1s2d3
- auth-gateway endpointleri ile sign-up ve sign-in yapılır, requestler için Postman kullanılır.
- Sign in sonrası JWT Bearer token üretilir.
- Test requestleri ile token kullanılarak requestler gönderilir ve hem authentication 
	hem de authorization kontrolleri yapılır.
- Tüm requestler Postman'den export alındı, "Auth.postman_collection.json" isimli dosya.


** todo **

- todo-gateway bir SpringBoot uygulamasıdır ve localhost:8080 adresinde ayağa kalkar.
- todo-management ise CRUD işlemlerini yapar.
- todo-management MongoDb kullanır, kurulumu için docker-compose dosyası mevcuttur.
- Kendi bilgisayarıma MongoDb Compass kurarak "mongodb://admin:admin@localhost:27017/" connection stringi ile bağlandım.
- localhost:8080/swagger-ui.html adresinden Swagger kullanılarak endpointler test edilebilir.
- Bu uygulamayı dışarıya kapalı olacak bir uygulama olarak düşündüğümden Authorization token istemiyor.


** group **

- group-gateway bir SpringBoot uygulamasıdır ve localhost:8081 adresinde ayağa kalkar.
- group-management ise CRUD işlemlerini yapar.
- group-management MongoDb kullanır, kurulumu için docker-compose dosyası mevcuttur.
- Kendi bilgisayarıma MongoDb Compass kurarak "mongodb://admin:admin@localhost:27018/" connection stringi ile bağlandım.
- localhost:8081/swagger-ui.html adresinden Swagger kullanılarak endpointler test edilebilir.
- Bu uygulamayı dışarıya kapalı olacak bir uygulama olarak düşündüğümden Authorization token istemiyor.


** gateway **

- gateway bir SpringBoot uygulamasıdır ve localhost:8888 adresinde ayağa kalkar.
- Front-end tarafından kullanılarak olan endpointler bu uygulamada bulunur.
- Request header'ında authorization Bearer token ister ve token geçerli mi diye auth-gateway üzerinden kontrol eder.
- todo-gateway ve group-gateway endpointlerini kullanarak arayüzlerin ihtiyaç duyacağı işleri yapar.
- localhost:8888/swagger-ui.html adresinden Swagger kullanılarak endpointler test edilebilir.
- auth-gateway sign-in requesti ile temin edilen Bearer token burada Swagger arayüzünde bulunan Authorize butonu ile açılan 
	yere mutlaka girilmeli. 	