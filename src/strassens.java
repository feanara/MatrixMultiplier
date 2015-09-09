/**
 * 
 * @author Emily Maust
 *
 * Array printing is in a separate method
 * because for the sake of simplicity,
 * we only need to print timestamp.
 */
public class strassens
{
	private int sizeLimit;

	public strassens(int recursions, int runtime, int[][] array) 
	{
		for (int i = 0; i<recursions; i++)
		{
			sizeLimit = array.length/2;
		}
	}
	
	public int[][] strassen(int[][] A, int[][] B, int depth)
	{
		int n = A.length;
		if(n <= sizeLimit)
		{
			return bruteForce.brute(n, A, B);
		}
		else
		{
			int [][] result = new int[n][n];
			if(n == 1)
			{
				result[0][0] = A[0][0] * B[0][0];
			}
			else
			{
				int [][] A11 = new int[n/2][n/2];
				int [][] A12 = new int[n/2][n/2];
				int [][] A21 = new int[n/2][n/2];
				int [][] A22 = new int[n/2][n/2];
				int [][] B11 = new int[n/2][n/2];
				int [][] B12 = new int[n/2][n/2];
				int [][] B21 = new int[n/2][n/2];
				int [][] B22 = new int[n/2][n/2];
				divideArray(A, A11, 0 , 0);
				divideArray(A, A12, 0 , n/2);
				divideArray(A, A21, n/2, 0);
				divideArray(A, A22, n/2, n/2);
				divideArray(B, B11, 0 , 0);
				divideArray(B, B12, 0 , n/2);
				divideArray(B, B21, n/2, 0);
				divideArray(B, B22, n/2, n/2);
				int [][] P1 = strassen(addMatrices(A11, A22), addMatrices(B11, B22), depth);
				int [][] P2 = strassen(addMatrices(A21, A22), B11, depth);
				int [][] P3 = strassen(A11, subtractMatrices(B12, B22), depth);
				int [][] P4 = strassen(A22, subtractMatrices(B21, B11), depth);
				int [][] P5 = strassen(addMatrices(A11, A12), B22, depth);
				int [][] P6 = strassen(subtractMatrices(A21, A11), addMatrices(B11, B12), depth);
				int [][] P7 = strassen(subtractMatrices(A12, A22), addMatrices(B21, B22), depth);
				int [][] C11 = addMatrices(subtractMatrices(addMatrices(P1, P4), P5), P7);
				int [][] C12 = addMatrices(P3, P5);
				int [][] C21 = addMatrices(P2, P4);
				int [][] C22 = addMatrices(subtractMatrices(addMatrices(P1, P3), P2), P6);
				copySubArray(C11, result, 0 , 0);
				copySubArray(C12, result, 0 , n/2);
				copySubArray(C21, result, n/2, 0);
				copySubArray(C22, result, n/2, n/2);
			}
			return result;
		}
	}

	public int [][] addMatrices(int [][] A, int [][] B)
	{
		int n = A.length;
		int [][] result = new int[n][n];
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				result[i][j] = A[i][j] + B[i][j];
		return result;
	}
	public int [][] subtractMatrices(int [][] A, int [][] B)
	{
		int n = A.length;
		int [][] result = new int[n][n];
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				result[i][j] = A[i][j] - B[i][j];
		return result;
	}
	public void divideArray(int[][] parent, int[][] child, int iB, int jB)
	{
		for(int i1 = 0, i2=iB; i1<child.length; i1++, i2++)
			for(int j1 = 0, j2=jB; j1<child.length; j1++, j2++)
			{
				child[i1][j1] = parent[i2][j2];
			}
	}
	public void copySubArray(int[][] child, int[][] parent, int iB, int jB)
	{
		for(int i1 = 0, i2=iB; i1<child.length; i1++, i2++)
			for(int j1 = 0, j2=jB; j1<child.length; j1++, j2++)
			{
				parent[i2][j2] = child[i1][j1];
			}
	}
	public static void printArray(int [][] array)
	{
		int n = array.length;
		System.out.println("Strassens: ");
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<n; j++)
			{
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}
}