Narrative:
In order to fulfill a non functional requirement
As a system
I want to support at least 10 sessions types per course

Scenario: add 10 sessions to a valid courseId 1
Given a course id 1
When I try to add 10 session types to this course
Then the database adding indicator has to be true