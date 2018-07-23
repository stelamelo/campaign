package com.campaign.persistence.conf;

import com.campaign.campaign.util.InputStreamUtil;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.After;
import org.junit.Before;

import java.io.InputStream;

public class PersistenceConfigurationTest {

    protected Jdbi jdbi;

    @Before
    public void initializeJdbi() {
        jdbi = Jdbi.create("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        jdbi.installPlugin(new SqlObjectPlugin());
        InputStream resourceStream = this.getClass().getResourceAsStream("/queries.sql");
        String queries = InputStreamUtil.getContentAsStringFromInputStream(resourceStream);
        String[] statements = queries.split(";");

        jdbi.withHandle(handle -> {
            int output = 0;
            for (String statement : statements) {
                output = output + handle.execute(statement);
            }

            return output;
        });
    }
}
