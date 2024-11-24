package logging;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class LogAggregator {
    /**
     * You are tasked with monitoring network traffic to ensure optimal performance and identify potential bottlenecks
     * in a streaming service environment like Netflix. The goal is to analyze incoming and outgoing traffic for a set
     * of servers and determine if any server is experiencing unusually high traffic.
     *
     * Given a log file containing records of network traffic, write a program that:
     * 1) Reads the log file.
     * 2) Aggregates the total bytes transferred for each server over a specified time window (e.g., the last hour).
     * 3) Identifies servers that exceed a defined traffic threshold (e.g., 10 GB) during that time window.
     *
     * Outputs the server IDs that exceed the threshold along with the total bytes transferred.
     *
     * Each record has the following format: <timestamp> <server_id> <bytes_transferred>
     * The program should be able to handle a large number of log entries efficiently.
     *
     * Implement unit tests to validate the functionality.
     */

    private static final ConcurrentHashMap<String, Integer> serverToBytesMap = new ConcurrentHashMap<>();
    private static final HashSet<String> uncompliantServersSet = new HashSet<>();

    private static final double bytesTransferCap = 8e6;
    /**
     * int: timeWindow in hours
     * @throws IOException
     */
    public void aggregate(int timeWindow) throws IOException {
        // Add the streaming_service_log.txt file to repo
        // File generated using BingAI. Generate larger file if desired
        File file = new File("src/main/java/logging/streaming_service_log.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        int maxBytesTransferred = 0;
        int numServersExceedingLimit = 0;
        while ((line = br.readLine()) != null) {
            String[] lines = line.split(" ");

            String server = lines[2];
            int bytes = Integer.parseInt(lines[3]);

            serverToBytesMap.putIfAbsent(server, 0);
            serverToBytesMap.put(server, serverToBytesMap.get(server) + bytes);

            if (serverToBytesMap.get(server) > bytesTransferCap) {
                uncompliantServersSet.add(server);
            }

            // Keep track of the maximum bytes transferred by any server
            if (serverToBytesMap.get(server) > maxBytesTransferred) {
                maxBytesTransferred = serverToBytesMap.get(server);
            }

        }

        System.out.printf("Total number of unique servers = %d\n", serverToBytesMap.size());
        System.out.printf("Number of uncompliant servers (who exceeded byte-transfer limit) =  %d\n", uncompliantServersSet.size());
        System.out.printf("Max bytes by the most uncompliant server = %d\n", maxBytesTransferred);
    }

}
