package tbd;

import java.util.ArrayList;
import java.util.List;

public class CSVParser {

	public List<String> parseCSVLine(String line) {
		List<String> fields = new ArrayList<>();

		// Handle empty line edge case
		if (line == null || line.isEmpty()) {
			return fields;
		}

		StringBuilder currentField = new StringBuilder();
		boolean inQuotes = false;
		int i = 0;

		while (i < line.length()) {
			char ch = line.charAt(i);

			if (ch == '"') {
				// Handle quote character based on current state
				int[] result = handleQuoteCharacter(line, i, currentField, inQuotes);
				i = result[0];
				inQuotes = result[1] == 1;
			} else if (ch == ',' && !inQuotes) {
				// Comma outside quotes means end of field
				fields.add(currentField.toString());
				currentField = new StringBuilder();
				i++;
			} else {
				// Regular character - add to current field
				currentField.append(ch);
				i++;
			}
		}

		// Don't forget to add the last field after the loop ends
		// This handles the case where the line doesn't end with a comma
		fields.add(currentField.toString());

		return fields;
	}

	private int[] handleQuoteCharacter(
			String line, int position, StringBuilder field, boolean inQuotes) {
		// If we're at the start of a new field, this begins a quoted field
		if (!inQuotes && field.isEmpty()) {
			return new int[] {position + 1, 1}; // Move position, set inQuotes to true
		}

		// If we're inside quotes, check for escaped quote
		if (inQuotes) {
			// Check if this is an escaped quote (double quotes "")
			if (position + 1 < line.length() && line.charAt(position + 1) == '"') {
				field.append('"');
				return new int[] {position + 2, 1}; // Skip both quotes, stay in quotes
			}
			// Otherwise, this quote ends the quoted field
			return new int[] {position + 1, 0}; // Move position, set inQuotes to false
		}

		// Quote in the middle of unquoted field - treat as regular character
		field.append('"');
		return new int[] {position + 1, 0}; // Move position, stay out of quotes
	}

	// Overloaded method to parse multiple lines
	public List<List<String>> parseCSV(String[] lines) {
		List<List<String>> result = new ArrayList<>();

		for (String line : lines) {
			result.add(parseCSVLine(line));
		}

		return result;
	}
}
