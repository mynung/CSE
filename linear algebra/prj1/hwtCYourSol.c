#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <Windows.h>
#include <math.h>


//비트맵 헤더를 한묶음으로
typedef struct tagBITMAPHEADER {
	BITMAPFILEHEADER bf;
	BITMAPINFOHEADER bi;
	RGBQUAD hRGB[256]; //이 코드에서는 필요없음 (8bit에만 필요)
}BITMAPHEADER;

//비트맵을 읽어와서 화소정보의 포인터를 리턴
BYTE* loadBitmapFile(int bytesPerPixel, BITMAPHEADER* bitmapHeader, int* imgWidth, int* imgHeight, char* filename);

//비트맵 파일 쓰기
void writeBitmapFile(int bytesPerPixel, BITMAPHEADER outputHeader, BYTE* output, int imgSize, char* filename);

//코드 가져온거

double** constructHaarMatrixRecursive(int n);//Haar행렬 구성

double** applyKroneckerProduct(double** A, int n, double a, double b);

double** concatenateTwoMatrices(double** hl, double** hr, int n); //두 행렬 합치기
void printMatrix(double** A, int m, int n, char name[]); //행렬 출력
double** constructIdentity(int k); //Identity 행렬 구성
double** allocateMemory(int m, int n); //메모리 할당
void releaseMemory(double** A, int m); //메모리 해제
double** normalizeMatrix(double** H, int n); //노멀라이즈
double** multiplyTwoMatrices(double** A, int m, int n, double** B, int l, int k); //행렬곱
double** transposeMatrix(double** A, int m, int n); //행렬 transpose
void reconstruct_image(double* Are, double** Ahat, int bytesPerPixel, int n); //행렬 재구성

int main() {
	/*******************************************************************/
	/*************************** Read image  ***************************/
	/*******************************************************************/
	BITMAPHEADER originalHeader;	//비트맵의 헤더부분을 파일에서 읽어 저장할 구조체
	BITMAPHEADER outputHeader;		//변형을 가한 헤더부분을 저장할 구조체
	int imgSize, imgWidth, imgHeight;					//이미지의 크기를 저장할 변수
	int bytesPerPixel = 3;			//number of bytes per pixel (1 byte for R,G,B respectively)

	BYTE* image = loadBitmapFile(bytesPerPixel, &originalHeader, &imgWidth, &imgHeight, "image_lena_24bit.bmp"); //비트맵파일을 읽어 화소정보를 저장 (불러들이는 이미지는 .c와 같은 폴더에 저장)
	if (image == NULL) return 0;

	imgSize = imgWidth * imgHeight; // total number of pixels
	BYTE* output = (BYTE*)malloc(bytesPerPixel * sizeof(BYTE) * imgSize);				//결과값을 저장할 포인터 선언 및 메모리 할당
	outputHeader = originalHeader;										//헤더정보를 출력헤더정보에 할당





	/*******************************************************************/
	/************************ Perform HWT/IHWT *************************/
	/*******************************************************************/
	//이미지 행렬 A 구성 (RGB값이 있으므로 픽셀당 값 하나씩만 읽어서 imgWidth x imgHeight 행렬 구성)
	int** A; //original image matrix
	A = (int**)malloc(sizeof(int*) * imgHeight);
	for (int i = 0; i < imgHeight; i++) {
		A[i] = (int*)malloc(sizeof(int) * imgWidth);
	}


	for (int i = 0; i < imgHeight; i++)
		for (int j = 0; j < imgWidth; j++)
			A[i][j] = image[(i * imgWidth + j) * bytesPerPixel];

	double** double_A = allocateMemory(imgHeight, imgHeight);
	for (int i = 0; i < imgHeight; i++) {
		for (int j = 0; j < imgHeight; j++) {
			double_A[i][j] = (double)A[i][j];
		}
	}
	
	//Haar matrix H 구성 (orthonormal column을 갖도록 구성)
	int n = imgHeight; //이미지가 정사각형(Height==Width)이라고 가정; n = 2^t,t=0,1,2,...
	printf("1-(b)\n");
	double** H = constructHaarMatrixRecursive(n);
	//printMatrix(H, n, n, "H");
	double** norm_H = normalizeMatrix(H, n);
	//printMatrix(norm_H, n, n, "normalize_H");

	//printMatrix(double_A, 3, 3, "A");


	//HWT 수행: 행렬곱 B = H'*A*H
	printf("1-(c)\n");
	double** transpose_H = transposeMatrix(norm_H, n, n);
//	printMatrix(transpose_H, 5, 5, "t_H");

	double** B=allocateMemory(n, n);
	B = multiplyTwoMatrices(transpose_H, n, n, double_A, n, n); //H'*A
	B = multiplyTwoMatrices(B, n, n, norm_H, n, n); //H'*A*H
	//printMatrix(B, 3, 3, "B");
	
	//행렬 B 자르기: B의 upper left corner(subsquare matrix)를 잘라 Bhat에 저장
	//Bhat은 B와 사이즈가 같으며 B에서 잘라서 저장한 부분 외에는 모두 0으로 채워짐
	printf("1-(d)\n");
	int k = 256; //k는 2의 배수, k의 값이 높을 수록 해상도가 높아짐
	double** Bhat = allocateMemory(n, n);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (i < k && j < k) Bhat[i][j] = B[i][j];
			else Bhat[i][j] = 0.0;
		}
	}
	//printMatrix(Bhat, 3, 3, "Bhat");

	




	//IHWT 수행: Ahat = H*Bhat*H'
	printf("1-(e)\n");
	double** Ahat = allocateMemory(n, n);
	Ahat = multiplyTwoMatrices(norm_H, n, n, Bhat, n, n);
	//printMatrix(Ahat, 5, 5, "Ahat");
	Ahat = multiplyTwoMatrices(Ahat, n, n, transpose_H, n, n);
	
	//printMatrix(Ahat, 5, 5, "Ahat");
	
	/*******************************************************************/
	/******************* Write reconstructed image  ********************/
	/*******************************************************************/
	//Ahat을 이용해서 위의 image와 같은 형식이 되도록 구성 (즉, Ahat = [a b;c d]면 [a a a b b b c c c d d d]를 만들어야 함)
	BYTE* Are = (BYTE*)malloc(bytesPerPixel * sizeof(BYTE) * imgSize);
	printf("1-(f)\n");
	reconstruct_image(Are, Ahat, bytesPerPixel, n);
	writeBitmapFile(bytesPerPixel, outputHeader, Are, imgSize, "output1.bmp");

	printf("3-(c)\n");

	double** low_H = allocateMemory(n / 2, n);
	double** high_H = allocateMemory(n / 2, n);
	for (int i = 0; i < n / 2; i++) {
		for (int j = 0; j < n; j++) {
			low_H[i][j] = transpose_H[i][j];
			high_H[i][j] = transpose_H[n / 2 + i][j];
		}
	}
	double** trans_low_H = transposeMatrix(low_H, n / 2, n);
	double** trans_high_H = transposeMatrix(high_H, n / 2, n);
	printf("low_H, high_H, trans_low_H, trans_high_H 준비완료\n");

	Ahat = multiplyTwoMatrices(trans_low_H, n, n / 2, low_H, n / 2, n);
	Ahat = multiplyTwoMatrices(Ahat, n, n, double_A, n, n);
	Ahat = multiplyTwoMatrices(Ahat, n, n, trans_low_H, n, n / 2);
	Ahat = multiplyTwoMatrices(Ahat, n, n / 2, low_H, n / 2, n);
	printf("part1\n");
	reconstruct_image(Are, Ahat, bytesPerPixel, n);
	writeBitmapFile(bytesPerPixel, outputHeader, Are, imgSize, "output2.bmp");

	Ahat = multiplyTwoMatrices(trans_low_H, n, n / 2, low_H, n / 2, n);
	Ahat = multiplyTwoMatrices(Ahat, n, n, double_A, n, n);
	Ahat = multiplyTwoMatrices(Ahat, n, n, trans_high_H, n, n / 2);
	Ahat = multiplyTwoMatrices(Ahat, n, n / 2, high_H, n / 2, n);
	printf("part2\n");
	reconstruct_image(Are, Ahat, bytesPerPixel, n);
	writeBitmapFile(bytesPerPixel, outputHeader, Are, imgSize, "output3.bmp");

	Ahat = multiplyTwoMatrices(trans_high_H, n, n / 2, high_H, n / 2, n);
	Ahat = multiplyTwoMatrices(Ahat, n, n, double_A, n, n);
	Ahat = multiplyTwoMatrices(Ahat, n, n, trans_low_H, n, n / 2);
	Ahat = multiplyTwoMatrices(Ahat, n, n / 2, low_H, n / 2, n);
	printf("part3\n");
	reconstruct_image(Are, Ahat, bytesPerPixel, n);
	writeBitmapFile(bytesPerPixel, outputHeader, Are, imgSize, "output4.bmp");

	Ahat = multiplyTwoMatrices(trans_high_H, n, n / 2, high_H, n / 2, n);
	Ahat = multiplyTwoMatrices(Ahat, n, n, double_A, n, n);
	Ahat = multiplyTwoMatrices(Ahat, n, n, trans_high_H, n, n / 2);
	Ahat = multiplyTwoMatrices(Ahat, n, n / 2, high_H, n / 2, n);
	printf("part4\n");
	reconstruct_image(Are, Ahat, bytesPerPixel, n);
	writeBitmapFile(bytesPerPixel, outputHeader, Are, imgSize, "output5.bmp");
	printf("3-(d)\n");

	double** llow_H = allocateMemory(n / 4, n);
	double** lhigh_H = allocateMemory(n / 4, n);
	for (int i = 0; i < n / 4; i++) {
		for (int j = 0; j < n; j++) {
			llow_H[i][j] = low_H[i][j];
			lhigh_H[i][j] = low_H[n / 4 + i][j];
		}
	}
	double** trans_llow_H = transposeMatrix(llow_H, n / 4, n);
	double** trans_lhigh_H = transposeMatrix(lhigh_H, n / 4, n);
	printf("llow_H, lhigh_H, trans_llow_H, trans_lhigh_H 준비완료\n");

	Ahat = multiplyTwoMatrices(trans_llow_H, n, n / 4, llow_H, n / 4, n);
	Ahat = multiplyTwoMatrices(Ahat, n, n, double_A, n, n);
	Ahat = multiplyTwoMatrices(Ahat, n, n, trans_llow_H, n, n / 4);
	Ahat = multiplyTwoMatrices(Ahat, n, n / 4, llow_H, n / 4, n);
	printf("part1\n");
	reconstruct_image(Are, Ahat, bytesPerPixel, n);
	writeBitmapFile(bytesPerPixel, outputHeader, Are, imgSize, "output6.bmp");

	Ahat = multiplyTwoMatrices(trans_llow_H, n, n / 4, llow_H, n / 4, n);
	Ahat = multiplyTwoMatrices(Ahat, n, n, double_A, n, n);
	Ahat = multiplyTwoMatrices(Ahat, n, n, trans_lhigh_H, n, n / 4);
	Ahat = multiplyTwoMatrices(Ahat, n, n / 4, lhigh_H, n / 4, n);
	printf("part2\n");
	reconstruct_image(Are, Ahat, bytesPerPixel, n);
	writeBitmapFile(bytesPerPixel, outputHeader, Are, imgSize, "output7.bmp");

	Ahat = multiplyTwoMatrices(trans_lhigh_H, n, n / 4, lhigh_H, n / 4, n);
	Ahat = multiplyTwoMatrices(Ahat, n, n, double_A, n, n);
	Ahat = multiplyTwoMatrices(Ahat, n, n, trans_llow_H, n, n / 4);
	Ahat = multiplyTwoMatrices(Ahat, n, n / 4, llow_H, n / 4, n);
	printf("part3\n");
	reconstruct_image(Are, Ahat, bytesPerPixel, n);
	writeBitmapFile(bytesPerPixel, outputHeader, Are, imgSize, "output8.bmp");

	Ahat = multiplyTwoMatrices(trans_lhigh_H, n, n / 4, lhigh_H, n / 4, n);
	Ahat = multiplyTwoMatrices(Ahat, n, n, double_A, n, n);
	Ahat = multiplyTwoMatrices(Ahat, n, n, trans_lhigh_H, n, n / 4);
	Ahat = multiplyTwoMatrices(Ahat, n, n / 4, lhigh_H, n / 4, n);
	printf("part4\n");
	reconstruct_image(Are, Ahat, bytesPerPixel, n);
	writeBitmapFile(bytesPerPixel, outputHeader, Are, imgSize, "output9.bmp");


	free(image);
	free(output);
	for (int i = 0; i < imgHeight; i++)
		free(A[i]);
	free(A);
	releaseMemory(double_A, n);
	releaseMemory(B, n);
	releaseMemory(H, n);
	releaseMemory(norm_H, n);
	releaseMemory(transpose_H, n);
	releaseMemory(Bhat, n);
	releaseMemory(Ahat, n);
	releaseMemory(low_H, n/2);
	releaseMemory(high_H, n/2);
	releaseMemory(trans_low_H, n);
	releaseMemory(trans_high_H, n);
	releaseMemory(llow_H, n / 4);
	releaseMemory(lhigh_H, n / 4);
	releaseMemory(trans_llow_H, n);
	releaseMemory(trans_lhigh_H, n);
	free(Are);
	

	return 0;
}

BYTE* loadBitmapFile(int bytesPerPixel, BITMAPHEADER* bitmapHeader, int* imgWidth, int* imgHeight, char* filename)
{
	FILE* fp = fopen(filename, "rb");	//파일을 이진읽기모드로 열기
	if (fp == NULL)
	{
		printf("파일로딩에 실패했습니다.\n");	//fopen에 실패하면 NULL값을 리턴
		return NULL;
	}
	else
	{
		fread(&bitmapHeader->bf, sizeof(BITMAPFILEHEADER), 1, fp);	//비트맵파일헤더 읽기
		fread(&bitmapHeader->bi, sizeof(BITMAPINFOHEADER), 1, fp);	//비트맵인포헤더 읽기
		//fread(&bitmapHeader->hRGB, sizeof(RGBQUAD), 256, fp);	//색상팔렛트 읽기 (24bitmap 에서는 존재하지 않음)

		*imgWidth = bitmapHeader->bi.biWidth;
		*imgHeight = bitmapHeader->bi.biHeight;
		int imgSizeTemp = (*imgWidth) * (*imgHeight);	// 이미지 사이즈를 상위 변수에 할당

		printf("Size of image: Width %d   Height %d\n", bitmapHeader->bi.biWidth, bitmapHeader->bi.biHeight);
		BYTE* image = (BYTE*)malloc(bytesPerPixel * sizeof(BYTE) * imgSizeTemp);	//이미지크기만큼 메모리할당

		fread(image, bytesPerPixel * sizeof(BYTE), imgSizeTemp, fp);//이미지 크기만큼 파일에서 읽어오기

		fclose(fp);
		return image;
	}
}



void writeBitmapFile(int bytesPerPixel, BITMAPHEADER outputHeader, BYTE* output, int imgSize, char* filename)
{
	FILE* fp = fopen(filename, "wb");

	fwrite(&outputHeader.bf, sizeof(BITMAPFILEHEADER), 1, fp);
	fwrite(&outputHeader.bi, sizeof(BITMAPINFOHEADER), 1, fp);
	//fwrite(&outputHeader.hRGB, sizeof(RGBQUAD), 256, fp); //not needed for 24bitmap
	fwrite(output, bytesPerPixel * sizeof(BYTE), imgSize, fp);
	fclose(fp);
}

double** constructHaarMatrixRecursive(int n) {
	double** h;
	if (n > 2)
		h = constructHaarMatrixRecursive(n / 2);
	else {
		//double** h;
		h = allocateMemory(2, 2);
		h[0][0] = 1; h[0][1] = 1; h[1][0] = 1; h[1][1] = -1; //H = [1 1; 1 -1]
		return h;
	}

	// construct the left half (Kronecket product of h and [1;1])
	double** Hl = applyKroneckerProduct(h, n, 1, 1);
	releaseMemory(h, n / 2);

	// construct the right half (Kronecker product of I and [1;-1])
	double** I = constructIdentity(n / 2);
	double** Hr = applyKroneckerProduct(I, n, 1, -1);
	releaseMemory(I, n / 2);


	// merge hl and hr
	double** H = concatenateTwoMatrices(Hl, Hr, n); //H = [Hl Hr]
	releaseMemory(Hl, n);
	releaseMemory(Hr, n);

	return H;
}

double** applyKroneckerProduct(double** A, int n, double a, double b) {
	double** h = allocateMemory(n, n / 2);

	for (int j = 0; j < n / 2; j++) {
		for (int i = 0; i < n / 2; i++) {
			h[2 * i][j] = A[i][j] * a;
			h[2 * i + 1][j] = A[i][j] * b;
		}
	}
	return h;
}

double** concatenateTwoMatrices(double** hl, double** hr, int n) {
	double** H = allocateMemory(n, n);

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (j < n / 2)
				H[i][j] = hl[i][j];
			else
				H[i][j] = hr[i][j - n / 2];
		}
	}
	return H;
}

void printMatrix(double** A, int m, int n, char name[]) {
	printf("\n%s = \n", name);
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++)
			printf("%lf ", A[i][j]);
		printf("\n");
	}
}

double** constructIdentity(int k) {
	double** I = allocateMemory(k, k);

	for (int i = 0; i < k; i++) {
		for (int j = 0; j < k; j++) {
			if (i != j)
				I[i][j] = 0.0;
			else
				I[i][j] = 1.0;
		}
	}
	return I;
}

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

double** normalizeMatrix(double** H, int n) {
	double len = 0.0;
	double** norm_H = allocateMemory(n, n);
	for (int i=0; i < n; i++) {
		len = 0.0;
		for (int j = 0; j < n; j++) {
			len += H[j][i] * H[j][i];
		}
		for (int j = 0; j < n; j++) {
			norm_H[j][i] = H[j][i] / sqrt(len);
		}
	}
		
	return norm_H;
}

double** multiplyTwoMatrices(double** A, int m, int n, double** B, int l, int k) {
	double** C;
	double sum = 0.0;
	if (n != l) return NULL; //곱셈이 불가능 할때
	C = allocateMemory(m, k);
	for (int i = 0; i < m; i++)
		for (int j = 0; j < k; j++) {
			for (int o = 0; o < n; o++)
				sum += A[i][o] * B[o][j];
			C[i][j] = sum;
			sum = 0.0;
		}
	return C;
}

double** transposeMatrix(double** A, int m, int n) {
	double** B = allocateMemory(n, m);

	for (int i = 0; i < m; i++)
		for (int j = 0; j < n; j++)
			B[j][i] = A[i][j];

	return B;
}

void reconstruct_image(BYTE* Are, double** Ahat, int bytesPerPixel, int n) {
	int index = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			for (int p = 0; p < bytesPerPixel; p++) Are[index++] = Ahat[i][j];
		}
	}
}
