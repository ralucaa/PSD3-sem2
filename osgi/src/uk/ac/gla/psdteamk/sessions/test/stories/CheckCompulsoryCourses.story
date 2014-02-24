Narrative:
In order to get full credits from my courses 
As a student
I want to check I signed up for everything required

Scenario: student username is not in the database
Given a student username 
When the username is not in the database
Then return an error message

Scenario: student username contains special characters
Given a student username
When the username contains special symbols that might allow SQL injection
Then filter out the username and perform the query

Scenario: student finds out compulsory courses
Given a student username
When the username is correct
And the student has compulsory courses
Then return a list of compulsory courses

Scenario: student finds out compulsory courses
Given a student username
When the username is correct
And the student does not have compulsory courses
Then return an empty list of compulsory courses