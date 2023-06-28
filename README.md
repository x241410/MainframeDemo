# TELUS > TELUS UI - BEST PRACTICES FOR JAVA

1.1 Testing Code
Specifically speaking about the code of the tests, steps and scripts in general, there are some best practices that enhance the development of a project. The idea of these best practices is to have a readable, understandable project, allowing to the team people and external people easily identify files, package, methods, steps and tests when is necessary.
These best practices should reduce the time that takes a new joiner to adapt to the project or to find a problem related with the update of data or modification of a step. As previously mention, the times are translated to saving money, to better yields and finally to better results.
Marker Annotation
Single Value Annotation
Full Annotation

Some best practices are:
• Should be trustworthy, the test must fail if the code is broken.
• Should be maintainable and readable.
- Especially when the code needs to be updated or possibly debugged.
- Must be understandable for others.
- Name the tests clear and legible.
• Should verify a single use case
- Validate one thing and one thing only.
- This make the test simpler and more understandable, which helps for maintainability and debugging.
- Use a minimal number of assertions.
• Should be Isolated
- Runnable on any machine.
- Without affecting each other
- If possible, should have no dependencies on environmental factors or external state.
• Should be Automated
- Make sure that are being run in an automated process.
- Needs to be accessible to and reviewed by everyone on the team.

1.2 Assert vs Verify
Asserts: will break the test and give an immediate response.
• Is best used when the check value must pass for the test to be able to continue to run.
• Example: Log in.
Verify: will continuous your tests, executing the other commands, even with a fail result.
• Is used to check non critical things.
• Example: the presence of a headline element.
Be careful deciding which one to use, analyze the situation, this depends on the case.
Verify
Verify
Assert
Assert

1.1 Use assertions instead of print statement
Print Statements requires manual intervention by developers to verify the output printed on the console to check if the test ran successfully or not. When the scripts and the tests contain a lot of steps this could this could end in a laborious and difficult task. A better approach is to use assertions which automatically indicate test results and could stop the tests if is necessary (Assertions).

2. Framework Recommendations
This section is about some recommendations to have best practices in the Selenium with Java Framework, is important to continue the best practices previously mentioned.
Incorrect use, the script will continue even if this section fails…

This is the basic structure of the core project from the Selenium with Java Framework. Here are available tools and utilities that were created to facilitate the UI testing. Some are related with the browser function, some common steps use in different situations (click elements, scroll window, etc.), other created to improve test performance and seek to apply the best practices.

2.1 Wait
Strongly recommended NOT using “Thread.sleep”, cause will add many problems to your test (blocks the current thread). The framework contains a group of utilities that could be useful during the step definition.
These utilities combine the necessity to have a break in the code (in a time matter) with common situation that a project could present.
The most common are:
• waitForElementVisible
• waitForElementText
• waitForElmentToBeCickable
• waitForElementVisibility
The use of these waits depends on the web Element to check.

2.2 Other Common Steps
The framework contains other common actions that could be implemented on the tests. These actions are related with the validation of a Web Element or with its interaction.
Is highly recommended to check the content of the core project to verify if the method or step is already there. This prevent the creation of new and repeated methods, facilitating the code reading and understanding.
This section contains different methods to click a Web Element using different approaches. The approach will depend on the element.
The section is focus on debug a Element, the main two methods that are inside: highlight and scroll.
Sometimes the user needs to declare a Web Element, with these methods Selenium can identify the Element to use it later.
In some cases, to navigate in a Website is necessary to use JavaScript commands. In this class there are methods related with this task.

2.3 Validations
Another important aspect as previously mention, are the validations. This helps to make a trustworthy code and to accomplish the objectives of a test. The framework contains standard asserts that are used in UI testing.
These validations cover some generic asserts, default messages, validate that two elements are equal, if a statement is true or false, if an element is present.

2.4 Other Recommendations
• Preferable use ID / Name or CSS as a Web Element Location Strategy. Xpath is the best option as provides the capability to add logic to find an object but make sure it is well constructed to avoid missing objects or confusing syntaxis.
• The page object doesn’t represent an entire page, it could be used to represent components on a page. For example, a top menu that appears in all pages, this section could be considered as a page and have its own java class.
• Check the methods that are already created on the Framework (reusability).
• When it is truly appropriate to take no action in a catch block, the reason needs to be explained in a comment.
• Never use “return” in the middle of a method, it should only be used at the end.
• “break” is used only for switch statement control.