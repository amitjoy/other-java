package fancyfoods.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

import fancyfoods.food.Customer;

 
@Entity
public class CustomerImpl implements Customer {

	@Id
	private String name;
	private double creditLimit;
	private double balance;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getCreditLimit() {
		return creditLimit;
	}

	@Override
	public double getBalance() {
		return balance;
	}

	public void setBalance(double newBalance) {
		balance = newBalance;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreditLimit(int creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String toString() {
		return getName() + " had a balance of £" + getBalance()
				+ " and a credit limit of £" + getCreditLimit();
	}

}
