Narrative:
In order to attend a course 
As a student
I want to book a timetable slot for each session of my course

Scenario: student username and/or session ID contain special characters
Given a student username and a session ID 
When the username or the ID contain special symbols
Then filter out the input and proceed 

Scenario: student username and/or session ID are null
Given a student username and a session ID 
When the username or the ID are null
Then return an error message