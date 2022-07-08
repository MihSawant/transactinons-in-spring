package sawant.mihir.springtransactions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sawant.mihir.springtransactions.model.Account;
import sawant.mihir.springtransactions.repository.AccountRepository;
import sawant.mihir.springtransactions.service.TransferService;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class TransferServiceUnitTest {
    AccountRepository repository = mock(AccountRepository.class);

    TransferService transferService = new TransferService(repository);

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
}
