package cia.group6.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

public class WatcherThread extends Thread {
	
	private String path;

	public WatcherThread() {
		path = "C:\\watched folder\\";
	}

	public void run() {
		System.out.println("started watching");
		try {
			watchFolder();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("finished watching");
	}

	public void watchFolder() throws URISyntaxException, IOException,
			InterruptedException {
		Path watchedPath = Paths.get(path);
		WatchService watchService = FileSystems.getDefault().newWatchService();

		watchedPath
				.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

		for (;;) {
			WatchKey key = watchService.take();

			for (WatchEvent<?> event : key.pollEvents()) {
				if (event.kind().name() == "ENTRY_CREATE") {
					sendPost(path + event.context());;
				}
			}

			if (!valid(key)) {
				break;
			}
		}
	}

	public void sendPost(String pathAndName) {
		ClientRequest request = new ClientRequest(
				"http://localhost:8080/cd-html5-mobile/rest/persist");
		request.accept("application/json");
		request.body("application/json", pathAndName);

		try {
			ClientResponse<String> response = request.post(String.class);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public boolean valid(WatchKey key) {
		return key.reset();
	}

}
