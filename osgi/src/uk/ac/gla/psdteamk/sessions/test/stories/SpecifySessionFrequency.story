Narrative: 
In order to not have to create a large number of sessions,
As a lecturer,
I want to specify that a session is a one off, or recurs weekly or fortnightly.

Scenario: valid lecturer, valid session, positive frequency
Given a username 2222222A, a password 2222222A, a session 1 and a frequency 7
When the user updates the session frequency
Then the session frequency should be updated in the database and the function should return true

Scenario: valid lecturer, valid session, negative frequency
Given a username 2222222A, a password 2222222A, a session 1 and a frequency -3
When the user updates the session frequency
Then the session frequency should not be updated in the database and the function should return false

Scenario: valid lecturer, invalid session, positive frequency
Given a username 2222222A, a password 2222222A, a session -1 and a frequency 7
When the user updates the session frequency
Then the session frequency should not be updated in the database and the function should return false

Scenario: invalid lecturer, valid session, positive frequency
Given a username 3333333A, a password 3333333A, a session 1 and a frequency 7
When the user updates the session frequency
Then the session frequency should not be updated in the database and the function should return false