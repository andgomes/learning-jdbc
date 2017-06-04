package org.andgomes.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.andgomes.model.Interviewed;

public class InterviewReader {

	public static List<Interviewed> readInterviews(File file)
			throws IOException {
		
		FileReader fileReader = new FileReader(file);
		
		try (BufferedReader br = new BufferedReader(fileReader)) {
			
			List<Interviewed> intervieweds = new LinkedList<>();
			
			String line;
			String[] lineComponents;
		
			while ((line = br.readLine()) != null) {
		
				lineComponents = line.split("\\s+");
			
				Interviewed interviewed = new Interviewed();
			
				interviewed.setName(lineComponents[0]);
				interviewed.setFirstGrade(
						Double.parseDouble(lineComponents[1]));
				interviewed.setSecondGrade(
						Double.parseDouble(lineComponents[2]));
				interviewed.setId(Integer.parseInt(lineComponents[3]));
				
				intervieweds.add(interviewed);
		
			}
		
			return intervieweds;
		
		}
	
	}		

}
