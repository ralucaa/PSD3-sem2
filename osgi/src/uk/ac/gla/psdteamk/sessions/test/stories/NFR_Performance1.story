Narrative:
In order to fulfill a non functional requirement
As a system
I want to support at least 10 sessions per course

Scenario: add 10 sessions to an invalid courseId 04
Given a course id 4
When I try to add 10 session types to this course
Then the database adding indicator has to be false

Scenario: add 10 sessions to an valid courseId 1
Given a course id 1
When I try to add 10 session types to this course
Then the database adding indicator has to be true