package shopping.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import shopping.dao.IBaseDAO;
import shopping.model.ShirtRef;
import shopping.repos.ShirtRefRepository;
import shopping.util.BadRequestException;
import shopping.util.ResourceNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static shopping.TestUtils.getShirtRef;

@RunWith(MockitoJUnitRunner.class)
public class ShirtRefServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock private IBaseDAO baseDAO;

    @Mock private ShirtRefRepository shirtRefRepository;

    @InjectMocks
    private ShirtRefService shirtRefService;


    @Test
    public void testGetAllShirtRefs() throws Exception {
        List<ShirtRef> shirtRefs = Arrays.asList(getShirtRef(1), getShirtRef(2), getShirtRef(3));
        when(shirtRefRepository.findAll()).thenReturn(shirtRefs);

        assertThat("Return of all shirtRef should not be empty",
                shirtRefService.getAllShirtRefs(), is(not(nullValue())));

        verify(shirtRefRepository).findAll();
    }

    @Test
    public void testGetShirtRefById() throws Exception {
        when(shirtRefRepository.findByShirtRefId(anyInt())).thenReturn(Optional.of(getShirtRef(1)));

        assertThat("Return of all shirtRef should not be empty",
                shirtRefService.getShirtRefById(1), is(not(nullValue())));

        verify(shirtRefRepository).findByShirtRefId(anyInt());
    }

    @Test
    public void testGetShirtRefById_404ExceptionExpected_IdNotFound() throws Exception {
        int id = 1;
        when(shirtRefRepository.findByShirtRefId(anyInt())).thenReturn(Optional.empty());

        expectedException.expect(ResourceNotFoundException.class);
        expectedException.expectMessage("Shirt Ref at ID <" + id + "> not found in database.");

        shirtRefService.getShirtRefById(id);
    }

    @Test
    public void testSaveShirtRef() throws Exception {
        ShirtRef shirtRef = getShirtRef(null);
        when(shirtRefRepository.save(any(ShirtRef.class)))
                .thenReturn(shirtRef);

        assertThat("Return of all shirtRef should not be empty",
                shirtRefService.saveShirtRef(shirtRef), is(not(nullValue())));

        verify(shirtRefRepository).save(any(ShirtRef.class));
    }

    @Test
    public void testUpdateShirtRef() throws Exception {
        ShirtRef shirtRef = getShirtRef(1);

        when(shirtRefRepository.findByShirtRefId(anyInt())).thenReturn(Optional.of(shirtRef));
        when(shirtRefRepository.findByShirtName(anyString())).thenReturn(Optional.empty());
        when(shirtRefRepository.save(any(ShirtRef.class))).thenReturn(shirtRef);

        assertThat("Return of all shirtRef should not be empty",
                shirtRefService.updateShirtRef(shirtRef), is(not(nullValue())));

        verify(shirtRefRepository).save(any(ShirtRef.class));
    }

    @Test
    public void testUpdateShirtRef_404ExceptionExpected_IdNotFound() throws Exception {
        int id = 1;
        ShirtRef shirtRef = getShirtRef(id);

        when(shirtRefRepository.findByShirtRefId(anyInt())).thenReturn(Optional.empty());

        expectedException.expect(ResourceNotFoundException.class);
        expectedException.expectMessage("Shirt Ref at ID <" + id + "> not found in database.");

        shirtRefService.updateShirtRef(shirtRef);
    }

    @Test
    public void testUpdateShirtRef_400ExceptionExpected_ShirtNameAlreadyExists() throws Exception {
        int id = 1;
        ShirtRef shirtRef1 = getShirtRef(id);
        ShirtRef shirtRef2 = getShirtRef(2);

        when(shirtRefRepository.findByShirtRefId(anyInt())).thenReturn(Optional.of(shirtRef1));
        when(shirtRefRepository.findByShirtName(anyString())).thenReturn(Optional.of(shirtRef2));

        expectedException.expect(BadRequestException.class);
        expectedException.expectMessage("There is a different Shirt Ref that already exists with name " +
                "[" + shirtRef2.getShirtName() + "]");

        shirtRefService.updateShirtRef(shirtRef2);
    }

    @Test
    public void testDeleteShirtRef() throws Exception {
        ShirtRef shirtRef = getShirtRef(1);

        when(shirtRefRepository.findByShirtRefIdAndAndShirtName(anyInt(), anyString()))
                .thenReturn(Optional.of(shirtRef));
        doNothing().when(baseDAO).delete(any(ShirtRef.class));

        assertThat("Return of all shirtRef should not be empty",
                shirtRefService.deleteShirtRef(shirtRef.getShirtRefId(), shirtRef.getShirtName()), is(not(nullValue())));

        verify(baseDAO).delete(any(ShirtRef.class));
    }

    @Test
    public void testDeleteShirtRef_400ExceptionExpected_IdNotFound() throws Exception {
        ShirtRef shirtRef1 = getShirtRef(1);
        ShirtRef shirtRef2 = getShirtRef(2);

        when(shirtRefRepository.findByShirtRefIdAndAndShirtName(anyInt(), anyString()))
                .thenReturn(Optional.empty());

        expectedException.expect(BadRequestException.class);
        expectedException.expectMessage("Search for shirt ref under " +
                "ID [" + shirtRef1.getShirtRefId() + "] and name [" + shirtRef2.getShirtName() +
                "] not found in database.");

        shirtRefService.deleteShirtRef(shirtRef1.getShirtRefId(), shirtRef2.getShirtName());
    }
}
