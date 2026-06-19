\# Year1-POE-2026-Semester-1



\## Overview



Year1-POE-2026 is a Java console-based messaging application developed as part of a first-year programming assignment. The project demonstrates object-oriented programming principles, user authentication, data validation, file handling, JSON processing, and unit testing.



The application allows users to create accounts, log in using their credentials, and send messages to other users through a menu-driven interface.



\---



\## Features



\### User Registration



Users can create an account by providing:



\* First name and surname

\* Username

\* Password

\* South African cellphone number



The application validates all user input before registration.



\#### Username Validation



Usernames must:



\* Contain an underscore (`\_`)

\* Be no more than five characters long



\#### Password Validation



Passwords must:



\* Be at least 8 characters long

\* Contain at least one uppercase letter

\* Contain at least one number

\* Contain at least one special character



\#### Phone Number Validation



Phone numbers must:



\* Use the South African international format

\* Begin with `+27`

\* Contain exactly nine digits after the country code



Example:



```

+27821234567

```



\---



\## User Login



Registered users can log into the application using their username and password.



Once authenticated, the system:



\* Verifies the user's credentials

\* Displays a personalised welcome message

\* Grants access to the messaging functionality



\---



\## Messaging System



After logging in, users can send messages through the application.



\### Message Features



\* Compose and send text messages

\* Validate recipient cellphone numbers

\* Limit messages to a maximum of 250 characters

\* Automatically generate unique message IDs

\* Generate message hashes

\* Store sent messages in JSON format



\### Message Validation



Messages exceeding 250 characters are rejected and users are informed how many characters exceed the limit.



\### Recipient Validation



Recipients must have cellphone numbers in the following format:



```

+27XXXXXXXXX

```



\---



\## Message Identification



Each message includes:



\### Message ID



A randomly generated 10-digit identifier.



Example:



```

1234567890

```



\### Message Hash



A hash generated using:



\* The first two digits of the Message ID

\* The message number

\* The first and last words of the message



Example:



```

12:3:HELLOWORLD

```



\---



\## Data Storage



\### User Information



User details are stored locally in:



```

Users.txt

```



\### Messages



Sent messages are stored in:



```

Messages.json

```



Each stored message includes:



\* Message Number

\* Message ID

\* Message Hash

\* Message Content

\* Recipient Number

\* Sender Username



\---



\## Technologies Used



\* Java

\* Maven

\* JSON (org.json library)

\* File I/O

\* JUnit Testing

\* Object-Oriented Programming Principles



\---



\## Project Structure



```

Year1-POE-2026

│

├── src/

│   ├── main/java/com/mycompany/assignment1/

│   │   ├── Assignment1.java

│   │   ├── Login.java

│   │   ├── Message.java

│   │   └── Arrays.java

│   │

│   └── test/java/

│       ├── ArraysUnitTest.java

│       ├── MessagesUnitTest.java

│       └── Test1.java

│

├── Users.txt

├── Messages.json

├── pom.xml

└── README.md

```



\---



\## Running the Project



\### Prerequisites



Ensure you have the following installed:



\* Java Development Kit (JDK 17 or later recommended)

\* Apache Maven



\### Clone the Repository



```bash

git clone https://github.com/Keegan-van-den-berg/Year1-POE-2026.git

cd Year1-POE-2026

```



\### Compile the Project



```bash

mvn compile

```



\### Run the Application



```bash

mvn exec:java

```



Alternatively, run the `Assignment1.java` file directly from your IDE.



\---



\## Running Tests



Execute the unit tests using Maven:



```bash

mvn test

```



The tests verify the correctness of the application's functionality, including validation and messaging features.



\---



\## Learning Outcomes



This project demonstrates understanding of:



\* Classes and objects

\* Encapsulation

\* Input validation

\* Loops and conditional statements

\* File handling

\* JSON manipulation

\* Unit testing

\* Menu-driven application design

\* Basic software engineering practices



\---



\## Author



\*\*Keegan van den Berg\*\*



Bachelor of Computer and Information Sciences (Application Development)



First-Year Portfolio of Evidence (POE)



2026

Semester 1



\---



\## License



This project was developed for educational purposes as part of a university assessment.



