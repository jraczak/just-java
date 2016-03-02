Given(/^I tap the increment button 20 times$/) do
  20.times do
    tap({text: '+'})
  end
end

When(/^I tap the increment button$/) do
  tap({text: '+'})
end

When(/^I tap the decrement button$/) do
  tap({text: '-'})
end

Given(/^I tap the whipped cream option$/) do
  tap({id: 'whipped_cream_checkbox'})
end

Given(/^I tap the chocolate option$/) do
  tap({id: 'chocolate_checkbox'})
end

Given(/^I enter the name "([^\"]*)" into the name field$/) do |name|
  enter_text_in({id: 'customer_name_input_field'}, name)
end

Then(/^I scroll down$/) do
  scroll({id: 'parent_scroll_view'}, :down)
end

Then(/^I tap the order button$/) do
  tap({id: 'order_button'})
end

Then(/^I should see the text "([^\"]*)"$/) do |text|
  wait_for_view({text: text})
end


# query("* {text LIKE[c] '*POINTS*'}")`


#Given(/^I have done a specific thing$/) do
 #  # Sample step definition
 #  # Example: (Given I am logged in)
 #  #  enter_text("* marked:'username'", USERNAME)
 #  #  enter_text("* marked:'password'", PASSWORD)
 #  #  touch("* marked:'login'")
 #  #  wait_for_view("* text:'Welcome #{USERNAME}'")
 #
 #  # Remember: any Ruby is allowed in your step definitions
 #  did_something = true
 #
 #  unless did_something
 #    fail 'Expected to have done something'
 #  end
 #end
 #
 #When(/^I do something$/) do
 #  # Sample step definition
 #  # Example: When I create a new entry
 #  #  touch("* marked:'new_entry'")
 #  #  enter_text("* marked:'entry_title'", 'My Entry')
 #  #  touch("* marked:'submit'")
 #end
 #
 #Then(/^something should happen$/) do
 #  # Sample step definition
 #  # Example: Then I should see the entry on my home page
 #  #  wait_for_view("* text:'My Entry'")
 #end