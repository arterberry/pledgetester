TestFramework (a.k.a Pledgetester )
===================================

A starter Java + Selenium WebDriver based automated test framework, designed to support continuous integration testing of web applications. 

<br>


Setup Requirements
==================
Maven<br>
Firefox (latestversion)<br>
Java (latest version)

POM configured for the following versions<br>
selenium-firefox-driver - 2.48.0<br>
selenium-chrome-driver - 2.48.0

<br>


The Basics
==========

The nomenclature of this test framework is as follows: 

####Common package
* Includes test driver and utility setup to extend to test class.
<br>

####Page package
* Build and define the application under test page. This includes DOM elements mapped by ID, name or XPath (preferred).
<br>

####Test package
* Holds test logic and extends drivers from Common package.
<br>

####Resource directory
* Include key test resource needs, like environment URLs or test data variables, to implement at runtime.
<br>

How To
======

To use, run `mvn -clean install` from local directory. You can run individual test classes by using Surefire's plugin  with Maven, like so: `mvn -Dtest=[CLASS NAME] test`.


 


