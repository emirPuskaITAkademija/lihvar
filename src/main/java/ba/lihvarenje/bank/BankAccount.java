package ba.lihvarenje.bank;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.StringJoiner;

@Entity
@Table(name = "bank_account", catalog = "sakila")
@NamedQueries(
        {
                @NamedQuery(name = "BankAccount.findAll", query = "SELECT ba FROM BankAccount ba")
        }
)
public class BankAccount extends AbstractBankAccount implements Serializable {
    @Id
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "owner")
    private String owner;

    public BankAccount(){

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(" : ");
        stringJoiner.add("Account = " + accountNumber);
        stringJoiner.add(" Amount = " + amount);
        stringJoiner.add(" Owner = "+owner);
        return stringJoiner.toString();
    }
}
