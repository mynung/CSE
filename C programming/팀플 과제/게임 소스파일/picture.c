#include "common.h"

void picture()
{
	gotoxy(52, 2);
	switch (lev)
	{
	case 1:  printf("<LEVEL 1: �ʱ�>"); break;
	case 2:  printf("<LEVEL 2: �߱�>"); break;
	case 3:  printf("<LEVEL 3: ���>"); break;
	}
	gotoxy(1, 24);
	printf("����������������������������������������������������������������������������������������������������������������������");
	gotoxy(100, 18); printf("  __         /��\\\n");
	gotoxy(100, 19); printf("  \\ \\        \\__/\n");
	gotoxy(100, 20); printf("   \\ \\/��\\  __/\\\n");
	gotoxy(100, 21); printf("   _\\/____\\_  __\\\n");
	gotoxy(100, 22); printf("   |       | /  \\\n");
	gotoxy(100, 23); printf("   |       |/    \\__\n");
	gotoxy(1, 18); printf("        /)������\n");
	gotoxy(1, 19); printf("��������������  \\\\\n");
	gotoxy(1, 20); printf(" ���������ܡ����� \\\n");
	gotoxy(1, 21); printf("���������������� |\n");
	gotoxy(1, 22); printf("������������ �ǫ�\n");
	gotoxy(1, 23); printf(" U��U������U��U\n");
}
