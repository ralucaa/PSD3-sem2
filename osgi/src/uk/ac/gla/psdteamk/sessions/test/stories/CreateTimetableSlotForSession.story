Narrative:
In order that rooms can be assigned to slots
As an administrator
I want to create a timetable slot for a session

Scenario: timetable slot is created successfully
Given a session
When the administrator tries to create a timetable slot for a session
Then true is returned

Scenario: the sessionId is invalid
Given a session
When the administrator tries to create a timetable slot for a session
Then false is returned

Scenario: the database contains the same sessionId more than once
Given a session
When the administrator tries to create a timetable slot for a session
Then false is returned

