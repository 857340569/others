package zp.base.test;

import zp.base.utils.CollectionUtils;

public class TestException {
	public static void main(String[] args) {
		new Child().sum(10);
	}
	static class Father
	{
		void sum(int sum)
		{
			sum++;
		}
	}
	static class Child extends Father
	{
		@Override
		void sum(int sum) {
			super.sum(sum);
			System.out.println(sum++);
		}
	}
	
}
