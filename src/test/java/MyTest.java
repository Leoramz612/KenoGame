import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

class MyTest {
	@Test
		// Test if oneSpot map gets the correct values after using constructor
	void testConstructor1() {
		GameLogic game = new GameLogic();
		assertEquals(2, game.oneSpot.get(1));
		assertNull(game.oneSpot.get(2));
	}
	@Test
		// Test if fourSpot map gets the correct values after using constructor
	void testConstructor4() {
		GameLogic game = new GameLogic();
		assertNull(game.fourSpot.get(1));
		assertEquals(1, game.fourSpot.get(2));
		assertEquals(5, game.fourSpot.get(3));
		assertEquals(75, game.fourSpot.get(4));
	}
	@Test
		// Test if eightSpot map gets the correct values after using constructor
	void testConstructor8() {
		GameLogic game = new GameLogic();
		assertNull(game.eightSpot.get(1));
		assertNull(game.eightSpot.get(2));
		assertNull(game.eightSpot.get(3));
		assertEquals(2, game.eightSpot.get(4));
		assertEquals(12, game.eightSpot.get(5));
		assertEquals(50, game.eightSpot.get(6));
		assertEquals(750, game.eightSpot.get(7));
		assertEquals(10000, game.eightSpot.get(8));
	}
	@Test
		// Test if tenSpot map gets the correct values after using constructor
	void testConstructor10() {
		GameLogic game = new GameLogic();
		assertEquals(5, game.tenSpot.get(0), 5);
		assertNull(game.tenSpot.get(1));
		assertNull(game.tenSpot.get(2));
		assertNull(game.tenSpot.get(3));
		assertNull(game.tenSpot.get(4));
		assertEquals(2, game.tenSpot.get(5));
		assertEquals(15, game.tenSpot.get(6));
		assertEquals(40, game.tenSpot.get(7));
		assertEquals(450, game.tenSpot.get(8));
		assertEquals(4250, game.tenSpot.get(9));
		assertEquals(100000, game.tenSpot.get(10));
	}
	@Test
		// Test if constructor properly initializes maps correctly by testing the size
	void testConstructorSizes() {
		GameLogic game = new GameLogic();
		assertEquals(1, game.oneSpot.size());
		assertEquals(3, game.fourSpot.size());
		assertEquals(5, game.eightSpot.size());
		assertEquals(7, game.tenSpot.size());
	}
	@Test
		// Test if generateNums() contains duplicate numbers or not by using HashSets and size()
	void testRandomNums() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> randomNums = game.generateNums();
		// HashSets do not add duplicates
		HashSet<Integer> uniqueNums = new HashSet<>(randomNums);
		assertEquals(randomNums.size(), uniqueNums.size());
	}
	@Test
		// Test if generateNums() contains duplicates or not by using HashSets and comparing the actual numbers
	void testRandomNums2() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> randomNums = game.generateNums();
		HashSet<Integer> uniqueNums = new HashSet<>(randomNums);
		ArrayList<Integer> uniqNums = new ArrayList<>(uniqueNums);
		Collections.sort(randomNums);
		Collections.sort(uniqNums);
		assertArrayEquals(randomNums.toArray(), uniqNums.toArray());
	}
	@Test
		// Test to see if matchedNums() gets all the correct common numbers between two ArrayLists
	void testMatched() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = game.generateNums();
		ArrayList<Integer> nums2 = game.generateNums();
		ArrayList<Integer> matches = new ArrayList<>();
		for(int i = 0; i < nums.size(); i++) {
			for(int j = 0; j < nums2.size(); j++) {
				if(nums.get(i) == nums2.get(j)) {
					matches.add(nums.get(i));
				}
			}
		}
		ArrayList<Integer> gameMatches = game.matchedNums(nums, nums2);
		Collections.sort(matches);
		Collections.sort(gameMatches);
		assertArrayEquals(matches.toArray(), gameMatches.toArray());
	}
	@Test
		// Test to see if calculateWinnings() works for one spot that matched
	void testWinnings1Match() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		numsSelected.add(2);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(2, game.calculateWinnings(1, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() works for one spot that didn't match
	void testWinnings1NoMatch() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		numsSelected.add(32);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(0, game.calculateWinnings(1, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() works for four spots that matched 2 numbers
	void testWinnings4Match2() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		numsSelected.add(1);
		numsSelected.add(2);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(1, game.calculateWinnings(4, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() works for four spots that matched 3 numbers
	void testWinnings4Match3() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		numsSelected.add(1);
		numsSelected.add(2);
		numsSelected.add(3);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(5, game.calculateWinnings(4, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() works for four spots that matched 4 numbers
	void testWinnings4Match4() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		numsSelected.add(1);
		numsSelected.add(2);
		numsSelected.add(3);
		numsSelected.add(4);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(75, game.calculateWinnings(4, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() works for four spots that didn't match any numbers
	void testWinnings4NoMatch() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(0, game.calculateWinnings(4, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() works for four spots that didn't match any numbers
	void testWinnings4NoMatchAgain() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		numsSelected.add(1);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(0, game.calculateWinnings(4, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() works for eight spots that matched 4 numbers
	void testWinnings8Match4() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		numsSelected.add(1);
		numsSelected.add(2);
		numsSelected.add(3);
		numsSelected.add(4);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(2, game.calculateWinnings(8, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() works for eight spots that matched 5 numbers
	void testWinnings8Match5() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		numsSelected.add(1);
		numsSelected.add(2);
		numsSelected.add(3);
		numsSelected.add(4);
		numsSelected.add(5);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(12, game.calculateWinnings(8, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() works for eight spots that matched 6 numbers
	void testWinnings8Match6() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		numsSelected.add(1);
		numsSelected.add(2);
		numsSelected.add(3);
		numsSelected.add(4);
		numsSelected.add(5);
		numsSelected.add(6);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(50, game.calculateWinnings(8, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() works for eight spots that matched 7 numbers
	void testWinnings8Match7() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		numsSelected.add(1);
		numsSelected.add(2);
		numsSelected.add(3);
		numsSelected.add(4);
		numsSelected.add(5);
		numsSelected.add(6);
		numsSelected.add(7);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(750, game.calculateWinnings(8, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() works for eight spots that matched 8 numbers
	void testWinnings8Match8() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		numsSelected.add(1);
		numsSelected.add(2);
		numsSelected.add(3);
		numsSelected.add(4);
		numsSelected.add(5);
		numsSelected.add(6);
		numsSelected.add(7);
		numsSelected.add(8);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(10000, game.calculateWinnings(8, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() works for eight spots that didn't match any numbers
	void testWinnings8NoMatch() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(0, game.calculateWinnings(8, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() works for eight spots that matched only 1 number
	void testWinnings8NoMatch1() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		numsSelected.add(1);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(0, game.calculateWinnings(8, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() works for eight spots that matched only 2 numbers
	void testWinnings8NoMatch2() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		numsSelected.add(1);
		numsSelected.add(2);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(0, game.calculateWinnings(8, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() works for eight spots that matched only 3 numbers
	void testWinnings8NoMatch3() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		numsSelected.add(1);
		numsSelected.add(2);
		numsSelected.add(3);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(0, game.calculateWinnings(8, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() works for ten spots that matched 0 numbers
	void testWinnings10Match0() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(5, game.calculateWinnings(10, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() works for ten spots that matched 5 numbers
	void testWinnings10Match5() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		numsSelected.add(1);
		numsSelected.add(2);
		numsSelected.add(3);
		numsSelected.add(4);
		numsSelected.add(5);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(2, game.calculateWinnings(10, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() works for ten spots that matched 6 numbers
	void testWinnings10Match6() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		numsSelected.add(1);
		numsSelected.add(2);
		numsSelected.add(3);
		numsSelected.add(4);
		numsSelected.add(5);
		numsSelected.add(6);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(15, game.calculateWinnings(10, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() works for ten spots that matched 7 numbers
	void testWinnings10Match7() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		numsSelected.add(1);
		numsSelected.add(2);
		numsSelected.add(3);
		numsSelected.add(4);
		numsSelected.add(5);
		numsSelected.add(6);
		numsSelected.add(7);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(40, game.calculateWinnings(10, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() works for ten spots that matched 8 numbers
	void testWinnings10Match8() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		numsSelected.add(1);
		numsSelected.add(2);
		numsSelected.add(3);
		numsSelected.add(4);
		numsSelected.add(5);
		numsSelected.add(6);
		numsSelected.add(7);
		numsSelected.add(8);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(450, game.calculateWinnings(10, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() works for ten spots that matched 9 numbers
	void testWinnings10Match9() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		numsSelected.add(1);
		numsSelected.add(2);
		numsSelected.add(3);
		numsSelected.add(4);
		numsSelected.add(5);
		numsSelected.add(6);
		numsSelected.add(7);
		numsSelected.add(8);
		numsSelected.add(9);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(4250, game.calculateWinnings(10, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() works for ten spots that matched 10 numbers
	void testWinnings10Match10() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		numsSelected.add(1);
		numsSelected.add(2);
		numsSelected.add(3);
		numsSelected.add(4);
		numsSelected.add(5);
		numsSelected.add(6);
		numsSelected.add(7);
		numsSelected.add(8);
		numsSelected.add(9);
		numsSelected.add(10);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		assertEquals(100000, game.calculateWinnings(10, matchedNums));
	}
	@Test
	void testWinnings10NoMatch() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		numsSelected.add(1);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		// 1 match no prize
		assertEquals(0, game.calculateWinnings(10, matchedNums));
		numsSelected.add(2);
		matchedNums = game.matchedNums(numsSelected, nums);
		// 2 match no prize
		assertEquals(0, game.calculateWinnings(10, matchedNums));
		numsSelected.add(3);
		matchedNums = game.matchedNums(numsSelected, nums);
		// 3 match no prize
		assertEquals(0, game.calculateWinnings(10, matchedNums));
		numsSelected.add(4);
		matchedNums = game.matchedNums(numsSelected, nums);
		// 4 match no prize
		assertEquals(0, game.calculateWinnings(10, matchedNums));
	}
	@Test
		// Test to see if calculateWinnings() accumulates sum properly for multiple draws with one spot
	void testWinningsAccumulated1() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		int winnings = 0;
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		// first drawing
		numsSelected.add(2);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		winnings += game.calculateWinnings(1, matchedNums);
		// second drawing
		numsSelected.set(0, 1);
		matchedNums = game.matchedNums(numsSelected, nums);
		winnings += game.calculateWinnings(1, matchedNums);
		assertEquals(4, winnings);
	}
	@Test
		// Test to see if calculateWinnings() accumulates sum properly for multiple draws with four spots
	void testWinningsAccumulated4() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		int winnings = 0;
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		// first drawing
		numsSelected.add(2);
		numsSelected.add(4);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		winnings += game.calculateWinnings(4, matchedNums);
		// second drawing
		numsSelected.clear();
		numsSelected.add(1);
		matchedNums = game.matchedNums(numsSelected, nums);
		winnings += game.calculateWinnings(4, matchedNums);
		// third drawing
		numsSelected.clear();
		numsSelected.add(7);
		numsSelected.add(11);
		numsSelected.add(15);
		matchedNums = game.matchedNums(numsSelected, nums);
		winnings += game.calculateWinnings(4, matchedNums);
		assertEquals(6, winnings);
	}
	@Test
		// Test to see if calculateWinnings() accumulates sum properly for multiple draws with eight spots
	void testWinningsAccumulated8() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		int winnings = 0;
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		// first drawing
		numsSelected.add(2);
		numsSelected.add(4);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		winnings += game.calculateWinnings(8, matchedNums);
		// second drawing
		numsSelected.clear();
		numsSelected.add(1);
		matchedNums = game.matchedNums(numsSelected, nums);
		winnings += game.calculateWinnings(8, matchedNums);
		// third drawing
		numsSelected.clear();
		numsSelected.add(7);
		numsSelected.add(11);
		numsSelected.add(15);
		matchedNums = game.matchedNums(numsSelected, nums);
		winnings += game.calculateWinnings(8, matchedNums);
		assertEquals(0, winnings);
	}
	@Test
		// Test to see if calculateWinnings() accumulates sum properly for multiple draws with ten spots
	void testWinningsAccumulated10() {
		GameLogic game = new GameLogic();
		ArrayList<Integer> nums = new ArrayList<>();
		int winnings = 0;
		for(int i = 1; i < 21; i++) {
			nums.add(i);
		}
		ArrayList<Integer> numsSelected = new ArrayList<>();
		// first drawing
		numsSelected.add(25);
		ArrayList<Integer> matchedNums = game.matchedNums(numsSelected, nums);
		winnings += game.calculateWinnings(10, matchedNums);
		// second drawing
		numsSelected.clear();
		numsSelected.add(1);
		numsSelected.add(2);
		numsSelected.add(3);
		numsSelected.add(4);
		numsSelected.add(5);
		numsSelected.add(6);
		matchedNums = game.matchedNums(numsSelected, nums);
		winnings += game.calculateWinnings(10, matchedNums);
		// third drawing
		numsSelected.clear();
		numsSelected.add(1);
		numsSelected.add(2);
		numsSelected.add(3);
		numsSelected.add(4);
		numsSelected.add(5);
		numsSelected.add(6);
		numsSelected.add(7);
		matchedNums = game.matchedNums(numsSelected, nums);
		winnings += game.calculateWinnings(10, matchedNums);
		assertEquals(60, winnings);
	}
}