Feature: Lens kart End to End

Scenario: Amount validation

Given the user opens the browser and loads the given url
And the user Mouseover on Contact Lenses 
And the user Click on Monthly under Explore By Disposability
And the user Select brand as Aqualens
And the user Click on the first product
And the user Click Buy Now
And the user Select No of boxes as 2 and Power as -1 for both eyes.
And the user Type your name in User's name 
When the user And click Save and continue
Then the user Print total amount and click Proceed to Checkout