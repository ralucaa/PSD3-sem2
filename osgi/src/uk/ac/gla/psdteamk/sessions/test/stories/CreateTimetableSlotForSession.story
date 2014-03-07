Narrative:
In order to rooms be assigned to slots
As an administrator
I want to create a timetable slot for a session

Scenario: timetable slot is created successfully
Given a session 2
When the administrator tries to create a timetable slot for a session
Then true is returned

Scenario: the sessionId is invalid
Given a session -1
When the administrator tries to create a timetable slot for a session
Then false is returned

