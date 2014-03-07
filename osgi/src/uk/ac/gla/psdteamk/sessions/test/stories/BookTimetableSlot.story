Narrative:
In order to attend a course 
As a student
I want to book a timetable slot for each session of my course

Scenario: timetable slot exists
Given a slotId 5
When the database request is made
Then the output is true

Scenario: timetable slot does not exist
Given a slotId 666
When the database request is made
Then the output is false
