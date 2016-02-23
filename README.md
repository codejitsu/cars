# cars

RESTful Garage Service Simulation

# install

```
mvn clean install
```

# run

```
mvn spring-boot:run
```

# queries

* Enter vehicle: returns new vehicle location in garage with 201 (CREATED), or 409 (CONFLICT) if the vehicle already in garage. 


    curl -X POST localhost:8080/car/1/enter -v
    
    *   Trying ::1...
    * Connected to localhost (::1) port 8080 (#0)
    > POST /car/1/enter HTTP/1.1
    > Host: localhost:8080
    > User-Agent: curl/7.43.0
    > Accept: */*
    >
    < HTTP/1.1 201 Created
    < Server: Apache-Coyote/1.1
    < Content-Type: application/json;charset=UTF-8
    < Transfer-Encoding: chunked
    < Date: Tue, 23 Feb 2016 07:34:06 GMT
    <
    * Connection #0 to host localhost left intact
    
    {"space":0,"level":1}
    
* Exit vehicle: returns old vehicle location in garage with 201 (CREATED), or 409 (CONFLICT) if the vehicle already left garage. 


    curl -X POST localhost:8080/car/1/exit -v
    
    *   Trying ::1...
    * Connected to localhost (::1) port 8080 (#0)
    > POST /car/1/enter HTTP/1.1
    > Host: localhost:8080
    > User-Agent: curl/7.43.0
    > Accept: */*
    >
    < HTTP/1.1 201 Created
    < Server: Apache-Coyote/1.1
    < Content-Type: application/json;charset=UTF-8
    < Transfer-Encoding: chunked
    < Date: Tue, 23 Feb 2016 07:34:06 GMT
    <
    * Connection #0 to host localhost left intact
    
    {"space":0,"level":1}
    
* Get vehicle location: returns vehicle location in garage with (OK) or 404 if vehicle not found in garage.
    
  
    curl -X GET localhost:8080/car/1/location -v
    *   Trying ::1...
    * Connected to localhost (::1) port 8080 (#0)
    > GET /car/1/location HTTP/1.1
    > Host: localhost:8080
    > User-Agent: curl/7.43.0
    > Accept: */*
    >
    < HTTP/1.1 200 OK
    < Server: Apache-Coyote/1.1
    < Content-Type: application/json;charset=UTF-8
    < Transfer-Encoding: chunked
    < Date: Tue, 23 Feb 2016 07:39:33 GMT
    <
    * Connection #0 to host localhost left intact

    {"space":0,"level":1}
    
* Get number of free slots: returns 200 (OK) with number of free slots in garage.


    curl -X GET localhost:8080/free -v
    *   Trying ::1...
    * Connected to localhost (::1) port 8080 (#0)
    > GET /free HTTP/1.1
    > Host: localhost:8080
    > User-Agent: curl/7.43.0
    > Accept: */*
    >
    < HTTP/1.1 200 OK
    < Server: Apache-Coyote/1.1
    < Content-Type: application/json;charset=UTF-8
    < Transfer-Encoding: chunked
    < Date: Tue, 23 Feb 2016 07:42:45 GMT
    <
    * Connection #0 to host localhost left intact
    
    9
    
# config via garage.properties

    garage.levels: 2
    garage.places.level: 5
    
    
    
    
    