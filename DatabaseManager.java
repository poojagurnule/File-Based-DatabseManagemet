import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DatabaseManager {
    private static final String METADATA_FILE = "metadata.txt";

    private Map<String, Table> tables;

    public DatabaseManager() {
        this.tables = new HashMap<>();
    }

    public void start() {
        System.out.println("You can use the following commands only:");
        System.out.println("CREATE TABLE <table_name> (col1 <datatype>, col2 <datatype>,...) -- to create a table");
        System.out.println("INSERT INTO <table_name> VALUES (val1, val2, ...) -- to insert values in a table");
        System.out.println("SELECT * FROM <table_name> -- to display all records from a table");
        System.out.println("EXIT or QUIT -- to terminate the program");

        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("EXIT") || command.equalsIgnoreCase("QUIT")) {
                flag = false;
                System.out.println("The program has been terminated.");
            } else {
                executeCommand(command);
            }
        }
    }

    private void executeCommand(String command) {
        String[] tokens = command.split("\\s+");
        String keyword = tokens[0];

        switch (keyword.toUpperCase()) {
            case "CREATE" -> createTable(tokens);
            case "INSERT" -> insertIntoTable(tokens);
            case "SELECT" -> selectFromTable(tokens);
            default -> System.out.println("Invalid command.");
        }
    }

    private void createTable(String[] tokens) {
        String tableName = tokens[2];
        StringBuilder metadataBuilder = new StringBuilder();

        for (int i = 3; i < tokens.length; i++) {
            String column = tokens[i].replace(",", "");
            metadataBuilder.append(column).append(" ");
        }

        Table table = new Table(tableName, metadataBuilder.toString().trim());
        tables.put(tableName, table);

        // Update metadata separately
        updateMetadataFile(table);
    }

    private void updateMetadataFile(Table table) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(METADATA_FILE, true))) {
            if (!new File(METADATA_FILE).exists()) {
                writer.println("Table Name Metadata");
            }
            writer.println(table.toString());
            System.out.println("Table created: " + table.getName());
        } catch (IOException e) {
            System.out.println("An error occurred while updating the metadata file.");
            e.printStackTrace();
        }
    }

    private void insertIntoTable(String[] tokens) {
        String tableName = tokens[2];
        String values = extractValues(tokens);

        Table table = tables.get(tableName);

        if (table != null) {
            try {
                String formattedValues = validateAndFormatValues(values, table);
                table.insertRecord(formattedValues);

                // Write the record to the table file
                try (PrintWriter writer = new PrintWriter(new FileWriter(tableName + ".txt", true))) {
                    writer.println(formattedValues);
                    System.out.println("Inserted into table: " + tableName);
                } catch (IOException e) {
                    System.out.println("An error occurred while inserting into the table.");
                    e.printStackTrace();
                }
            } catch (ValidationException e) {
                System.out.println("Validation error: " + e.getMessage());
            }
        } else {
            System.out.println(tableName + " is not created");
        }
    }

    private String extractValues(String[] tokens) {
        StringBuilder valuesBuilder = new StringBuilder();

        // Start from index 3 to skip the first three tokens
        for (int i = 3; i < tokens.length; i++) {
            valuesBuilder.append(tokens[i]).append(" ");
        }

        return valuesBuilder.toString().trim();
    }

    private String validateAndFormatValues(String values, Table table) throws ValidationException {
        return values.trim();
    }


    private void selectFromTable(String[] tokens) {
        String tableName = tokens[3];
        Table table = tables.get(tableName);

        if (table != null) {
            table.displayAllRecordsFromFile();
        } else {
            System.out.println(tableName + " is not created");
        }
    }

    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.start();
    }
}

