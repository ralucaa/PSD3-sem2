Narrative:
In order to meet the technical requirements
As a system
I want to support at least 100 concurrent users.

Scenario: 101 concurrent users logged in
Given 101 concurrently logged in users
When the system checks the number of logged in users
Then at least 101 users should be logged in