package com.aj.casino.baccarat;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.restfb.types.Place;

public class test {

	private static int randInt(int min, int max) {
		SecureRandom rand = new SecureRandom();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	 public void testRandom(){
		          System.out.println("Random不设置种子：");
		          for (int i = 0; i < 5; i++) {
		           Random random = new Random();
		             for (int j = 0; j < 10; j++) {
		                 System.out.print(" " + random.nextInt(100) + ", ");
		           }
		             System.out.println("");
		        }
		  
		          System.out.println("");
		  
		          System.out.println("Random设置种子：");
		          for (int i = 0; i < 5; i++) {
		              Random random = new Random();
		              random.setSeed(100);
		              for (int j = 0; j < 10; j++) {
		                  System.out.print(" " + random.nextInt(100) + ", ");
		              }
		              System.out.println("");
		          }
		          
		          System.out.println("");
				  
		          System.out.println("Secure Random设置种子：");
		          SecureRandom SecureRandom = new SecureRandom();
//		          SecureRandom.setSeed(10);
		          for (int i = 0; i < 5; i++) {
		              for (int j = 0; j < 10; j++) {
		                  System.out.print(" " + SecureRandom.nextInt(100) + ", ");
		              }
		              System.out.println("");
		          }
		      }
	public static void main(String[] args) throws NoSuchAlgorithmException {
		
//		for(int i=0 ; i<50; i++) {
//			System.out.print(randInt(0, 50)+",");
//		}
//		List<String> arrayList = new ArrayList<>();
		System.out.println(49%50);
	}
	
}
