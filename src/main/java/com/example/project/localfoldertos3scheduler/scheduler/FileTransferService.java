package com.example.project.localfoldertos3scheduler.scheduler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;

@Service
public class FileTransferService {

	@Value("${copy.from.local.file.path}")
	private String copyFrom;

	@Value("${copy.to.local.file.path}")
	private String copyTo;

	@Value("${amazon.aws.bucket.name}")
	private String bucketName;

	@Autowired
	private AmazonS3 amazonS3;

	@Scheduled(cron = "0 * * ? * *")
	public void everyMinCalling() {
		File folder = new File(copyFrom);
		for (File file : folder.listFiles()) {
			amazonS3.putObject(bucketName, file.getName(), file);
			System.out.println(file.getName() + " -- Uploaded");
			try {
				Files.move(Paths.get(file.getAbsolutePath()), Paths.get(copyTo + "/" + file.getName()));
				System.out.println(file.getName() + " -- Moved");
			} catch (IOException e) {
			}
		}
	}
}
