Narrative: 
In order to have room bookings recorded 
As an administrator 
I want to assign a room to a timetable slot 

Scenario: administrator tries to assign correct room ID to empty timetable slot 
Given a sessionId 44 and roomId 567
When the assignRoom method is executed
Then the method returns true
