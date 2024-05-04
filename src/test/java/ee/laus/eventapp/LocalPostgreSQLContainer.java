package ee.laus.eventapp;

import org.testcontainers.containers.PostgreSQLContainer;

public class LocalPostgreSQLContainer extends PostgreSQLContainer<LocalPostgreSQLContainer> {
    public static final String IMAGE = "postgres";
    public static LocalPostgreSQLContainer container;

    private LocalPostgreSQLContainer() {
        super(IMAGE);
    }

    public static LocalPostgreSQLContainer getInstance() {
        if (container == null) {
            container = new LocalPostgreSQLContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("TEST_DB_URL", container.getJdbcUrl());
        System.setProperty("TEST_DB_USERNAME", container.getUsername());
        System.setProperty("TEST_DB_PASSWORD", container.getPassword());
    }
}
