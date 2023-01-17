# online-perfume-store-project
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

Create a schema and required tables in MySQL (feel free to use SQL script: **init_db.sql** file in resources folder).

Set up a connection to your DB in ConnectionUtil class:
![](https://scontent.fifo4-1.fna.fbcdn.net/v/t1.15752-9/323578816_1213046629306362_2215155386858367049_n.png?_nc_cat=103&ccb=1-7&_nc_sid=ae9488&_nc_ohc=Br1RdXfD-pAAX_YzaUV&_nc_ht=scontent.fifo4-1.fna&oh=03_AdTHX6QAvQc8v_LiBw9f0qenNlMNLJwzQ_vfLXn8i_aL1w&oe=63EE17BA)

IMPORTANT: while configuring Tomcat, please, go to the Deployment tab, select **war exploded artifact** and change the application context to */* as follows:
![](https://scontent.fifo4-1.fna.fbcdn.net/v/t1.15752-9/323008351_554805376572493_2863545099693500338_n.png?_nc_cat=105&ccb=1-7&_nc_sid=ae9488&_nc_ohc=SOpSAKee-sMAX_yAJQZ&_nc_ht=scontent.fifo4-1.fna&oh=03_AdTtiv-SrCudLgq2X2jqH57ZF4wMFZvCGgeP4Gca4D20Qg&oe=63EE11E2)

DB diagram:

![](https://scontent.fifo4-1.fna.fbcdn.net/v/t1.15752-9/324541562_1562498364256003_1109880123247903629_n.png?_nc_cat=102&ccb=1-7&_nc_sid=ae9488&_nc_ohc=LDdpY4--iz0AX-PBL7p&_nc_ht=scontent.fifo4-1.fna&oh=03_AdS_H0uM7zpUkDUmlX21wmv8kULAzvF6c14VontuHGp0CQ&oe=63EE2D65)
