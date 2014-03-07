Narrative:
In order to meet the technical requirements
As A system
I want to support at least 1000 different users

Scenario: Add 1000 users
Given a number of users 1000
When I try to populate the database with the users
Then it should return true

Scenario: Add 1001 users
Given a number of users 1001
When I try to populate the database with the users
Then it should return true
