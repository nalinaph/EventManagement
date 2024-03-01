#Event Management Project#

It is a One-to-Many Mapping Project between User(1) and Event(N)

This project demonstrates how to implement a one-to-many mapping relationship between User and Event entities using Java with Hibernate, expose it through a REST API, and persist data in a MySQL database.

**Features:**

One-to-many mapping between User and Event entities.
Expose RESTful API endpoints for CRUD operations on Users and Events.
Data persistence using MySQL database.


**Prerequisites**

Before running the project, ensure you have the following installed:

Java Development Kit (JDK)
Apache Maven
MySQL database server
Spring Boot : 3.2.3

**Steps**:

S1:**Clone the repository** : git clone <https://github.com/nalinaph/EventManagement.git>    Note: branch name: master

S2: Create a MySQL database(DB name:eventmgmt) and configure the connection in application.properties.

S3:After setting up the project, you can access the REST API endpoints to perform CRUD operations on Users and Events. Use a tool like Postman to interact with the API.

S4: First we have to create user, then we can create events(because there is an 1-N mapping between the tables, user_id is a Foreign Key in "Event" table)


1. Create a user(POST) : http://localhost:8080/api/user  
Json object :
{
    "userFirstname": "Rakesh",
    "userLastname" :"MC"
}

2. Create event(POST): http://localhost:8080/api/events?userId=2 (Here replace userId=2 with an userId which is in your DB table)
Json object :
{
    "eventName": "Marriage",
    "location": "DVG",
    "organizer":"Ninada Events",
    "numberOfAttendees":1510,
    "dateTime":"2024-03-16T06:30:00",
    "duration":8
}

3. Get event detail by Id(GET) : http://localhost:8080/api/events?eventId=3  
 (Here replace eventId=3 with an userId which is in your DB table)

4. Get all events(GET) : http://localhost:8080/api/events/allEvents

5. Events Between Date and Time range(GET):http://localhost:8080/api/events/eventsrange?startDate=2024-01-21&endDate=2024-05-25&minDuration=6&maxDuration=17
 (Here replace date and duration as per table data)

6. Events ByOrganizerAnd Attendees(GET) :http://localhost:8080/api/events/filteredevents?organizer=Ninada Events&numberOfAttendees=1510
                                                                      (Here replace organiser and number of attendees as per table data)

7.Update Event(PUT): http://localhost:8080/api/events?eventId=2  
(Here replace eventId=3 with an userId which is in your DB table)

Json object :
{
    "eventName": "Anniversary",
    "location": "US",
    "organizer":"Happy Events",
    "dateTime": "2024-05-21T11:30:00",
    "duration":8

}

8. Daily attendees count by date(GET) : http://localhost:8080/api/events/dailyattendeescount?startDate=2024-01-28&endDate=2024-05-20
    (Here replace date range as per table data)

9. Delete Event(DELETE) : http://localhost:8080/api/events/12
   (Here replace 12 with event Id which is in DB table)

Dependencies:
1.  MYSQL Driver
2.  spring web
3. spring Data JPA
4. Lombok
5. Spring Boot Dev Tools
6. add Model Mapper dependency
