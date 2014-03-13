Narrative:
In order to allow the students to complete the course
As a administrator
I want to check that there are no timetable slot clashes between courses .

Scenario: there are no timetable clashes
Given a course id 1
When an administrator checks for clashes
Then he should be informed that there are no timetable clashes

Scenario: there are timetable clashes
Given a course id 2
When an administrator checks for clashes
Then he should be informed that there are timetable clashes