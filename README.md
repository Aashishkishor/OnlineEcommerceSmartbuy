# OnlineEcommerceSmartbuy

OnlineEcommerceSmartbuy is a desktop-based Java Project that allows users (Admin) to log in, view products, filter by categories, search items, and manage inventory visually. This project demonstrates Object-Oriented Programming (OOP), Swing UI development, event handling, and modular Java project design and also uses the DBMS concepts in details.

The application allows the Admin to log in, search products, filter categories, view stock, and interact with a visually rich UI. It also demonstrates full OOP concepts, Collections, JDBC database connectivity, multithreading, and exception handling in details.

## 🔐 Login Credentials

**Admin Login:**
- **Username:** admin
- **Password:** admin123

*Note: These credentials are configured in the database. You can modify them by updating the admin table in your database.*

# 📌 Project Overview

This project showcases how an OnlineEcommerceSmartbuy works internally using core Java.

It includes:

- A Login Module
- A Product Dashboard
- Search and Category Filtering
- Product Listing in Dynamic Panels
- Database Connectivity via JDBC
- Object-Oriented Design using Java Classes

# ✨ Features

- **OOP Architecture/Concepts** — Polymorphism, inheritance, interfaces, and exception handling for modular code.
- **MVC Design Pattern** — Separation of concerns for maintainability and scalability.
- **Dynamic Product Catalog** — Category filtering, searching, and real-time stock updates.
- **Admin Authentication** — Secure login with database verification.
- **Database Connectivity** — Full CRUD functionality through JDBC (MySQL/Oracle).
- **Responsive UI** — Java Swing GUI with multi-threaded updates.
- **Cart Simulation** — Add-to-cart demonstration for administrative users.
- **Code Quality** — Well-documented with Javadoc comments and modular design.

# 🏗️ Technologies Used

**Module Technology**

- **Programming Language:** Java (JDK 17+)
- **GUI Framework:** Java Swing
- **Database:** MySQL or SQLite
- **Connectivity:** JDBC
- **IDE:** IntelliJ IDEA/Vs code
- **OOP Concepts:** Fully implemented

# 🚀 How to Run the Project

✅ **Step 1** — Install Required Software
Make sure these two things are installed on your computer:
- (a) JDK 17 or newer (Java Development Kit — required to run Java programs)
- (b) IntelliJ IDEA Community Edition (Free and best for Java projects).

✅ **Step 2** — Configure SDK
Go to File → Project Structure → Project Choose JDK 17 Click Apply → OK

✅ **Step 3** — Open the Project in IntelliJ/Vs code
Open IntelliJ IDEA Click File → Open Select your OnlineEcommerceSmartbuy folder Wait for IntelliJ to load all files Run the Application Click the green Run ▶️ button at the top-right

# 🚀 Clone the repository

```bash
git clone https://github.com/Aashishkishor/OnlineEcommerceSmartbuy.git
```

👉 Compile the project
```bash
javac -d out src/**/*.java
```

👉 Run the application
```bash
java -cp out Main
```

# 📁 Project Structure

```
OnlineEcommerceSmartbuy/
│
├── src/                                    # Source code directory
│   ├── com/
│   │   └── ecommerce/
│   │       ├── main/
│   │       │   └── Main.java              # Main entry point of application
│   │       │
│   │       ├── model/                      # Data Models (Entity Classes)
│   │       │   ├── Product.java           # Product entity
│   │       │   ├── Category.java          # Category entity
│   │       │   ├── Admin.java             # Admin user entity
│   │       │   └── Cart.java              # Shopping cart entity
│   │       │
│   │       ├── view/                       # UI Components (Swing Views)
│   │       │   ├── LoginFrame.java        # Login screen
│   │       │   ├── DashboardFrame.java    # Main dashboard
│   │       │   ├── ProductPanel.java      # Product display panel
│   │       │   └── CategoryPanel.java     # Category filter panel
│   │       │
│   │       ├── controller/                 # Business Logic Controllers
│   │       │   ├── LoginController.java   # Handles login authentication
│   │       │   ├── ProductController.java # Manages product operations
│   │       │   └── CartController.java    # Handles cart operations
│   │       │
│   │       ├── dao/                        # Database Access Objects
│   │       │   ├── DatabaseConnection.java # JDBC connection manager
│   │       │   ├── ProductDAO.java        # Product database operations
│   │       │   ├── CategoryDAO.java       # Category database operations
│   │       │   └── AdminDAO.java          # Admin database operations
│   │       │
│   │       └── util/                       # Utility Classes
│   │           ├── Validator.java         # Input validation
│   │           └── Constants.java         # Application constants
│   │
│   └── resources/                          # Resource files
│       ├── images/                         # Product images
│       └── config.properties               # Configuration file
│
├── lib/                                    # External libraries
│   └── mysql-connector-java.jar           # MySQL JDBC driver
│
├── database/                               # Database scripts
│   ├── schema.sql                         # Database schema
│   └── sample_data.sql                    # Sample data for testing
│
├── out/                                    # Compiled .class files (build output)
│
├── docs/                                   # Documentation
│   └── javadoc/                           # Generated Javadoc
│
├── .idea/                                  # IntelliJ IDEA configuration
├── OnlineEcommerceSmartbuy.iml            # IntelliJ project file
└── README.md                               # Project documentation (this file)
```

# 🎮 Application Features

👤 **Add Product** - Name, category, quantity, price
- Validation + custom exceptions

🔧 **Update Product** - Modify stock, price, or details

❌ **Delete Product** - Safe removal with confirmation

🔍 **Search Product** - Search by name, ID, category
- Uses Collection filters internally

📊 **View All Products** - Table UI with live updates

# 🧠 Learning Outcomes

We will learn:

- Java OOP fundamentals (Inheritance, Polymorphism, Encapsulation)
- JDBC Database Connectivity
- MVC architecture
- GUI development using Swing
- Multithreading and synchronization
- Collections & Generics
- Writing clean, modular, production-style Java code

# 🎯 Future Enhancements

- User-facing storefront and registration
- Order management and checkout
- Product images and multimedia features
- Enhanced admin analytics

# 📄 License

Open source for educational and academic Project use.

# 👤 Author

**[Aashish kishor]** [https://github.com/Aashishkishor]

Project: OnlineEcommerceSmartbuy

# 🙏 Acknowledgments

Developed for [Java Programming(R1UC304C)], [Galgotias University/CSE Department]. Special thanks to faculty and Java learning communities.
