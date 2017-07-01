# AddressBookManager

[![Build Status](https://travis-ci.org/deepaksengar/AddressBookManager.svg?branch=master)](https://travis-ci.org/deepaksengar/AddressBookManager)

The application is a simulation of a address book application to maintain contacts which allows to add, remove and print contact, as well as maintain multiple addressbooks.

Execution Instruction:
---------------------
	Goto the root directory of projects and execute following:
	To remove all older builds and jars :
	-	mvn clean
	
	To build the code and run tests :
	-	mvn clean install
	
	To run the tests :
	-	mvn test -B

Dependency:
-----------
	1) Java 8
	2) JUnit 4 : for test execution
	

Design:
------
	1) Model :
		Contact :
			This contains name and phoneNumber. This entry will be stored in AddressBook.
		AddressBook :
			AddressBook has a name and set of Contact entries..
	
	2) Service :
		AddressBookManager :
			This class manages address books for user and create/update contacts in addressBook.
		AddressBookService : 
			This interface provides method to maintain a single addressBook.
		AddressBookServiceImpl : 
			This class provides implementation to maintain a single addressBook, and uses AddressBookManager for operations.	
		MultipleAddressBookService : 
			This interface provides method to maintain a multiple addressBook.
		MultipleAddressBookServiceImpl : 
			This class provides implementation to create/update/maintain multiple addressBooks.	
	
	3) Helper : 
		This class provides validation method for String and PhoneNumber, as well as convert input in proper format.
	
	4) Test Classes :
		InputValidationTest
		AddressBookTest
		ContactTest
		AddressBookManagerTest: To check that all operations on address book are working as intended.
		MultipleAddressBookServiceTest: 
			To satisfy acceptance criteria, managing multiple addressBooks, add/remove/print-all/printUnique contacts.

        

