Narrative: 
IN ORDER TO not have to create a large number of sessions,
AS A lecturer,
I WANT TO specify that a session is a one off, or recurs weekly or fortnightly.

Scenario: 
GIVEN a session and a positive integer frequency
WHEN the user updates the session 
THEN it should be updated in the database
AND the function should return true.

Scenario: 
GIVEN a session and a negative integer frequency
WHEN the user updates the session
THEN the session frequency should not be updated in the database 
AND the function should return false.

Scenario: 
GIVEN a missing session and a frequency
WHEN the user updates the session
THEN the session frequency should not be updated in the database 
AND the function should return false.