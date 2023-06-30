package ak.sandbox.balanseservice.service;


import ak.sandbox.balanseservice.entity.Balance;
import ak.sandbox.balanseservice.entity.History;
import ak.sandbox.balanseservice.repository.BalanceRepository;
import ak.sandbox.balanseservice.repository.HistoryRepository;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
public class BalanceServiceTest {

    private MockWebServer mockWebServer;

    @MockBean
    private BalanceRepository balanceRepository;

    @MockBean
    private HistoryRepository historyRepository;

    @Autowired
    private BalanceService balanceService;

    @BeforeEach
    public void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    @WithMockUser(username = "operator", roles ={"OPERATOR"})
    public void testAddNewUser() {

        String user = "testUser";


        balanceService.addNewUser(user);


        verify(balanceRepository, times(1)).save(any(Balance.class));
        verify(historyRepository, times(1)).save(any(History.class));
    }

    @Test
    @WithMockUser(username = "operator", roles ={"OPERATOR"})
    public void testIncreaseBalance() {
        // Arrange
        String user = "testUser";
        double amount = 100.0;


        Balance balance = new Balance();
        balance.setUser(user);
        balance.setBalance(0.0);
        when(balanceRepository.findByUser(user)).thenReturn(java.util.Optional.of(balance));


        balanceService.increaseBalance(user, amount);


        assertEquals(100.0, balance.getBalance());
        verify(balanceRepository, times(1)).save(balance);
        verify(historyRepository, times(1)).save(any(History.class));
    }

    @Test
    @WithMockUser(username = "operator", roles ={"OPERATOR"})
    public void testDecreaseBalance() {

        String user = "testUser";
        double amount = 50.0;


        Balance balance = new Balance();
        balance.setUser(user);
        balance.setBalance(100.0);
        when(balanceRepository.findByUser(user)).thenReturn(java.util.Optional.of(balance));


        balanceService.decreaseBalance(user, amount);


        assertEquals(50.0, balance.getBalance());
        verify(balanceRepository, times(1)).save(balance);
        verify(historyRepository, times(1)).save(any(History.class));
    }

    @Test
    @WithMockUser(username = "admin", roles ={"ADMIN"})
    public void testGetBalance() {

        String user = "testUser";


        Balance balance = new Balance();
        balance.setUser(user);
        balance.setBalance(100.0);
        when(balanceRepository.findByUser(user)).thenReturn(java.util.Optional.of(balance));


        double result = balanceService.getBalance(user);


        assertEquals(100.0, result);
        verify(balanceRepository, times(0)).save(any(Balance.class));
        verify(historyRepository, times(0)).save(any(History.class));
    }
}
