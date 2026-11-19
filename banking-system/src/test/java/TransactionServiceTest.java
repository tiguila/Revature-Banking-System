

import org.example.Repository.AccountDAO;
import org.example.Repository.TransactionDAO;
import org.example.Repository.UserDAO;
import org.example.model.Account;
import org.example.model.User;
import org.example.service.AccountService;
import org.example.service.TransactionService;
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
public class TransactionServiceTest {

    @Mock
    private Scanner scanner;

    @Mock
    private UserDAO userDAO;

    @Mock
    private AccountDAO accountDAO;

    @Mock
    private TransactionDAO transactionDAO;

    @InjectMocks
    private TransactionService transactionService;

    @Captor
    private ArgumentCaptor<Double> amountCaptor;


    // =========================
    // DEPOSIT TESTS
    // =========================

    @Test
    void deposit_Success() {

        // Arrange
        User currentUser = mock(User.class);
        Account expectedAccount = new Account();

        when(currentUser.getUserId()).thenReturn(101);
        when(scanner.nextDouble()).thenReturn(500.0);

        when(transactionDAO.getAccountByUserId(101))
                .thenReturn(expectedAccount);

        when(transactionDAO.depositFunds(expectedAccount, 500.0))
                .thenReturn(true);

        // Act
        Account result = transactionService.deposit(currentUser);

        // Assert
        assertNotNull(result);
        assertEquals(expectedAccount, result);

        verify(transactionDAO).getAccountByUserId(101);

        verify(transactionDAO).depositFunds(
                eq(expectedAccount),
                amountCaptor.capture()
        );

        assertEquals(500.0, amountCaptor.getValue());
    }


    @Test
    void deposit_AccountNotFound() {

        // Arrange
        User currentUser = mock(User.class);

        when(currentUser.getUserId()).thenReturn(101);
        when(scanner.nextDouble()).thenReturn(500.0);

        when(transactionDAO.getAccountByUserId(101))
                .thenReturn(null);

        // Act
        Account result = transactionService.deposit(currentUser);

        // Assert
        assertNull(result);

        verify(transactionDAO).getAccountByUserId(101);

        verify(transactionDAO, never())
                .depositFunds(any(), anyDouble());
    }


    @Test
    void deposit_InvalidAmount() {

        // Arrange
        User currentUser = mock(User.class);
        Account expectedAccount = new Account();

        when(currentUser.getUserId()).thenReturn(101);
        when(scanner.nextDouble()).thenReturn(-100.0);

        when(transactionDAO.getAccountByUserId(101))
                .thenReturn(expectedAccount);

        // Act
        Account result = transactionService.deposit(currentUser);

        // Assert
        assertEquals(expectedAccount, result);

        verify(transactionDAO).getAccountByUserId(101);

        verify(transactionDAO, never())
                .depositFunds(any(), anyDouble());
    }


    @Test
    void deposit_DAOFailure() {

        // Arrange
        User currentUser = mock(User.class);
        Account expectedAccount = new Account();

        when(currentUser.getUserId()).thenReturn(101);
        when(scanner.nextDouble()).thenReturn(200.0);

        when(transactionDAO.getAccountByUserId(101))
                .thenReturn(expectedAccount);

        when(transactionDAO.depositFunds(expectedAccount, 200.0))
                .thenReturn(false);

        // Act
        Account result = transactionService.deposit(currentUser);

        // Assert
        assertEquals(expectedAccount, result);

        verify(transactionDAO)
                .depositFunds(expectedAccount, 200.0);
    }


    @Test
    void withdraw_Success() {

        // Arrange
        User currentUser = mock(User.class);
        Account expectedAccount = new Account();

        when(currentUser.getUserId()).thenReturn(101);
        when(scanner.nextDouble()).thenReturn(100.0);

        when(transactionDAO.getAccountByUserId(101))
                .thenReturn(expectedAccount);

        when(transactionDAO.withdrawFunds(expectedAccount, 100.0))
                .thenReturn(true);

        // Act
        Account result = transactionService.withdraw(currentUser);

        // Assert
        assertNotNull(result);
        assertEquals(expectedAccount, result);

        verify(transactionDAO).withdrawFunds(
                eq(expectedAccount),
                amountCaptor.capture()
        );

        assertEquals(100.0, amountCaptor.getValue());
    }


    @Test
    void withdraw_AccountNotFound() {

        // Arrange
        User currentUser = mock(User.class);

        when(currentUser.getUserId()).thenReturn(101);
        when(scanner.nextDouble()).thenReturn(100.0);

        when(transactionDAO.getAccountByUserId(101))
                .thenReturn(null);

        // Act
        Account result = transactionService.withdraw(currentUser);

        // Assert
        assertNull(result);

        verify(transactionDAO, never())
                .withdrawFunds(any(), anyDouble());
    }


    @Test
    void withdraw_InvalidAmount() {

        // Arrange
        User currentUser = mock(User.class);
        Account expectedAccount = new Account();

        when(currentUser.getUserId()).thenReturn(101);
        when(scanner.nextDouble()).thenReturn(-50.0);

        when(transactionDAO.getAccountByUserId(101))
                .thenReturn(expectedAccount);

        // Act
        Account result = transactionService.withdraw(currentUser);

        // Assert
        assertEquals(expectedAccount, result);

        verify(transactionDAO, never())
                .withdrawFunds(any(), anyDouble());
    }


    @Test
    void withdraw_InsufficientFunds() {

        // Arrange
        User currentUser = mock(User.class);
        Account expectedAccount = new Account();

        when(currentUser.getUserId()).thenReturn(101);
        when(scanner.nextDouble()).thenReturn(1000.0);

        when(transactionDAO.getAccountByUserId(101))
                .thenReturn(expectedAccount);

        when(transactionDAO.withdrawFunds(expectedAccount, 1000.0))
                .thenReturn(false);

        // Act
        Account result = transactionService.withdraw(currentUser);

        // Assert
        assertEquals(expectedAccount, result);

        verify(transactionDAO)
                .withdrawFunds(expectedAccount, 1000.0);
    }


    // =========================
    // GET ACCOUNT TEST
    // =========================

    @Test
    void getAccountByUserId_Success() {

        // Arrange
        Account expectedAccount = new Account();

        when(scanner.nextInt()).thenReturn(101);

        when(transactionDAO.getAccountByUserId(101))
                .thenReturn(expectedAccount);

        // Act
        Account result = transactionService.getAccountbyUserId();

        // Assert
        assertNotNull(result);
        assertEquals(expectedAccount, result);

        verify(transactionDAO)
                .getAccountByUserId(101);
    }

}