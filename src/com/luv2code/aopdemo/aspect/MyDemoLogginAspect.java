package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLogginAspect {

	// add a new advice for @AfterReturning on the findaccounts method
	//Ne pas oublier les parenthèses traitresses et l'execution
	@AfterReturning(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccount(..))",
			returning="result")
	public void afterReturningFindAccountsAdvice(
			JoinPoint theJoinPoint, List<Account> result) {
				
		// print out wich method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n======>>>> executing @AfterReturning on method :" + method);
		
		// print out the results of the method call 
		System.out.println("\n======>>>> executing @AfterReturning on method :" + result);
		
		// let's post process the data ...let's modify it
		
		
		// convert the account names to uppercase
		convertAccountNamesToUpperCase(result);
		
		System.out.println("\n======>>>> executing @AfterReturning on method :" + result);
		
	}

	private void convertAccountNamesToUpperCase(List<Account> result) {

		// loop through accounts
		
		for(Account tempAccount : result) {
		
			// get uppercase version of name
			String theUpperName = tempAccount.getName().toUpperCase();
			
			// update the name on the account
			tempAccount.setName(theUpperName);
			
				}
			}
	
	
}
