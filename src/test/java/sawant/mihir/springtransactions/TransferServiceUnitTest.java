package sawant.mihir.springtransactions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sawant.mihir.springtransactions.model.Account;
import sawant.mihir.springtransactions.repository.AccountRepository;
import sawant.mihir.springtransactions.service.TransferService;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TransferServiceUnitTest {

    @Mock
    private AccountRepository repository;

    @InjectMocks
    TransferService transferService;

    @Test
    @DisplayName("Check the Transfer Money Flow")
    public void basicTransferFlowTest(){
        var sender = new Account(1, "", new BigDecimal(300));
        var receiver = new Account(2, "", new BigDecimal(300));

        given(repository.findById(sender.id())).willReturn(Optional.of(sender));
        given(repository.findById(receiver.id())).willReturn(Optional.of(receiver));

        transferService.transferMoney(sender.id(), receiver.id(), new BigDecimal(50));

        verify(repository).changeAmount(1, new BigDecimal(250));
        verify(repository).changeAmount(2, new BigDecimal(350));

    }

    @Test
    @DisplayName("Check for Transfer Amount more than balance")
    public void notEnoughBalanceTest(){

        var sender = new Account(1, "", new BigDecimal(2000));
        var receiver = new Account(2, "", new BigDecimal(2000));

        given(repository.findById(sender.id())).willReturn(Optional.of(sender));
        given(repository.findById(receiver.id())).willReturn(Optional.of(receiver));

        assertThrows(RuntimeException.class,
                () -> transferService.transferMoney(1, 2, new BigDecimal(4000)));

        verify(repository, never()).changeAmount(anyInt(), any());

    }


}
