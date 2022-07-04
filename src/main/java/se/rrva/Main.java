package se.rrva;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    public static void main(String[] args) throws Exception {
        Logger logger = LoggerFactory.getLogger(Main.class);
        int port = args.length > 0 ? Integer.parseInt(args[0]) : Integer.parseInt( System.getProperty("port", "8080") );
        EventServer eventServer = new EventServer();

        eventServer.setPort(port);
        eventServer.start();

        logger.info("Listening on {}...", port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Server shutting down. Goodbye...");
            try {
                eventServer.stop();
            }
            catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }));

    }

}