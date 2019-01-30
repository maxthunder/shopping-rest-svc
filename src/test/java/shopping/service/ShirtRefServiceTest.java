package shopping.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import shopping.TestUtils;
import shopping.dao.IBaseDAO;
import shopping.model.ShirtRef;
import shopping.repos.ShirtRefRepository;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static shopping.TestUtils.getShirtRef;

@RunWith(MockitoJUnitRunner.class)
public class ShirtRefServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock private ShirtRefRepository shirtRefRepository;

    @InjectMocks
    private ShirtRefService shirtRefService;


    @Test
    public void getAllShirtRefs() throws Exception {
        List<ShirtRef> shirtRefs = Arrays.asList(getShirtRef(1), getShirtRef(2), getShirtRef(3));
        when(shirtRefRepository.findAll()).thenReturn(shirtRefs);

        assertThat("Return of all shirtRef should not be empty (technically exception will get thrown)",
                shirtRefService.getAllShirtRefs(), is(not(nullValue())));

        verify(shirtRefRepository).findAll();
    }
}
