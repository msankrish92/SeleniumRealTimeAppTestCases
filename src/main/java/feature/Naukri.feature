Feature: Naukri

Scenario: TC001_Naukri

Given the user load naukri url
And the user goes the first opened window and get the company Name
And the user closes that window
And the user goes the second opened window and get the company Name
And the user closes that window
When the user uploads unsupported file as CV
Then the user validates the error message