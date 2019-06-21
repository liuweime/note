import java.util.Arrays;

public class SelfSort
{


	public void bubbleSort(int[] data)
	{
		for (int i = 0 ; i < data.length-1; i++) {

			for (int j = 0; j < data.length-1-i; j++) {

				if (data[j] > data[j+1]) {
					int tmp = data[j];
					data[j] = data[j+1];
					data[j+1] = tmp;
				}
			}
		}
	}

	public void insertSort(int[] data)
	{
		for (int i = 1; i < data.length; i++) {
			int tmp = data[i];
			int j = i;
			while (j > 0 && data[j-1] >= tmp) {
				data[j] = data[j-1];
				j--;
			}
			// 移动空出的位置插入新元素
			data[j] = tmp;
		}
	}

	/**
	 * 选择排序 每次循环在剩余列表中选出最小的
	 * 
	 * @param
	 */
	public void selectSort(int[] data)
	{
		for (int i = 0; i < data.length; i++) {
			// 假定列表首个元素最小
			int min = i;

			// 与剩余列表元素比较
			for (int j = i; j < data.length; j++) {
				if (data[min] > data[j]) {
					min = j;
				}
			}

			// 进行交换
			int tmp = data[min];
			data[min] = data[i];
			data[i] = tmp;
		}
	}

	/**
	 *
	 * @param len
	 * @return
	 */
	public int[] createRandomData(int len)
	{

		int[] data = new int[len];
		for (int i = 0; i < data.length; i++) {
			data[i] = (int)(Math.random() * len)  + 1;
		}

		return data;
	}

	public static void main(String[] args)
	{
		SelfSort ob = new SelfSort();
		int len = 20;
		int[] data = ob.createRandomData(len);
		System.out.println(Arrays.toString(data));

		ob.selectSort(data);
		System.out.println(Arrays.toString(data));
	}
}