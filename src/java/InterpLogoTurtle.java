import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.ANTLRStringStream;

public class InterpLogoTurtle {

	public static File loggingConfigFile = new File("logging.properties");

	public static void main(String[] args) throws Exception {
		// create a CharStream that reads from standard input
		ANTLRStringStream input;
		if (args.length > 0)
			input = new ANTLRStringStream(args[0]);
		else
			input = new ANTLRInputStream(System.in);

		// Initialize logging.
		LogManager lm = LogManager.getLogManager();

		// Use 'logging.properties' if it exists.
		if (loggingConfigFile.exists()) {
			lm.readConfiguration(new FileInputStream(loggingConfigFile));
		}
		// If it doesn't exist, it's probably the TA, so turn off logging
		// at the root logger ("").
		else {
			lm.getLogger("").setLevel(Level.OFF);
		}

		Interpreter x = new Interpreter(input);
	}
}
