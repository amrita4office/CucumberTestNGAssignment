Feature: To validate User Registration

Scenario: To validate registration of a user

Given Open the required URL
When Click on the SignUP link
#And Fill in the User Details in registration screen as "U62","test","U62.test@test.com","U62Test","pwd1"
And Fill in the User Details in registration screen as "U","test","@test.com","pwd1"
#Then Validate the registration "Dear U62 test" and "U62.test@test.com"
Then Validate the registration "U","test","@test.com"