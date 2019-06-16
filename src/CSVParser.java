/*
A CSV parser.
 */

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;

public class CSVParser {
    private String filepath;

    public CSVParser(String filepath){
        this.filepath = filepath;
    }

    public Iterable<CSVRecord> getCSVParser(){
        Iterable<CSVRecord> records = null;
        try {
            Reader in = new FileReader(this.filepath);
            records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
        }
        catch (Exception e){
            System.out.println(e);
        }
        if (records == null){
            System.out.println("CSVParser empty");
        }
        return records;
    }
}
