package com.tracker;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;

@SpringBootApplication
public class TrackerApplication{
	
	private static final String COMMA = ",";

	@Value("${tableAFile.path}")
	private String tableAFile;
	
	@Value("${tableBFile.path}")
	private String tableBFile;
	
	@Value("${tableCFile.path}")
	private String tableCFile;
	
	@Autowired
	ResourceLoader resourceLoader;

	public static void main(String[] args) {
		SpringApplication.run(TrackerApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runApplication(TrackerRepository trackerRepository){
		return args ->{
			File file;
			System.out.println("======================================Table A======================================");
			file = resourceLoader.getResource(tableAFile).getFile();
			List<TableA> tableA = Files.lines(file.toPath(), Charset.forName("UTF-8"))
					.skip(1)
					.map(str -> {
						String[] arr = str.split(COMMA);
						return  new TableA(arr[0], arr[1], arr[2], arr[3], Long.parseLong(arr[4]), Long.parseLong(arr[5])) ;
					}).collect(Collectors.toList());
			//Insert into table_a
			trackerRepository.insertTableA(tableA);
			
			System.out.println("======================================Table B======================================");
			file = resourceLoader.getResource(tableBFile).getFile();
			List<TableB> tableB = Files.lines(file.toPath(), Charset.forName("UTF-8"))
					.skip(1)
					.map(str -> {
						String[] arr = str.split(COMMA);
						return  new TableB(arr[0], arr[1], arr[2], arr[3], Long.parseLong(arr[4]), Long.parseLong(arr[5])) ;
					}).collect(Collectors.toList());
			//Insert into table_b
			trackerRepository.insertTableB(tableB);
			
			System.out.println("======================================Table C======================================");
			file = resourceLoader.getResource(tableCFile).getFile();
			List<TableC> tableC = Files.lines(file.toPath(), Charset.forName("UTF-8"))
			.skip(1)
			.map(str ->{
				String[] arr = str.split(COMMA);
				return new TableC(arr[0], arr[3], arr[4], Double.valueOf(arr[1]), Double.valueOf(arr[2])); 
			//arr[0], arr[3], arr[4], Double.valueOf(arr[1]), Double.valueOf(arr[2])
			}).collect(Collectors.toList());
			//Insert into table_c
			trackerRepository.insertTableC(tableC);
		};
	}
	
	
	@Data @NoArgsConstructor @AllArgsConstructor
	class TableA {
		String userIp, userMac, apName, apMac;
		Long startTime, endTime;
	}
	@Data @NoArgsConstructor @AllArgsConstructor
	class TableB {
		String userIp, userMac, apName, apMac;
		Long startTime, endTime;
	}
	@Data @NoArgsConstructor @AllArgsConstructor
	class TableC {
		String prefix, name, category;
		Double lat, lon;
	}
}
