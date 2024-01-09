import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Table {
    private String name;
    private String metadata;
    private Map<Integer, String> records;

    public Table(String name, String metadata) {
        this.name = name;
        this.metadata = metadata;
        this.records = new HashMap<>();
    }

    public void insertRecord(String record) {
        int recordId = records.size() + 1;
        records.put(recordId, record);
    }

    public void displayAllRecordsFromFile() {
        System.out.println("Records in table " + name + ":");
        try (BufferedReader reader = new BufferedReader(new FileReader(name + ".txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading records from the table.");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return name + " " + metadata;
    }

    public String getName() {
        return name ;
    }

    public String getMetadata() {
        return metadata ;
    }
}