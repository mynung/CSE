//202311264 ���ȫ
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
int main(void)
{
	int f_year, s_year, i, day;
	//f_year: ���� ����, s_year: ���� ����, i:�ӽ��Լ�(���� ���� ����), day:�� ��¥��
	printf("���� ������ �Է��Ͻÿ�: ");
	scanf("%d", &f_year);
	if (f_year <= 0)
		printf("���� ������ ���� �������� �մϴ�.");
	else
	{
		printf("���� ������ �Է��Ͻÿ�: ");
		scanf("%d", &s_year);
		if (s_year <= f_year)
			printf("���� ������ ���� �������� Ŀ���մϴ�.");
		else //����� ���α׷� ����
		{
			day = 365 * (s_year - f_year); //���� ����� ������ �� ����� ��¥��
			i = f_year; 
			while (i <= s_year) //���� �˻�
			{
				if ((i % 4 == 0 && i % 100 != 0) || i % 400 == 0)
				{
					printf("����: %d\n", i);
					day += 1; //�����϶� ���� ��¥ 1����
				}
				i++;
			}
			printf("%d/1/1 ~ %d/1/1 ������ �� ��¥��: %d ��", f_year, s_year, day); //������ ���
		}
	}
	return 0;
}