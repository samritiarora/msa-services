package com.nagp.assgnmnt.mservices;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = TransactionApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
//@TestPropertySource(locations = {"classpath:application.properties", "classpath:bootstrap.properties"})
@EnableJpaRepositories(basePackages = "com.nagp.assgnmnt.mservices.repository")
@RunWith(SpringRunner.class)
public class TransactionApplicationTest
{
    @Test
    public void contextStarts()
    {
    }
}
