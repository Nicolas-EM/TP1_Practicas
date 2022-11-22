package es.ucm.tp1.control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import es.ucm.tp1.logic.exceptions.InputOutputRecordException;
import es.ucm.tp1.utils.StringUtils;

public class Record {
	private final String filename = "record.txt";
	private Level level;
	private Long currentRecord;
	private final String NEW_RECORD_MSG = "Creating default record for level";
	
	public static Record load(Level level) {
		return new Record(level);
	}
	
	private Record(Level level) {
		try(BufferedReader inStream = new BufferedReader(new FileReader(this.filename))) {
			this.level = level;
			
			String line = inStream.readLine();
			while (line != null) {
				if(level.toString().equals(line.split(":")[0])) this.currentRecord = Long.parseLong(line.split(":")[1]);
				line = inStream.readLine();
			}
			
			if(this.currentRecord == null) {
				System.out.println(String.format("%s '%s'", NEW_RECORD_MSG, this.level));
				this.currentRecord = Long.MAX_VALUE;
			}
		}
		catch (IOException ioe) { System.out.println("An error ocurred on reading a file"); }
	}
	
	public void save() throws InputOutputRecordException {
		try(BufferedReader inStream = new BufferedReader(new FileReader(this.filename))) {
			StringBuilder str = new StringBuilder();
			
			String line = inStream.readLine();
			while (line != null) {
				if(line.split(":")[0] != level.toString()) str.append(line + StringUtils.LINE_SEPARATOR);
				line = inStream.readLine();
			}
			
			str.append(this.level + ":" + this.currentRecord);
		}
		catch (IOException ioe) { throw new InputOutputRecordException("An error ocurred on reading a file"); }
	}
	
	public long getRecord() {
		return this.currentRecord;
	}

	public void updateRecord(long newRecord) {
		this.currentRecord = newRecord;
	}
}
