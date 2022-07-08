package sawant.mihir.springtransactions.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sawant.mihir.springtransactions.model.Account;
import sawant.mihir.springtransactions.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {

    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transferMoney(int senderId, int receiverId, BigDecimal amount){
        var sender = accountRepository.findById(senderId);
        var receiver = accountRepository.findById(receiverId);

        var senderBalance = sender.amount();
        var receiverBalance = receiver.amount();

        if(amount.compareTo(sender.amount()) > 0) throw new RuntimeException("Not enough Balance to proceed the transaction");

        var senderAmount = senderBalance.subtract(amount);
        var receiverAmount = receiverBalance.add(amount);



        accountRepository.changeAmount(senderId, senderAmount);
        accountRepository.changeAmount(receiverId, receiverAmount);

//        throw new RuntimeException("Transaction Fails");
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAllAccounts();
    }
}
