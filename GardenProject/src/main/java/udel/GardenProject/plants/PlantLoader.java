package udel.GardenProject.plants;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Load all the plants from local file databases.
 * 
 * @author Team 0
 */
public class PlantLoader {

	ArrayList<String> plants = new ArrayList<>();

	public static List<String> readFile(String filename) {
		List<String> records = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = reader.readLine()) != null) {
				line = line.substring(1, line.length() - 1);
				records.add(line);
			}
			reader.close();
		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();
		}
		return records;

	}

	public static void main(String[] args) throws IOException {
		Path startingDir = Paths.get(System.getProperty("user.dir"));
		String pattern = "*.json";
		Finder finder = new Finder(pattern);
		Files.walkFileTree(startingDir, finder);
		finder.done();
		Path a = null;
		for (Path p : finder.files) {
			List<String> notShort = readFile(p.toString());
			ArrayList<String> plants = new ArrayList<>();
			for (String s : notShort) {
				String[] words = s.split("}");
				plants.addAll(Arrays.asList(words));
			}
			for (String c : plants) {
				c = c.substring(2);
				System.out.println(c);
			}
		}
	}

	static class Finder extends SimpleFileVisitor<Path> {

		private final PathMatcher matcher;
		private int numMatches = 0;
		private LinkedList<Path> files = new LinkedList<>();

		Finder(String pattern) {
			matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
		}

		public void toString(Path p) {

		}

		// Compares the glob pattern against
		// the file or directory name.
		void find(Path file) {
			Path name = file.getFileName();
			if (name != null && matcher.matches(name)) {
				files.add(file);
				numMatches++;
				// System.out.println(file);
			}
		}

		// Prints the total number of
		// matches to standard out.
		void done() {
			// System.out.println("Matched: " + numMatches);
		}

		// Invoke the pattern matching
		// method on each file.
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
			find(file);
			return CONTINUE;
		}

		// Invoke the pattern matching
		// method on each directory.
		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
			find(dir);
			return CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc) {
			System.err.println(exc);
			return CONTINUE;
		}
	}

	static void usage() {
		System.err.println("java Find <path>" + " -name \"<glob_pattern>\"");
		System.exit(-1);
	}
}
