package com.tracker;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class TrackerFileReadComponent {
	
	@Autowired
	ResourceLoader resourceLoader;
	
	/**
	 * @return
	 * @throws IOException
	 */
	public List<TableA> readFileTableA20120407() throws IOException{
		File file = resourceLoader.getResource("classpath:TableA20120407.csv").getFile();
		return Files.lines(file.toPath(), Charset.forName("UTF-8"))
			.skip(1)
			.map(str -> {
				String[] arr = str.split(",");
				return new TableA(arr[0], arr[1], arr[2], arr[3], Long.parseLong(arr[4]), Long.parseLong(arr[5]));
			})
			.collect(Collectors.toList());
	}

	/**
	 * @return
	 * @throws IOException
	 */
	public List<TableB> readFileTableB20120409() throws IOException{
		File file = resourceLoader.getResource("classpath:TableB20120409.csv").getFile();
		return Files.lines(file.toPath(), Charset.forName("UTF-8"))
				.skip(1)
				.map(str -> {
					String[] arr = str.split(",");
					return new TableB(arr[0], arr[1], arr[2], arr[3], Long.parseLong(arr[4]), Long.parseLong(arr[5]));
				})
				.collect(Collectors.toList());
	}
	
	/**
	 * @return
	 * @throws IOException
	 */
	public List<TableC> readFileTableC() throws IOException{
		File file = resourceLoader.getResource("classpath:TableC.csv").getFile();
		return Files.lines(file.toPath(), Charset.forName("UTF-8"))
				.skip(1)
				.map(str ->{
					String[] arr = str.split(",");
					return new TableC(arr[0], Double.valueOf(arr[1]), Double.valueOf(arr[2]), arr[3], arr[4]);
				}).collect(Collectors.toList());
				
	}
}
