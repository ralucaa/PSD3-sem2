Narrative: In order to have room bookings recorded 
As an administrator 
I want to assign a room to a timetable slot 

Scenario: administrator tries to assign correct room ID to empty timetable slot 
Given a sessionId and roomId 
When the assignRoom method is executed 
Then the roomId is $roomId 
And sessionId is $sessionId 
Then close connection

Scenario: administrator tries to assign correct room ID to unavailable timetable slot 
Given a sessionId and roomId 
When the assignRoom method is executed 
Then an exception is thrown 
Then close connection

Scenario: administrator tries to assign wrong room ID to an available timetable slot 
Given a sessionId and roomId 
When the assignRoom method is executed 
Then an exception is thrown 
Then close connection

Scenario: administrator tries to assign wrong room ID to an unavailable timetable slot 
Given a sessionId and roomId 
When the assignRoom method is executed 
Then an exception is thrown
Then close connection