Narrative:
In order to get full credits from my courses 
As a student
I want to check I signed up for everything required

Scenario: student username is not in the database
Given a student username bogus
When I check the student in database
Then the output of the check is false

Scenario: student checks if is registered for the compulsory courses
Given a student username 3333333A
When I check the student in database
Then the output of the check is true