Narrative:
In order to get full credits from my courses 
As a student
I want to check I signed up for everything required

Scenario: student username is not in the database
Given a student username 000000000
When the database request is made
Then the output is false

Scenario: student username contains special characters
Given a student username a$b
When the database request is made
Then the output is false

Scenario: student sees compulsory courses
Given a student username 1007100b
When the database request is made
Then the output is true