#include "common.h"

void diff(char *w)
{

	system("cls");
	gotoxy(55, 4);
	printf("<���̵�>");
	Sleep(500);
	gotoxy(55, 8);
	printf("<1.�ʱ�>");
	gotoxy(55, 12);
	printf("<2.�߱�>");
	gotoxy(55, 16);
	printf("<3.���>");
	gotoxy(68, 8);
	printf("���������� ��n__r ��\n");
	gotoxy(68, 9);
	printf("���������� /). ��.)/)\n");
	gotoxy(68, 10);
	printf("������ ��������������\n");
	gotoxy(68, 11);
	printf("���������������������� ��\n");
	gotoxy(68, 12);
	printf("��/���������ܡ����������� ��\n");
	gotoxy(68, 13);
	printf("��!������������ ���� �塡l  \n");
	gotoxy(68, 14);
	printf("�� �� ������ ���� �� ����\n");
	gotoxy(68, 15);
	printf("���������������ʡ���������\n");
	Sleep(500);
	gotoxy(42, 20);
	printf("<������ ���̵��� ��ȣ�� �Է��ϼ���>");
	gotoxy(58, 22);
	*w = getchar();
	while ((getchar() != '\n'));

}