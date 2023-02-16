# online-perfume-store-project (on the seller side)
![](https://camo.githubusercontent.com/56b47db356cbe11ae460bf7ce94b6dc496cf337ed9d34e36ac443e88711e2eff/68747470733a2f2f7777772e706f696e746672616e63686973652e636f2e756b2f696d616765732f7a6f6f6d2f61727469636c65732f70657266756d652d6f6e6c696e652d73686f702e6a7067)

This project aims to provide a fundamental realization of different components in web applications and put into practice how we can use Servlet and JSP to create Java web applications.
App technologies used to build a project: **JDK** 11, **Apache Maven** (version 3.8.5), **Apache Tomcat** (version 9.0.65), **MySQL** for DB management (version 8.0.30), **JDBC**, **Java Servlet API**, **JSP**, **JSTL**, **HTML** and **CSS**.

Application has a **Controller** (presentation), **Service** (business logic) and **DAO** (data access) layers - three-tier architecture.

So, this is a very simple web application that requires registration and authentication process (as a seller) to access all *available functions*:

1. create a brand or/and fragrance;

2. display all created brands or/and fragrances, or/and delete them;

3. add a particular seller to a fragrance, as well as remove him;

4. logout at any time.

In order to be able to run and test its functionality, first of all, you need to install **JDK** (11 or higher), **Apache Maven**, **Apache Tomcat** (recommended version 9.0.65) and **MySQL**.

Then clone this project to your IDE:
`git clone https://github.com/marta-liushniak/online-perfume-store-project`.

Create a schema and required tables in MySQL (feel free to use SQL script: **init_db.sql** file in resources folder) and set up a connection to your DB in ConnectionUtil class.

IMPORTANT: while configuring Tomcat, please, go to the Deployment tab, select **war exploded artifact** and change the application context to */*.
