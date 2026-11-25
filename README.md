# SimpleCucumber




# Commands
mvn test -Dcucumber.filter.tags="@Smoke"
mvn test -Ddataproviderthreadcount=2 


mvn test -Ddataproviderthreadcount=2  -Dcucumber.filter.tags="@Smoke"



# Surefire behaviour


How Surefire Finds Your Runner Automatically

Surefire relies on two built-in conventions:
• Default Test Directory. Surefire automatically scans src/test/java and all subpackages inside it—including your runners package (src/test/java/runners/RunCucumberTest.java).
• Default Naming Patterns. During mvn test, Surefire executes any class matching these wildcard patterns:
	• **/Test*.java
	• **/*Test.java (matches your RunCucumberTest.java)
	• **/*Tests.java
	• **/*TestCase.java


# Q and A

Which is the best way to maintain different test executions ?

I recommend keeping one Cucumber Runner class and controlling test executions through Cucumber Tags, Maven System Properties, and Maven Profiles.
Creating separate runner classes for every suite (like SmokeRunner.java, RegressionRunner.java) creates duplicate boilerplate and makes parallel thread management harder to maintain.

Comparing Test Execution Strategies


Approach	How It Works	Best For	Drawbacks
Single Runner + Tags & CLI (Recommended)	Keep RunCucumberTest generic and pass -Dcucumber.filter.tags and -Denv at runtime.	CI/CD pipelines, flexible suite filtering, and clean codebases.	Commands get long if you pass 5+ flags at once.
Maven Profiles (pom.xml)	Group predefined tags, thread counts, and properties under <profiles> (e.g., mvn test -Psmoke).	Standardized team/CI runs where you want short, memorable commands.	Adds XML verbosity to pom.xml.
TestNG XML Suites	Use testng.xml files to pass <parameter> tags (like browser=chrome and browser=firefox).	Running the exact same scenarios across multiple browsers simultaneously.	Requires wiring Surefire to <suiteXmlFiles>, overriding automatic runner discovery.
Multiple Runner Classes	Create SmokeTest.java, RegressionTest.java with hardcoded @CucumberOptions.	Quick one-click runs directly inside an IDE without editing run configurations.	High code duplication; hard to combine tags dynamically.


How to Structure This in Your Project


1. Separate "What to Run" from "Where to Run"

• Use Cucumber Tags for test scope: Tag your .feature files with @smoke, @regression, or feature areas like @checkout. Leave tags = "" empty in RunCucumberTest.java so it acts as a flexible entry point.
• Use System Properties for environment & browser: Pass runtime settings via -D flags (e.g., -Denv=staging -Dbrowser=chrome) and read them in a ConfigReader utility class using System.getProperty("env", "qa") (with a sensible default so IDE runs still work).

2. Add Maven Profiles for Common Combinations

If your team frequently runs specific combinations (such as a fast smoke check vs. a full nightly regression), define <profiles> in your pom.xml so anyone can run mvn test -Psmoke or mvn test -Pregression:
xml
<profiles>
    <profile>
        <id>smoke</id>
        <properties>
            <cucumber.filter.tags>@smoke</cucumber.filter.tags>
            <dataproviderthreadcount>2</dataproviderthreadcount>
        </properties>
    </profile>
    <profile>
        <id>regression</id>
        <properties>
            <cucumber.filter.tags>@regression and not @wip</cucumber.filter.tags>
            <dataproviderthreadcount>6</dataproviderthreadcount>
        </properties>
    </profile>
</profiles>
Use code with caution.

3. Layer in testng.xml Only for Cross-Browser Matrices

Stick to Maven commands alone if you only test one browser per run. Introduce a testng.xml file only when you want a single command to launch parallel <test> blocks for Chrome, Firefox, and Edge at the same time.
Sharing how your team runs tests lets me generate the exact pom.xml profiles and configuration setup for your project.

Which parameters change between your test runs?

Test suites (Smoke, Regression)
Target environments (QA, Staging)
Multiple browsers (Chrome, Firefox)
Parallel thread counts

Where will you trigger most test executions?

Local IDE only
Local terminal commands
CI/CD pipeline (Jenkins, GitHub)
Both local and CI/CD
Build my test execution setup

