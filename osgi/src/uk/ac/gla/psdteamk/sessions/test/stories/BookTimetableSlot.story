Narrative:
In order to attend a course 
As a student
I want to book a timetable slot for each session of my course

Scenario: student username and sessionID contain special characters
Given a student username ab and sessionID ab
When the database request is made
Then the output is false 

Scenario: student books a timetable slot
Given a student username 1007100b and sessionID 666
When the database request is made
Then the output is true