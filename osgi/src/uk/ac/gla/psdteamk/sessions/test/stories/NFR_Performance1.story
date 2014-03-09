Narrative:
In order to fulfill a non functional requirement
As a system
I want to support at least 10 sessions per course

Scenario: add 10 sessions to a course 04
Given a course id 04
When I try to add 10 sessions to this course
Then the database adding indicator has to be true