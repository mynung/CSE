#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>


//functions forlocateMemory convenience
double** allocateMemory(int m, int n);
void releaseMemory(double** A, int m);
void printMatrix(double** A, int m, int n, char name[]);

//functions to implement in prj0
//transposeMatrix, normalizeVector, calculateLength
//scaleMatrix, multiplyTwoMatrices, addTwoMatrices
double** transposeMatrix(double** A, int m, int n);
double** normalizeVector(double** v, int n);
double calculateLength(double** v, int n);
double** scaleMatrix(double** A, int m, int n, double c);
double** multiplyTwoMatrices(double** A, int m, int n, double** B, int l, int k);
double** addTwoMatrices(double** A, int m, int n, double** B, int l, int k);

int main() {
	srand(time(NULL));
	double** A;
	double** B;
	double** C;
	double** D;
	double** E;
	double** H;
	double** n_H;
	double** v; //vector
	double** w;	//vector
	double Length;
	int m = 5; 	//number of rows
	int n = 10;	//number of columns
	int c = 2;
	int k = 1;

	//Test transposeMatrix
	A = allocateMemory(m, n);
	for (int i = 0; i < m; i++)
		for (int j = 0; j < n; j++)
			A[i][j] = (double)i * j;
	printMatrix(A, m, n, "A");

	B = transposeMatrix(A, m, n);
	printMatrix(B, n, m, "A transposed ,B");

	//Test normalizeVector
	v = allocateMemory(m, 1);
	for (int i = 0; i < m; i++)
		v[i][0] = i;
	w = normalizeVector(v, m);
	printMatrix(v, m, 1, "v");
	printMatrix(w, m, 1, "v_normalized");

	//Test calculateLength
	Length = calculateLength(v, m);
	printf("\ncalculateLength=\n");
	printf("%lf\n", Length);


	//Test scaleMatrix
	C = scaleMatrix(A, m, n, 3);
	printMatrix(C, m, n, "C, A scaleMatrix");

	//Test multiplyTwoMatrices 
	D = allocateMemory(n, 2);
	for (int i = 0; i < n; i++)
		for (int j = 0; j < 2; j++)
			D[i][j] = (rand() % 6) - 2;//-2~3사이 난수 정수 생성
	printMatrix(D, n, 2, "D");
	printMatrix(multiplyTwoMatrices(A, m, n, D, n, 2), m, 2, "A and D multiplyTwoMatrices");

    //Test addTwoMatrices
	E = allocateMemory(n, 2);
	for (int i = 0; i < n; i++)
		for (int j = 0; j < 2; j++)
			E[i][j] = (rand() % 6) - 2;//-2~3사이 난수 정수 생성
	printMatrix(E, n, 2, "E");
	printMatrix(addTwoMatrices(D, n, 2, E, n, 2), n, 2, "D and E addTwoMatrices");

	//release Memory
	releaseMemory(A, m);
	releaseMemory(B, n);
	releaseMemory(C, m);
	releaseMemory(D, n);
	releaseMemory(E, n);
	releaseMemory(v, m);
	releaseMemory(w, m);
	//make matrices A, B, C, H, n_H and calculate
	A = allocateMemory(c, c);
	B = allocateMemory(c, c);
	C = allocateMemory(c, c);
	H = allocateMemory(c, c);
	n_H = allocateMemory(c, c);
	v = allocateMemory(c, 1);
	w = allocateMemory(c, 1);
	for (int i = 0; i < c; i++)
		for (int j = 0; j < c; j++)
			A[i][j] = k++;
	printMatrix(A, c, c, "A");

	//for (int i = 0; i < c; i++)
	//	for (int j = 0; j < c; j++)
	//		H[i][j] = (rand() % 6) - 2;//-2~3사이 난수 정수 생성
	H[0][0] = 1;
	H[0][1] = 1;
	H[1][0] = 1;
	H[1][1] = -1;
	printMatrix(H, c, c, "H");

	
	for (int i = 0; i < c; i++) {
		for (int j = 0; j < c; j++)
			v[j][0] = H[j][i];
		w = normalizeVector(v, c);
		for (int j = 0; j < c; j++)
			n_H[j][i] = w[j][0];
	}
	printMatrix(n_H, c, c, "normalize_H");

	B = multiplyTwoMatrices(multiplyTwoMatrices(transposeMatrix(n_H, c, c), c, c, A, c, c), c, c, n_H, c, c);
	printMatrix(B, c, c, "B");

	C = multiplyTwoMatrices(multiplyTwoMatrices(n_H, c, c, B, c, c), c, c, transposeMatrix(n_H, c, c), c, c);
	printMatrix(C, c, c, "C");


	//release all the memory allocated
	releaseMemory(A, c);
	releaseMemory(B, c);
	releaseMemory(C, c);
	releaseMemory(H, c);
	releaseMemory(n_H, c);
	releaseMemory(v, c);
	releaseMemory(w, c);

	return 0;
}

//functions for convenience
double** allocateMemory(int m, int n) {
	double** A;
	A = (double**)malloc(sizeof(double*) * m);
	for (int i = 0; i < m; i++) {
		A[i] = (double*)malloc(sizeof(double) * n);
	}
	return A;
}


void releaseMemory(double** A, int m) {
	for (int i = 0; i < m; i++)
		free(A[i]);
	free(A);
}

void printMatrix(double** A, int m, int n, char name[]) {
	printf("\n%s = \n", name);
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++)
			printf("%lf ", A[i][j]);
		printf("\n");
	}
}

//functions to implement in prj0 
double** transposeMatrix(double** A, int m, int n) {
	double** B = allocateMemory(n, m);

	for (int i = 0; i < m; i++)
		for (int j = 0; j < n; j++)
			B[j][i] = A[i][j];

	return B;
}

double** normalizeVector(double** v, int m) {
	double** w;
	double len = 0.0;

	for (int i = 0; i < m; i++)
		len += v[i][0] * v[i][0];
	len = sqrt(len);

	w = allocateMemory(m, 1);
	for (int i = 0; i < m; i++)
		w[i][0] = v[i][0] / len;

	return w;
}

double calculateLength(double** v, int n) {
	double len = 0.0;

	for (int i = 0; i < n; i++) {
		len += v[i][0] * v[i][0];
	}

	return sqrt(len);
}

double** scaleMatrix(double** A, int m, int n, double c) {
	double** B;
	B = allocateMemory(m, n);
	for (int i = 0; i < m; i++)
		for (int j = 0; j < n; j++)
			B[i][j] = A[i][j] * c;
	return B;
}

double** multiplyTwoMatrices(double** A, int m, int n, double** B, int l, int k) {
	double** C;
	double sum = 0.0;
	if (n != l) return NULL; //곱셈이 불가능 할때
	C = allocateMemory(m, k);
	for(int i = 0;i<m;i++)
		for (int j = 0; j < k; j++) {
			for (int o = 0; o < n; o++)
				sum += A[i][o] * B[o][j];
			C[i][j] = sum;
			sum = 0.0;
		}
	return C;
}

double** addTwoMatrices(double** A, int m, int n, double** B, int l, int k) {
	double** C;
	if (m != l || n != k) return NULL;
	C = allocateMemory(m, n);
	for (int i = 0; i < m; i++)
		for (int j = 0; j < n; j++)
			C[i][j] = A[i][j] + B[i][j];
	return C;
}




