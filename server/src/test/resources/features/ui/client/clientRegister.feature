Feature: Client: Automate the workflow to Register

  Scenario Outline: Client register process with variable dependents
    # SETUP & INITIAL NAVIGATION
    Given the user is on the InsuranceAssist landing page
    And click on the register button

    # PRIMARY REGISTRATION
    When the client enters primary registration details:
      | Field         | Value          |
      | Full Name     | <UserFullName> |
      | Email         | <UserEmail>    |
      | Date of Birth | <UserDOB>      |
      | Gender        | <UserGender>   |
      | Phone Number  | <UserPhone>    |
      | Address       | <UserAddress>  |
    And sets the password to "<UserPassword>"
    And submits the registration form

    # DEPENDENT REGISTRATION
    And adds <NumberOfDependents> dependents with the following details
      | Field                | Value          |
      | Dep1FullName         | <Dep1FullName> |
      | Dep1DOB              | <Dep1DOB>      |
      | Dep1Phone            | <Dep1Phone>    |
      | Dep1Email            | <Dep1Email>    |
      | Dep1Address          | <Dep1Address>  |
      | Dep1Gender           | <Dep1Gender>   |
      | Dep1Relation         | <Dep1Relation> |
      | Dep2FullName         | <Dep2FullName> |
      | Dep2DOB              | <Dep2DOB>      |
      | Dep2Phone            | <Dep2Phone>    |
      | Dep2Email            | <Dep2Email>    |
      | Dep2Address          | <Dep2Address>  |
      | Dep2Gender           | <Dep2Gender>   |
      | Dep2Relation         | <Dep2Relation> |
      | Dep3FullName         | <Dep3FullName> |
      | Dep3DOB              | <Dep3DOB>      |
      | Dep3Phone            | <Dep3Phone>    |
      | Dep3Email            | <Dep3Email>    |
      | Dep3Address          | <Dep3Address>  |
      | Dep3Gender           | <Dep3Gender>   |
      | Dep3Relation         | <Dep3Relation> |
      | Dep4FullName         | <Dep4FullName> |
      | Dep4DOB              | <Dep4DOB>      |
      | Dep4Phone            | <Dep4Phone>    |
      | Dep4Email            | <Dep4Email>    |
      | Dep4Address          | <Dep4Address>  |
      | Dep4Gender           | <Dep4Gender>   |
      | Dep4Relation         | <Dep4Relation> |

    # POLICY SELECTION & COMPLETION
    And selects the "<PolicyPlan>" policy plan
    Then the registration process is completed successfully
    And close the browser

    # VERIFICATION
    Given Open browser
    Then Navigate to insuranceAssist URL
    And Select role "Client"
    Then Input username "<Username>"
    And Input password "<UserPassword>"
    Then Click on login button
    And Verify if client landed on dashboard page with username "<Username>"
    Then Logout client
    And Close browser

    Examples:
      | UserFullName   | Username   | UserEmail        | UserPassword | UserDOB    | UserGender | UserPhone    | UserAddress          | NumberOfDependents | Dep1FullName | Dep1DOB    | Dep1Phone   | Dep1Email        | Dep1Address          | Dep1Gender | Dep1Relation | Dep2FullName | Dep2DOB    | Dep2Phone   | Dep2Email        | Dep2Address          | Dep2Gender | Dep2Relation | Dep3FullName | Dep3DOB    | Dep3Phone   | Dep3Email        | Dep3Address          | Dep3Gender | Dep3Relation | Dep4FullName | Dep4DOB    | Dep4Phone   | Dep4Email        | Dep4Address          | Dep4Gender | Dep4Relation | PolicyPlan |
      | Alice Johnson  | alice1test | alice1@test.com  | Test@123456  | 15-05-1990 | Female     | 9912345678   | 10 Main St, Anytown  | 0                  |              |            |             |                  |                      |            |              |              |            |             |                  |                      |            |              |              |            |             |                  |                      |            |              |              |            |             |                  |                      |            |              | Silver     |
      | Bob Carter     | bobtest    | bob@test.com     | Test@123456  | 10-03-1985 | Male       | 9911111111   | 11 Elm St, Cityville | 1                  | Tom Carter   | 07-07-1987 | 9911122222  | tom@test.com     | 12 Elm St, Cityville | Male       | Brother      |              |            |             |                  |                      |            |              |              |            |             |                  |                      |            |              |              |            |             |                  |                      |            |              | Gold       |
      | Carol White    | caroltest  | carol@test.com   | Test@123456  | 25-09-1978 | Female     | 9922222222   | 22 Oak Dr, Othertown | 2                  | Sam White    | 01-01-2005 | 9933344444  | sam@test.com     | 22 Oak Dr, Othertown | Male       | Son          | Lily White   | 02-02-2008 | 9955566666  | lily@test.com    | 22 Oak Dr, Othertown | Female     | Daughter     |              |            |             |                  |                      |            |              |              |            |             |                  |                      |            |              | Silver     |
      | David Green    | davidtest  | david@test.com   | Test@123456  | 12-12-1970 | Male       | 9933333333   | 33 Pine Rd, Smalltown| 3                  | Anna Green   | 06-06-1972 | 9977788888  | anna@test.com    | 33 Pine Rd, Smalltown| Female     | Wife         | Ben Green    | 03-03-2000 | 9999900000  | ben@test.com     | 33 Pine Rd, Smalltown| Male       | Son          | Mia Green    | 04-04-2003 | 9991212121  | mia@test.com     | 33 Pine Rd, Smalltown| Female     | Daughter     |              |            |             |                  |                      |            |              | Gold       |
      | Emma Brown     | emmatest   | emma@test.com    | Test@123456  | 08-08-1992 | Female     | 9944444444   | 44 Maple Ln, Bigcity | 4                  | John Brown   | 01-01-1990 | 9934343434  | john@test.com    | 44 Maple Ln, Bigcity | Male       | Husband      | Kate Brown   | 05-05-2010 | 9956565656  | kate@test.com    | 44 Maple Ln, Bigcity | Female     | Daughter     | Max Brown    | 06-06-2012 | 9978787878  | max@test.com     | 44 Maple Ln, Bigcity | Male       | Son          | Zoe Brown    | 07-07-2015 | 9990909090  | zoe@test.com     | 44 Maple Ln, Bigcity | Female     | Daughter     | Silver     |


