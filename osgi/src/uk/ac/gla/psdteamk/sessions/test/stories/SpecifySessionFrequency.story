Narrative: 
In order to not have to create a large number of sessions,
As a lecturer,
I want to specify that a session is a one off, or recurs weekly or fortnightly.

Scenario: valid session, positive frequency
Given a session 1 and a frequency 7
When the user updates the session
Then the session frequency should be updated in the database and the function should return true

Scenario: valid session, negative frequency
Given a session 1 and a frequency -3
When the user updates the session
Then the session frequency should not be updated in the database and the function should return false

Scenario: invalid session, positive frequency
Given a session -1 and a frequency 7
When the user updates the session
Then the session frequency should not be updated in the database and the function should return false