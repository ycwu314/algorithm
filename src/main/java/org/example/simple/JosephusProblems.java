package org.example.simple;


import org.junit.Test;

import java.util.LinkedList;

/**
 * 约瑟夫环<br>
 * 设有N个人依次围成一圈，从第1个人开始报数，第M个人出列，然后从出列的下一个人开始报数，数到第M个人又出列，...，如此反复到所有的人全部出列为止，设N个人的编号分别为1，2，...，N，打印出出列的顺序
 *
 * @author ycwu
 *
 */
public class JosephusProblems {

	/**
	 * using array to simulate
	 */
	@Test
	public void test1() {
		// out order: 4,2,1,3,6,5
		int N = 6, M = 4;
		final int OUT = -1;
		int[] arr = new int[N];
		for (int i = 1; i <= N; i++) {
			arr[i - 1] = i;
		}

		int count = N, j = -1; // j : shout out index
		while (count > 0) {
			// when i=M, j is the out index
			for (int i = 1; i <= M; i++) {
				// shout out first
				j = (j + 1) % N;
				// if this index is already out, then find the next one
				if (arr[j] == OUT) {
					while (arr[j] == OUT) {
						j = (j + 1) % N;
					}
				}
			}
			System.out.println(arr[j]);
			arr[j] = OUT;
			count--;
		}
	}

	/**
	 * using linked list to simulate
	 */
	@Test
	public void test2() {
		// out order: 4,2,1,3,6,5
		int N = 6, M = 4;
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			list.add(i);
		}

		int count = N, j = 0;
		while (count > 0) {
			// reset the start index
			j = (j - 1 + count) % count;

			// when i=M, j is the out index
			for (int i = 1; i <= M; i++) {
				// notice: mod count, because each round the number left is down
				j = (j + 1) % count;
			}

			System.out.println(list.remove(j));
			count--;

		}
	}

}
