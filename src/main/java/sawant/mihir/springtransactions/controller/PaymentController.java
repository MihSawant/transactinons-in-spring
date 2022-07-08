package sawant.mihir.springtransactions.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import sawant.mihir.springtransactions.model.Account;
import sawant.mihir.springtransactions.service.TransferService;

import java.math.BigDecimal;
import java.util.List;

@RestController
public record PaymentController(TransferService service) {

    @GetMapping("/accounts")
    public List<Account> allAccounts(){
        return service.getAllAccounts();
    }


    @PostMapping("/payment")
    public void transferMoney(@RequestHeader int senderId,
                              @RequestHeader int receiverId, @RequestHeader BigDecimal amountToTransfer){
        service.transferMoney(senderId, receiverId, amountToTransfer);
    }
}
