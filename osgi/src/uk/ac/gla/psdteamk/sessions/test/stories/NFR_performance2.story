Narrative:
In order to meet the technical requirements
As A system
I want to support at least 1000 different users

Scenario: Add 1000 users
Given a number of users $u
When I try to populate the database with the users
Then I should be able to add 1000 and more of them

Scenario: Add 1001 users
Given a number of users $u
When I try to populate the database with the users
Then I should be able to add 1001 of them
