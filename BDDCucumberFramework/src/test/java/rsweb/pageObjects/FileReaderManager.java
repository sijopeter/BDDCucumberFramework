package rsweb.pageObjects;

public class FileReaderManager {
    private static FileReaderManager fileReaderManager = new FileReaderManager();
    private static PropertyReader configFileReader;

    private FileReaderManager() {
    }

    public static FileReaderManager getInstance( ) {
        return fileReaderManager;
    }

    public PropertyReader getConfigReader() {
        return (configFileReader == null) ? new PropertyReader() : configFileReader;
    }
}
