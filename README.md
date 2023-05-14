Restaurant Voting Application
===============================
## Technical requirement

Build a voting system for deciding where to have lunch.
* 2 types of users: admin and regular users
* Admin can input a restaurant, and it's lunch menu of the day (2-5 items usually, just a dish name and price)
* Menu changes each day (admins do the updates)
* Users can vote for a restaurant they want to have lunch at today
* Only one vote counted per user
* If user votes again the same day:
* * If it is before 11:00 we assume that he changed his mind.
* * If it is after 11:00 then it is too late, vote can't be changed

-------------------------------------------------------------
- Stack: [JDK 17](http://jdk.java.net/17/), Spring Boot 3.x, Lombok, H2, Caffeine Cache, SpringDoc OpenApi 2.x
- Run: `mvn spring-boot:run` in root directory.
-----------------------------------------------------
[REST API documentation](http://localhost:8080/)  
Credentials:
```
User:  user@yandex.ru / password
Admin: admin@gmail.com / admin
Guest: guest@gmail.com / guest
```

### User 

Functionality administrator:

* GET /api/admin/users - get a list of all users
* PUT /api/admin/users/{id} - update user profile by id
* DELETE /api/admin/users/{id} - delete user profile by id
* PATCH /api/admin/users/{id} - enable or disable user account
* GET /api/admin/users/{id} - get user profile by id
* POST /api/admin/users - create user profile
* GET /api/admin/users/by-email - get user profile by email

Functionality user:

* POST /api/profile - create new account
* GET /api/profile - get your account
* PUT /api/profile - update your account
* DELETE /api/profile - delete your account


### Restaurant

Functionality administrator:

* GET /api/admin/restaurants - get a list of all restaurants
* GET /api/admin/restaurants/actual - get a list of all restaurants with actual menu
* GET /api/admin/restaurants{id} - get a restaurant by id
* GET /api/admin/restaurants{id}/actual_menu - get a restaurant by id with actual menu
* POST /api/admin/restaurants - create new restaurant
* PUT /api/admin/restaurants/{restaurantId} - update restaurant by id
* DELETE /api/admin/restaurants/{id} - delete restaurant by id

Functionality user:

* GET /api/user/restaurants{id} - get a restaurant by id
* GET /api/user/restaurants{id}/actual_menu - get a restaurant by id with actual menu
* GET /api/user/restaurants/actual - get a list of all restaurants with actual menu
* POST /api/user/restaurants/{id}/register_vote - vote for restaurant using id

### Dish

Functionality only administrator:

* GET /api/admin/restaurants/{restId}/dishes/{id} - get dish by id
* GET /api/admin/restaurants/{restId}/dishes - get a list of all dishes by restaurant id
* POST /api/admin/restaurants/{restId}dishes - add dish to the restaurant menu
* PUT /api/admin/restaurants/{restId}/dishes/{id} - update a dish in the restaurant menu
* DELETE /api/admin/restaurants/{restId}/dishes/{id} - delete dish by id

### Vote

Functionality administrator:

* GET /api/admin/votes?date_created - get a list of votes by date
* DELETE /api/admin/votes/{id} - delete vote by id

Functionality user:

* GET /api/user/votes - get all votes of authenticated user
