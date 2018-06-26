Feature: CucumberJava
  Employee manager

  Scenario: Manage employee's salary
    Given For the employee with id 5 the manager said that will increase the salary
    When The manager want to increse the salary with 5%
    Then In the final,the employee with id 5 should has as salary  159.81
    
    