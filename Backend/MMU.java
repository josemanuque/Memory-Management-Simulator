package Backend;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MMU {
	private List<Page> pages;
	private RAM ram;
	private List<Process> processes;

	public static void startSimulation(String fileName) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {
				// Utilizamos expresiones regulares para extraer las partes clave y valores
				Pattern pattern = Pattern.compile("(new|use|delete|kill)\\((\\d+)(?:,(\\d+))?\\)");
				Matcher matcher = pattern.matcher(line);

				if (matcher.find()) {
					String keyword = matcher.group(1);
					int pid = Integer.parseInt(matcher.group(2));
					int value = 0; // Inicializamos value a 0

					if (matcher.group(3) != null) {
						value = Integer.parseInt(matcher.group(3));
					}

					switch (keyword) {
						case "new":
							// Procesar una operación "new"
							System.out.println("Operación 'new', pid: " + pid + ", value: " + value);
							break;
						case "use":
							// Procesar una operación "use"
							System.out.println("Operación 'use', pid: " + pid);
							break;
						case "delete":
							// Procesar una operación "delete"
							System.out.println("Operación 'delete', pid: " + pid);
							break;
						case "kill":
							// Procesar una operación "kill"
							System.out.println("Operación 'kill', pid: " + pid);
							break;
						default:
							// La línea no corresponde a una operación conocida
							System.out.println("Línea no reconocida: " + line);
					}
				} else {
					// La línea no tiene el formato esperado
					System.out.println("Línea mal formateada: " + line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param processID
	 * @param size
	 */
	public int newProcess(int processID, int size) {
		// TODO - implement MMU.newProcess
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ptr
	 */
	public void use(int ptr) {
		// TODO - implement MMU.use
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ptr
	 */
	public void delete(int ptr) {
		// TODO - implement MMU.delete
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pid
	 */
	public void kill(int pid) {
		// TODO - implement MMU.kill
		throw new UnsupportedOperationException();
	}

}