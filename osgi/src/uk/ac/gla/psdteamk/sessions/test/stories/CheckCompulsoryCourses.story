Narrative:
In order to get full credits from my courses 
As a student
I want to check I signed up for everything required

Scenario: student username is not in the database
Given a student with username bogususer and password bogususer
When the student checks if he is fully registered for the compulsory courses
Then the output of the check is false

Scenario: student checks if is registered for the compulsory courses
Given a student with username 3333333A and password 3333333A
When the student checks if he is fully registered for the compulsory courses
Then the output of the check is false

Scenario: student checks if is registered for the compulsory courses
Given a student with username 3333333E and password 3333333E
When the student checks if he is fully registered for the compulsory courses
Then the output of the check is true