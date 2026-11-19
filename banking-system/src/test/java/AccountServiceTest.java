
import org.example.Repository.AccountDAO;
import org.example.model.Account;
import org.example.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {


    @Mock
    private AccountDAO accountDAO;

    @Mock
    private Scanner scanner;

    @InjectMocks
    private AccountService accountService;

    @Captor
    private ArgumentCaptor<Integer> accountIdCaptor;

    @Captor
    private ArgumentCaptor<String> statusCaptor;


    @Test
    void updateAccountStatus_Approved() {

        // Arrange
        Account expectedAccount = new Account();

        when(scanner.nextInt())
                .thenReturn(101, 1);

        when(accountDAO.updateAccountStatus(101, "approved"))
                .thenReturn(expectedAccount);

        // Act
        Account result = accountService.updateAccountStatus();

        // Assert
        assertNotNull(result);
        assertEquals(expectedAccount, result);

        verify(accountDAO).updateAccountStatus(
                accountIdCaptor.capture(),
                statusCaptor.capture()
        );

        assertEquals(101, accountIdCaptor.getValue());
        assertEquals("approved", statusCaptor.getValue());
    }

    @Test
    void updateAccountStatus_Denied() {

        // Arrange
        Account expectedAccount = new Account();

        when(scanner.nextInt())
                .thenReturn(102, 2);

        when(accountDAO.updateAccountStatus(102, "denied"))
                .thenReturn(expectedAccount);

        // Act
        Account result = accountService.updateAccountStatus();

        // Assert
        assertNotNull(result);

        verify(accountDAO).updateAccountStatus(102, "denied");
    }

    @Test
    void getAccountById_Success() {

        // Arrange
        Account expectedAccount = new Account();

        when(scanner.nextInt()).thenReturn(101);

        when(accountDAO.getAcccountByAccountId(101))
                .thenReturn(expectedAccount);

        // Act
        Account result = accountService.getAcccountByAccountId();

        // Assert
        assertNotNull(result);
        assertEquals(expectedAccount, result);

        verify(accountDAO).getAcccountByAccountId(101);
    }

    @Test
    void getAccountById_NotFound() {

        // Arrange
        when(scanner.nextInt()).thenReturn(999);

        when(accountDAO.getAcccountByAccountId(999))
                .thenReturn(null);

        // Act
        Account result = accountService.getAcccountByAccountId();

        // Assert
        assertNull(result);

        verify(accountDAO).getAcccountByAccountId(999);
    }
}
