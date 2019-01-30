package shopping.repos;

import config.TestConfiguration;
import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import shopping.TestUtils;
import shopping.model.ShirtRef;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DataJpaTest
@Transactional
public class ShirtRefRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ShirtRefRepository shirtRefRepository;

    @Autowired
    SessionFactory sessionFactory;

    JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    @Ignore
    public void createShirtRef() {
        // track initial state in test database:
        final int count = countRowsInTable("shirt_ref");

        ShirtRef shirtRef = new ShirtRef("newShirt111", "XXL", "Tan Top");
        shirtRefRepository.save(shirtRef);

        // Manual flush is required to avoid false positive in test
        sessionFactory.getCurrentSession().flush();
        assertNumShirtRefs(count + 1);
    }

    protected int countRowsInTable(String tableName) {
        return JdbcTestUtils.countRowsInTable(this.jdbcTemplate, tableName);
    }

    protected void assertNumShirtRefs(int expected) {
        assertEquals("Number of rows in the [shirt_ref] table.", 
                expected, countRowsInTable("user"));
    }

    @Test
    @Ignore
    public void testFindAll() {
        ShirtRef shirtRef1 = TestUtils.getShirtRef(1);
        shirtRef1.setShirtRefId(null);

        ShirtRef shirtRef2 = TestUtils.getShirtRef(2);
        shirtRef2.setShirtRefId(null);

        entityManager.persist(shirtRef1);
        entityManager.persist(shirtRef2);

        List<ShirtRef> resultList = shirtRefRepository.findAll();

        assertThat(resultList.size(), is(2));
        List<ShirtRef> sortedList = resultList.stream()
                .sorted(Comparator.comparing(ShirtRef::getShirtRefId))
                .collect(Collectors.toList());

        assertThat(sortedList.get(0).getShirtName(), is(shirtRef1.getShirtName()));
        assertThat(sortedList.get(0).getShirtSize(), is(shirtRef1.getShirtSize()));
        assertThat(sortedList.get(0).getShirtStyle(), is(shirtRef1.getShirtStyle()));

        assertThat(sortedList.get(1).getShirtName(), is(shirtRef2.getShirtName()));
        assertThat(sortedList.get(1).getShirtSize(), is(shirtRef2.getShirtSize()));
        assertThat(sortedList.get(1).getShirtStyle(), is(shirtRef2.getShirtStyle()));
    }

}
