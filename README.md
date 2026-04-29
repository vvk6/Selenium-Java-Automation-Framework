# 🛒 E-Commerce Selenium Java Automation Framework

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)]()
[![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white)]()
[![TestNG](https://img.shields.io/badge/TestNG-FF7F00?style=for-the-badge&logo=testng&logoColor=white)]()
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)]()

## 📝 Overview
This repository contains a robust, scalable Web Automation Framework built from scratch using **Java**, **Selenium WebDriver**, and **TestNG**. It is designed to automate end-to-end user journeys for an E-commerce web application. 

The framework strictly adheres to the **Page Object Model (POM)** design pattern to ensure maximum code reusability and maintainability. It also implements `ThreadLocal` for parallel execution, ensuring complete thread safety during test runs.

## ✨ Key Features & Architecture
* **Page Object Model (POM):** Web elements and actions are strictly separated from test scripts, making the code clean and easy to maintain.
* **Thread-Safe Execution:** Utilizes `ThreadLocal` within the `TestBase` class to manage WebDriver instances, preventing race conditions during parallel test execution.
* **Test Grouping:** Test cases (distributed across 3 core test classes) are bucketed using TestNG `@Groups` (e.g., Sanity, Regression, Smoke) for targeted execution.
* **Data-Driven Testing:** Leverages TestNG `@Parameters` and custom Excel utilities to inject test data dynamically from external sources.
* **Flaky Test Handling:** Custom `IRetryAnalyzer` implementation to automatically retry failed test cases.
* **Custom Utilities:** A dedicated `utility` package housing reusable components:
  * `ExcelUtility`: For reading/writing test data.
  * `WaitUtility`: Centralized explicit and fluent wait management.
  * `CommonMethodsUtility`: Wrappers for common interactions (clicks, scrolling, dropdowns).

## 📂 Project Structure
```text
Selenium-Java-Automation-Framework/
├── src/main/java/
│   ├── pages/               # Page Object Classes (Locators and Actions)
│   ├── base/                # TestBase class with ThreadLocal WebDriver setup
│   └── utility/             # Helper classes (Excel, Waits, CommonMethods, Retry)
├── src/test/java/
│   └── testcases/           # Test classes containing @Test methods
├── src/test/resources/
│   ├── testdata/            # External data files (Excel, CSV)
│   └── properties/          # Configuration files (config.properties)
├── testng.xml               # TestNG suite configuration and grouping
└── pom.xml                  # Maven dependencies and build configurations
