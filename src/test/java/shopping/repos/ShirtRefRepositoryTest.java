package shopping.repos;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import shopping.TestUtils;
import shopping.model.ShirtRef;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ShirtRefRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ShirtRefRepository shirtRefRepository;

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
