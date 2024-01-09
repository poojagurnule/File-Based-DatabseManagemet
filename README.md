<h1 align = "center"> Simple File-Based Database Management System </h1>
<p align="center">
<a href="Java url">
    <img alt="Java" src="https://img.shields.io/badge/Java-darkblue.svg" />
</a>

</p>

This project is a simple file-based database management system written in Java. It allows users to create tables, insert records, and display records from tables through a basic command-line interface.

## Technical Stack Used
- JAVA 17
- IntelliJ IDEA

## Getting Started
1. Clone the repository or download the source code files.
2. Open the project in your preferred Java development environment.
3. Compile and run the DatabaseManager.java file to start the program.
## Features
Table Creation: Users can create tables by specifying the table name and column metadata.

Record Insertion: Users can insert records into existing tables.

Record Display: Users can display all records from a table.

## Example Commands
Here are some example commands you can try:

- ### Table Creation:
```
CREATE TABLE my_table (col1 INTEGER, col2 STRING)
```
  
- ### Record Insertion:
```
INSERT INTO my_table VALUES (456, 'Java Programming')
```

- ### Record Display:
```
SELECT * FROM <table_name>
```
  
- ### Terminate the program:
```
EXIT
```

## File Structure
The program uses two files:
metadata.txt: Stores the metadata of the tables created.<br>
<table_name>.txt: Stores the records inserted into each table.<br>
Make sure both files are present in the same directory as the Java source file.

 ## Here is Output:


 ![image](https://github.com/poojagurnule/File-Based-DatabseManagemet/assets/102051371/443ae152-ca3f-4e71-a70c-26cfac64c085)

 Thank You .....
