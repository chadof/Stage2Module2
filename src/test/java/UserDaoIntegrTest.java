import dao.UserDaoImpl;
import entities.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Testcontainers
public class UserDaoIntegrTest {

    UserDaoImpl test = new UserDaoImpl();

    @Container
    static PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("spring.datasource.url")
            .withUsername("spring.datasource.username")
            .withPassword("spring.datasource.password");

    @BeforeAll
    public static void beforeAll() {
        database.start();
    }
    @BeforeEach
    public void setUp() {
        test.deleteAll();
    }
    @AfterAll
    public static void afterAll() {
        database.stop();
    }

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", database::getJdbcUrl);
        registry.add("spring.datasource.username", database::getUsername);
        registry.add("spring.datasource.password", database::getPassword);
    }
    @Test
    public void userInsertTest(){

        User user1 = new User("Иван","Merenkov@asd.ru",33);
        User user2 = new User("Ангелина","asdasd@asd.ru",42);
        User user3 = new User("Дмитрий","asfqweqw@asd.ru",34);

        test.save(user1);
        test.save(user2);
        test.save(user3);

        assertThat((test.findAll().size())==3);

        test.deleteAll();
    }
    @Test
    public void userDeleteTest(){

        User user1 = new User("Иван","Merenkov@asd.ru",33);
        User user2 = new User("Ангелина","asdasd@asd.ru",42);
        User user3 = new User("Дмитрий","asfqweqw@asd.ru",34);

        test.save(user1);
        test.save(user2);
        test.save(user3);
        test.delete(user1);

        assertThat((test.findAll().size())==2);

        test.deleteAll();
    }
    @Test
    public void userUpdateTest(){

        User user1 = new User("Иван","Merenkov@asd.ru",33);

        test.save(user1);
        user1.setName("Ангелина");
        test.update(user1);

        Assert.assertEquals("Ангелина",(test.findById(user1.getId())).getName());

        test.deleteAll();
    }
}